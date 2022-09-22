package payroll;

import lombok.Data;

import java.util.Objects;

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
    Order() {}

    /**
     * コンストラクタ
     * @param description
     * @param status
     */
    Order(String description, Status status) {

        this.description = description;
        this.status = status;
    }
}
