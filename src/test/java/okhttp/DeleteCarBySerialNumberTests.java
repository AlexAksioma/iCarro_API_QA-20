package okhttp;

import com.google.gson.Gson;
import dto.CarDto;
import dto.RegistrationBodyDto;
import dto.ResponseMessageDto;
import dto.TokenDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class DeleteCarBySerialNumberTests {public static final MediaType JSON = MediaType.get("application/json");
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://ilcarro-backend.herokuapp.com";

    String accessToken, serialNumber;
    @BeforeClass
    public void registration(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobagginsqa@mail.com")
                .password("QAzxc!_"+i)
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(registrationBodyDto), JSON);
        Request request = new Request.Builder()
                .url(baseUrl+"/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseJson;
        if(response.isSuccessful()){
            try {
                responseJson=response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TokenDto tokenDto = gson.fromJson(responseJson, TokenDto.class);
            System.out.println("token --> "+tokenDto.getAccessToken());
            accessToken = tokenDto.getAccessToken();
        } else if (response == null) {
            System.out.println("response is null");
        }else {
            System.out.println("response code --> "+response.code());
        }
    }
    @AfterMethod
    public void addNewCar(){
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
        RequestBody requestBody = RequestBody.create(gson.toJson(carDto), JSON);
        Request request = new Request.Builder()
                .url(baseUrl+"/v1/cars")
                .addHeader("Authorization",accessToken)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseJson;
            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ResponseMessageDto responseMessageDto = gson.fromJson(responseJson, ResponseMessageDto.class);
            serialNumber = carDto.getSerialNumber();
    }

    @Test
    public void deleteCarBySerialNumberPositiveTest(){
        Request request = new Request.Builder()
                .url(baseUrl+"/v1/cars/"+serialNumber)
                .addHeader("Authorization",accessToken)
                .delete()
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Assert.assertTrue(response.isSuccessful());
    }

}
