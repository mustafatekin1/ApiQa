package post_http_request;

import base_urls.AgroMonitoringBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AgroMonitoringTestData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post03 extends AgroMonitoringBaseUrl {
    /*
		 	 Given
        "http://api.agromonitoring.com/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0"
                                 {
                                           "name":"Polygon Sample",
                                           "geo_json":{
                                              "type":"Feature",
                                              "properties":{},
                                              "geometry":{
                                                 "type":"Polygon",
                                                 "coordinates":[
                                                    [
                                                       [-121.1958,37.6683],
                                                       [-121.1779,37.6687],
                                                       [-121.1773,37.6792],
                                                       [-121.1958,37.6792],
                                                       [-121.1958,37.6683]
                                                    ]
                                                 ]
                                              }
                                           }
                                        }
        When
         I send POST Request to the Url
    Then
        Assert Status Code (201)
        And Response Body should be like {
                                            "id": "5fd8c383714b523b2ce1f154",
                                            "geo_json": {
                                                "geometry": {
                                                    "coordinates": [
                                                        [
                                                            [
                                                                -121.1958,
                                                                37.6683
                                                            ],
                                                            [
                                                                -121.1779,
                                                                37.6687
                                                            ],
                                                            [
                                                                -121.1773,
                                                                37.6792
                                                            ],
                                                            [
                                                                -121.1958,
                                                                37.6792
                                                            ],
                                                            [
                                                                -121.1958,
                                                                37.6683
                                                            ]
                                                        ]
                                                    ],
                                                    "type": "Polygon"
                                                },
                                                "type": "Feature",
                                                "properties": {
                                                }
                                            },
                                            "name": "Polygon Sample",
                                            "center": [
                                                -121.1867,
                                                37.67385
                                            ],
                                            "area": 190.9484,
                                            "user_id": "5fd8c02a3da20c000759e0f8",
                                            "created_at": 1608041347
                                        }

		*/

@Test
    public void post03(){
   //1. set the url
    spec.pathParams("first","agro", "second", 1.0, "third", "polygons");
    spec.queryParam("appid", "f4ffe3b2ef1fcb3600ab1d7fbc88c2f0");
// 2. set the expected data in the test_data package then create object from that class and call the method
    AgroMonitoringTestData requestBody = new AgroMonitoringTestData();
    Map<String, Object> requestBodyMap = requestBody.requestBodySetup();
//3. send url and body and get the response
    Response response = given().spec(spec).contentType(ContentType.JSON).
            body(requestBodyMap).
            when().post("/{first}/{second}/{third}");

    response.prettyPrint();
    // we add the other json data into the expDataMap
    requestBodyMap.put("area", 190.9484);
    requestBodyMap.put("user_id", "5fd8c02a3da20c000759e0f8");
    requestBodyMap.put("created_at", 1632942370);


// 4. Do the assertion
    Map<String, Object> responseBody = response.as(HashMap.class);// we use GSON to convert to a map.
    System.out.println(responseBody);

    assertEquals(requestBodyMap.get("area"), responseBody.get("area"));
    assertEquals(requestBodyMap.get("name"), responseBody.get("name"));

    assertEquals(requestBody.geometrySetup().get("type"),// I have used the method to reach "type":"Polygon"
            ((Map)((Map)responseBody.get("geo_json")).get("geometry")).get("type"));

    System.out.println(((Map)((Map)responseBody.get("geo_json")).get("geometry")).get("coordinates").toString().substring(25,34));
    System.out.println(String.valueOf(requestBody.coordinates[0][1][0]));

assertEquals(String.valueOf(requestBody.coordinates[0][1][0]), // we reach the coordinate by using indexes then to string
        ((Map)((Map)responseBody.get("geo_json")).get("geometry")).get("coordinates").toString().substring(25,34));

}



}
