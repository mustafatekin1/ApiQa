package post_http_request;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class Post01 {


	/*
	 We need both endpoint(url) and request body for post method.

	 Positive Scenario:
	 Given () Bir GET Request asagida verilen Endpoint'e yollanir
	        https://restful-booker.herokuapp.com/booking

	 and()  the following data
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
	 when()
	  send the url and the data above

	 then()
	 	status code 200
	 and()
	 	request body is as follows:
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
    public void post01() {
        //1 set the url
        String url = "https://restful-booker.herokuapp.com/booking";
        //2 set the expected data
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2013-02-23");
        bookingDates.put("checkout", "2014-10-23");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Ali");
        expectedData.put("lastname", "Ak");
        expectedData.put("totalprice", 10000);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates);
        expectedData.put("additionalneeds", "Lunch");

        System.out.println(expectedData);

        //3 send the request (url and data in this case because we use "post" method) and get the response
        Response response = given().contentType(ContentType.JSON).body(expectedData).when().post("https://restful-booker.herokuapp.com/booking");
        // some APIs require the content type!
        response.prettyPrint();

        //4 do the assertions
        Map<String, Object> actualData = response.as(HashMap.class);
        // no need to put to actualData since we use all the data in the response object
        System.out.println(actualData);

        assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        //because the actualData is as {booking={firstname=Ali, lastname= ... },
        //(teacher's way) by using explicit casting to (Map)
        assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedData.get("bookingdates"), ((Map)actualData.get("booking")).get("bookingdates"));

        // it is better to compare "checkin". The error will be more detailed.
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                ((Map)(((Map)actualData.get("booking")).get("bookingdates"))).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),
                ((Map)(((Map)actualData.get("booking")).get("bookingdates"))).get("checkout"));
    }

}
