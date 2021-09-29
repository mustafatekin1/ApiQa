package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09 extends HerOkuAppBaseUrl {
    /* Video: 10 part 1, 3rd class; 18.09.2021
ex. test case:
  GIVEN
        https://restful-booker.herokuapp.com/booking/22
    WHEN
        user send a GET request to the url
    THEN
        http status code should be 200
        and
        Content Type should be "application/json; charset=utf-8"
     and
        response body should be like this:
       {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
*/
@Test
 public void get09() {
    //1. set the url
    spec.pathParams("first","booking","second", 5);

    //2. set the expected data
    Map<String, Object> expectedData = new HashMap<>();
    expectedData.put("Status Code", 200);
    expectedData.put("firstname", "Mark");
    expectedData.put("lastname", "Brown");
    expectedData.put("totalprice", 605);
    expectedData.put("depositpaid", true);

    expectedData.put("additionalneeds", "Breakfast");
    expectedData.put("Content Type", "application/json; charset=utf-8");

    expectedData.put("bookingdates.checkin", "2015-08-01");
    expectedData.put("bookingdates.checkout", "2019-01-26");
    // instead of above we can set a map for bookingdates also:
    // Normally we start forming Map from the inner json data
    Map<String, String> expBookDate = new HashMap<>();
    expBookDate.put("checkin", "2015-08-01");
    expBookDate.put("checkout", "2019-01-26");

    expectedData.put("bookingdates", expBookDate);
    System.out.println(expectedData);



    //3. send request and get the response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    Map <String, Object> actualData = response.as(HashMap.class);
    actualData.put("firstname", "Mark");
    actualData.put("lastname", "Brown");
    actualData.put("totalprice", 605);
    actualData.put("depositpaid", true);
    actualData.put("additionalneeds", "Breakfast");

    actualData.put("bookingdates.checkin", "2015-08-01");
    actualData.put("bookingdates.checkout", "2019-01-26");
// instead of above we can set a map for actual bookingdates also:

    Map<String, String> actBookDate = new HashMap<>();
    actBookDate.put("checkin", "2015-08-01");
    actBookDate.put("checkout", "2019-01-26");

    actualData.put("bookingdates", actBookDate);
    System.out.println(actualData);
    //4. do the assertions

    assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
    assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
    assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
    assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
    assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
//1st way for the inner json (bookingdates)- The teacher did not use this way. I tested this way, it worked
    assertEquals(expectedData.get("bookingdates.checkout"), actualData.get("bookingdates.checkout"));
    assertEquals(expectedData.get("bookingdates.checkin"), actualData.get("bookingdates.checkin"));

    //2nd way for the inner json (bookingdates)
    assertEquals(expectedData.get("bookingdates"), actualData.get("bookingdates"));

//3rd way for the inner json (bookingdates) (Teacher's way was this)
    assertEquals(expBookDate.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
//actualData.get("bookingdates") is object. We made explicit casting and change it to Map and then use .get() method
    // Watchout for the paranthesis: ((Map)actualData.get("bookingdates")).get()     
    assertEquals(expBookDate.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));

    assertEquals(expectedData.get("Status Code"), response.getStatusCode());
    assertEquals(expectedData.get("Content Type"), response.getContentType());


}




}
