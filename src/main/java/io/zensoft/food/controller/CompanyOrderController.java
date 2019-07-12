package io.zensoft.food.controller;

import io.zensoft.food.dto.CompanyOrderWithUserOrdersDto;
import io.zensoft.food.dto.GeneralPageDto;
import io.zensoft.food.dto.SimpleCompanyOrderDto;
import io.zensoft.food.endpoint.CompanyOrderEndpoint;
import io.zensoft.food.security.CurrentUser;
import io.zensoft.food.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company-orders")
public class CompanyOrderController {

    private CompanyOrderEndpoint companyOrderEndpoint;

    @Autowired
    public CompanyOrderController(CompanyOrderEndpoint companyOrderEndpoint) {
        this.companyOrderEndpoint = companyOrderEndpoint;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/open")
    public ResponseEntity<SimpleCompanyOrderDto> openOrder(@CurrentUser UserPrincipal currentUser){
        return ResponseEntity.ok(companyOrderEndpoint.openOrder(currentUser));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/close")
    public ResponseEntity<SimpleCompanyOrderDto> closeOrder(@CurrentUser UserPrincipal currentUser){
        return ResponseEntity.ok(companyOrderEndpoint.closeOrder(currentUser));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get-current")
    public ResponseEntity<CompanyOrderWithUserOrdersDto> getCurrentOrder(){
        return ResponseEntity.ok(companyOrderEndpoint.currentCompanyOrder());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<GeneralPageDto> getAllOrders(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "15") int limit){
        return ResponseEntity.ok(companyOrderEndpoint.getAllOrders(page, limit));
    }
}
