package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestStep {
    Response response;
    int idProject;

    @Given("que tengo una cuenta en {}")
    public void queTengoUnaCuenta(String app) {
    }

    @When("hago POST a URL {} con el body")
    public void hagoPOSTAURLHttpsTodoLyApiProjectsJsonConElBody(String url,String body) {
        response = given()
                    .auth()
                    .preemptive()
                    .basic("catov5@catov5.com", "12345")
                    .body(body)
                    .log()
                    .all().
                when()
                    .post(url);

        response.then().log().all();
    }

    @Then("el codigo de respuesta deberia ser {int}")
    public void elCodigoDeRespuestaDeberiaSer(int expectedResult) {
        response.then()
                .statusCode(expectedResult);
    }

    @And("el nombre del projecto deberia ser {}")
    public void elNombreDelProjectoDeberiaSerCucumber(String expectedName) {
        response.then()
                .body("Content",equalTo(expectedName));
    }

    @And("obtengo el Id del projecto en ID")
    public void obtengoElIdDelProjectoEnID() {
        idProject = response.then().extract().path("Id");
    }

    @When("hago PUT a URL {} con el body")
    public void hagoPUTAURLHttpsTodoLyApiProjectsIDJsonConElBody(String url, String body) {
        response =  given()
                    .auth()
                    .preemptive()
                    .basic("catov5@catov5.com", "12345")
                    .body(body)
                    .log()
                    .all().
                when()
                     .put(url.replace("ID",idProject+""));
        response .then().log().all();
    }

    @When("hago GET a URL {}")
    public void hagoGETAURLHttpsTodoLyApiProjectsIDJson(String url) {
        response = given()
                        .auth()
                        .preemptive()
                        .basic("catov5@catov5.com", "12345")
                        .log()
                        .all().
                when()
                        .get(url.replace("ID",idProject+""));
        response .then().log().all();
    }

    @When("hago DELETE a URL {}")
    public void hagoDELETEAURLHttpsTodoLyApiProjectsIDJson(String url) {
        response = given()
                    .auth()
                    .preemptive()
                    .basic("catov5@catov5.com", "12345")
                    .log()
                    .all().
                when()
                 .delete(url.replace("ID",idProject+""));
        response .then().log().all();
    }

    @And("el atributo Deleted deberia estar en {}")
    public void elAtributoDeletedDeberiaEstarEnTrue(String expectedResult) {
        response.then().body("Deleted",equalTo(Boolean.valueOf(expectedResult)));
    }
}
