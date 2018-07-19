package com.ripstech.apiconnector2.service.queryparameter;

import com.ripstech.apiconnector2.entity.send.filter.condition.And;
import com.ripstech.apiconnector2.entity.send.filter.condition.Or;
import com.ripstech.apiconnector2.entity.send.filter.expression.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.ripstech.apiconnector2.service.queryparameter.JsonFilter.*;

class JsonFilterTest {

	private static Stream<Arguments> createValidTests() {
		return Stream.of(
				Arguments.of(
						new JsonFilter(new Equal("id", 1)),
						"{\"__equal\":{\"id\":1}}"
				            ),
				Arguments.of(
						new JsonFilter(new Null("id")),
						"{\"__null\":{\"id\":\"\"}}")
				,
				Arguments.of(
						new JsonFilter(new NotNull("id")),
						"{\"__notNull\":{\"id\":\"\"}}"
				            ),
				Arguments.of(
						new JsonFilter(new GreaterThan("id", 20)),
						"{\"__greaterThan\":{\"id\":20}}"
				            ),
				Arguments.of(
						new JsonFilter(new LessThan("id", 20)),
						"{\"__lessThan\":{\"id\":20}}"
				            ),
				Arguments.of(
						new JsonFilter(new LessThanEqual("id", 20)),
						"{\"__lessThanEqual\":{\"id\":20}}"
				            ),
				Arguments.of(
						new JsonFilter(new Equal("id", "test'")),
						"{\"__equal\":{\"id\":\"test'\"}}"
				            ),
				Arguments.of(
						new JsonFilter(new Equal("id", "test\"")),
						"{\"__equal\":{\"id\":\"test\\\"\"}}"
				            ),
				Arguments.of(
						new JsonFilter(new And(new Equal("id", 1), new Equal("name", "test"))),
						"{\"__and\":[{\"__equal\":{\"id\":1}},{\"__equal\":{\"name\":\"test\"}}]}"
				            ),
				Arguments.of(
						new JsonFilter(new Or(new Equal("name", "test"), new Equal("name", "test2"))),
						"{\"__or\":[{\"__equal\":{\"name\":\"test\"}},{\"__equal\":{\"name\":\"test2\"}}]}"
				            ),
				Arguments.of(
						new JsonFilter(
								new And(
										new Or(
												new Equal("id", 1),
												new Equal("id", 2)
										),
										new And(
												new Equal("name", "test1"),
												new Equal("name", "test2"),
												new Like("name", "test%")
										)
								)
						),
						"{\"__and\":[{\"__or\":[{\"__equal\":{\"id\":1}},{\"__equal\":{\"id\":2}}]},{\"__and\":[{\"__equal\":{\"name\":\"test1\"}},{\"__equal\":{\"name\":\"test2\"}},{\"__like\":{\"name\":\"test%\"}}]}]}"
				            ),
				Arguments.of(
						new JsonFilter(
								new And(
										new Or(
												new Equal("id", 1),
												new NotEqual("id", 2)
										),
										new And(
												new Equal("name", "test1"),
												new Equal("name", "test2"),
												new Like("name", "test%")
										)
								)
						),
						"{\"__and\":[{\"__or\":[{\"__equal\":{\"id\":1}},{\"__notEqual\":{\"id\":2}}]},{\"__and\":[{\"__equal\":{\"name\":\"test1\"}},{\"__equal\":{\"name\":\"test2\"}},{\"__like\":{\"name\":\"test%\"}}]}]}"
				            ),
				Arguments.of(
						new JsonFilter(
								new And(
										new Or(
												new Equal("id", 1),
												new NotEqual("id", 2)
										),
										new And(
												new Equal("name", "test1"),
												new Equal("name", "test2"),
												new NotLike("name", "test%")
										)
								)
						),
						"{\"__and\":[{\"__or\":[{\"__equal\":{\"id\":1}},{\"__notEqual\":{\"id\":2}}]},{\"__and\":[{\"__equal\":{\"name\":\"test1\"}},{\"__equal\":{\"name\":\"test2\"}},{\"__notLike\":{\"name\":\"test%\"}}]}]}"
				            ),
				Arguments.of(
						new JsonFilter(
								new Or(
										new LessThanEqual("id", 1),
										new Equal("id", 13),
										new Equal("id", 42),
										new And(
												new Like("type", "%_TAG"),
												new NotLike("type", "CQ_%")
										),
										new NotEqual("default", false),
										new Or(
												new Equal("name", "name1"),
												new Equal("name", "name2"),
												new And(
														new Like("type", "XSS%"),
														new Like("name", "na%e")
												)
										)
								)
						),
						"{\"__or\":[{\"__lessThanEqual\":{\"id\":1}},{\"__equal\":{\"id\":13}},{\"__equal\":{\"id\":42}},{\"__and\":[{\"__like\":{\"type\":\"%_TAG\"}},{\"__notLike\":{\"type\":\"CQ_%\"}}]},{\"__notEqual\":{\"default\":false}},{\"__or\":[{\"__equal\":{\"name\":\"name1\"}},{\"__equal\":{\"name\":\"name2\"}},{\"__and\":[{\"__like\":{\"type\":\"XSS%\"}},{\"__like\":{\"name\":\"na%e\"}}]}]}]}"
				            ),
				Arguments.of(
						new JsonFilter(
								or(
										lessThanEqual("id", 1),
										equal("id", 13),
										equal("id", 42),
										and(
												like("type", "%_TAG"),
												notLike("type", "CQ_%")
										),
										notEqual("default", false),
										or(
												equal("name", "name1"),
												equal("name", "name2"),
												and(
														like("type", "XSS%"),
														like("name", "na%e")
												)
										)
								)
						),
						"{\"__or\":[{\"__lessThanEqual\":{\"id\":1}},{\"__equal\":{\"id\":13}},{\"__equal\":{\"id\":42}},{\"__and\":[{\"__like\":{\"type\":\"%_TAG\"}},{\"__notLike\":{\"type\":\"CQ_%\"}}]},{\"__notEqual\":{\"default\":false}},{\"__or\":[{\"__equal\":{\"name\":\"name1\"}},{\"__equal\":{\"name\":\"name2\"}},{\"__and\":[{\"__like\":{\"type\":\"XSS%\"}},{\"__like\":{\"name\":\"na%e\"}}]}]}]}"
				            )
		                );
	}

	@ParameterizedTest(name = "[{index}] {1}")
	@MethodSource("createValidTests")
	void create(JsonFilter jsonFilter, String expected) {
		Assertions.assertEquals(expected, jsonFilter.asJsonString());
	}

}