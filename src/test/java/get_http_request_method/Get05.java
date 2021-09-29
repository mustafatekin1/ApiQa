package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends HerOkuAppBaseUrl {
    /*
test case:
 GIVEN
   https://restful-booker.herokuapp.com/booking/
 WHEN
    send a GET request to the url
  THEN
    http status code should be 200
     AND
        response format should be application/json
     AND
        among the data there should be "Mark" as firstname and "Ericsson" as lastname
       ** this is specific data from a large container... We should use "queryParam"
       ** if we search for a books category for example we use pathParam.
       ** if we search for a specific book we use queryParam:
  It is easy in Postman. There is Key, Value cells already.
*/
 @Test
 public void get05() {
// 1. set the url:
     // we set the base url for the given url in Get02 class in HerOkuAppBaseUrl class in base_urls package
// we can proceed with the spec...
     // do not forget to extend HerOkuAppBaseUrl class
     spec.pathParam("first", "booking").
             queryParams("firstname","Jim","lastname","Brown");
     // check postman base url/booking/8 (if there is bookingid 8) then we get the firstname and lastname from there
     // in queryParam we have to use the keys and values from the test case
   //2. set the exoected data-later
   //3. Send the request and get the response
   Response response = given().spec(spec).when().get("/{first}"); // no action/code is needed for queryParam
   response.prettyPrint();

   // do assertion
  response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
  assertTrue(response.asString().contains("bookingid"));
  // if there is booking id it means that there is a booikngid which contains queryParams "Jim" and "Brown"
  // We make this logic as testers!!!



 }




}
