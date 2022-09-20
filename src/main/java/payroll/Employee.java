package payroll;

import lombok.Data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 従業員エンティティ
 */
@Entity
@Data
class Employee {

    /** ID **/
    private @Id @GeneratedValue Long id;
    /** firstName **/
    private String firstName;
    /** lastName **/
    private String lastName;

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
     * @param firstName
     * @param lastName
     * @param role
     */
    Employee(String firstName, String lastName, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
