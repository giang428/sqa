import io.cucumber.junit.CucumberSerenityRunner;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DemoTest {
    @Test
    public void test(){
        Page page = new Page();
        page.init();
        System.out.println(page.msg());
    }
    @DefaultUrl("https://cvgnhamau.net/")
    class Page extends PageObject {
        public void init(){
            open();
            find(By.xpath("//a[@class='nav-link px-2']")).click();
            find(By.id("username")).sendKeys("0795254116");
            find(By.id("password")).sendKeys("Admin@123");
            find(By.id("btnLogin")).click();
            getDriver().navigate().to("https://cvgnhamau.net/nha-mau-yeu-thich");
        }
        public String msg(){
            find(By.xpath("//span[text()='Bỏ Thích']")).waitUntilVisible().click();
            System.out.println("Clicked");
            return find(By.xpath("//div[@id='list-toast']/div[@id='toast']")).waitUntilPresent().getText();
        }
    }
}
