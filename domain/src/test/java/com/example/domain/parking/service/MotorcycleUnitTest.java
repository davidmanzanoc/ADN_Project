package com.example.domain.parking.service;

import com.example.domain.vehicle.motorcycle.model.Motorcycle;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class MotorcycleUnitTest {

    private static final String NULL_CYLINDER_CAPACITY = "Ingrese el cilindraje del vehiculo";

    @Test
    public void setCylinderCapacity_nullCylinderCapacity_isCorrect() {
        //Arrange
        String licensePlate = "HUC-49";
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        String cylinderCapacity = "";
        //Act
        try{
            new Motorcycle(licensePlate, entryDate, cylinderCapacity);
        } catch (Exception e) {
            //Assert
            assertEquals(NULL_CYLINDER_CAPACITY, e.getMessage());
        }
    }
}
