package payroll.form;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 従業員のフォーム条件クラス
 */
@Data
@Entity
public class EmployeeForm {
    /** firstName */
    @NotNull
    @Size(max=6)
    private  String firstName;

    /** lastName */
    @NotNull
    @Size(max=6)
    private String lastName;

    /** role */
    @Nullable
    @Size(max=20)
    private String role;



}
