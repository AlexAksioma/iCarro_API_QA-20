package okhttp;

import com.google.gson.Gson;
import dto.ErrorMessageDto;
import dto.RegistrationBodyDto;
import dto.TokenDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class RegistrationTestsOkhttp {
    public static final MediaType JSON = MediaType.get("application/json");
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://ilcarro-backend.herokuapp.com";
    @Test
    public void registrationPositiveTest(){
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
            System.out.println(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseJson;
        if(response.isSuccessful()){
            try {
                responseJson=response.body().string();
                System.out.println(responseJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TokenDto tokenDto = gson.fromJson(responseJson, TokenDto.class);
            System.out.println("token --> "+tokenDto.getAccessToken());

        } else if (response == null) {
            Assert.fail("response is null");
        }else {
            System.out.println("response code --> "+response.code());
            Assert.fail("test failed with code " +response.code());
        }

    }
    @Test
    public void registrationNegativeTest_emailWO_dog(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobagginsqamail.com")
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
            System.out.println(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseJson;
        if (response == null)
            Assert.fail("response is null");
        else if(!response.isSuccessful()){
            try {
                responseJson=response.body().string();
                System.out.println(responseJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ErrorMessageDto errorMessageDto = gson.fromJson(responseJson, ErrorMessageDto.class);
            System.out.println("message --> "+errorMessageDto.getMessage().toString());
            System.out.println("error --> "+errorMessageDto.getError());
        }else {
            System.out.println("response code --> "+response.code());
            Assert.fail("test failed with code " +response.code());
        }

    }
}
