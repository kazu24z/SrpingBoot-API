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

    private @Id @GeneratedValue Long id;
    private String name;
    private String role;

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

    /**
     * オブジェクト生成の状態確認
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.role, employee.role);
    }

    /**
     * オブジェクトのハッシュ化
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }

    /**
     * ペイロード生成
     * @return
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }
}
