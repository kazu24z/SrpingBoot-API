package payroll;

/**
 * 注文が見つからない時の例外メッセージ
 */
public class OrderNotFoundException extends RuntimeException {
    /**
     * エラーメッセージ表示
     * @param id
     */
    OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}
