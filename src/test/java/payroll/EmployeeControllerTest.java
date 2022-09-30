package payroll;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import payroll.controller.EmployeeController;
import payroll.entity.Employee;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * EmployeeControllerテストクラス
 */
@ExtendWith(SpringExtension.class)
@Slf4j
@SpringBootTest
public class EmployeeControllerTest {
    /** Target Class */
    @Autowired
    private EmployeeController target;

    /** MockMvc */
    private MockMvc mockMvc;

    /** WebApplicationContext */
    @Autowired
    private WebApplicationContext context;

    /**
     * setUp
     */
    @BeforeEach
    public void setUp() {
        // mockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * tearDown
     * @throws Exception
     */
    @AfterEach
    public void tearDown() throws Exception {
        ;
    }

    /**
     * リソース一覧をGETで取得
     * @throws Exception
     */
    @Test
    void getAllOk() throws Exception {
        // 処理実行
        MvcResult result = mockMvc.perform(
            get("/employees").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        // 検証 - レスポンス
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // レスポンスログ確認（レスポンスの検証は別途します）
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * GETでリソース1つ取得
     * @throws Exception
     */
    @Test
    void getOneOk() throws Exception {
        MvcResult result = mockMvc.perform(
            get("/employees/1").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        //TODO:後で実装
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * 存在しないリソースへのGETアクセス
     * @throws Exception
     */
    @Test
    void getOneNg_NotFoundId() throws Exception {
        MvcResult result = mockMvc.perform(
            get("/employees/999").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        //TODO:後で実装
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * 規約違反（文字列）のリソースへのGETアクセス
     * @throws Exception
     */
    @Test
    void getOneNg_IdIsString() throws Exception {
        MvcResult result = mockMvc.perform(
            get("/employees/test").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        //TODO:後で実装
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * POSTメソッドでのリソース作成成功
     * @throws Exception
     */
    @Test
    void postOk() throws Exception {
        Employee employee = new Employee("aiue", "taro", "Engineer");
        var objectMapper = new ObjectMapper();

        MvcResult result = mockMvc.perform(
            post("/employees")
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        //TODO:後で実装
        log.info("レスポンス");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * POST処理失敗ケース
     * @throws Exception
     */
    @Test
    void postNg() throws Exception {
        ArrayList<Employee> employees = new ArrayList<Employee>(
            Arrays.asList(
                // FirstName文字数超過
                new Employee("tooLongFirstname", "taro", "Engineer"),
                // FirstName空白
                new Employee("", "taro", "Engineer"),
                // LastName空白
                new Employee("yama", "", "Engineer"),
                // LastName文字数超過
                new Employee("yama", "tooLongLastname", "Engineer"),
                // Role文字数超過
                new Employee("yama", "taro", "tooLongRoleNamexxxxxxxxxxxxxxxxxxxxxxxxxxxx")

            )
        );

        ObjectMapper objectMapper = new ObjectMapper();

        for(int i = 0; i < employees.size(); i++){
            MvcResult result = mockMvc.perform(
                post("/employees")
                    .content(objectMapper.writeValueAsString(employees.get(i)))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andReturn();

            assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
            //TODO:後で実装
            log.info("レスポンス");
            log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
        }


    }

}
