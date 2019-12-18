package com.ripstech.api.connector.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.application.scan.Library;
import com.ripstech.api.entity.send.application.scan.LibrarySend;

import java.util.List;

public class LibraryService extends PatchDeletePostGetService<Library, LibrarySend> {

    private final long applicationId;
    private final long scanId;

    public LibraryService(String baseUri, long applicationId, long scanId) {
        super(baseUri);
        this.applicationId = applicationId;
        this.scanId = scanId;
    }

    @Override
    public TypeReference<Library> getGenericType() {
        return new TypeReference<Library>() {};
    }

    @Override
    public TypeReference<List<Library>> getGenericListType() {
        return new TypeReference<List<Library>>() {};
    }

    @Override
    protected String getPath() {
        return String.format("applications/%d/scans/%d/libraries", applicationId, scanId);
    }

}
