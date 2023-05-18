Feature: Search for product
  @SFP01
  Scenario Outline: Search with queries which has less than 2 characters
    Given User is on main page
    When User search for queries like "<query>"
    Then The page should display error message "Từ khóa tìm kiếm không đúng. Từ khóa phải có ít nhất 2 ký tự."
    Examples:
      | query |
      |       |
      | a     |
      | b     |
      | .     |
      | #     |
  @SFP02
  Scenario Outline: Search with invalid queries
    Given User is on main page
    When User search for queries like "<query>"
    Then The page should display message "Có 0 kết quả tìm kiếm cho \"<query>\""
    Examples:
      | query                              |
      | %^$%^@&%^#^&                       |
      | .   .. . . . . . . .    . . .. . . |
      | hq289h978dynoy8ny8ryw87ry3o2       |
  @SFP03
  Scenario Outline: Search with valid queries
    Given User is on main page
    When User search for queries like "<query>"
    Then The result page should display products that satisfy search queries "<query>"
    Examples:
      | query   |
      | asus    |
      | dell    |
      | legion  |
      | nitro 5 |
  @SFP04
  Scenario: Check search result in result page
    Given Search result has less or equals than 24 products
    Then Result page should display exact number of products total
    But Result page should not display paging buttons
  @SFP05
  Scenario: Check search result in result page with paging buttons
    Given Search result has more than 24 products
    Then Result page should display first 24 products in total
    And Result page should display paging buttons




