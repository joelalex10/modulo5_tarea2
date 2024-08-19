package basicRestAssured;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssured2 {
    /*
    * given() --> headers/ body / params / logs
    * when() --> method > post put delete get (url)
    * then() --> verificacion -> acceso al response
    *            manipular respuesta
    *            extraer datos
    *            verificacion (code /msg/body/ time)
    * */

    // https://www.liquid-technologies.com/online-json-to-schema-converter
    @Test
    public void schemaValidationTest(){

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.newBuilder()
                        .setValidationConfiguration(
                                         ValidationConfiguration.newBuilder()
                                        .setDefaultVersion(SchemaVersion.DRAFTV4)
                                       .freeze()
                                  )
                        .freeze();

       // create project
        given()
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
                .post("https://todo.ly/api/projects.json").
        then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("expectedSchema2.json")
                        .using( schemaFactory  )
                     )
                .body("Content",equalTo("RestAssured1"))
                .log()
                .all();


    }
}
