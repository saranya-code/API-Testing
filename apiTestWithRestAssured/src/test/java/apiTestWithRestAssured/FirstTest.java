package apiTestWithRestAssured;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstTest {
	
	
	
	@Test(enabled = false)
	public void getRequest()
	{
		String url = "https://reqres/api/users?page=2";
		Response response = RestAssured.get(url);
		
		Assert.assertEquals(response.getStatusCode(), 200,"Response code  mismatch");
		
		int total_pages = response.jsonPath().get("total_pages");
			
		Assert.assertEquals(total_pages, 2,"total_pages value mismatch");
		
	}

	
	@Test(enabled = true)
	public void postApiRequest()
	{
		String url = "https://reqres.in/api/users";
		String contentType = "applicaation/json";
		String bodyData ="{\n"
				+ "    \"name\": \"morpheus\",\n"
				+ "    \"job\": \"leader\"\n"
				+ "}";
		Response response = RestAssured.given().contentType(contentType).body(bodyData).when().post(url).then().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 201,"Response code  mismatch");
		
		String name = response.jsonPath().get("name");
		
		Assert.assertEquals(name,"morpheus","name mismatch");
		
	}
	
	
}
