import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.repository.*;
import org.junit.Test;

import javax.transaction.Transactional;


public class OrderApiServiceTest {
    private final AccountRepository accountRepository;

    /* 바로 주문하기 눌렀을 때 사용하기 위한 ProductRepository */
    private final ProductRepository productRepository;

    /* CartItem의 정보를 가져오기 위한 Repository */
    private final CartItemRepository cartItemRepository;

    /* order 저장을 위한 Repository */
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    public OrderApiServiceTest(AccountRepository accountRepository, ProductRepository productRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Test
    @Transactional
    public void createOrderService(){
        // given
        Order order = new Order();
//        order.builder()
//                .grandTotal()
//        address.setRoad_address("address");
//        address.setAccount(accountService.findOne(accountId).get());
//        address.setZipCode(84984);
//        // when
//        addressService.save(address);

    }
}