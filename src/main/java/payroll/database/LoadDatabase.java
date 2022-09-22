package payroll.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payroll.entity.Employee;
import payroll.entity.Order;
import payroll.enums.Status;
import payroll.repository.EmployeeRepository;
import payroll.repository.OrderRepository;

/**
 * テストデータ用クラス
 */
@Configuration
class LoadDatabase {
    /**
     * LoadDatabaseクラスのログを格納
     */
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * データベース初期化
     * @param employeeRepository
     * @param orderRepository
     * @return
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {

        return args -> {
            employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
            employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));


            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });

        };
    }
}
