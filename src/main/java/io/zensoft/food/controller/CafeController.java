package io.zensoft.food.controller;

import io.zensoft.food.dto.request.CafeCreateRequestDto;
import io.zensoft.food.dto.CafeDto;
import io.zensoft.food.dto.request.CafeUpdateRequestDto;
import io.zensoft.food.endpoint.CafeEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cafes")
public class CafeController {

    private final CafeEndpoint cafeEndpoint;

    @Autowired
    public CafeController(CafeEndpoint cafeEndpoint) {
        this.cafeEndpoint = cafeEndpoint;
    }

    @GetMapping
    public ResponseEntity<List<CafeDto>> findAll() {
        return ResponseEntity.ok(cafeEndpoint.getAll());
    }

    @PostMapping
    public ResponseEntity<CafeDto> add(@Valid @RequestBody CafeCreateRequestDto request) {
        return ResponseEntity.ok(cafeEndpoint.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CafeDto> update(@PathVariable Long id, @Valid @RequestBody CafeUpdateRequestDto request) {
        return ResponseEntity.ok(cafeEndpoint.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cafeEndpoint.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CafeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cafeEndpoint.getById(id));
    }

}
