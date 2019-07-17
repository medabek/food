package io.zensoft.food.controller;

import io.zensoft.food.dto.TodaysMenuDto;
import io.zensoft.food.dto.request.TodaysMenuRequestDto;
import io.zensoft.food.endpoint.TodaysMenuEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/todays-menu")
public class TodaysMenuController {

    private TodaysMenuEndpoint todaysMenuEndpoint;

    @Autowired
    public TodaysMenuController(TodaysMenuEndpoint todaysMenuEndpoint) {
        this.todaysMenuEndpoint = todaysMenuEndpoint;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add-dish")
    public ResponseEntity<TodaysMenuDto> addDish(@Valid @RequestBody TodaysMenuRequestDto request){
        return ResponseEntity.ok(todaysMenuEndpoint.addDish(request));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/remove-dish")
    public ResponseEntity<Void> removeMenu(@Valid @RequestBody TodaysMenuRequestDto request){
        todaysMenuEndpoint.removeDish(request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/relevant")
    public ResponseEntity<TodaysMenuDto> relevant(){
        return ResponseEntity.ok(todaysMenuEndpoint.relevant());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/irrelevant")
    public ResponseEntity<TodaysMenuDto> irrelevant(){
        return ResponseEntity.ok(todaysMenuEndpoint.irrelevant());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/todays-menu")
    public ResponseEntity<TodaysMenuDto> todaysMenu(){
        return ResponseEntity.ok(todaysMenuEndpoint.getTodaysMenu());
    }
}
