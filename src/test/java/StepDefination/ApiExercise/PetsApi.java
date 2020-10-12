package StepDefination.ApiExercise;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.util.HashMap;
import java.util.List;

/**
 * Created by atariq on 12/10/2020.
 */
public class PetsApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";
    private Response response;
    private String bodyData;
    private JsonPath jsonPathEvaluator;
    private String errorMessage;
    private List<HashMap> dataList;

    @Given("^I submit the GET request to get all pets with status \"([^\"]*)\"$")
    public void i_submit_the_GET_request_to_get_all_pets_with_status(String apiName) throws Throwable {
        RestAssured.baseURI = BASE_URL;
        System.out.println(apiName + " Api Url " + BASE_URL);
        response = getAllPets("available");
    }

    @Then("^\"([^\"]*)\" Api should return valid status code$")
    public void api_should_return_valid_status_code(String apiName) throws Throwable {

        System.out.println(response.getStatusCode());
        errorMessage = apiName + " Api response code is " + response.getStatusCode();
        Assert.assertEquals(errorMessage, 200, response.getStatusCode());
    }

    @Then("^I should receive correct header content$")
    public void i_should_recieve_correct_header_content() throws Throwable {
        String contentType = response.header("Content-Type");
        errorMessage = " Api response content-type is" + contentType + " While expected is : application/json ";
        Assert.assertEquals(errorMessage, contentType, "application/json");
    }

    @Then("^I should receive valid response$")
    public void i_should_receive_vaid_response() throws Throwable {
        bodyData = response.getBody().asString();
        jsonPathEvaluator = new JsonPath(bodyData);
        dataList = jsonPathEvaluator.getList("$");
        for (HashMap obj: dataList) {
            errorMessage = obj.toString() + " does not contain the required key : status ";
            Assert.assertEquals(errorMessage, true, obj.containsKey("status"));
        }
    }

    @Then("^I display list of all pets with name \"([^\"]*)\" and status is \"([^\"]*)\"$")
    public void i_display_list_of_all_pets_with_name_and_status_is(String name, String status) throws Throwable {
        int i = 0;
        for (HashMap obj: dataList) {
            System.out.println(obj.get("status") + "  -  " + obj.get("name"));
            if (obj.get("status").equals(status) && obj.get("name").equals(name)) i++;
        }
        System.out.println("Total pets with name " + name + " and status is " + status + " are " + i);
    }

    public static Response getAllPets(String petStatus) {
        RequestSpecification httpRequest = RestAssured.given();
        Response apiResponse = httpRequest.queryParam("status", petStatus).get("/findByStatus");
        return apiResponse;
    }
}