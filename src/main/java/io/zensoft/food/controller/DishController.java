package io.zensoft.food.controller;

import io.zensoft.food.dto.DishDto;
import io.zensoft.food.dto.request.DishCreateRequestDto;
import io.zensoft.food.dto.request.DishUpdateRequestDto;
import io.zensoft.food.endpoint.DishEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    private final DishEndpoint dishEndpoint;

    @Autowired
    public DishController(DishEndpoint dishEndpoint) {
        this.dishEndpoint = dishEndpoint;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<DishDto>> getAllByCafeId(@RequestParam("cafeId") @NotNull Long cafeId){
        return ResponseEntity.ok(dishEndpoint.getAllDishesByCafeId(cafeId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<DishDto> add(@Valid @RequestPart(value = "dish") DishCreateRequestDto request,
                                       @RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok(dishEndpoint.add(request, file));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DishDto> update(@PathVariable Long id, @RequestBody DishUpdateRequestDto request,
                                          @RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok(dishEndpoint.update(id, request, file));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dishEndpoint.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(dishEndpoint.getById(id));
    }
}
