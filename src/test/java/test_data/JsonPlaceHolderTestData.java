package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {


    public Map<String, Object> expectedDataSetup() {
    Map<String, Object> expectedData = new HashMap<>();
		expectedData.put("userId",5);
    //expectedData.put("id", 201);
		expectedData.put("title","nema problema");
		expectedData.put("completed",true);
    return expectedData;
    }

}
