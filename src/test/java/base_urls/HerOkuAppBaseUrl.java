package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppBaseUrl {
    // create object with the data type RequestSpecification  (RequestSpecification is interface)
   protected RequestSpecification spec;

    //Before means the method will be executed before other test methods!!!
   @Before
   public void setup() {
       spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
   }
}
