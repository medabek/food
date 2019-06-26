package io.zensoft.food.controller;

import io.zensoft.food.domain.DishUpdateRequest;
import io.zensoft.food.dto.DishDto;
import io.zensoft.food.dto.request.DishCreateRequestDto;
import io.zensoft.food.dto.request.DishUpdateRequestDto;
import io.zensoft.food.endpoint.DishEndpoint;
import io.zensoft.food.model.Dish;
import io.zensoft.food.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    private DishService dishService;
    private final DishEndpoint dishEndpoint;

    @Autowired
    public DishController(DishService dishService, DishEndpoint dishEndpoint) {
        this.dishService = dishService;
        this.dishEndpoint = dishEndpoint;
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllByCafeId(@RequestParam("cafeId") @NotNull Long cafeId){
        return ResponseEntity.ok(dishEndpoint.getAllDishesByCafeId(cafeId));
    }

    @PostMapping
    public ResponseEntity<DishDto> add(@Valid @RequestBody DishCreateRequestDto request) {
        return ResponseEntity.ok(dishEndpoint.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDto> update(@PathVariable Long id, @RequestBody DishUpdateRequestDto request) {
        return ResponseEntity.ok(dishEndpoint.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dishEndpoint.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(dishEndpoint.getById(id));
    }
}
