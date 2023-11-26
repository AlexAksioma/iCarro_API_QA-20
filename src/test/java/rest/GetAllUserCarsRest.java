package rest;

import api.CarController;
import dto.CarsDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllUserCarsRest extends CarController {

    @Test
    public void getAllUserCarsPositiveTest(){
        Assert.assertEquals(statusCodeGetAllUserCarsResponse(), 200);
    }

    @Test
    public void getAllUserCarsPositiveTest_Array(){
        CarsDto carsDto = getAllUserCarAllUserCars();
        for (int i = 0; i < carsDto.getCars().length; i++) {
            System.out.println(i+" serial number --> "+carsDto.getCars()[i].getSerialNumber());
        }
    }

    @Test
    public void getAllUserCarsNegativeTest(){
        Assert.assertEquals(statusCodeGetAllUserCarsResponseNegative(), 401);
    }

    @Test
    public void validateMessageFromGetAllCarsNegative(){
        Assert.assertTrue(messageFromGetAllCarsNegative()
                .equals("JWT strings must contain exactly 2 period characters. Found: 0"));
    }
}
