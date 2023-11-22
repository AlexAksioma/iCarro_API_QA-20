package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.CarDto;
import dto.ErrorMessageDto;
import dto.ResponseMessageDto;

import static com.jayway.restassured.RestAssured.given;

public class CarController extends BaseApi{

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
}
