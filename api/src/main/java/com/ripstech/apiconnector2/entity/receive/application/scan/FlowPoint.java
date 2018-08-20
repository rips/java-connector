package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.application.Scan;

public abstract class FlowPoint {

    protected long id;
    protected Integer line;
    protected File file;
    protected Scan scan;
    protected Function function;
    @JsonProperty("class")
    protected Class clazz;

    public long getId() {
        return id;
    }

    public Integer getLine() {
        return line;
    }

    public File getFile() {
        return file;
    }

    public Scan getScan() {
        return scan;
    }

    public Function getFunction() {
        return function;
    }

    public Class getClazz() {
        return clazz;
    }
}
