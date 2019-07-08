package io.zensoft.food.mapper;

import io.zensoft.food.dto.SimpleCompanyOrderDto;
import io.zensoft.food.dto.CompanyOrderWithUserOrdersDto;
import io.zensoft.food.model.CompanyOrder;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyOrderMapper {

    private OrderMapper orderMapper;

    @Autowired
    public CompanyOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public SimpleCompanyOrderDto toCompanyOrderOpenDto(@NonNull CompanyOrder companyOrder){
        return SimpleCompanyOrderDto
                .builder()
                .id(companyOrder.getId())
                .status(companyOrder.getOrderStatus())
                .openingTime(companyOrder.getOpeningTime())
                .managerId(companyOrder.getManager().getId())
                .build();
    }

    public SimpleCompanyOrderDto toCompanyOrderCloseDto(@NonNull CompanyOrder companyOrder){
        return SimpleCompanyOrderDto
                .builder()
                .id(companyOrder.getId())
                .status(companyOrder.getOrderStatus())
                .openingTime(companyOrder.getOpeningTime())
                .closingTime(companyOrder.getClosingTime())
                .managerId(companyOrder.getManager().getId())
                .total(companyOrder.getTotal())
                .build();
    }

    public CompanyOrderWithUserOrdersDto toCompanyOrderGetOrdersDto(@NonNull CompanyOrder companyOrder){
        return CompanyOrderWithUserOrdersDto
                .builder()
                .id(companyOrder.getId())
                .status(companyOrder.getOrderStatus())
                .orders(companyOrder.getOrders()
                        .stream()
                        .map(orderMapper::toOrderDto)
                        .collect(Collectors.toList()))
                .total(companyOrder.getTotal())
                .build();
    }
}
