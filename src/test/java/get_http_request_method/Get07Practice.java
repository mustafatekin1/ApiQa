package get_http_request_method;

import base_urls.DummyRestApiExampleBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get07Practice extends DummyRestApiExampleBaseUrl {
        /*
test case:

 WHEN
    I send a GET request to the url http://dummy.restapiexample.com/api/v1/employees
  THEN
    http status code should be 200
     AND
        response content type is "application/json"
     AND
        http status line should be "HTTP/1.1 200 OK
     AND
       There are 24 employees in total
     AND
        there should be this employee:
        {
            "id": 12,
            "employee_name": "Quinn Flynn",
            "employee_salary": 342000,
            "employee_age": 22,
            "profile_image": ""
        },
*/

    @Test
    public void get07 () {
    //1. set the url: Lets set the base url in the base_urls package and extends this class to that class.
  // Then we add the pathParam or pathParams

        spec.pathParams("first","api","second","v1", "third","employees");
    //2. set the expected data = later
    //3. Send the request and get the response
    Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
    response.prettyPrint();
    //4. Do the assertion

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                body("data.id", hasSize(24)).
                body("data.id", hasItem(12),
                        "data.employee_name", hasItem("Quinn Flynn"),
                        "data.employee_salary", hasItem(320800),
                        "data.employee_age", hasItem(61),
                        "data.profile_image", hasItem(""));




    }
}
