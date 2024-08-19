Feature: Project API
  Scenario: CRUD Project

    Given I am authenticated with jquispe3600@gmail.com and 123456
    When I send POST /api/projects.json with body
    """
    {
      "Content":"Cucumber",
      "Icon":"1"
    }
    """
    Then response code should be 200
    And the attribute string "Content" should be "Cucumber"
    And save "Id" in the variable "Id_Project"
    When I send PUT /api/projects/Id_Project.json with body
    """
    {
      "Content":"CucumberUpdate",
    }
    """
    Then response code should be 200
    And the attribute string "Content" should be "CucumberUpdate"
    When I send GET /api/projects/Id_Project.json with body
    """
    """
    Then response code should be 200
    And the attribute string "Content" should be "CucumberUpdate"
    When I send DELETE /api/projects/Id_Project.json with body
    """
    """
    Then response code should be 200
    And the attribute string "Content" should be "CucumberUpdate"
    And the attribute boolean "Deleted" should be "true"