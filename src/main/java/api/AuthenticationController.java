package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.ErrorMessageDto;
import dto.RegistrationBodyDto;
import dto.ResponseMessageDto;
import dto.TokenDto;

import static com.jayway.restassured.RestAssured.given;

public class AuthenticationController extends BaseApi{
        private Response registrationLogin(RegistrationBodyDto registrationBodyDto, String url){
                return given()
                        .body(registrationBodyDto)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseUrl+url)
                        .thenReturn();
        }

        public int statusCodeResponseRegLog(RegistrationBodyDto registrationBodyDto, String url){
                return registrationLogin(registrationBodyDto, url).getStatusCode();
        }

        public String tokenResponseRegLog(RegistrationBodyDto registrationBodyDto, String url){
                return registrationLogin(registrationBodyDto, url).getBody().as(TokenDto.class).getAccessToken();
        }
        public String messageResponseNegativeRegLog(RegistrationBodyDto registrationBodyDto, String url){
                return registrationLogin(registrationBodyDto, url).getBody().as(ErrorMessageDto.class).getMessage().toString();
        }




}
