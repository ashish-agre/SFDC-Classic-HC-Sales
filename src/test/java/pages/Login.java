package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login extends ChromeDriver {

    WebDriver driver;
    By loginId = By.xpath("//label[text()='Username']//following::input[1]");
    By txtUsername = By.xpath(".//*[@id='username']");
    By passWord = By.xpath("//label[text()='Password']//following::input[1]");
    By txtPassword = By.xpath(".//*[@id='password']");
    By submitButton = By.xpath("//input[@name='Login']");
    By buttonLogin = By.xpath(".//*[@id='Login']");
    By remeberMe = By.xpath("//label[text()='Remember me']//preceding::input[1]");
    By userDownArrow = By.xpath(".//*[@id='userNav-arrow']");
    By logout = By.linkText("Logout");

    /**
     * *********************************************************************
     * Method Name: logOut
     *
     * @Description : Click on logout button
     * @Author Monika
     * **********************************************************************
     */

    public boolean logOut(WebDriver driver) {
        boolean flag = false;

        try {
            WebDriverWait waitUser = new WebDriverWait(driver, 60);
            waitUser.until(ExpectedConditions.visibilityOfElementLocated(userDownArrow));
            driver.findElement(userDownArrow).click();
            Thread.sleep(3000);
            driver.findElement(logout).click();
            driver.close();
            //driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;

    }

    /**
     * *********************************************************************
     * Method Name: getBrowser
     *
     * @Description : Function to get required instance of browser
     * @Author :  Saransh Goyal
     * **********************************************************************
     */
    public WebDriver getBrowser(WebDriver driver, String browser) {

        try {
            switch (browser) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
                    driver = new Login();
                    break;
                default:
                    System.out.println("Invalid option set for browser. Valid options are firefox/chrome/ie");
                    Exception e = new Exception("Invalid browser option");
                    throw (e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    @Override
    public WebElement findElement(By by) {

        try {
            Thread.sleep(1000);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return by.findElement((SearchContext) this);
    }

    /**
     * *********************************************************************
     * Method Name: enterLoginDetails
     *
     * @Description : Function to login to Portal
     * @Author :  Sunil Chellwani
     * **********************************************************************
     */
    public void enterLoginDetails(WebDriver driver, String url, String id, String pass) {

        try {

            driver.manage().window().maximize();
            driver.navigate().to(url);
            WebDriverWait waitUser = new WebDriverWait(driver, 60);
            waitUser.until(ExpectedConditions.elementToBeClickable(loginId));
            driver.findElement(loginId).sendKeys(id);

            waitUser.until(ExpectedConditions.visibilityOfElementLocated(passWord));
            driver.findElement(passWord).sendKeys(pass);

            waitUser.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
            driver.findElement(submitButton).click();

            System.out.println("Login successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
