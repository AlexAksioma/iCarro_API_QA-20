package api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseOptions;
import com.jayway.restassured.specification.RequestSpecification;
import dto.*;
import org.testng.annotations.BeforeSuite;

import static com.jayway.restassured.RestAssured.given;

public class CarController extends BaseApi{

    String token = "";

    public String serialNumber="";
    RequestSpecification requestSpecificationCar;
    @BeforeSuite
    public void getTokenForCarController(){
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username(usernameReg)
                .password(passwordReg)
                .build();
        token = given()
                .body(registrationBodyDto)
                .contentType(ContentType.JSON)
                .when()
                .post(baseUrl+"/v1/user/login/usernamepassword")
                .thenReturn()
                .getBody().as(TokenDto.class).getAccessToken();
        System.out.println("token --> "+token);
        requestSpecificationCar = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", token)
                .build();
    }
    //===============================================
    private Response addNewCarResponse(CarDto carDto){
        return given()
                .body(carDto)
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post(baseUrl+"/v1/cars")
                .thenReturn();
    }
    public int statusCodeAddNewCarResponse(CarDto carDto){
        return addNewCarResponse(carDto).getStatusCode();
    }

    public String messageFromAddNewCarPositive(CarDto carDto){
        return addNewCarResponse(carDto).as(ResponseMessageDto.class).getMessage();
    }
    public String messageFromAddNewCarNegative(CarDto carDto){
        return addNewCarResponse(carDto).as(ErrorMessageDto.class).getMessage().toString();
    }
    //===================================================
    private Response getAllUserCarsResponse(){
        System.out.println("===============================================");
        return given()
                .spec(requestSpecificationCar)
                //.contentType(ContentType.JSON)
                //.header("Authorization", token)
                .when()
                .get(baseUrl+"/v1/cars/my")
                .thenReturn();
    }
    public int statusCodeGetAllUserCarsResponse(){
        return getAllUserCarsResponse().getStatusCode();
    }

    public CarsDto getAllUserCarAllUserCars(){
        return getAllUserCarsResponse().as(CarsDto.class);
    }

    public int statusCodeGetAllUserCarsResponseNegative(){
        return getAllUserCarsResponseNegative().getStatusCode();
    }

    private Response getAllUserCarsResponseNegative() {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "wrong token")
                .when()
                .get(baseUrl+"/v1/cars/my")
                .thenReturn();
    }
    public String messageFromGetAllCarsNegative(){
        return getAllUserCarsResponseNegative().as(ErrorMessageDto.class).getMessage().toString();
    }
    //====================================================
    private Response deleteCarByIdResponse(String serialNumber){
        return  given()
                .spec(requestSpecificationCar)
                .delete(baseUrl+"/v1/cars/"+serialNumber)
                .thenReturn();
    }
    public int statusCodeDeleteCarByIdResponse(String serialNumber){
        return deleteCarByIdResponse(serialNumber).getStatusCode();
    }

    public String messageDeleteCarByIdResponseNegative(String serialNumber){
        return deleteCarByIdResponse(serialNumber)
                .as(ErrorMessageDto.class).getMessage().toString();
    }
    //==================================================
    private Response bookingCarResponse(BookedPeriods bookedPeriods, String serialNumber){
        return given()
                .spec(requestSpecificationCar)
                .body(bookedPeriods)
                .when()
                .post(baseUrl+"/v1/cars/"+serialNumber+"/booking")
                .thenReturn();
    }
    public int statusCodeBookingCarResponse(BookedPeriods bookedPeriods, String serialNumber){
        return bookingCarResponse(bookedPeriods, serialNumber).getStatusCode();
    }
}
