package com.github.christopheml.mtgapi.responses;

import java.util.List;

public abstract class ListResponse<ENTITY> extends ApiResponse<ENTITY> {

    private List<ENTITY> entities;

    public List<ENTITY> getEntities() {
        return entities;
    }

    public void setEntities(List<ENTITY> entities) {
        this.entities = entities;
    }

}
