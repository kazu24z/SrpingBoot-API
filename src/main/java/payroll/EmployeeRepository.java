package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 従業員リポジトリ
 */
interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
