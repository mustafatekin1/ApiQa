package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
    /*
test case:
 GIVEN
   https://restful-booker.herokuapp.com/booking/5
 WHEN
    send a GET request to the url
  THEN
    http status code should be 200
     AND
        response content type is "application/json"
     AND
       response body should be like:
       {
       "firstname":"Sally",
       "lastname": "Ericsson";
       "totalprice":111,
       "depositpaid": false,
       "bookingdates":{"checkin":"2017-05-23",
                       "checkout": "2019-07-02"}
       }
*/
    @Test
    public void get06() {
  //1 set the url. we have already base url. Lets use that and set our url:
  spec.pathParams("first","booking","second", 5);
  //2. set the expected data--later
  //3. Send the request and get the response

  Response response = given().spec(spec).when().get("/{first}/{second}");
response.prettyPrint();

//4. do assertion
        //1st way
 //     response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
//      body("firstname", equalTo("Mary"), "lastname",equalTo("Ericsson"),
//              "totalprice", equalTo(419), "depositpaid", equalTo(false),
//             "bookingdates.checkin", equalTo("2015-05-02"),
//              "bookingdates.checkout", equalTo("2021-01-12"),
//              "additionalneeds", equalTo("Breakfast"));

      //2nd way: use JsonPath
        // JsonPath is a class and has many useful methods to navigate inside Json data
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        // We need to create JsonPath object:
        // Remember that there are 2 ways to create object:
        // 1st way is with new - the object is created from the scratch with new keyword
        // 2nd way is to create from another object. Here we use response object to create JsonPath object

       JsonPath json = response.jsonPath(); // From now on, json object has all the data with the response object
//    assertEquals("first message does not match", "Eric", json.getString("firstname"));
//    assertEquals("last message does not match", "Jones", json.getString("lastname"));
//    assertEquals("depositpaid does not match", false, json.getBoolean("depositpaid"));
//        assertEquals("total price does not match", 195, json.getInt("totalprice"));
//        assertEquals("checkin does not match", "2018-01-02", json.getString("bookingdates.checkin"));
//        assertEquals("checkout does not match", "2021-07-05", json.get("bookingdates.checkout"));
        // get also works but it is always better to use specific method.

    //3rd way soft assertion (verification)
        /*
         soft assertion:
    1. Create SoftAssert object
    2. Do assertion
    3. Use softAssertAll() method
         */
//1. create SoftAssert object.
        SoftAssert soft = new SoftAssert();

//2. do assertion. This SoftAssert comes from test ng. The previous one was from junit.
soft.assertEquals(json.getString("firstname"), "Mary", "first names do not match");
// the sequence in test ng is different: actual, expected, message
soft.assertEquals(json.getString("lastname"), "Smith", "last names do not match");
soft.assertEquals(json.getInt("totalprice"), 551, "total prices do not match");
soft.assertEquals(json.getBoolean("depositpaid"), true, "deposit paid results do not match");

//3.
   soft.assertAll();// if we dont use assertAll() method, test passes but actually we do not test!!!
    }


}
