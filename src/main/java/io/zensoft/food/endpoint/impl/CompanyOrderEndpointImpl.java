package io.zensoft.food.endpoint.impl;

import io.zensoft.food.dto.SimpleCompanyOrderDto;
import io.zensoft.food.dto.CompanyOrderWithUserOrdersDto;
import io.zensoft.food.endpoint.CompanyOrderEndpoint;
import io.zensoft.food.mapper.CompanyOrderMapper;
import io.zensoft.food.model.CompanyOrder;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.CompanyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyOrderEndpointImpl implements CompanyOrderEndpoint {

    private CompanyOrderService companyOrderService;
    private CompanyOrderMapper companyOrderMapper;

    @Autowired
    public CompanyOrderEndpointImpl(CompanyOrderService companyOrderService,
                                    CompanyOrderMapper companyOrderMapper) {
        this.companyOrderService = companyOrderService;
        this.companyOrderMapper = companyOrderMapper;
    }

    @Transactional
    @Override
    public SimpleCompanyOrderDto openOrder(UserPrincipal currentUser) {

        CompanyOrder companyOrder = companyOrderService.openOrder(currentUser);

        return companyOrderMapper.toCompanyOrderOpenDto(companyOrder);
    }

    @Transactional
    @Override
    public SimpleCompanyOrderDto closeOrder(UserPrincipal currentUser) {

        CompanyOrder companyOrder = companyOrderService.closeOrder(currentUser);

        return companyOrderMapper.toCompanyOrderCloseDto(companyOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public CompanyOrderWithUserOrdersDto currentCompanyOrder() {

        CompanyOrder companyOrder = companyOrderService.currentCompanyOrder();

        return companyOrderMapper.toCompanyOrderGetOrdersDto(companyOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompanyOrderWithUserOrdersDto> getAllOrders() {

        List<CompanyOrder> companyOrders = companyOrderService.getAllOrders();

        return companyOrders.stream()
                .map(companyOrderMapper::toCompanyOrderGetOrdersDto)
                .collect(Collectors.toList());
    }
}
