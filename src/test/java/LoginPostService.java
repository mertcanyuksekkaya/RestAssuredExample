import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class LoginPostService {

    @Test
    public void getPageService(){

        //Specify base URI
        RestAssured.baseURI = "http://thedemosite.co.uk";
        RequestSpecification request = RestAssured.given();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username","testuser");
        jsonObject.put("password","testpassword");

        request.header("Content-Type","application/x-www-form-urlencoded");
        request.body(jsonObject.toJSONString());
        Response response = request.request(Method.POST,"/login.php");

        Headers headers = response.headers();
        System.out.println(headers+"\n");

        int statusCode=response.getStatusCode();
        System.out.println("Status code: "+statusCode+"\n");
        Assert.assertEquals(statusCode,200);

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assert.assertTrue(responseBody.contains("<html>"));



    }
}
