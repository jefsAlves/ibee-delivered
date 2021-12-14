package com.example.food.services;

import com.example.food.api.dto.KitchenDTO;
import com.example.food.model.services.KitchenService;
import com.example.food.utils.ObjectMock;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenServiceIT {

    @Autowired
    private KitchenService kitchenService;

    ObjectMock objectMock = new ObjectMock();

    @Test
    void givenSearchKitchen_ShouldRetrieval() {
        Long kitchenId = 1L;

        KitchenDTO kitchen = kitchenService.searchKitchen(kitchenId);

        assertThat(kitchen.getId()).isNotNull();
    }

    @Test
    void givenUpdateKitchen_ShouldRegister() {
        Long kitchenId = 1L;
        KitchenDTO kitchenDTO = new KitchenDTO();
        kitchenDTO.setName("teste");

        var kitchen = kitchenService.updateKitchen(kitchenId, kitchenDTO);

        assertThat(kitchen).isNotNull();
    }

}
