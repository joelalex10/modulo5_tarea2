Feature: Project API
  Scenario: Como usuario en todo.ly
            Quiero realizar un CRUD usando el API
            Para confirmar la utilizacion externa

    Given que tengo una cuenta en todo.ly
    # create
    When hago POST a URL https://todo.ly/api/projects.json con el body
    """
    {
      "Content":"Cucumber",
      "Icon":"1"
    }
    """
    Then el codigo de respuesta deberia ser 200
    And el nombre del projecto deberia ser Cucumber
    And obtengo el Id del projecto en ID
    # updated
    When hago PUT a URL https://todo.ly/api/projects/ID.json con el body
    """
    {
      "Content":"CucumberUpdated"
    }
    """
    Then el codigo de respuesta deberia ser 200
    And el nombre del projecto deberia ser CucumberUpdated
    # read
    When hago GET a URL https://todo.ly/api/projects/ID.json
    Then el codigo de respuesta deberia ser 200
    And el nombre del projecto deberia ser CucumberUpdated

    # delete
    When hago DELETE a URL https://todo.ly/api/projects/ID.json
    Then el codigo de respuesta deberia ser 200
    And el nombre del projecto deberia ser CucumberUpdated
    And el atributo Deleted deberia estar en true