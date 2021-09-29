package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class Get03 extends JsonPlaceHolderBaseUrl {
    /**
     GIVEN
        https://jsonplaceholder.typicode.com/todos/23
     WHEN
        user send GET request to the url
     THEN
        http status code should be 200
     AND
        response format should be application/json
     AND
        title is "et itaque necessitatibus maxime molestiae qui quas velit"
     AND
        "completed" is false
     AND
        "userId" is 2
     */
    // we dont need main method with this @Test annotation
    @Test
    public void get03() {
 //1. step: Set the url:
        // As we learn in API02 lesson, lets create a new base url class in base_urls package
        // Lets name the parent class for the base url as:
spec.pathParams("first","todos","second",23);

//2. set the expected data (we'll learn later)
//3. Type the automation script: send the GET request and get the response

Response response = given().spec(spec).when().get("/{first}/{second}");
response.prettyPrint();

//4. do Assertion
  //1st way
//response.then().assertThat().statusCode(200).contentType("application/json");
        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));
        // JSON is a content type and content type is an enum in junit. we call JSON from ContentType enum
        //enum: are storage for constant variables.
        // we can put "states" inside an enum:
        //enum syntax:  public enum StatesInAmerica {}

 // body("title", equalTo("actual title")). equalTo() complains. To solve this do the following.
// >>hover on equalTo>>more actions>>import static>>select third one: Matchers.equalTo()

       // assertEquals("et itatique necessitatibus maxime molestiae qui quas velit", response.getHeader("title"));

// 2nd way : lets remove the repetition of body 3 times. Seperate "key", equalTo(), with comma(,)
        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),"userId", equalTo(2));

    }






}
