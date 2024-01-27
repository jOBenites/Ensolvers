package com.ensolvers.notes.controllers.dto;

public class ApiPaginateResponse<T> extends ApiResponse  {

    public Long totalElements;
    public Integer totalPages;

    public ApiPaginateResponse(String status, Integer statusCode, String message, Object data,
                               Long totalElements, Integer totalPages) {
        super(status, statusCode, message, data);
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
