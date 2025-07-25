package com.itsme.yocto_spring.repository;

import com.itsme.yocto_spring.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, String> {
    Optional<History> findByUserIdAndDate(String userId, LocalDate date);

    List<History> findByUserIdAndDateBetween(String userId, LocalDate startDate, LocalDate endDate);
}
