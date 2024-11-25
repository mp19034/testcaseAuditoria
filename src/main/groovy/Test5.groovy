import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class Test5 extends TestPage{

    private static void navigateToYoutube() throws InterruptedException{
        driver.get("https://www.youtube.com"); //get youtube url
        Thread.sleep(3000); //3 sec wait
        System.out.println("Navegado a YouTube."); //confirm navigation to youtube
    }

    private static void clickLogin(){
        // Buscar el boton acceder y hacer click
        WebElement registerB = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div[2]/ytd-masthead/div[4]/div[3]/div[2]/ytd-button-renderer/yt-button-shape/a"));
        registerB.click();
        // Dar click sobre el boton siguiente.
        WebElement nextC = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div[1]/div/div/button"));
        nextC.click();
        //Esperar unos segundos
        Thread.sleep(2000);
        //Captura de excepciones
    }

    @Override
    public void routine(){
        navigateToYoutube()
        clickLogin()
    }
}
