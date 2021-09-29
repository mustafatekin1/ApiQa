package get_http_request_method;

import base_urls.DummyRestApiExampleBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get09P2 extends DummyRestApiExampleBaseUrl {
    /*
    when
        I send a request to the url http://dummy.restapiexample.com/api/v1/employees
    then
        status code should be 200
    And
        status line should be "HTTP/1.1 200 OK"
    and
        content type should be "application/json"
    and
     there should be this user:
        {
        "id": 18,
            "employee_name": "Gloria Little",
            "employee_salary": 237500,
            "employee_age": 59,
            "profile_image": ""
    },

   and () the user's salary should be more than Bradley's.
    {
        "id": 19,
            "employee_name": "Bradley Greer",
            "employee_salary": 132000,
            "employee_age": 41,
            "profile_image": ""
    }
     */
@Test
public void get09P2(){

    spec.pathParams("first","api", "second", "v1","third", "employee", "final", "18");
    Response response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
    response.prettyPrint();

    response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK").
    body("data.id", equalTo(18),
           "data.employee_name", equalTo("Gloria Little"),
            "data.employee_salary", equalTo(237500));
    // to compare the salaries.

    JsonPath json = response.jsonPath();
    int gloriaSalary = json.getInt("data.employee_salary");
    System.out.println("Gloria Little's Salary = " + gloriaSalary);

    // For the other person's salary we do the same things we have done so far for Gloria
    spec.pathParams("first","api", "second", "v1","third", "employee", "final", "19");
    Response response2 = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
    JsonPath json2 = response2.jsonPath();

    int bradleySalary = json2.getInt("data.employee_salary");
    System.out.println("Bradley Greer's Salary = " + bradleySalary);

    Assert.assertTrue(gloriaSalary>bradleySalary);
    // Note: if we get String we can change them to integer with the method Integer.parseInt(""). ex:
    System.out.println(Integer.parseInt("123")); // 123


}


}
