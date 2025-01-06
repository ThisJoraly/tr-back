package com.danilscheglov.transport.service;

import com.danilscheglov.transport.model.Order;
import com.danilscheglov.transport.model.dto.OrderRequest;
import com.danilscheglov.transport.model.dto.OrderResponse;
import com.danilscheglov.transport.model.Client;
import com.danilscheglov.transport.model.Operator;
import com.danilscheglov.transport.model.Flight;
import com.danilscheglov.transport.repository.OrderRepository;
import com.danilscheglov.transport.repository.ClientRepository;
import com.danilscheglov.transport.repository.OperatorRepository;
import com.danilscheglov.transport.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OperatorRepository operatorRepository;
    private final FlightRepository flightRepository;

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<OrderResponse> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toResponse);
    }

    public OrderResponse saveOrder(OrderRequest orderRequest) {
        Client client = clientRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Operator operator = operatorRepository.findById(orderRequest.getOperatorId())
                .orElseThrow(() -> new RuntimeException("Operator not found"));
        Flight flight = flightRepository.findById(orderRequest.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Order order = toEntity(orderRequest);
        order.setClient(client);
        order.setOperator(operator);
        order.setFlight(flight);

        Order savedOrder = orderRepository.save(order);
        return toResponse(savedOrder);
    }

    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Client client = clientRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Operator operator = operatorRepository.findById(orderRequest.getOperatorId())
                .orElseThrow(() -> new RuntimeException("Operator not found"));
        Flight flight = flightRepository.findById(orderRequest.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        existingOrder.setOrderStartpoint(orderRequest.getOrderStartpoint());
        existingOrder.setOrderEndpoint(orderRequest.getOrderEndpoint());
        existingOrder.setOrderDispatchDate(orderRequest.getOrderDispatchDate());
        existingOrder.setOrderDeliveryDate(orderRequest.getOrderDeliveryDate());
        existingOrder.setOrderStatus(orderRequest.getOrderStatus());
        existingOrder.setClient(client);
        existingOrder.setOperator(operator);
        existingOrder.setFlight(flight);

        Order updatedOrder = orderRepository.save(existingOrder);
        return toResponse(updatedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private Order toEntity(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderStartpoint(orderRequest.getOrderStartpoint());
        order.setOrderEndpoint(orderRequest.getOrderEndpoint());
        order.setOrderDispatchDate(orderRequest.getOrderDispatchDate());
        order.setOrderDeliveryDate(orderRequest.getOrderDeliveryDate());
        order.setOrderStatus(orderRequest.getOrderStatus());
        return order;
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getClient().getClientName(),
                order.getOperator().getOperatorName(),
                order.getFlight().getFlightId(),
                order.getOrderStartpoint(),
                order.getOrderEndpoint(),
                order.getOrderDispatchDate(),
                order.getOrderDeliveryDate(),
                order.getOrderStatus()
        );
    }
}
