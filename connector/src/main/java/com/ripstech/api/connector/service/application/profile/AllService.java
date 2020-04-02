package com.ripstech.api.connector.service.application.profile;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.SimpleGetListService;
import com.ripstech.api.entity.receive.application.Profile;

public class AllService extends SimpleGetListService<Profile> {

  public AllService(String baseUri) {
    super(baseUri);
  }

  @Override
  protected String getPath() {
    return "/applications/profiles/all";
  }

  @Override
  public TypeReference<List<Profile>> getGenericListType() {
    return new TypeReference<List<Profile>>() {};
  }
}
