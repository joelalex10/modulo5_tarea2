package runner;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import util.ConfigurationEnv;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class RequestStepClean {
    Response response;
    RequestInfo info = new RequestInfo();
    Map<String,String> varMap=  new HashMap<>();

    @Given("I am authenticated with {} and {}")
    public void iAmAuthenticatedWithCredentials(String userName, String password) {
        String credential = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
        info.setHeaders("Authorization", "Basic " + credential);
        info.setUrl(ConfigurationEnv.host +replaceValueVariable("/api/authentication/token.json"));
        response = FactoryRequest.make("GET").send(info);
        String token = response.then().extract().path("TokenString");
        varMap.put("Token", token);
        info.removeHeader("Authorization");
        info.setHeaders("Token", token);
    }
    @When("I send {word} {} with body")
    public void iSendPOSTApiProjectsJsonWithBody(String method,String url, String body) {
        info.setUrl(ConfigurationEnv.host +replaceValueVariable(url)).setBody(body);
        response = FactoryRequest.make(method).send(info);
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int expectedResult) {
        response.then().statusCode(expectedResult);
    }

    @And("the attribute {word} {string} should be {string}")
    public void theAttributeShouldBe(String type,String attribute, String expectedResult) {
        if (type.contains("boolean"))
            response.then().body(attribute,equalTo(Boolean.valueOf(expectedResult)));
        else
            response.then().body(attribute,equalTo(expectedResult));
        if (type.contains("string") && type.equals("UserEmail")){
            response.then().body(attribute,equalTo(Boolean.valueOf(expectedResult)));
            System. out.println("EL TOKEN ES: "+response.then().extract().path("TokenString"));
            info.setHeaders("Token",response.then().extract().path("TokenString"));
            info.removeHeader("Authorization");
        }
    }

    @And("save {string} in the variable {string}")
    public void saveInTheVariable(String attribute, String variable) {
        varMap.put(variable,response.then().extract().path(attribute)+"");
    }

    private String replaceValueVariable(String value){
        for (String key:varMap.keySet()){
            value= value.replace(key,varMap.get(key));
        }
        return value;
    }

}
