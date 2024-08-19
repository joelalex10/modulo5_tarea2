Feature: Login

  Scenario: Como usario admin
            Quiero ingresar user y password
            Para poder iniciar session

     Given Como usuario Admin
     When ingreso mi usuario: "abc def" en la control user
     And ingreso mi pasword: 12345 en el control password
     And realizo click en el boton login
     Then deberia iniciar sesion
