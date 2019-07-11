package io.zensoft.food.controller;

import io.zensoft.food.dto.OrderDto;
import io.zensoft.food.dto.request.AddItemRequestDto;
import io.zensoft.food.endpoint.OrderEndpoint;
import io.zensoft.food.security.CurrentUser;
import io.zensoft.food.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderEndpoint orderEndpoint;

    @Autowired
    public OrderController(OrderEndpoint orderEndpoint) {
        this.orderEndpoint = orderEndpoint;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/add-item")
    public ResponseEntity<OrderDto> addItem(@Valid @RequestBody AddItemRequestDto request, @CurrentUser UserPrincipal currentUser){
        return ResponseEntity.ok(orderEndpoint.addItem(request, currentUser));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/get-history")
    public ResponseEntity<List<OrderDto>> getAllByUser(@CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "limit", defaultValue = "2") int limit){
        return ResponseEntity.ok(orderEndpoint.getOrdersByCurrentUser(currentUser, page, limit));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/get-current-order")
    public ResponseEntity<OrderDto> getCurrent(@CurrentUser UserPrincipal currentUser){
        return ResponseEntity.ok(orderEndpoint.getCurrentOrder(currentUser));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/confirm")
    public ResponseEntity<OrderDto> confirmOrder(@CurrentUser UserPrincipal currentUser){
        return ResponseEntity.ok(orderEndpoint.confirm(currentUser));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/delete-item")
    public ResponseEntity<Void> deleteItem(@Valid @RequestBody AddItemRequestDto request, @CurrentUser UserPrincipal currentUser){
        orderEndpoint.delete(request, currentUser);
        return ResponseEntity.noContent().build();
    }

}
