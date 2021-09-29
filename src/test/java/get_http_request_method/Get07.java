package get_http_request_method;

import base_urls.JsonPlaceHolder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolder {
    /* Video: 10 part 1; 18.09.2021
test case:
 GIVEN
   https://jsonplaceholder.typicode.com/todos
 WHEN
    send a GET request to the url
  THEN
    http status code should be 200
     AND
       print all ids greater than 190
       - assert that there are 10 ids greater than 190.
     AND
        print all userIds less than 5
            assert that the maximum userId less than 5 is 4
     AND
        print all titles whose ids are less than 5.
        -Assert that "delectus aut autem" is among them
*/

    @Test
    public void get07() {
        spec.pathParams("first","todos");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        //print all ids greater than 190
        // assert that there are 10 ids greater than 190.
        JsonPath json = response.jsonPath();
        List <Integer> idList = json.getList("findAll{it.id>190}.id"); // Groovy Language
        // it.id means "id from this Json data". It is like the syntax this.name (name from this class)
        //it.id>190 means "the json data greater than 190"
        //findAll{it.id>190}.id means "find just id not the whole json data with id greater than 190
        System.out.println(idList); //[191, 192, 193, 194, 195, 196, 197, 198, 199, 200]


       assertEquals("The number of ids is not 10",10, idList.size());

//        print all userIds less than 5
//        assert that the maximum userId less than 5 is 4
        List<Integer> List2 = json.getList("findAll{it.userId<5}.userId");
        System.out.println(List2);//
        Collections.sort(List2);// our list is sorted in ascending order
        assertEquals((Integer)4, List2.get(List2.size()-1));//
//assertEquals(4, List2.get(List2.size()-1); complains because the method requires (object, object)
// That's why we explicit cast the Integer




//        print all titles whose ids are less than 5.
//        Assert that "delectus aut autem" is among them
        List <String> titleList = json.getList("findAll{it.id<5}.title");
        System.out.println(titleList);

        System.out.println(titleList.contains("delectus aut autem")); //true
        //1st way
        assertTrue(titleList.contains("delectus aut autem"));

        //2nd way with Lambda
        System.out.println(titleList.stream().anyMatch(t->t.equals("delectus aut autem"))); // true
        assertTrue(titleList.stream().anyMatch(t->t.equals("delectus aut autem")));
    }


}
