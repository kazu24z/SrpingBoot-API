package payroll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import payroll.exception.OrderNotFoundException;

/**
 * 注文検索例外発生時の処理用クラス
 */
@ControllerAdvice
public class OrderNotFoundAdvice {
    /**
     * 例外メッセージ返却
     * @param ex
     * @return エラーメッセージ
     */
    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String employeeNotFoundHandler(OrderNotFoundException ex) {
        return ex.getMessage();
    }
}
