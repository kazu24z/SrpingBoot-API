package payroll.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import payroll.controller.OrderController;
import payroll.entity.Order;
import payroll.enums.Status;

/**
 * 注文エンティティを注文モデルに変換するクラス
 */
@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    /**
     * エンティティをモデルに変換するメソッド
     * @param order
     * @return orderModel
     */
    @Override
    public EntityModel<Order> toModel(Order order) {

        EntityModel<Order> orderModel = EntityModel.of(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        if (Status.IN_PROGRESS.equals(order.getStatus())) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}
