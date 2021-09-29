package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get04 extends JsonPlaceHolderBaseUrl {
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
        there should be 200 todos
     AND
       one of the todos should be "quis eius est sint explicabo"
     AND
        2,7 and 9 should be among userIds.
*/
@Test
    public void get04 () {
 //1 set the url:
    spec.pathParam("first", "todos");
//2. set the expected data-- later
//3. Type the automation script=> send the request and get the response
    // at first execute accept type "application/json" ==>> given().spec(spec).accept(ContentType.JSON)
    Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
response.prettyPrint();// we use this to see the response body
//do Assertion

    response.
    then().
    assertThat().
    statusCode(200).
    contentType(ContentType.JSON).
            body("id", hasSize(200)). // type hasSize>>hover>>more actions>>import static method>>Matchers.
            body("title", hasItem("quis eius est sint explicabo")).
         body("userId", hasItem(2), "userId", hasItem(7), "userId", hasItem(9)) ;
// shorter way removing repetitions:
    response.
            then().
            assertThat().
            statusCode(200).
            contentType(ContentType.JSON).
            body("id", hasSize(200)). // type hasSize>>hover>>more actions>>import static method>>Matchers.
            body("title", hasItem("quis eius est sint explicabo"),
            "userId", hasItems(2,7,9)) ; // we have hasItems to check multiple items existence



}




}
