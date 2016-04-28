import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by katty on 21/04/2016.
 */
public class IMdbParameterizedTests {
    private WebDriver driver;
    private  String baseUrl;
    private String searchTerm;

    public IMdbParameterizedTests(String searchTerm){
        this.searchTerm = searchTerm;
    }

    public class Imdbtest1 {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @Before
        public void setUp() throws Exception {
            driver = new HtmlUnitDriver();
            baseUrl = "https://www.imdb.com/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testImdbtest1() throws Exception {
            driver.get(baseUrl + "/");
            String searchTerm = "la guerra de las galaxias";
            driver.findElement(By.id("navbar-query")).clear();
            driver.findElement(By.id("navbar-query")).sendKeys(searchTerm);
            driver.findElement(By.id("navbar-submit-button")).click();
            driver.findElement(By.xpath("//td[2]/a")).click();
            assertTrue(driver.getTitle().matches("^regexpi:\\.[\\s\\S]*\\$\\{searchTerm\\}\\.[\\s\\S]*$"));
        }

        @After
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }

}
