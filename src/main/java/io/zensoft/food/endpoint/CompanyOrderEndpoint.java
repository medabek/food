package io.zensoft.food.endpoint;

import io.zensoft.food.dto.SimpleCompanyOrderDto;
import io.zensoft.food.dto.CompanyOrderWithUserOrdersDto;
import io.zensoft.food.security.UserPrincipal;

import java.util.List;

public interface CompanyOrderEndpoint {

    SimpleCompanyOrderDto openOrder(UserPrincipal currentUser);

    SimpleCompanyOrderDto closeOrder(UserPrincipal currentUser);

    CompanyOrderWithUserOrdersDto currentCompanyOrder();

    List<CompanyOrderWithUserOrdersDto> getAllOrders(int page, int limit);
}
