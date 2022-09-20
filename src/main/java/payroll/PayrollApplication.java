package payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * メインクラス
 */
@SpringBootApplication
public class PayrollApplication {
	/**
	 * プログラム実行の起点となるメソッド
	 * @param args
	 */
	public static void main(String... args) {
		SpringApplication.run(PayrollApplication.class, args);
	}
}
