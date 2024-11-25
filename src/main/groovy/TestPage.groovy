import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.WebDriverWait

import java.time.Duration

// class to inherit test suites
abstract class TestPage {

    protected static WebDriver driver;

    protected static WebDriverWait wait;

    protected static Actions actions;


     public TestPage(){
         System.setProperty("webdriver", "C:\\Users\\josec\\Documents\\drivers\\chromedriver-win64");

         ChromeOptions options = new ChromeOptions();
         options.addArguments("--start-maximized", "--disable-popup-blocking");

         driver = new ChromeDriver(options);
         wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         actions = new Actions(driver);
     }

    public abstract void routine();

    //call exec routine in order to run the routine implemented by test suite.
    public final void executeRoutine() {
        try {
            routine();
        } catch (Exception e) {
            System.err.println("An error occurred while executing the routine: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Optionally quit the driver after execution
            if (driver != null) {
                Thread.sleep(5000);
                driver.quit();
                System.out.println("Driver closed.");
            }
        }
    }
}
