package payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.entity.Employee;

/**
 * 従業員リポジトリ
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
