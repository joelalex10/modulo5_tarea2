package basicRestAssured;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssured {
    /*
    * given() --> headers/ body / params / logs
    * when() --> method > post put delete get (url)
    * then() --> verificacion -> acceso al response
    *            manipular respuesta
    *            extraer datos
    *            verificacion (code /msg/body/ time)
    * */
    @Test
    public void someThing(){
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
                .body("Content",equalTo("RestAssured1"))
                .log()
                .all();


    }


    @Test
    public void createProjectWithExternalFile(){

        String ruta = new File("").getAbsolutePath()+"/src/test/resources/body.json";
        // create project
        given()
                .auth()
                .preemptive()
                .basic("catov5@catov5.com", "12345")
                .body(new File(ruta))
                .log()
                .all().
        when()
                .post("https://todo.ly/api/projects.json").
        then()
                .log()
                .all();
    }

    @Test
    public void createProjectWithJsonObject(){
        JSONObject body = new JSONObject();
        body.put("Content","EynarJSON");
        body.put("Icon",1);
        // create project
        given()
                .auth()
                .preemptive()
                .basic("catov5@catov5.com", "12345")
                .body(body.toString())
                .log()
                .all().
        when()
                .post("https://todo.ly/api/projects.json").
        then()
                .log()
                .all();
    }




}
