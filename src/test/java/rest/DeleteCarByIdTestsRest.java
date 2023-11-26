package rest;

import api.CarController;
import dto.CarDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteCarByIdTestsRest extends CarController {

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
    public void deleteCarByIdPositiveTest(){
        Assert.assertEquals(statusCodeDeleteCarByIdResponse(serialNumber), 200);
    }
    @Test
    public void deleteCarByIdNegativeTest_wrongSerialNumber(){
        Assert.assertEquals(statusCodeDeleteCarByIdResponse(serialNumber+"wrong"), 400);
    }

    @Test
    public void validateMessageDeleteCarByIdNegativeTest_wrongSerialNumber(){
        Assert.assertTrue(messageDeleteCarByIdResponseNegative(serialNumber+"wrong")
                .equals("Car with serial number "+serialNumber+"wrong"+" not found"));
    }


}
