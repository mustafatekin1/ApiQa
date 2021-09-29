package get_http_request_method;

import base_urls.DummyRestApiExampleBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertTrue;

public class Get08Practice extends DummyRestApiExampleBaseUrl {
/*
test case:
 WHEN
    I send a GET request to the url http://dummy.restapiexample.com/api/v1/employees
  THEN
    http status code should be 200
     AND
        response content type is "application/json"
     AND
        http status line should be "HTTP/1.1 200 OK"
     AND
       There should be the following people: Doris Wilder, Jenette Caldwell, Bradley Greer
       There should not be "Mustafa"
 */
@Test
    public void get08 (){


        spec.pathParams("first","api","second","v1","third","employees");

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");

        response.then().assertThat().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType(ContentType.JSON).
                body("data.employee_name", hasItems("Doris Wilder", "Jenette Caldwell", "Bradley Greer"));

        Assert.assertFalse(response.asString().contains("Mustafa"));
    System.out.println(response.asString().contains("Mustafa"));

        //or
        assertTrue(response.asString().contains("Doris Wilder"));
    assertTrue(response.asString().contains("Jenette Caldwell"));
    assertTrue(response.asString().contains("Bradley Greer"));

    System.out.println(response.asString().contains("Jenette Caldwell"));


    }


}
