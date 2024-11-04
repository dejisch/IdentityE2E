Feature: Car Valuation

  Scenario Outline: Cars valuation based on extracted vehicle registration numbers
    Given I have the input file "<Input File>" and the output file "<Output File>"
    When I fetch vehicle details for each registration from the car valuation website
    Then the fetched details should match with the output file
    Examples:
    |Input File    |Output File                             |
    |car_input.txt |car_output.txt                          |
    |car_input2.txt|car_output2.txt                         |
    |car_input.txt |car_output_with_right_expectedResult.txt|