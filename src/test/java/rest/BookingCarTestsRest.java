package rest;

import api.CarController;
import dto.BookedPeriods;
import dto.CarDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class BookingCarTestsRest extends CarController {
    @BeforeMethod
    public void addNewCarForDelete(){
        int i = new Random().nextInt(1000)+1000;
        CarDto carDto = CarDto.builder()
                .serialNumber("123-"+i)
                .manufacture("Ford")
                .model("focus")
                .year("2020")
                .fuel("Electric")
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        serialNumber = carDto.getSerialNumber();
        Assert.assertEquals(statusCodeAddNewCarResponse(carDto), 200);
    }

    @Test
    public void bookingCarPositiveTest(){
        BookedPeriods bookedPeriods = BookedPeriods.builder()
                .startDate("2023-11-27")
                .endDate("2023-11-28")
                .build();
        Assert.assertEquals(statusCodeBookingCarResponse(bookedPeriods, serialNumber), 200);
    }
}
