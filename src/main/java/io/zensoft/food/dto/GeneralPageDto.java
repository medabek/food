package io.zensoft.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralPageDto {

    private Long total;

    private int totalPages;

    private List<Object> list;
}
