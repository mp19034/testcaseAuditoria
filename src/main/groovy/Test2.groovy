import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.Keys
import java.time.Duration

class Test2 extends TestPage{

    private static void navigateToYoutube() throws InterruptedException{
        driver.get("https://www.youtube.com"); //get youtube url
        Thread.sleep(3000); //3 sec wait
        System.out.println("Navegado a YouTube."); //confirm navigation to youtube
    }

    private static void loginToYouTube(String email, String password) throws InterruptedException {
        try {
            // Encuentra el botón de inicio de sesión (Login) en la página principal utilizando su ruta XPath.
            WebElement loginButton = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div[2]/ytd-masthead/div[4]/div[3]/div[2]/ytd-button-renderer/yt-button-shape/a/yt-touch-feedback-shape/div"));

            // Haz clic en el botón para abrir el formulario de inicio de sesión.
            loginButton.click();
            System.out.println("Ejecutando Log in."); // Mensaje de confirmación en consola.
            Thread.sleep(2000); // Pausa para esperar a que cargue

            // Encuentra el campo de correo electrónico utilizando su atributo "id".
            WebElement emailField = driver.findElement(By.id("identifierId"));

            // Escribe el correo electrónico proporcionado y presiona la tecla Enter.
            emailField.sendKeys(email, Keys.RETURN);
            System.out.println("Agregando Correo."); // Mensaje de confirmación en consola.
            Thread.sleep(2000); // Pausa breve para permitir.

            // Encuentra el campo de contraseña utilizando una espera explícita para asegurarte de que esté visible.
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[2]/div/div/div/form/span/section[2]/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input")));

            // Escribe la contraseña proporcionada y presiona la tecla Enter.
            passwordField.sendKeys(password, Keys.RETURN);
            System.out.println("Agregando Contraseña."); // Mensaje de confirmación en consola.

            // Breve pausa para finalizar el inicio de sesión.
            Thread.sleep(2000);
            System.out.println("Inicio de sesión completado."); // Confirmación final.

        } catch (NoSuchElementException e) {
            System.out.println("No se encontró un elemento necesario para el inicio de sesión: " + e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            System.out.println("Tiempo de espera agotado mientras se esperaba el campo de contraseña: " + e.getMessage());
            throw e;
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado durante el inicio de sesión: " + e.getMessage());
            throw e;
        }
    }

    private static void interactWithShorts() throws InterruptedException {
        try {
            // Espera explícita para encontrar el enlace de "Shorts" que sea interactuable
            WebElement shortsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='endpoint' and @title='Shorts']")));

            // Realiza un clic usando la acción del mouse
            actions.moveToElement(shortsLink).click().perform();

            // Imprime un mensaje en la consola para confirmar que la interacción fue exitosa
            System.out.println("Interacción con Shorts completada.");

        } catch (NoSuchElementException e) {
            System.out.println("No se encontró el enlace de 'Shorts': " + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("Tiempo de espera agotado para encontrar el enlace de 'Shorts': " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private static void fetchChannelName() throws InterruptedException {
        // Configura un tiempo de espera explícito de 20 segundos para el WebDriver
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Espera hasta que el elemento que contiene el nombre del canal sea visible
            WebElement channelNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".yt-core-attributed-string__link") // Selector CSS para identificar el nombre del canal
            ));
            // Extrae el texto del elemento (nombre del canal) y lo imprime en la consola
            String channelName = channelNameElement.getText();
            System.out.println("Nombre del canal: " + channelName);
        } catch (TimeoutException e) {
            // Maneja el caso donde el elemento no se encuentra dentro del tiempo especificado
            System.out.println("No se encontró el elemento del nombre del canal dentro del tiempo especificado.");
            throw e;
        } catch (NoSuchElementException e) {
            // Maneja el caso donde el elemento no puede ser localizado
            System.out.println("No se pudo localizar el elemento del nombre del canal: " + e.getMessage());
            throw e;
        } catch (StaleElementReferenceException e) {
            // Maneja el caso donde el elemento ya no es válido en el DOM
            System.out.println("El elemento del nombre del canal ya no es válido en el DOM: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            // Captura cualquier otro error inesperado y lo imprime
            System.out.println("Ocurrió un error inesperado al obtener el nombre del canal: " + e.getMessage());
            throw e;
        }
    }

    private static void toggleReaction(WebElement button, String actionName) { //ok
        String buttonClass = button.getAttribute("class");

        // Si la clase contiene "yt-spec-touch-feedback-shape--touch-response-inverse", significa que el botón ya está activado
        if (buttonClass.contains("yt-spec-touch-feedback-shape--touch-response-inverse")) {
            // Si el botón ya está activo, imprime un mensaje indicando que no es necesario hacer clic
            System.out.println("El botón '" + actionName + "' ya está activo.");
            Thread.sleep(2000);
        } else {
            // Si el botón no está activo, hace clic en él
            actions.moveToElement(button).click().perform();
            // Imprime un mensaje indicando que se hizo clic en el botón de la acción correspondiente (Like o Dislike)
            System.out.println("Se hizo clic en el botón de '" + actionName + "'.");
        }
    }

    private static void likeAndDislikeVideo() throws InterruptedException { // Acción: "Like" y "Dislike" del video

        try {
            // Esperar 5 segundos para asegurar que el botón "Like" esté completamente cargado antes de interactuar con él
            Thread.sleep(5000); // Espera adicional para asegurar carga completa

            // Espera explícita hasta que el botón "Like" sea clickeable
            WebElement likeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    // Selección del botón "Like" mediante XPath
                    By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-shorts/div[3]/div[2]/ytd-reel-video-renderer[1]/div[5]/ytd-reel-player-overlay-renderer/div[2]/div/div[1]/ytd-like-button-renderer/ytd-toggle-button-renderer[1]/yt-button-shape/label/button/yt-touch-feedback-shape/div")
            ));

            // Llama a la función toggleReaction para realizar la acción de "Like"
            // Valida si ya está la acción de like o no y manda mensaje de confirmación
            toggleReaction(likeButton, "Like");
        } catch (NoSuchElementException e) {
            System.out.println("No se encontró el botón 'Like': " + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("Tiempo de espera agotado para el botón 'Like': " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido durante la acción 'Like': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado durante la acción 'Like': " + e.getMessage());
        }

        // Segundo bloque try-catch para la acción "Dislike"
        try {
            // Esperar 5 segundos antes de interactuar con el botón "Dislike" para garantizar que la página cargue correctamente
            Thread.sleep(5000); // Espera antes de interactuar con el botón "Dislike"

            // Espera explícita hasta que el botón "Dislike" sea clickeable
            WebElement dislikeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    // Selección del botón "Dislike" mediante XPath
                    By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-shorts/div[3]/div[2]/ytd-reel-video-renderer[1]/div[5]/ytd-reel-player-overlay-renderer/div[2]/div/div[1]/ytd-like-button-renderer/ytd-toggle-button-renderer[2]/yt-button-shape/label/button/yt-touch-feedback-shape/div")
            ));

            // Llama a la función toggleReaction para realizar la acción de "Dislike"
            toggleReaction(dislikeButton, "Dislike");
        } catch (NoSuchElementException e) {
            System.out.println("No se encontró el botón 'Dislike': " + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("Tiempo de espera agotado para el botón 'Dislike': " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido durante la acción 'Dislike': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado durante la acción 'Dislike': " + e.getMessage());
        }
    }

    // TODO: revisar comment video
    private static void commentOnVideo() throws InterruptedException { // Acción: seleccionar botón de comentarios y cerrar
        try {
            // Esperar hasta que el botón de comentarios sea visible y esté clickeable
            WebElement commentsButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comments-button")));
            if (commentsButton != null) {
                // Hacer clic en el botón de comentarios
                actions.moveToElement(commentsButton).click().perform();
                System.out.println("Comentarios abiertos.");
            } else {
                System.out.println("No se encontró el botón de comentarios.");
                return; // Si no se encuentra el botón, terminamos el método aquí
            }

            // Pausa de 3 segundos para permitir que los comentarios se carguen
            Thread.sleep(3000);

            // Esperar hasta que el botón de cierre de comentarios sea visible y esté clickeable
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ytd-engagement-panel-title-header-renderer//button[contains(@aria-label, 'Cerrar')]")));

            if (closeButton != null) {
                // Hacer clic en el botón de cerrar comentarios
                closeButton.click();
                //texto de confirmacion
                System.out.println("Comentarios cerrados.");
            } else {
                System.out.println("No se encontró el botón de cerrar comentarios.");
            }

            // Pausa de 3 segundos antes de finalizar el método
            Thread.sleep(3000);

        } catch (NoSuchElementException e) {
            System.out.println("No se encontró uno de los elementos esperados: " + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("El tiempo de espera agotado para encontrar un elemento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private static void shareVideo() throws InterruptedException {
        // Intentar encontrar el botón de compartir y hacer clic en él
        try {
            // Esperar a que el botón de compartir sea visible y clickeable
            WebElement shareButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("share-button")));
            shareButton.click(); // Hacer clic en el botón de compartir
            System.out.println("Compartir ventana abierta.");

            // Pausa de 3 segundos para permitir que la ventana de compartir se cargue
            Thread.sleep(3000);

            // Esperar hasta que el botón de cerrar la ventana de compartir sea visible y clickeable
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ytd-unified-share-panel-renderer > .yt-icon-shape > div")));

            // Mover el ratón sobre el botón de cerrar y hacer clic en él
            actions.moveToElement(closeButton).click().perform();
            System.out.println("Compartir ventana cerrada.");

        } catch (NoSuchElementException e) {
            // Si no se encuentra el botón de compartir
            System.out.println("No se encontró el botón de compartir.");
            throw e;
        } catch (TimeoutException e) {
            // Si el tiempo de espera se agotó para encontrar el botón
            System.out.println("Tiempo de espera agotado para encontrar el botón.");
            throw e;
        } catch (Exception e) {
            // Captura cualquier otro error inesperado
            System.out.println("Ocurrió un error: " + e.getMessage());
            throw e;
        }
    }



    @Override
    public void routine(){
        navigateToYoutube()
        loginToYouTube("doej21352@gmail.com","dummyPwd1234")
        interactWithShorts()
        fetchChannelName()
        likeAndDislikeVideo()
//        commentOnVideo()
        shareVideo()
    }
}
