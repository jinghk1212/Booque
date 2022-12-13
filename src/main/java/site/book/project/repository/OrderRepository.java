package site.book.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.book.project.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderByOrderIdDesc();

}
