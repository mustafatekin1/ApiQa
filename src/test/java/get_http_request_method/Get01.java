package get_http_request_method;

import io.restassured.response.Response;
import org.junit.Test;
// junit is for testing purposes in Java

import static io.restassured.RestAssured.*;
//we cleared .given and type .* to get all library

public class Get01 {
/*
In API testing we will use Gherkin Language.
We use some keywords:
-	given, when, then, and.
We will form test cases with these four keywords.
-	given : prerequisites
-	when : is used to declare actions
-	then : is used for outputs
-	and : is used to combine multiple given, when or then.

example:
given
    https:...
when
    user send a GET request to the url
then
    HTTP status code should be 200
and
    content type should be JSON
and
    status line should be HTTP/1.1 200 OK (note that this is case sensitive. http failed)
* */

/*
* to automate test case
* we have to create a method for every test case
* */

    // we use @annotation instead of `main method`
    @Test
    public void get01 ()
    {
    /* There are 4 steps in api testing
      1. Set the url (endpoint).
      2. Set the expected data
      3. Type the automation script to send GET request
      4. Do assertion: status code 200?, data format (content type) JSON?, status line http/1.1 200?


      */
// 1. set the url:
    String url = "https://restful-booker.herokuapp.com/booking/3";
// 2. Set the expected data

// 3. Type the automation script to send GET request
    Response response = given().when().get(url);
    //Response is a class. And we create response object from that class and we can use the methpds of the class
    response.prettyPrint(); // this is to print the response
        // we get the same output from Postman also. So no need to postman. We can do it in Intellij or Eclipse also
        // we prefer intellij, eclipse rather than postman

// 4. Do assertion: status code 200?, data format (content type) JSON?, status line http/1.1 200?
 // it was seen clearly in Postman. Instead we use reponse.then()... in Java
    response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
/* If there are multiple errors on assertion. Please remember that the execution stops upon the first error.
    Other errors can not be identified.
    This assertion is called "hard assertion"
    We have also "Soft Assertion (verification)": All codes will be executed. All errors will be reported.
    Hard assertion is much more common
 */

// Print the status code on the console:
        System.out.println(response.getStatusCode() + " - " +
                response.getStatusLine() + "-" +response.getContentType());
        System.out.println("execution time:" + response.getTime());
        System.out.println("headers : \n " + response.getHeaders());

        System.out.println("lets print specific header example: server => " + response.getHeader("server"));
        // for specific header


    }


}
