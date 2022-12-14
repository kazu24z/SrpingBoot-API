package payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payroll.assembler.OrderModelAssembler;
import payroll.entity.Order;
import payroll.enums.Status;
import payroll.exception.OrderNotFoundException;
import payroll.form.OrderForm;
import payroll.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * 注文用コントローラ
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    /** DI:orderRepository */
    private final OrderRepository orderRepository;
    /** DI:assembler */
    private final OrderModelAssembler assembler;

    /**
     * コンストラクタ
     * @param orderRepository
     * @param assembler
     */
    @Autowired
    public OrderController(OrderRepository orderRepository, OrderModelAssembler assembler) {

        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    /**
     * 一覧取得
     * @return 注文モデルのリスト
     */
    @GetMapping
    public CollectionModel<EntityModel<Order>> all() {

        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            orders,
            linkTo(
                methodOn(OrderController.class).all()
            )
                .withSelfRel()
        );
    }

    /**
     * 注文1件取得
     *
     * @param id
     * @return 注文モデル
     */
    @GetMapping("/{id:\\d+}")
    public EntityModel<Order> one(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order);
    }

    /**
     * 注文登録
     * @param orderForm
     * @return 注文エンティティ
     */
    @PostMapping
    public ResponseEntity<EntityModel<Order>> newOrder(@Validated @RequestBody OrderForm orderForm) {

        Order newOrder = orderRepository.save(
            new Order(
                orderForm.getDescription(),
                Status.IN_PROGRESS
            )
        );

        return ResponseEntity
            .created(
                linkTo(
                    methodOn(OrderController.class)
                        .one(newOrder.getId())
                )
                .toUri()
            )
            .body(assembler.toModel(newOrder));
    }

    /**
     * 注文キャンセル
     * @param id
     * @return 注文エンティティ
     */
    @DeleteMapping("/{id:\\d+}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        if (Status.IN_PROGRESS.equals(order.getStatus())) {
            order.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
            .body(Problem.create()
                .withTitle("Method not allowed")
                .withDetail("You can't cancel an order that is in the " + order.getStatus() + " status"));
    }

    /**
     * 注文完了
     * @param id
     * @return 注文エンティティ
     */
    @PutMapping("/{id:\\d+}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
            .body(Problem.create()
                .withTitle("Method not allowed")
                .withDetail("You can't complete an order that is in the " + order.getStatus() + " status"));
    }
}
