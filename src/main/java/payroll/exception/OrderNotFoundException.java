package payroll.exception;

/**
 * 注文未存在例外クラス
 */
public class OrderNotFoundException extends RuntimeException {
    /**
     * コンストラクタ
     * @param id
     */
    public OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}
