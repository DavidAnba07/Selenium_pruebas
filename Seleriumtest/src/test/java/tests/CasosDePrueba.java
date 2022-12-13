package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;

public class CasosDePrueba {
    //Atributos
    private WebDriver driver;
    private WebDriverWait wait;

    private JavascriptExecutor js;

    private String rutaDriver = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String propertyDriver = "webdriver.chrome.driver";

    @AfterMethod
    public void posCondicion() {
        driver.close();
    }

    @BeforeMethod
    public void preCondiciones() {

        System.setProperty(propertyDriver, rutaDriver);

        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10);

        js = (JavascriptExecutor) driver;

        driver.navigate().to("https://open.spotify.com/");

        driver.manage().window().maximize();
    }

    @Test
    public void CP001_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("123454321");

        driver.findElement(By.name("displayname")).sendKeys("Pobre Domingo :D");

        driver.findElement(By.id("day")).sendKeys("28");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");

        driver.findElement(By.name("year")).sendKeys("1991");


        driver.findElement(By.xpath("//label[@for='gender_option_male']")).click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();


        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();


        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText(), "Confirma que no eres un robot.");

    }

    @Test
    public void CP002_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("domingo.saedra@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("123454321");

        driver.findElement(By.name("displayname")).sendKeys("Pobre Domingo :D");

        driver.findElement(By.id("day")).sendKeys("28");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");


        driver.findElement(By.name("year")).sendKeys("1991");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));

        js.executeScript("arguments[0].scrollIntoView();", optionMale);


        optionMale.click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();


        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();

        WebElement btnRegistro = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Las direcciones de correo')]")).getText(), "Las direcciones de correo electrónico no coinciden.");
    }

    @Test
    public void CP003_Login_Fallido() {

        By localizadorBtnlogin = By.xpath("//button[@data-testid='login-button']");

        WebElement btnlogin = driver.findElement(localizadorBtnlogin);

        btnlogin.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-username"))).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.id("login-password")).sendKeys("12354612");

        driver.findElement(By.xpath("//label[@for='login-remember']")).click();

        driver.findElement(By.id("login-button")).click();

    }

    @Test
    public void CP004_Login_celular() {

        By localizadorBtnlogin = By.xpath("//button[@data-testid='login-button']");

        WebElement btnlogin = driver.findElement(localizadorBtnlogin);

        btnlogin.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-username")));

        By LocalizadorBtnLogincel = By.xpath("//button[@data-testid='phone-login']");

        WebElement btnloginCel = driver.findElement(LocalizadorBtnLogincel);

        btnloginCel.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("phonelogin-phonenumber"))).sendKeys("930818254");

        driver.findElement(By.xpath("//label[@for='phonelogin-remember']")).click();

        driver.findElement(By.id("phonelogin-button")).click();

    }

    @Test
    public void CP005_Login_celular_Fallido() {

        By localizadorBtnlogin = By.xpath("//button[@data-testid='login-button']");

        WebElement btnlogin = driver.findElement(localizadorBtnlogin);

        btnlogin.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-username")));

        By LocalizadorBtnLogincel = By.xpath("//button[@data-testid='phone-login']");

        WebElement btnloginCel = driver.findElement(LocalizadorBtnLogincel);

        btnloginCel.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("phonelogin-phonenumber"))).sendKeys("8254");

        driver.findElement(By.xpath("//label[@for='phonelogin-remember']")).click();

        driver.findElement(By.id("phonelogin-button")).click();
    }

    @Test
    public void CP005_Buscar_Banda() {

        By localizadorBtnBuscador = By.xpath("//span[contains(text(),'Buscar')]");

        WebElement btnBuscar = driver.findElement(localizadorBtnBuscador);

        btnBuscar.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-testid='search-input']"))).sendKeys("Metallica");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid='herocard-click-handler']"))).click();
    }

    @Test
    public void CP006_Buscar_Reproducir() {

        By localizadorBtnBuscador = By.xpath("//span[contains(text(),'Buscar')]");

        WebElement btnBuscar = driver.findElement(localizadorBtnBuscador);

        btnBuscar.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-testid='search-input']"))).sendKeys("Metallica");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid='herocard-click-handler']"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='Button-sc-qlcn5g-0 futnNt']"))).click();

    }

    @Test
    public void CP007_Proceso_Premium() {

        By localizadorBtnBuscador = By.xpath("//span[contains(text(),'Conseguir 3')]");

        WebElement btnBuscar = driver.findElement(localizadorBtnBuscador);

        btnBuscar.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Obtén 3 meses')]"))).click();

    }
}
