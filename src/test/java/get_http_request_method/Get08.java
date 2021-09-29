package get_http_request_method;

import base_urls.JsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonPlaceHolder {
/* Video: 10 part 1, 2nd class; 18.09.2021
The biggest challenge in API testing is data types.
- API has json, xml as data types
- Java has Object and primitives as data types
 For communication between Java and API, we must have same data types. That's why we convert data types:
*** De-serialisation: converting json data type to object (String, non-primitive data types, maps, List ... ).
*** Serialisation: converting from Java object to json
We have 2 options for this De-serialisation and Serialisation:
- GSON (by Google )
- Object mapper (more popular)


ex. test case:
 GIVEN
   https://jsonplaceholder.typicode.com/todos/2
 WHEN
    send a GET request to the url
  THEN
    http status code should be 200
     AND
       completed is false
       userId is 1
       title is "quis ut nam facilis et officia qui"
       header "Via" is "1.1 vegur"
       header "Server" is "cloudflare"

       the data is as follows:
          {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
          }

*/
@Test
    public void get08(){
    //1 set the url:
     spec.pathParams("first", "todos", "second", "2");
   //2. set the expected data
    Map <String, Object> expectedData = new HashMap<>();

    expectedData.put("userId", 1);
    expectedData.put("id", 2);
    expectedData.put("title", "quis ut nam facilis et officia qui");
    expectedData.put("completed", false);
    expectedData.put("Status Code", 200);
    expectedData.put("Via", "1.1 vegur");
    expectedData.put("Server", "cloudflare");

    System.out.println(expectedData);// {id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}

   //3. send the request and get the response
     Response response =  given().spec(spec).when().get("/{first}/{second}");
     response.prettyPrint();
    Map <String, Object> actualData = response.as(HashMap.class); // as.() method comes from GSON
                        // we convert json data in the response to map (Java) with as method from GSON
    actualData.put("userId", 1);
    actualData.put("id", 2);
    actualData.put("title", "quis ut nam facilis et officia qui");
    actualData.put("completed", false);
    System.out.println(actualData);

   //4. do the assertions
    // 1st way (new way by using expected data)
    assertEquals(expectedData.get("userId"), actualData.get("userId"));
    assertEquals(expectedData.get("id"), actualData.get("id"));
    assertEquals(expectedData.get("title"), actualData.get("title"));
    assertEquals(expectedData.get("completed"),actualData.get("completed"));

    assertEquals(expectedData.get("Status Code"),response.getStatusCode());
    assertEquals(expectedData.get("Via"),response.getHeader("Via"));
    assertEquals(expectedData.get("Server"),response.getHeader("Server"));


    // 2nd way: the way we have done so far.
   response.then().assertThat().statusCode(200).
           body("userId", equalTo(1),
                   "completed", equalTo(false),
                   "title", equalTo("quis ut nam facilis et officia qui"));
//   assertTrue(response.header("Via", equalTo("1.1 Vegur"),
//                   "Server", equalTo("cloudflare")));

    }



}
