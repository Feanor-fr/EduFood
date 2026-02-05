package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.OrderDto;
import org.springframework.data.domain.Page;

public interface OrderService {
    Page<OrderDto> getOrdersByCustomer(String customer, Integer page, Integer size);

    void create(String cartCookie);
}
