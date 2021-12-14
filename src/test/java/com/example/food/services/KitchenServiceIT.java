package com.example.food.services;

import com.example.food.api.dto.KitchenDTO;
import com.example.food.model.services.KitchenService;
import com.example.food.utils.ObjectMock;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class KitchenServiceIT {

    @Autowired
    private KitchenService kitchenService;

    ObjectMock objectMock = new ObjectMock();

    @Before
    public void setup() {
        var kitchenDTO = objectMock.createKitchen();
        kitchenService.createKitchen(kitchenDTO);
    }

    @Test
    void givenSearchKitchen_ShouldRetrieval() {
        Long kitchenId = 1L;

        KitchenDTO kitchen = kitchenService.searchKitchen(kitchenId);

        assertThat(kitchen.getId()).isNotNull();
    }

}
