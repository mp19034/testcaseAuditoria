import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

//manipular el navegador que puedes ocupar este
import org.openqa.selenium.WebElement;

//usar todos los objetos que estan en el dom del css
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions;

//el navegador el drive de este
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        System.setProperty("webdriver", "C:\\Users\\Usuario\\Desktop\\Auditoria\\chromedriver-win64\\chromedriver-win64");
        //agg el path donde esta el chrome
        // Opciones de Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximizar ventana
        options.addArguments("--disable-popup-blocking"); // Deshabilitar bloqueadores de pop-ups
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
            // Navegar a YouTube
            driver.get("https://www.youtube.com");
            Thread.sleep(2000);  // 10000 milisegundos = 10 segundos
            System.out.println("Esperando 5 segundos...");

            //accedemos a cuenta:/html/body/ytd-app/div[1]/div[2]/ytd-masthead/div[4]/div[3]/div[2]/ytd-button-renderer/yt-button-shape/a/yt-touch-feedback-shape/div
            // Localizar el elemento "Shorts"
            WebElement login = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div[2]/ytd-masthead/div[4]/div[3]/div[2]/ytd-button-renderer/yt-button-shape/a/yt-touch-feedback-shape/div"));

            // Interactuar con el elemento "Shorts"
            login.click();

            Thread.sleep(2000);  // 10000 milisegundos = 10 segundos
            System.out.println("Esperando 5 segundos...");

            WebElement searchBox = driver.findElement(By.id("identifierId")); //buscamos xpath este
            searchBox.sendKeys("pruebauditoria24@gmail.com");
            //le ingresamo un valor a buscar dentor de la caja de busqueda
            searchBox.sendKeys(Keys.RETURN);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Localizar el campo de contraseña usando el XPath completo
            WebElement searchPass = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[2]/div/div/div/form/span/section[2]/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input"));

// Ingresar la contraseña en el campo
            searchPass.sendKeys("prueba123");

// Simular presionar la tecla ENTER
            searchPass.sendKeys(Keys.RETURN);

// Configurar un tiempo de espera implícito después de la acción
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            //--------------------------------------------------------------------------------------------------------------
            // Configura el tiempo de espera implícita
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            // Localizar el elemento "Shorts"
            WebElement shortsLink = driver.findElement(By.xpath("//a[@id='endpoint' and @title='Shorts']"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Interactuar con el elemento "Shorts"
            actions.moveToElement(shortsLink).click().perform()

            // Esperar por un elemento único en la página de Shorts
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='shorts-container']")));
            System.out.println("Se cargó correctamente la página de Shorts.");

            //--------------------------------------------------------------------------------------------------------------
            // Espera explícita para asegurarse de que el elemento sea visible
            WebElement channelNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".yt-core-attributed-string__link") // Usamos el selector CSS
            ));

            // Obtener el texto del nombre del canal
            String channelName = channelNameElement.getText();
            System.out.println("Nombre del canal: " + channelName);

            //--------------------------------------------------------------------------------------------------------------
            // Espera a que el botón "Like" esté visible

            Thread.sleep(5000); // Esperar 2 segundos
            // Interactuar con el botón "Like"
            WebElement likeReact = wait1.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-shorts/div[3]/div[2]/ytd-reel-video-renderer[1]/div[5]/ytd-reel-player-overlay-renderer/div[2]/div/div[1]/ytd-like-button-renderer/ytd-toggle-button-renderer[1]/yt-button-shape/label/button/yt-touch-feedback-shape/div")
            ));

            // Verificar el estado del botón "Like" basado en su clase
            String likeClass = likeReact.getAttribute("class");

            if (likeClass.contains("yt-spec-touch-feedback-shape--touch-response-inverse")) {
                System.out.println("El botón 'Like' ya está activo. No se realiza ninguna acción.");
            } else {
                actions.moveToElement(likeReact).click().perform();
                System.out.println("Se hizo clic en el botón de 'Like' PASS.");
            }

            Thread.sleep(2000); // Esperar 2 segundos

            // Interactuar con el botón "Dislike"
            WebElement dislikeReact = wait1.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-shorts/div[3]/div[2]/ytd-reel-video-renderer[1]/div[5]/ytd-reel-player-overlay-renderer/div[2]/div/div[1]/ytd-like-button-renderer/ytd-toggle-button-renderer[2]/yt-button-shape/label/button/yt-touch-feedback-shape/div")
            ));

            // Verificar el estado del botón "Dislike" basado en su clase
            String dislikeClass = dislikeReact.getAttribute("class");

            if (dislikeClass.contains("yt-spec-touch-feedback-shape--touch-response-inverse")) {
                System.out.println("El botón 'Dislike' ya está activo. No se realiza ninguna acción.");
            } else {
                actions.moveToElement(dislikeReact).click().perform();
                System.out.println("Se hizo clic en el botón de 'Dislike' PASS.");
            }


            Thread.sleep(2000);

            // Interactuar con el botón "Comentarios" (usando ID)
            WebElement commentsReact = wait1.until(ExpectedConditions.elementToBeClickable(
                    By.id("comments-button")
            ));

            // Hacemos clic en el botón "Comentarios"

            if (actions.moveToElement(commentsReact).click().perform() != false) {
                System.out.println("Se hizo clic en el botón de 'Comentarios' PASS.");
                Thread.sleep(2000); // Esperar 2 segundos
                // --------------------------------------------------------------------------------------------------------------
                // Si deseas cerrar la ventana de comentarios después de abrirla, puedes hacerlo usando un botón de cierre
                WebElement closeButton = wait1.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-shorts/div[4]/div[2]/ytd-engagement-panel-section-list-renderer[1]/div[1]/ytd-engagement-panel-title-header-renderer/div[3]/div[6]/ytd-button-renderer/yt-button-shape/button/yt-touch-feedback-shape/div") // Reemplazar por el XPath adecuado
                ));
                closeButton.click();
                System.out.println("Se cerró la ventana de 'Comentarios'.");
            } else {
                System.out.println("No paso.");
            }


            //compartir: /html/body/ytd-app/div[1]/ytd-page-manager/ytd-shorts/div[3]/div[2]/ytd-reel-video-renderer[17]/div[5]/ytd-reel-player-overlay-renderer/div[2]/div/div[3]/ytd-button-renderer/yt-button-shape/label/button/yt-touch-feedback-shape/div
            //*[@id="share-button"]/ytd-button-renderer/yt-button-shape/label/button/yt-touch-feedback-shape/div/div[2]
            // Interactuar con el botón "Compartir"
            WebElement shareReact = wait1.until(ExpectedConditions.elementToBeClickable(
                    By.id("share-button") // ID del botón "Compartir"
            ));
            shareReact.click(); // Hacemos clic en el botón
            System.out.println("Se hizo clic en el botón de 'Compartir' PASS.");


// Localizar el botón "Cerrar" dentro del panel de compartir
            WebElement closeButton = wait1.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#button > yt-icon > span > div > svg") // XPath ajustado para el botón de cerrar
            ));
// Hacemos clic en el botón de cerrar
            actions.moveToElement(closeButton).click().perform();
            System.out.println("Se cerró la ventana de 'Compartir'.");



            Thread.sleep(10000);  // 10000 milisegundos = 10 segundos
            System.out.println("Esperando 10 segundos...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra el navegador
            driver.quit();
        }
    }
}
