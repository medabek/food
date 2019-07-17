package io.zensoft.food.mapper;

import io.zensoft.food.dto.CompanyOrderWithUserOrdersDto;
import io.zensoft.food.dto.SimpleCompanyOrderDto;
import io.zensoft.food.model.CompanyOrder;
import io.zensoft.food.service.impl.CompanyOrderServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyOrderMapper {

    private OrderMapper orderMapper;
    private CompanyOrderServiceImpl companyOrderService;

    @Autowired
    public CompanyOrderMapper(OrderMapper orderMapper,
                              CompanyOrderServiceImpl companyOrderService) {
        this.orderMapper = orderMapper;
        this.companyOrderService = companyOrderService;
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
                .cafesTotal(companyOrderService.getCafesTotal(companyOrder))
                .build();
    }

    public CompanyOrderWithUserOrdersDto toCompanyOrderGetOrdersDto(@NonNull CompanyOrder companyOrder){
        return CompanyOrderWithUserOrdersDto
                .builder()
                .id(companyOrder.getId())
                .status(companyOrder.getOrderStatus())
                .orders(companyOrder.getCompanyTotal()
                        .stream()
                        .map(orderMapper::toOrderDto)
                        .collect(Collectors.toList()))
                .cafesTotal(companyOrderService.getCafesTotal(companyOrder))
                .build();
    }
}
