package payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.entity.Order;

/**
 * 注文リポジトリ
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
