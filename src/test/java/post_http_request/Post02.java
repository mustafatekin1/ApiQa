package post_http_request;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class Post02 {
	/*
	 We need both endpoint(url) and request body for post method.
	 post() can be dangerous for APIs (and other methods changing/adding data post, put, delete, patch)
	 Just the "get" method is safe because it just reads data.

	 Given ()
		https://jsonplaceholder.typicode.com/todos

	 and()  the following data
	 		{
			    "userId": 5,
			    "title": "nema problema",
			    "completed": true
			}

	 when()
	  		send the url and the data above

	 then()
	 		status code 201

	 and ()	content Type
	 			"application/json; charset=utf-8"

	 and()
	 			request body is as follows:
		 		{
				    "userId": 5,
				    "id": 55,
				    "title": "nema problema",
				    "completed": true
				}

		*/

    @Test
    public void post02 () {
        //1 set the url
        String url = "https://jsonplaceholder.typicode.com/todos";

        // 2 set the expected data
//		Map<String, Object> expectedData = new HashMap<>();
//		expectedData.put("userId", 5);
//		//expectedData.put("id", 201);
//		expectedData.put("title", "nema problema");
//		expectedData.put("completed", true);
////		expectedData.put("Status Code", 201);   // These must not be here. We have to move these 2 lines after sending the request
////		expectedData.put("Content Type", "application/json[]; charset=utf-8");
//
//		System.out.println(expectedData);

        //After moving the 2nd step to test_data package:

        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData ();
        Map<String, Object> expectedDataMap = expectedData.expectedDataSetup();

        // 3 Send the request (response and body) and get the response

        Response response = given().
                auth().basic("admin", "1234"). // we add also auth().basic("user name", "password") for testing purposes.
                        contentType(ContentType.JSON). // contentType maybe required for some APIs. if not used gives error or missing data
                        body(expectedDataMap).//we send expected data with the body() method.
                        when(). // we use when():  meaning that when we send the url and the request body
                        post(url); // we send the request with the "post()" method.

        response.prettyPrint();

        // We have to move these 2 lines after sending the request; otherwise we can not actually test status code :)
        // If they were before the request, we send those data.
        expectedDataMap.put("Status Code", 201);
        expectedDataMap.put("Content Type", "application/json; charset=utf-8");

        //4 do the assertions
        // We first change the json data in response body to Java object by using Map:
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedDataMap.get("Status Code"), response.getStatusCode());
        assertEquals(expectedDataMap.get("Content Type"), response.contentType());

        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        //assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));

    }

}
