package com.app.travelplan.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> {
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;

    public ListResponse toListResponse(ListResponse listResponse
            , List<T> content
            , int pageNo
            , int pageSize
            , long totalElements
            , int totalPages
            , boolean last) {
        listResponse.setContent(content);
        listResponse.setPageNo(pageNo);
        listResponse.setPageSize(pageSize);
        listResponse.setTotalElements(totalElements);
        listResponse.setTotalPages(totalPages);
        listResponse.setLast(last);

        return listResponse;
    }
}
