import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
public class BigSeleniumJava {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), (Capabilities) options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 30);
    }
    private By bodyLocator = By.tagName("body");
    private By loginLocator = By.xpath("/html/body/div[3]/div[1]/a[3]");
    private By usernameFieldLocator = By.xpath("//*[@id='login-form']/div[1]/input[1]");
    private By passwordFieldLocator = By.xpath("//*[@id='login-form']/div[1]/input[2]");
    private By loginButtonLocator = By.xpath("//*[@id='login-button']");
    private By logoutButtonLocatorOpenerLocator = By.xpath("/html/body/div[3]/div[1]/div[2]/div[1]");
    private By logoutButtonLocator = By.xpath("//*[@id='menu-dd-container']/div/a[10]");
    private By loginTextLocator = By.xpath("/html/body/div[8]/div/div/div[2]/span[1]/a");
    //Message
    private By forumMessageLocator = By.xpath("//*[@id=\"messages_btn\"]");
    private By forumMessageFriendLocator = By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/div/div/div[3]/div/a");
    private By forumMessageSpaceLocator = By.xpath("//*[@id=\"chat\"]");
    //Complex x-pahtes
    private By footerContainLocator = By.xpath("//div[contains(@class, 'footer-container')]");
    private WebElement waitVisibilityAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
    @Test
    public void testSuccessLoginsendForumandLogout() {
        //Go this web site and enter this credentials
        this.driver.get("https://replay.az/");
        WebElement openLogin = waitVisibilityAndFindElement(loginLocator);
        openLogin.click();
        WebElement usernameField = waitVisibilityAndFindElement(usernameFieldLocator);
        usernameField.sendKeys("tomsmith");
        WebElement passwordField = waitVisibilityAndFindElement(passwordFieldLocator);
        passwordField.sendKeys("Tomas123");
        WebElement loginButton = waitVisibilityAndFindElement(loginButtonLocator);
        loginButton.click();
        //After succesfull,login check forum select friend and send him message: "Hallo,ich bin Tester"
        WebElement messageButton=waitVisibilityAndFindElement(forumMessageLocator);
        messageButton.click();
        WebElement friendSelectButton=waitVisibilityAndFindElement(forumMessageFriendLocator);
        friendSelectButton.click();
        WebElement sendMessageFriend=waitVisibilityAndFindElement(forumMessageSpaceLocator);
        sendMessageFriend.click();
        sendMessageFriend.sendKeys("Hallo, ich bin Tester ");
        sendMessageFriend.sendKeys(Keys.ENTER);
        //Check log out functionality of web Site
        WebElement FindLogout = waitVisibilityAndFindElement(logoutButtonLocatorOpenerLocator);
        FindLogout.click();
        WebElement TriggerLogout = waitVisibilityAndFindElement(logoutButtonLocator);
        TriggerLogout.click();
     
        
    }
    @Test
    public void ReadPageTitle() {
        this.driver.get("https://replay.az/");
        //Verify the title of the website can be readen , then print it 
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
    }
    @Test
    public void StaticPageTest() {
    this.driver.get("https://replay.az/");
    // Verify the presence and visibility of the logo
    WebElement staticCheck = waitVisibilityAndFindElement(bodyLocator);
    String staticCheckText = staticCheck.getText();
    assertTrue(staticCheckText.contains("Populyar"));

}
@Test
public void ComplexXpathTest() {
    this.driver.get("https://replay.az/");
    WebElement footerElement = waitVisibilityAndFindElement(footerContainLocator);
    String footerText = footerElement.getText();
    assertTrue(footerText.contains("Europa plus"));
}

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}