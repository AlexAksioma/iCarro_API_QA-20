package rest;

import api.AuthenticationController;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AuthenticationController {
    String username, password;
    String url = "/v1/user/login/usernamepassword";
    @BeforeClass
    public void registrationUser(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobagginsqa@mail.com")
                .password("QAzxc!_"+i)
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        System.out.println("username --> "+registrationBodyDto.getUsername());
        System.out.println("password --> "+registrationBodyDto.getPassword());
        System.out.println("sttaus code --> "+statusCodeResponseRegLog(registrationBodyDto,
                "/v1/user/registration/usernamepassword"));
        username = registrationBodyDto.getUsername();
        password = registrationBodyDto.getPassword();
    }

    @Test
    public void loginPositiveTest(){
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(username)
                .password(password)
                .build();
        Assert.assertEquals(statusCodeResponseRegLog(registrationBodyDto, url), 200);
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(username)
                .password("111111111")
                .build();
        Assert.assertEquals(messageResponseNegativeRegLog(registrationBodyDto, url),
                "Login or Password incorrect");
    }
}
