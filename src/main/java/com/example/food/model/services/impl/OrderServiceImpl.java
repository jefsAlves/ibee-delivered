package com.example.food.model.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.dto.OrderInputDTO;
import com.example.food.model.dto.OrdersDTO;
import com.example.food.model.entities.Order;
import com.example.food.model.entities.enums.OrderStatus;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.OrderInputMapper;
import com.example.food.model.mapper.OrderMapper;
import com.example.food.model.mapper.OrdersMapper;
import com.example.food.model.mapper.ProductsMapper;
import com.example.food.model.repository.OrderRepository;
import com.example.food.model.services.CityService;
import com.example.food.model.services.OrderService;
import com.example.food.model.services.PaymentService;
import com.example.food.model.services.ProductService;
import com.example.food.model.services.RestaurantService;
import com.example.food.model.services.UserService;
import com.example.food.model.util.MessageUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private OrdersMapper mappers;

	@Autowired
	private OrderInputMapper orderMapper;

	@Autowired
	private ProductsMapper productMapper;

	@Autowired
	private CityService cityService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

//	@Autowired
//	private ModelMapper modelMapper;

//	@Override
//	public OrderDTO searchOrder(Long orderId) {
//		var order = orderRepository.search(orderId);
//		if (order == null) {
//			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
//		}
//		return mapper.toDTO(order);
//	}

	@Override
	public OrderDTO searchOrder(Long orderId) {
		var order = searchByOrder(orderId);
		return mapper.toDTO(order);
	}

	public Order searchByOrder(Long orderId) {
		var order = orderRepository.findById(orderId);
		return order.orElseThrow(() -> new IdNotFoudException());
	}

//	@Override
//	public OrderDTO searchOrder(Long orderId) {
//		var order = orderRepository.findById(orderId);
//		order.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
//		return modelMapper.map(order.get(), OrderDTO.class);
//	}

	@Override
	public List<OrdersDTO> listOrders() {
		List<Order> order = orderRepository.findAll();
		return mappers.toDTOList(order);
	}

	@Transactional
	@Override
	public OrderDTO createOrder(OrderInputDTO orderInputDTO) {
		var order = orderMapper.toEntity(orderInputDTO);
		validationOrder(order);
		validationOrderItens(order);
		order.setFreigthRate(order.getRestaurant().getFreigthRate());
		order.calculateTotal();
		orderRepository.save(order);
		return mapper.toDTO(order);
	}

	public void validationOrder(Order orders) {
		var restaurant = restaurantService.search(orders.getRestaurant().getId());
		var payment = paymentService.search(orders.getPayments().getId());
		var user = userService.search(orders.getUsers().getId());

		orders.setRestaurant(restaurant);
		orders.setPayments(payment);
		orders.setUsers(user);
		orders.setOrderStatus(OrderStatus.CREATE);

		if (restaurant.notAcceptancePaymentForm(payment)) {
			throw new BusinessException(MessageUtil.RESTAURANT_NOT_ACCEPTANCE_PAYMENT);
		}
	}

	public void validationOrderItens(Order order) {
		order.getOrderItem().forEach(item -> {
			var productDTO = productService.searchProduct(order.getRestaurant().getId(), item.getProducts().getId());
			var product = productMapper.toEntity(productDTO);

			item.setOrder(order);
			item.setProducts(product);
			item.setUnitaryPrice(product.getPrice());
		});
	}

}
