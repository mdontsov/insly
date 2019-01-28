@start @end
  Feature: Homepage check

    Scenario: Sign in for demo
      Given homepage is opened
      And required elements are present
      Then required fields are filled in
      Then administrator account details are filled in
      Then terms and conditions are checked
      And signup is completed