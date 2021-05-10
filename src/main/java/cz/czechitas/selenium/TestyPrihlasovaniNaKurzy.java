package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void prihlaseniRodiceSExistujicimUctem() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        WebElement zalozkaEmail= prohlizec.findElement(By.id("email"));
        zalozkaEmail.sendKeys("barbora.vadurova@seznam.cz");
        WebElement zalozkaHeslo= prohlizec.findElement(By.id("password"));
        zalozkaHeslo.sendKeys("Heslo123");
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//button[@class='btn btn-primary']"));
        tlacitkoPrihlasit.click();
      //  WebElement zalozkaPrihlasenyUzivatel = prohlizec.findElement(By.xpath("//a class[@title='Barbora Vadurova']"));
      //  zalozkaPrihlasenyUzivatel.getText();




    }

    private void prihlaseniKUctu() {
      /*  WebElement zalozkaEmail= prohlizec.findElement(By.id("email"));
        zalozkaEmail.sendKeys("barbora.vadurova@seznam.cz");
        WebElement zalozkaHeslo= prohlizec.findElement(By.id("password"));
        zalozkaHeslo.sendKeys("Heslo123");
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//button[@class='btn btn-primary']"));
        tlacitkoPrihlasit.click();
        WebElement zalozkaPrihlasenyUzivatel = prohlizec.findElement(By.xpath("//a class[@title='Barbora Vadurova']"));
        zalozkaPrihlasenyUzivatel.getText();  */

    }

    @Test
    public void vybereKurzANasledneSePrihlasiKUctu() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        prihlaseniKeKurzuZalkadyAlgoritmizace();
        prihlaseniKUctu();
    }

    private void prihlaseniKeKurzuZalkadyAlgoritmizace() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement zalozkaPrihlaseniKeKurzu = prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/1-digitalni-akademie-testovani']"));
        zalozkaPrihlaseniKeKurzu.click();
        WebElement zalozkaVytvoreniPrihlasky = prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci/pridat/21-zaklady-algoritmizace']"));
        zalozkaVytvoreniPrihlasky.click();
    }

    @Test
    public void prihlaseniKUctuANasledneVybraniKurzu (){
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        prihlaseniKUctu();
        prihlaseniKeKurzuZalkadyAlgoritmizace();
    }

    @AfterEach
    public void tearDown() {
      //  prohlizec.close();
    }
}
