package io.zensoft.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderPageDto {

    private Long total;

    private int totalPages;

    private List<OrderDto> list;
}
