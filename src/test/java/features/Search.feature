Feature: Verify search functionality

  @search
  Scenario: Search for a keyword
    Given I navigate to Search at "https://www.lely.com/en"
    And I accept cookies if present
    When I click the search button
    And I enter the search term "happy"
    And I submit the search
    Then I should see search results containing "happy"

  @techDocs
  Scenario: View and download document from TechDocs
    Given I navigate to TechDocs at "https://www.lely.com/techdocs/"
    When I select "LUNA" from the dropdown
    Then I should see the catalogs
    When I view a document
    Then The document should open in a new tab
    When I return to the previous tab
    And I download the document
    Then I should verify the document is downloaded
