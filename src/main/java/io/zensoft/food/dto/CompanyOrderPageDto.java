package io.zensoft.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompanyOrderPageDto {

    private Long total;

    private int totalPages;

    private List<CompanyOrderWithUserOrdersDto> list;
}
