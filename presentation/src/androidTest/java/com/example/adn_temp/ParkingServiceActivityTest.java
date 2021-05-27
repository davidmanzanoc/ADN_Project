package com.example.adn_temp;

import androidx.test.core.app.ActivityScenario;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class ParkingServiceActivityTest {

    @Test
    public void saveCar_carSaved_carOnDisplay() {
        try( ActivityScenario<ParkingServiceActivity> launchActivity = ActivityScenario.
                launch(ParkingServiceActivity.class)) {
            //Arrange
            String licensePlate = "YMU-950";
            //Act
            ParkingServiceActivityResources.clickButton(ParkingServiceActivityResources.
                    getResRadioButtonCar());
            ParkingServiceActivityResources.editText(ParkingServiceActivityResources.
                    getResEditTextLicensePlate(), licensePlate);
            //Assert
            ParkingServiceActivityResources.clickButton(ParkingServiceActivityResources.
                    getResButtonSaveVehicle());
        }
    }
}