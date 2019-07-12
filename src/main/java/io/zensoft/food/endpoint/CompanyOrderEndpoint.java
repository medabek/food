package io.zensoft.food.endpoint;

import io.zensoft.food.dto.CompanyOrderWithUserOrdersDto;
import io.zensoft.food.dto.GeneralPageDto;
import io.zensoft.food.dto.SimpleCompanyOrderDto;
import io.zensoft.food.security.UserPrincipal;

public interface CompanyOrderEndpoint {

    SimpleCompanyOrderDto openOrder(UserPrincipal currentUser);

    SimpleCompanyOrderDto closeOrder(UserPrincipal currentUser);

    CompanyOrderWithUserOrdersDto currentCompanyOrder();

    GeneralPageDto getAllOrders(int page, int limit);
}
