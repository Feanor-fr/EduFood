package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.repository.OrderRepository;
import kg.attractor.edufood.service.OrderService;
import kg.attractor.edufood.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Override
    public Page<OrderDto> getOrdersByCustomer(String customer, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        return repository.findOrdersByCustomer_EmailOrderByCreatedDateDesc(customer, pageable)
                .map(ConvertHelper::convertToOrderDto);
    }
}
