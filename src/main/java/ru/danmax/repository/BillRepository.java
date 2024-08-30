package ru.danmax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danmax.entity.Bill;


public interface BillRepository extends JpaRepository<Bill, Long> {
}
