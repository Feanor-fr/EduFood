package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.CartItemDto;
import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.model.Order;
import kg.attractor.edufood.model.User;
import kg.attractor.edufood.repository.OrderRepository;
import kg.attractor.edufood.service.CartService;
import kg.attractor.edufood.service.OrderService;
import kg.attractor.edufood.utils.ConvertHelper;
import kg.attractor.edufood.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final UserHelper userHelper;
    private final CartService cartService;

    @Override
    public Page<OrderDto> getOrdersByCustomer(String customer, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        return repository.findOrdersByCustomer_EmailOrderByCreatedDateDesc(customer, pageable)
                .map(ConvertHelper::convertToOrderDto);
    }

    @Override
    public void create(String cartCookie) {
        User userAuth = ConvertHelper.convertTouser(userHelper.getAuthUser());

        log.info("cookie: {}", cartCookie);
        Map<Integer, Integer> cartMap = cartService.readCartFromCookie(cartCookie);
        List<CartItemDto> items = cartService.buildCartItems(cartMap);
        Double total = cartService.calculateTotal(items);
        log.info("total= {}", total);
        Order order = ConvertHelper.convertToOrder(userAuth, total);

        repository.saveAndFlush(order);
    }
}
