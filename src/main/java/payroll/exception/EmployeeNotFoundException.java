package payroll.exception;

/**
 * 従業員が見つからない時の処理用クラス
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * エラーメッセージ表示
     * @param id
     */
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
