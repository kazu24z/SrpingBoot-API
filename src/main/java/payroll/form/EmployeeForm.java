package payroll.form;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 従業員のフォーム条件クラス
 */
@Data
public class EmployeeForm {
    /** firstName */
    @NotBlank
    @Size(max=6)
    private  String firstName;

    /** lastName */
    @NotBlank
    @Size(max=6)
    private String lastName;

    /** role */
    @Nullable
    @Size(max=20)
    private String role;



}
