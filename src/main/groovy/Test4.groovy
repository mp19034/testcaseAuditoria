import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions

class Test4 extends TestPage{

    private static void navigateToYoutube() throws InterruptedException{
        driver.get("https://www.youtube.com"); //get youtube url
        Thread.sleep(3000); //3 sec wait
        System.out.println("Navegado a YouTube."); //confirm navigation to youtube
    }

    private static void clickMusicButton() throws InterruptedException{

        System.out.println("Click en music")
        WebElement musicButton = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/tp-yt-app-drawer/div[2]/div/div[2]/div[2]/ytd-guide-renderer/div[1]/ytd-guide-section-renderer[3]/div/ytd-guide-entry-renderer[2]/a"))
        musicButton.click()

        Thread.sleep(3000)

        for(int i = 0; i < 2; i++){
            WebElement button = driver.findElement(By.xpath("html/body/ytd-app/div[1]/ytd-page-manager/ytd-browse[2]/div[3]/ytd-carousel-header-renderer/div/ytd-topic-channel-details-renderer/div/div[2]/div/tp-yt-paper-tabs/div/div/yt-tab-group-shape/div[1]/yt-tab-shape[" + (i + 1) + "]"))
            //click in button to see type of trend
            button.click()

            //check for first video element
            WebElement firstPlaylistTitle = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-browse[2]/ytd-two-column-browse-results-renderer/div[1]/ytd-section-list-renderer/div[2]/ytd-item-section-renderer[1]/div[3]/ytd-shelf-renderer/div[1]/div[2]/yt-horizontal-list-renderer/div[2]/div/div/yt-lockup-view-model[1]/div/div/yt-lockup-metadata-view-model/div[1]/h3/a/span"))


            System.out.println("click en boton: " + button.text + "...")
            System.out.println(firstPlaylistTitle.text)
            Thread.sleep(1000)
        }


    }

    @Override
    public void routine(){
        navigateToYoutube()
        clickMusicButton()
    }
}
