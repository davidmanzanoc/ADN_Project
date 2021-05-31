package com.example.domain.parking.service;

import com.example.domain.vehicle.car.model.Car;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class VehicleUnitTest {

    private static final String NULL_LICENCE_PLATE = "Ingrese la placa del vehiculo";

    @Test
    public void setLicensePlate_nullLicensePlate_isCorrect() {
        //Arrange
        String licensePlate = "";
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        //Act
        try {
            new Car(licensePlate, entryDate);
        } catch (Exception e) {
            //Assert
            assertEquals(NULL_LICENCE_PLATE, e.getMessage());
        }
    }
}
