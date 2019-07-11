package io.zensoft.food.endpoint.impl;

import io.zensoft.food.dto.CompanyOrderWithUserOrdersDto;
import io.zensoft.food.dto.CompanyOrderPageDto;
import io.zensoft.food.dto.SimpleCompanyOrderDto;
import io.zensoft.food.endpoint.CompanyOrderEndpoint;
import io.zensoft.food.mapper.CompanyOrderMapper;
import io.zensoft.food.model.CompanyOrder;
import io.zensoft.food.repository.CompanyOrderRepository;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.CompanyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class CompanyOrderEndpointImpl implements CompanyOrderEndpoint {

    private CompanyOrderService companyOrderService;
    private CompanyOrderMapper companyOrderMapper;
    private CompanyOrderRepository companyOrderRepository;

    @Autowired
    public CompanyOrderEndpointImpl(CompanyOrderService companyOrderService,
                                    CompanyOrderRepository companyOrderRepository,
                                    CompanyOrderMapper companyOrderMapper) {
        this.companyOrderService = companyOrderService;
        this.companyOrderMapper = companyOrderMapper;
        this.companyOrderRepository = companyOrderRepository;
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
    public CompanyOrderPageDto getAllOrders(int page, int limit) {

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CompanyOrder> allCompanyOrders = companyOrderRepository.findAll(pageableRequest);

        return new CompanyOrderPageDto(allCompanyOrders.getTotalElements(),
                allCompanyOrders.getTotalPages(),
                allCompanyOrders.getContent().stream()
                        .map(companyOrderMapper::toCompanyOrderGetOrdersDto)
                        .collect(Collectors.toList()));
    }

}
