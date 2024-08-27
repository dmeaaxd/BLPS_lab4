package ru.danmax.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.danmax.entity.Discount;


public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
