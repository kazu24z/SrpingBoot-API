package payroll.entity;

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
public
class Employee {

    /** ID **/
    private @Id @GeneratedValue Long id;
    /** firstName **/
    private String firstName;
    /** lastName **/
    private String lastName;

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
    public Employee(String firstName, String lastName, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    /**
     * firstNameとlastNameを統合した旧版のnameを取得
     * @return String
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * firstNameとlastNameを統合し、旧版のnameプロパティにも対応
     * @param name
     */
    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

}
