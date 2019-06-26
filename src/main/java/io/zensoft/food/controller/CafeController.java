package io.zensoft.food.controller;

import io.zensoft.food.dto.request.CafeCreateRequestDto;
import io.zensoft.food.dto.CafeDto;
import io.zensoft.food.dto.request.CafeUpdateRequestDto;
import io.zensoft.food.endpoint.CafeEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<CafeDto>> findAll() {
        return ResponseEntity.ok(cafeEndpoint.getAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CafeDto> add(@Valid @RequestBody CafeCreateRequestDto request) {
        return ResponseEntity.ok(cafeEndpoint.save(request));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CafeDto> update(@PathVariable Long id, @Valid @RequestBody CafeUpdateRequestDto request) {
        return ResponseEntity.ok(cafeEndpoint.update(id, request));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cafeEndpoint.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CafeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cafeEndpoint.getById(id));
    }

}
