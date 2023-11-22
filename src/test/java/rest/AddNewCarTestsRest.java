package rest;

import api.CarController;
import dto.CarDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTestsRest extends CarController {

    @Test
    public void addNewCarPositiveTest(){
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
        Assert.assertEquals(statusCodeAddNewCarResponse(carDto), 200);
    }
    @Test
    public void addNewCarNegativeTest_EmptyFieldSerialNumBer_400(){
        int i = new Random().nextInt(1000)+1000;
        CarDto carDto = CarDto.builder()
                .manufacture("Ford")
                .model("focus")
                .year("2020")
                .fuel("Electric")
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Assert.assertEquals(statusCodeAddNewCarResponse(carDto), 400);
    }
    @Test
    public void validateMessageAddNewCarPositiveTest(){
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
       Assert.assertEquals(messageFromAddNewCarPositive(carDto), "Car added successfully");
    }
    @Test
    public void validateMessageAddNewCarNegativeTest_EmptyFieldSerialNumBer_400(){
        int i = new Random().nextInt(1000)+1000;
        CarDto carDto = CarDto.builder()
                .manufacture("Ford")
                .model("focus")
                .year("2020")
                .fuel("Electric")
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
       Assert.assertTrue(messageFromAddNewCarNegative(carDto)
               .contains("serialNumber=must not be blank"));
    }
}
