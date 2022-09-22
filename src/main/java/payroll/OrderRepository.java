package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 注文リポジトリ
 */
interface OrderRepository extends JpaRepository<Order, Long> {
}
