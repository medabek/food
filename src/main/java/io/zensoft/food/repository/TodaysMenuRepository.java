package io.zensoft.food.repository;

import io.zensoft.food.enums.TodaysMenuStatus;
import io.zensoft.food.model.TodaysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodaysMenuRepository extends JpaRepository<TodaysMenu, Long> {

    Optional<TodaysMenu> findByStatus(TodaysMenuStatus status);

    Optional<TodaysMenu> findById(Long todaysMenuId);
}
