package payroll.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import payroll.controller.EmployeeController;
import payroll.entity.Employee;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * EmployeeエンティティをEmployeeエンティティモデルに変換するクラス
 */
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
    /**
     * Employeeエンティティをモデルに変換するメソッド
     * @param employee
     * @return
     */
    @Override
    public EntityModel<Employee> toModel(Employee employee) {

        return EntityModel.of(
            employee,
            linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}
