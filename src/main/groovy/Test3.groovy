import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions

class Test3 extends TestPage{

    private static void navigateToYoutube() throws InterruptedException{
        driver.get("https://www.youtube.com"); //get youtube url
        Thread.sleep(3000); //3 sec wait
        System.out.println("Navegado a YouTube."); //confirm navigation to youtube
    }

    private static void clickTendencyButton() throws InterruptedException{

        System.out.println("Click en trending")
        WebElement trendingButton = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/tp-yt-app-drawer/div[2]/div/div[2]/div[2]/ytd-guide-renderer/div[1]/ytd-guide-section-renderer[3]/div/ytd-guide-entry-renderer[1]/a"))
        trendingButton.click()

        Thread.sleep(3000)

        for(int i = 0; i < 4; i++){
            WebElement button = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-browse[2]/div[3]/ytd-tabbed-page-header/tp-yt-app-header-layout/div/tp-yt-app-header/div[2]/tp-yt-app-toolbar/div/div/tp-yt-paper-tabs/div/div/yt-tab-group-shape/div[1]/yt-tab-shape[" + (i + 1) + "]"))

            //click in button to see type of trend
            button.click()

            //check for first video element
            WebElement firstVideoTitle = driver.findElement(By.id("video-title"))


            System.out.println("click en boton: " + button.text + "...")
            System.out.println(firstVideoTitle.text)
            Thread.sleep(1000)
        }


    }

    @Override
    public void routine(){
        navigateToYoutube()
        clickTendencyButton()
    }
}
