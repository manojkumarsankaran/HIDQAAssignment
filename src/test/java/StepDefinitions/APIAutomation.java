package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class APIAutomation {
	String response;

	@Given("user sends GET request")
	public void user_sends_get_request() {
		response = given().get("https://coinmap.org/api/v1/venues/").then().extract().response().asString();
	}

	@Then("verify the count of {string}")
	public void verify_the_count_of(String string) {
		String[] arr = string.split(",");
		JsonPath js = new JsonPath(response);
		for (int i = 0; i < arr.length; i++) {
			int venueCount = js.getInt("venues.size()");
			int count = 0;
			for (int j = 0; j < venueCount; j++) {
				String resp = js.getString("venues[" + j + "].category");
				if (resp.equals(arr[i])) {
					count = count + 1;
				}
			}
			System.out.println(arr[i] + " count is " + count);
		}
	}

	@Then("verify the name of {string} and {string}")
	public void verify_the_name_of_and(String food, String geolocations) {

		ArrayList<String> foodList = new ArrayList<String>();
		ArrayList<String> geoLocationsList = new ArrayList<String>();
		JsonPath js = new JsonPath(response);
		int venueCount = js.getInt("venues.size()");
		for (int j = 0; j < venueCount; j++) {
			String category = js.getString("venues[" + j + "].category");
			if (category.equals(food)) {
				String name = js.getString("venues[" + j + "].name");
				foodList.add(name);
				String geoloc = js.getString("venues[" + j + "].geolocation_degrees");
				geoLocationsList.add(geoloc);
				System.out.println(name);
				System.out.println(geoloc);
				System.out.println();
			}
		}
		System.out.println(foodList);
		System.out.println(geoLocationsList);
		System.out.println(foodList.size());
		System.out.println(geoLocationsList.size());
	}

}
