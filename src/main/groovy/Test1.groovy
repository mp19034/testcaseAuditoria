import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys

class Test1 extends TestPage{

    private static void navigateToYoutube() throws InterruptedException{
        driver.get("https://www.youtube.com"); //get youtube url
        Thread.sleep(3000); //3 sec wait
        System.out.println("Navegado a YouTube."); //confirm navigation to youtube
    }

    public static void SearchVideo(){
        WebElement searchBox = driver.findElement(By.name("search_query"));
        searchBox.sendKeys("Futbol");
        searchBox.sendKeys(Keys.RETURN);
        Thread.sleep(3000);
    }


    @Override
    public void routine(){
        navigateToYoutube()
        SearchVideo()
    }
}
