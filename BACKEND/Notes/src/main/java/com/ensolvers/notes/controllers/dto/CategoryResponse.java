package com.ensolvers.notes.controllers.dto;

public class CategoryResponse {

    private Long categoryId;
    private String nombre;
    private Integer asigned;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAsigned() {
        return asigned;
    }

    public void setAsigned(Integer asigned) {
        this.asigned = asigned;
    }
}
