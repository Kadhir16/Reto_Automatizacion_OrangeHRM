@Sequential @AddCandidate
Feature: Recruitment Module
  Scenario: Add a new candidate in the Recruitment module
    Given the user is logged in and on the Recruitment module
    When the user clicks on the "+ Add" button
    And fills in the candidate's details:
      | First Name           | Brenda               |
      | Middle Name          | M.                   |
      | Last Name            | Vernal               |
      | Vacancy              | test                 |
      | Email                | john.doe@example.com |
      | Contact Number       | 1234567890           |
      | Resume               | resume.pdf           |
      | Keywords             | Java, Selenium       |
      | Date of Application  | 2024-10-12           |
      | Notes                | Notas escritas test  |
      | Consent to keep data | Yes                  |
    And clicks the "Save" button
    Then the candidate is added successfully
    Then the user is redirected to the Application Stage Information page




