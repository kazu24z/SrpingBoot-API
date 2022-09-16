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

    private @Id @GeneratedValue Long id;

    private String description;
    private Status status;

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

    /**
     * エンティティの存在確認
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
                && this.status == order.status;
    }

    /**
     * ハッシュ化
     * @return ハッシュ値
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.status);
    }

    /**
     * ペイロード用文字列変換
     * @return 文字列（JSON）
     */
    @Override
    public String toString() {
        return "Order{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + '}';
    }
}
