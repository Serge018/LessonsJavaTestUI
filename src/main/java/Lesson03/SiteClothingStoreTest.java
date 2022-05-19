package Lesson03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class SiteClothingStoreTest {

    private static final String clothingStoreUrl = "http://automationpractice.com/index.php";
    private static final String selectorButtonSignIn = "a[href='http://automationpractice.com/index.php?controller=my-account']";
    private static final String selectorButtonSubmitAccountCreate = "#SubmitCreate";
    private static final String xPathHintCreateAccountError = "//[@id='create_account_error']/ol/li[contains(text(),'Invalid email address.')]";
    private static final String[] chromeOptionsArguments = { "--start-maximized", "--disable-notifications" };
    private static final int durationAwaitElement = 5;

    public static void main(String[] args) {
        // Устновка драйвра
        WebDriverManager.chromedriver().setup();

        // Создание экземпляра драйвера
        ChromeOptions chromeOptions = new ChromeOptions();
        for (String chromeOptionsArgument : chromeOptionsArguments) {
            chromeOptions.addArguments(chromeOptionsArgument);
        }
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Переход на главную страницу сайта
        driver.get(clothingStoreUrl);

        // Ожидание кнопки "Sign in"
        Duration durationAwaitButtonSignIn = Duration.ofSeconds(durationAwaitElement);
        WebDriverWait webDriverWaitButtonSignIn = new WebDriverWait(driver, durationAwaitButtonSignIn.getSeconds());
        webDriverWaitButtonSignIn.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selectorButtonSignIn)));

        // Совершается клик по кнопке "Sign in"
        WebElement buttonSignIn = driver.findElement(By.cssSelector(selectorButtonSignIn));
        buttonSignIn.click();

        // Ожидание кнопки "Create an account"
        Duration durationAwaitButtonSubmitAccountCreate = Duration.ofSeconds(durationAwaitElement);
        WebDriverWait webDriverWaitButtonSubmitAccountCreate = new WebDriverWait(driver, durationAwaitButtonSubmitAccountCreate.getSeconds());
        webDriverWaitButtonSubmitAccountCreate.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selectorButtonSubmitAccountCreate)));

        // Совершается клик по кнопке "Create an account"
        WebElement buttonSubmitAccountCreate = driver.findElement(By.cssSelector(selectorButtonSubmitAccountCreate));
        buttonSubmitAccountCreate.click();

        // Ожидание подсказки для пользователя о некорректном значении в поле для адреса почты
        Duration durationAwaitHintCreateAccountError = Duration.ofSeconds(durationAwaitElement);
        WebDriverWait webDriverWaitHintCreateAccountError = new WebDriverWait(driver, durationAwaitHintCreateAccountError.getSeconds());
        webDriverWaitHintCreateAccountError.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xPathHintCreateAccountError)));

        driver.quit();
    }

}
