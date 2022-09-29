package payroll.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 注文のフォーム条件クラス
 */
@Data
public class OrderForm {
    /** description */
    @NotBlank
    @Size(min=10, max=50)
    public String description;

}
