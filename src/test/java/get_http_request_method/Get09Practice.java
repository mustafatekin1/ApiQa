package get_http_request_method;

import base_urls.DummyRestApiExampleBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get09Practice extends DummyRestApiExampleBaseUrl {

/*
when
    I send a request to the url http://dummy.restapiexample.com/api/v1/employees
then
    status code should be 200
And
    status line should be "HTTP/1.1 200 OK"
and
    content type should be "application/json"
and
    there should be ids 3, 7, 9
and
    there should be a total 24 id.
 */

@Test
public void get09() {

    spec.pathParams("first","api","second", "v1", "third", "employees");

    Response response = given().spec(spec).when().get("/{first}/{second}/{third}");

    response.prettyPrint();

    response.then().
            assertThat().
            statusCode(200).
            statusLine("HTTP/1.1 200 OK").
            contentType(ContentType.JSON).
            body("data.id", hasItems(3, 7, 9),
                    "data.id", hasSize(24));


}



}
