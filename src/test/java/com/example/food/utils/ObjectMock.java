package com.example.food.utils;

import com.example.food.api.dto.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectMock {

    public OrderInputDTO createOrder() {
        OrderInputDTO orderInputDTO = new OrderInputDTO();
        orderInputDTO.setRestaurant(createRestaurant());
        orderInputDTO.setPayments(createPayment());
        orderInputDTO.setAddress(createAddress());
        orderInputDTO.setUsers(createUser());
        orderInputDTO.setOrderItem(createOrderItem());

        return orderInputDTO;
    }

    public RestaurantsDTO createRestaurant() {
        RestaurantsDTO restaurantsDTO = new RestaurantsDTO();
        restaurantsDTO.setId(1L);
        restaurantsDTO.setName("teste");
        return restaurantsDTO;
    }

    public PaymentsDTO createPayment() {
        PaymentsDTO payments = new PaymentsDTO();
        payments.setId(1L);
        return payments;
    }

    public AddressDTO createAddress() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressCep("09439345");
        addressDTO.setAddressComplement("345A");
        addressDTO.setAddressStreet("New Street");
        addressDTO.setAddressNumber("2343");
        addressDTO.setAddressNeighborhood("Manhattan");
        return addressDTO;
    }

    public UsersDTO createUser() {
        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(1L);
        userDTO.setUser("teste");
        return userDTO;
    }

    public List<OrderItemInputDTO> createOrderItem() {
        List<OrderItemInputDTO> orderItem = new ArrayList<>();

        OrderItemInputDTO orderItemInputDTO = new OrderItemInputDTO();
        orderItemInputDTO.setProductsId(1L);
        orderItemInputDTO.setObservation("teste");
        orderItemInputDTO.setQuantity(1);

        orderItem.add(orderItemInputDTO);

        return orderItem;
    }

    public KitchenDTO createKitchen() {
        KitchenDTO kitchenDTO = new KitchenDTO();
        kitchenDTO.setName("teste");
        return kitchenDTO;
    }

}
