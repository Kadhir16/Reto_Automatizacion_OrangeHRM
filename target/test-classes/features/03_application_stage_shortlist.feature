@Sequential
Feature: Application Stage
  As a recruiter
  I want to validate the candidate's application status
  And mark them as shortlisted if applicable

  Scenario: Shortlist a candidate in the Application Stage
    Given the candidate is on the Application Stage
    And the candidate's application status is "Application Initiated"
    When the user clicks on the "Shortlist" button
    And navigates to the Shortlist Candidate
    And clicks the "Save" button
    Then the candidate is shortlisted successfully



