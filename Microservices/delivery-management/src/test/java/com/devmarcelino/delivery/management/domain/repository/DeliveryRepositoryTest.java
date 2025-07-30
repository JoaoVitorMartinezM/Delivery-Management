package com.devmarcelino.delivery.management.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devmarcelino.delivery.management.domain.model.ContactPoint;
import com.devmarcelino.delivery.management.domain.model.Delivery;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DeliveryRepositoryTest {

    @Autowired DeliveryRepository deliveryRepository;

    @Test
    public void shouldPersist() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.addItem("Desktop", 1);
        delivery.addItem("Notebook", 1);

        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();

        assertEquals(2, persistedDelivery.getItems().size());

    }

    private Delivery.PreparationDetails createdValidPreparationDetails() {
        ContactPoint sender = ContactPoint.builder()
        .zipcode("00000-00")
        .street("Rua dos Navegantes")
        .number("02")
        .complement("casa")
        .name("João")
        .phone("48 11111-1111")
        .build();

        ContactPoint recipient = ContactPoint.builder()
        .zipcode("00000-01")
        .street("Rua das Oliveiras")
        .number("01")
        .complement("sala 400")
        .name("José")
        .phone("48 11111-2222")
        .build();

        return Delivery.PreparationDetails.builder()
        .sender(sender)
        .recipient(recipient)
        .distanceFee(new BigDecimal("10.99"))
        .courierPayout(new BigDecimal("5.00"))
        .expectedDeliveryTime(Duration.ofHours(5))
        .build();
    }
    
}
