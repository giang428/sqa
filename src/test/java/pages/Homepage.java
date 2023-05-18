package pages;

import io.opentelemetry.sdk.trace.internal.data.ExceptionEventData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DefaultUrl("https://ducvietco.com/")
public class Homepage extends PageObject {
    @FindBy(xpath = "//div[@class='main-header']/descendant::input[@name='q']")
    WebElementFacade searchBox;
    @FindBy(xpath = "//div[@class='main-header']/descendant::button[@type='submit']")
    WebElementFacade searchButton;
    @FindBy(className = "product-result")
    WebElementFacade searchResultMessage;

    @FindBy(className = "text-danger")
    WebElementFacade errorMessage;

    @FindBy(className = "search-result")
    WebElementFacade noSearchResult;

    @FindBy(xpath = "//div[contains(@class,'item-product clearfix')]")
    List<WebElementFacade> searchResult;

    @FindBy(id = "yw0")
    WebElementFacade paging;

    public String getMessage(WebElementFacade elementFacade){
        return elementFacade.waitUntilVisible().getText();
    }

    public String getErrorMessage(){
        return getMessage(errorMessage);
    }

    public String getNoSearchResultMessage(){
        return getMessage(noSearchResult);
    }

    public void enterKeywords(String keywords){
        searchBox.waitUntilVisible().sendKeys(keywords);
    }
    public void clickSearchButton(){
        searchButton.waitUntilClickable().click();
    }

    public boolean hasPagingButtons(){
        try {
            return paging.waitUntilPresent().isVisible();
        } catch (Exception e) {return false;}
    }

    public int getTotalSearchResultInMessage(){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(searchResultMessage.getText());
        if(m.find()) return Integer.parseInt(searchResultMessage.getText().substring(m.start(),m.end()));
        return 0;
    }

    public int getTotalSearchResult(){
        return searchResult.size();
    }

    public boolean checkSearchResult(String query) {
        String resultTitle = searchResult.get(0).find(By.xpath("/./descendant::h3")).getText().toLowerCase();
        return resultTitle.contains(query.toLowerCase());
    }


}
