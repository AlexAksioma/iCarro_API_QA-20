package rest;

import api.AuthenticationController;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {

    String url = "/v1/user/registration/usernamepassword";
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobagginsqa@mail.com")
                .password("QAzxc!_"+i)
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        System.out.println("username --> "+registrationBodyDto.getUsername());
        System.out.println("password --> "+registrationBodyDto.getPassword());
        Assert.assertEquals(statusCodeResponseRegLog(registrationBodyDto, url),200);
    }
    @Test
    public void registrationNegativeTest_wrongEmail_400(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobagginsqamail.com")
                .password("QAzxc!_"+i)
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        Assert.assertEquals(statusCodeResponseRegLog(registrationBodyDto, url),400);
    }
    @Test
    public void validateMessageRegistrationNegativeTest_wrongEmail_400(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobagginsqamail.com")
                .password("QAzxc!_"+i)
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        Assert.assertTrue(messageResponseNegativeRegLog(registrationBodyDto, url)
                .contains("must be a well-formed email address"));
    }

    @Test
    public void getToken(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(i+"bilbobagginsqa@mail.com")
                .password("QAzxc!_"+i)
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        System.out.println(tokenResponseRegLog(registrationBodyDto, url));
    }
}
