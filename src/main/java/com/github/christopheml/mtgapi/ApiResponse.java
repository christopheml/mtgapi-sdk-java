package com.github.christopheml.mtgapi;

public class ApiResponse<T> {

    private final T entity;

    public ApiResponse(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

}
