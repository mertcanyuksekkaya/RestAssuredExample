import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class LoginPostService {

    @Test
    public void FailedLoginTest(){

        RestAssured.baseURI="http://thedemosite.co.uk";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Response list=RestAssured.given()
                .urlEncodingEnabled(true)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .param("username","testuser")
                .param("password","test1")
                .post("/login.php");

        list.then()
                .statusCode(200);
        System.out.println(list.headers());
        System.out.println(list.asString());
        Assert.assertTrue(list.asString().contains("**Failed Login**"),"Failed login başlığı bulunamadı!");

    }

    @Test
    public void SuccessLoginTest(){

        RestAssured.baseURI="http://thedemosite.co.uk";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Response list=RestAssured.given()
                .urlEncodingEnabled(true)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .param("username","testuser")
                .param("password","testpass")
                .post("/login.php");

        list.then()
                .statusCode(200);
        System.out.println(list.headers());
        System.out.println(list.asString());
        Assert.assertTrue(list.asString().contains("**Successful Login**"),"Successful login başlığı bulunamadı!");

    }
}
