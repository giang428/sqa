package definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.Homepage;

public class SearchStepDefs {
    Homepage homepage;

    @Given("User is on main page")
    public void userIsOnMainPage() {
        homepage.open();
    }

    @When("User search for queries like {string}")
    public void userSearchForQueriesLikeQuery(String arg0) {
        homepage.enterKeywords(arg0);
        homepage.clickSearchButton();
    }

    @Then("The page should display error message {string}")
    public void thePageShouldDisplayErrorMessage(String arg0) {
        Assert.assertEquals(
                arg0, homepage.getErrorMessage());
    }

    @Then("The page should display message {string}")
    public void thePageShouldDisplayMessage(String arg0) {
        Assert.assertEquals(arg0,homepage.getNoSearchResultMessage());
    }

    @Given("Search result has less or equals than {int} products")
    public void searchResultPageHasLessThanOrEqualsProducts(int arg0) {
        userIsOnMainPage();
        userSearchForQueriesLikeQuery("acer");
        int totalResult = homepage.getTotalSearchResultInMessage();
        Assert.assertTrue(totalResult <= arg0);
    }

    @Then("Result page should display exact number of products total")
    public void resultPageShouldDisplayExactNumberOfProductsTotal() {
        int totalP = homepage.getTotalSearchResult();
        int totalPInMsg = homepage.getTotalSearchResultInMessage();
        Assert.assertEquals(totalP,totalPInMsg);
    }

    @But("Result page should not display paging buttons")
    public void resultPageShouldNotDisplayPagingButtons() {
        Assert.assertFalse(homepage.hasPagingButtons());
    }

    @Given("Search result has more than {int} products")
    public void searchResultPageHasMoreThanProducts(int arg0) {
        userIsOnMainPage();
        userSearchForQueriesLikeQuery("dell");
        int totalResult = homepage.getTotalSearchResultInMessage();
        Assert.assertTrue(totalResult > arg0);
    }

    @Then("Result page should display first {int} products in total")
    public void resultPageShouldDisplayFirstProductsInTotal(int arg0) {
        int total = homepage.getTotalSearchResult();
        Assert.assertEquals(arg0,homepage.getTotalSearchResult());
    }

    @And("Result page should display paging buttons")
    public void resultPageShouldDisplayPagingButtons() {
        Assert.assertTrue(homepage.hasPagingButtons());
    }

    @Then("The result page should display products that satisfy search queries {string}")
    public void theResultPageShouldDisplayProductsThatSatisfySearchQueries(String arg0) {
        Assert.assertTrue(homepage.checkSearchResult(arg0));
    }
}
