package payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payroll.assembler.EmployeeModelAssembler;
import payroll.entity.Employee;
import payroll.exception.EmployeeNotFoundException;
import payroll.form.EmployeeForm;
import payroll.repository.EmployeeRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * 従業員モデル用コントローラ
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    /** DI:EmployeeRepository **/
    private final EmployeeRepository repository;
    /** DI:EmployeeAssembler **/
    private final EmployeeModelAssembler assembler;

    /**
     * コンストラクタ
     * @param repository
     * @param assembler
     */
    @Autowired
    public EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }


    /**
     * 従業員一覧取得
     * @return CollectionModel
     */
    @GetMapping
    public CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    /**
     * 従業員詳細取得
     * @param id
     * @return 従業員モデル
     */
    @GetMapping("/{id:\\d+}")
    public EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    /**
     * 従業員登録
     * @param employeeForm
     * @return 従業員エンティティ
     */
    @PostMapping
    public ResponseEntity<?> newEmployee(@Validated @RequestBody EmployeeForm employeeForm) {

        Employee newEmployee = new Employee(
            employeeForm.getFirstName(),
            employeeForm.getLastName(),
            employeeForm.getRole()
        );

        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity
            .created(
                entityModel
                    .getRequiredLink(IanaLinkRelations.SELF)
                    .toUri()
            )
            .body(entityModel);

    }

    /**
     * 従業員更新
     * @param newEmployee
     * @param id
     * @return 従業員エンティティ
     */
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        Employee updatedEmployee = repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    /**
     * 従業員削除
     * @param id
     * @return 従業員エンティティ
     */
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
