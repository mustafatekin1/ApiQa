package get_http_request_method;

import base_urls.JsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class Get09P extends JsonPlaceHolder {
/*
test case:
 GIVEN
   https://jsonplaceholder.typicode.com/todos
 WHEN
    send a GET request to the url
 AND
    accept type is "application/json"
  THEN
    http status code should be 200
     AND
        response format should be application/json
     AND
        make sure total number of todos is 200.
*/

    @Test
    public void get09P() {
        spec.pathParams("first", "todos");
       Response response =  given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
       response.prettyPrint();

       response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
               body("id", hasSize(200));

    }



}
