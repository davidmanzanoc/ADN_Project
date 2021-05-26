package com.example.domain.service;

import com.example.domain.exception.ParkingLimitException;
import com.example.domain.exception.RestrictedAccessByDayException;
import com.example.domain.model.Car;
import com.example.domain.model.Motorcycle;
import com.example.domain.model.Vehicle;
import com.example.domain.repository.CarRepository;
import com.example.domain.repository.MotorcycleRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ParkingServiceUnitTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private MotorcycleRepository motorcycleRepository;

    private ParkingService parkingService;
    private static final String RESTRICTED_ACCESS_BY_DAY = "The vehicle's license plate is restricted for today's entry.";
    private static final String PARKING_LIMIT_EXCEPTION = "The parking lot has reached its capacity limit.";

    @Before
    public void inicializarVariables() {
        carRepository = Mockito.mock(CarRepository.class);
        motorcycleRepository = Mockito.mock(MotorcycleRepository.class);
        parkingService = new ParkingService(carRepository, motorcycleRepository);
    }

    @Test
    public void getParkingTime_4HoursDifference_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        LocalDateTime exitDate = LocalDateTime
                .of(2021, 5, 23, 17, 57, 0);
        Vehicle vehicle = new Vehicle("YMU-95C", entryDate);
        //Act
        int parkingTime = parkingService.getParkingTime(vehicle.getEntryDate(), exitDate);
        //Assert
        assertEquals(4, parkingTime);
    }

    @Test
    public void carParkingCost_5HoursParking_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        LocalDateTime exitDate = LocalDateTime
                .of(2021, 5, 23, 18, 57, 0);
        Car car = new Car("YMU-95C", entryDate);
        //Act
        int parkingCost = parkingService.carParkingCost(car, exitDate);
        //Assert
        assertEquals(5000, parkingCost);
    }

    @Test
    public void carParkingCost_24HoursParking_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        LocalDateTime exitDate = LocalDateTime
                .of(2021, 5, 24, 13, 57, 0);
        Car car = new Car("YMU-95C", entryDate);
        //Act
        int parkingCost = parkingService.carParkingCost(car, exitDate);
        //Assert
        assertEquals(8000, parkingCost);
    }

    @Test
    public void carParkingCost_30HoursParking_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        LocalDateTime exitDate = LocalDateTime
                .of(2021, 5, 24, 19, 57, 0);
        Car car = new Car("YMU-95C", entryDate);
        //Act
        int parkingCost = parkingService.carParkingCost(car, exitDate);
        //Assert
        assertEquals(14000, parkingCost);
    }

    @Test
    public void carParkingCost_33HoursParking_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        LocalDateTime exitDate = LocalDateTime
                .of(2021, 5, 24, 22, 57, 0);
        Car car = new Car("YMU-95C", entryDate);
        //Act
        int parkingCost = parkingService.carParkingCost(car, exitDate);
        //Assert
        assertEquals(16000, parkingCost);
    }

    @Test
    public void motorcycleParkingCost_5HoursParkingAnd500CylinderCapacity_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        LocalDateTime exitDate = LocalDateTime
                .of(2021, 5, 23, 18, 57, 0);
        Motorcycle motorcycle = new Motorcycle("YMU-95C", entryDate, 500);
        //Act
        int parkingCost = parkingService.motorcycleParkingCost(motorcycle, exitDate);
        //Assert
        assertEquals(2500, parkingCost);
    }

    @Test
    public void motorcycleParkingCost_24HoursParkingAnd650CylinderCapacity_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        LocalDateTime exitDate = LocalDateTime
                .of(2021, 5, 24, 13, 57, 0);
        Motorcycle motorcycle = new Motorcycle("YMU-95C", entryDate, 650);
        //Act
        int parkingCost = parkingService.motorcycleParkingCost(motorcycle, exitDate);
        //Assert
        assertEquals(6000, parkingCost);
    }

    @Test
    public void validateLicensePlate_startsWithAOnATuesday_isCorrect() {
        //Arrange
        int tuesday = 2;
        String licensePlate = "AMU-95C";
        //Act
        boolean accessDenied = parkingService.validateLicensePlate(licensePlate, tuesday);
        //Assert
        assertFalse(accessDenied);
    }

    @Test
    public void validateLicensePlate_startsWithAOnASunday_isCorrect() {
        //Arrange
        int sunday = 7;
        String licensePlate = "AMU-95C";
        //Act
        boolean accessDenied = parkingService.validateLicensePlate(licensePlate, sunday);
        //Assert
        assertTrue(accessDenied);
    }

    @Test
    public void saveCar_limitOfCarsInTheParking_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        int sunday = 7;
        Car car = new Car("YMU-95C", entryDate);
        when(carRepository.getNumberOfCars()).thenReturn(parkingService.MAX_NUMBER_OF_CARS);
        //Act
        try {
            parkingService.saveCar(car, sunday);
        } catch (ParkingLimitException e) {
            //Assert
            assertEquals(PARKING_LIMIT_EXCEPTION, e.getMessage());
        }
    }

    @Test
    public void saveCar_restrictedAccessByDay_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        int sunday = 7;
        Car car = new Car("AMU-95C", entryDate);
        when(carRepository.getNumberOfCars()).thenReturn(7);
        //Act
        try {
            parkingService.saveCar(car, sunday);
        } catch (RestrictedAccessByDayException e) {
            //Assert
            assertEquals(RESTRICTED_ACCESS_BY_DAY, e.getMessage());
        }
    }

    @Test
    public void saveMotorcycle_limitOfMotorcyclesInTheParking_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        int sunday = 7;
        Motorcycle motorcycle = new Motorcycle("YMU-95C", entryDate, 500);
        when(motorcycleRepository.getNumberOfMotorcycles()).thenReturn(parkingService.MAX_NUMBER_OF_MOTORCYCLES);
        //Act
        try {
            parkingService.saveMotorcycle(motorcycle, sunday);
        } catch (ParkingLimitException e) {
            //Assert
            assertEquals(PARKING_LIMIT_EXCEPTION, e.getMessage());
        }
    }

    @Test
    public void saveMotorcycle_restrictedAccessByDay_isCorrect() {
        //Arrange
        LocalDateTime entryDate = LocalDateTime
                .of(2021, 5, 23, 13, 57, 0);
        int sunday = 7;
        Motorcycle motorcycle = new Motorcycle("AMU-95C", entryDate, 500);
        when(motorcycleRepository.getNumberOfMotorcycles()).thenReturn(7);
        //Act
        try {
            parkingService.saveMotorcycle(motorcycle, sunday);
        } catch (RestrictedAccessByDayException e) {
            //Assert
            assertEquals(RESTRICTED_ACCESS_BY_DAY, e.getMessage());
        }
    }
}
