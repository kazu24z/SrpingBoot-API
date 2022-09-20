package payroll;

/**
 * 従業員が見つからない時の処理用クラス
 */
class EmployeeNotFoundException extends RuntimeException {

    /**
     * エラーメッセージ表示
     * @param id
     */
    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
