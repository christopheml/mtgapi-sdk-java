package com.github.christopheml.mtgapi.responses;

public abstract class SingleResponse<ENTITY> extends ApiResponse<ENTITY> {

    private ENTITY entity;

    public ENTITY getEntity() {
        return entity;
    }

    public void setEntity(ENTITY entity) {
        this.entity = entity;
    }
}
