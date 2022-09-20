package payroll;

import lombok.Data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 従業員のドメインクラス
 */
@Entity
@Data
public class Employee {

    /** ID **/
    private @Id @GeneratedValue Long id;
    /** name **/
    private String name;
    /** role **/
    private String role;

    /**
     * コンストラクタ
     */
    Employee() {}

    /**
     * コンストラクタ
     * @param name
     * @param role
     */
    Employee(String name, String role) {

        this.name = name;
        this.role = role;
    }
}
