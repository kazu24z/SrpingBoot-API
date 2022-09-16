package payroll;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * 従業員モデル用コントローラ
 */
@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    /**
     * コンストラクタ
     * @param repository
     * @param assembler
     */
    @Autowired
    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }


    /**
     * 従業員一覧取得
     * @return CollectionModel
     */
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    /**
     * 従業員登録
     * @param newEmployee
     * @return
     */
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    /**
     * 従業員詳細取得
     * @param id
     * @return
     */
    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    /**
     * 従業員更新
     * @param newEmployee
     * @param id
     * @return
     */
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    /**
     * 従業員削除
     * @param id
     */
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
