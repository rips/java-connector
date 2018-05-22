package com.ripstech.apiconnector2.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayToMapDeserializer extends StdDeserializer<Map<Integer, Integer>> {

	public ArrayToMapDeserializer() {
		super(Map.class);
	}

	@Override
	public Map<Integer, Integer> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		switch (p.getCurrentToken()) {
			case START_ARRAY:
				JsonDeserializer<Object> deserializerList = ctxt.findRootValueDeserializer(ctxt.constructType(List.class));
				List<Integer> deserializeList = (List<Integer>) deserializerList.deserialize(p, ctxt);
				return IntStream.range(0, deserializeList.size())
						       .boxed()
						       .collect(Collectors.toMap(Function.identity(), deserializeList::get));
			case START_OBJECT:
				JsonDeserializer<Object> deserializerMap = ctxt.findRootValueDeserializer(ctxt.constructType(Map.class));
				Map<Integer, Integer> deserializeMap = (Map<Integer, Integer>) deserializerMap.deserialize(p, ctxt);
				return deserializeMap;
			default:
				return Collections.emptyMap();
		}
	}
}
