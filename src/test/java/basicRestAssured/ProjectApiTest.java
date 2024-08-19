package basicRestAssured;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProjectApiTest {
    /*
    * given() --> headers/ body / params / logs
    * when() --> method > post put delete get (url)
    * then() --> verificacion -> acceso al response
    *            manipular respuesta
    *            extraer datos
    *            verificacion (code /msg/body/ time)
    * */
    @Test
    public void verifyCreateReadUpdateDelete(){

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.newBuilder()
                        .setValidationConfiguration(
                                         ValidationConfiguration.newBuilder()
                                        .setDefaultVersion(SchemaVersion.DRAFTV4)
                                       .freeze()
                                  )
                        .freeze();

       // create project
        Response response = given()
                                .auth()
                                .preemptive()
                                .basic("catov5@catov5.com", "12345")
                                .body("{\n" +
                                        "   \"Content\":\"RestAssured1\",\n" +
                                        "   \"Icon\":\"1\"\n" +
                                        "}")
                                .log()
                                .all().
                             when()
                                .post("https://todo.ly/api/projects.json");

        response.then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("expectedSchema2.json")
                        .using( schemaFactory  )
                     )
                .body("Content",equalTo("RestAssured1"))
                .log()
                .all();

        int idProject = response.then().extract().path("Id");

        // update
        given()
                .auth()
                .preemptive()
                .basic("catov5@catov5.com", "12345")
                .body("{\n" +
                        "   \"Content\":\"UPDATED\",\n" +
                        "   \"Icon\":\"4\"\n" +
                        "}")
                .log()
                .all().
       when()
                .put("https://todo.ly/api/projects/"+idProject+".json")
       .then()
                .statusCode(200)
                .body("Content",equalTo("UPDATED"))
                .body("Icon",equalTo(4))
                .log()
                .all();
        // read
        given()
                .auth()
                .preemptive()
                .basic("catov5@catov5.com", "12345")
                .log()
                .all().
       when()
                .get("https://todo.ly/api/projects/"+idProject+".json")
       .then()
                .statusCode(200)
                .body("Content",equalTo("UPDATED"))
                .log()
                .all();
        // delete
        given()
                .auth()
                .preemptive()
                .basic("catov5@catov5.com", "12345")
                .log()
                .all().
        when()
                .delete("https://todo.ly/api/projects/"+idProject+".json")
        .then()
                .statusCode(200)
                .body("Content",equalTo("UPDATED"))
                .body("Deleted",equalTo(true))
                .log()
                .all();

    }
}
