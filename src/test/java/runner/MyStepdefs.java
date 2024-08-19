package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {

    /*
    *  expresiones regulares
    *  expresiones cucumber
    *  {} podemos utilizarlo para remplazar cualquier valor
    *  {string} podemos utilizarlo para remplazar un string entre comillas dobles
    *    "valor"  "valor1  valor2"
    *  {word} podemos usarlo cuando solamente tenemos 1 palabra
    *  {int} podemos enviarle un valor numerico
    *  {float} podemos enviarle un valor flotante
    * */



    @Given("Como usuario {}")
    public void comoUsuarioAdmin(String tipoDeUsuario) {

    }
    @When("ingreso mi usuario: {string} en la control user")
    public void ingresoMiUsuarioEynarEnLaControlUser(String user) {
    }

    @And("ingreso mi pasword: {int} en el control password")
    public void ingresoMiPaswordEnElControlPassword(int password) {
    }

    @And("realizo click en el boton {word}")
    public void realizoClickEnElBotonLogin(String nombreBoton) {
    }

    @Then("deberia iniciar sesion")
    public void deberiaIniciarSesion() {
    }
}
