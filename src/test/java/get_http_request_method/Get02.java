package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 extends HerOkuAppBaseUrl  {
    /*
    GIVEN
        https://restful-booker.herokuapp.com/booking/1001
    WHEN
        user send a GET request to the url
    THEN
        http status code should be 404
    AND
        status line should be HTTP/1.1 404 Not Found
    AND
        response body contains "Not Found"
    AND
        response body does not contain "Techproed"
    AND
        server is "Cowboy"


     */
    @Test
    public void get02 () {
 // 1. set the url
    // String url = "https://restful-booker.herokuapp.com/booking/1001"; ==> not recommended
        //                                  /booking/1001 are path parameters
        //  we should put base url in parent class
        //  (because this base url is common and common things are put in parent class in java)
        //  and then we use that.
   spec.pathParams("first","booking", "second", 1001);
    // if there are multiple path parameters we have to use plural pathParams() method.
        // if single param use pathParam() method
        // path parameters are used to make the path smaller.
// 2. Set the expected data
        // we will learn later

 // 3. Type the automation script: send the request and get the response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    // Important note: after typing given()>>hover>>more actions>>import static io.restassured.RestAssured.given
        // then also remove .given and replace it with .* to make it more general
    response.prettyPrint(); // Lets check if we get the body or not. Is our path right
// 4. Do assertion
        //a. response.then().assertThat().statusCode(404);// it is better to check one by one. here first one statusCode
// b. response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");// passed
response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
   // c. contains "Not Found" ?
   // we change the response body to String:
        //System.out.println(response.asString().contains("Not Found"));// true:
        // As a tester we dont prefer seeing true false. We want green check or red cross.
        // That's why we use assertTrue method
        //assertTrue(true)==>green tick, assertTrue(false)==>red cross
        assertTrue(response.asString().contains("Not Found"));
//type assertTrue()>>hover>>more actions>>import static org.junit.Assert.true>>change true to .*
   //d. does not contain "Techpro"
        assertTrue(!response.asString().contains("Techpro"));
// d. 2nd way : teacher user assertFalse() method for this:
        assertFalse(response.asString().contains("Techpro"));
 // e. server = cowboy
        //System.out.println(response.getHeader("server"));
       assertEquals("Cowboy", response.getHeader("server"));
       // expected data is from test case, response comes from API.
        // If the arguments are equal, we get green tick else red cross


    }

}
