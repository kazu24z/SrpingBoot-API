package payroll.entity;

import lombok.Data;
import payroll.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 注文エンティティ
 */
@Entity
@Data
@Table(name = "CUSTOMER_ORDER")
public class Order {
    /** id */
    private @Id @GeneratedValue Long id;
    /** description */
    private String description;
    /** status */
    private Status status;

    /**
     * コンストラクタ
     */
    public Order() {}

    /**
     * コンストラクタ
     * @param description
     * @param status
     */
    public Order(String description, Status status) {

        this.description = description;
        this.status = status;
    }
}
