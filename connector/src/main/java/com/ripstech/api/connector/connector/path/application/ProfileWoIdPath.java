package com.ripstech.api.connector.connector.path.application;

import com.ripstech.api.connector.connector.Path;
import com.ripstech.api.connector.service.application.profile.AllService;

public class ProfileWoIdPath extends Path
{
  public ProfileWoIdPath(String baseUri) {
    super(baseUri);
  }

  @SuppressWarnings("unused")
  public AllService all() {
    AllService service = new AllService(baseUri);
    setPrefs(service);
    return service;
  }
}
