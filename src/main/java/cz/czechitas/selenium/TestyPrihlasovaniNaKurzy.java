package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;



    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void rodicSExistujicimUctemSeMusiPrihlasit(){
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        prihlaseniKUctu();

        WebElement zalozkaPrihlasenyUzivatel = prohlizec.findElement(By.xpath("//*[@title='Barbora Vadurova']"));
        Assertions.assertNotNull(zalozkaPrihlasenyUzivatel);


    }

    public void prihlaseniKUctu(){

        WebElement zalozkaEmail= prohlizec.findElement(By.id("email"));
        zalozkaEmail.sendKeys("barbora.vadurova@seznam.cz");
        WebElement zalozkaHeslo= prohlizec.findElement(By.id("password"));
        zalozkaHeslo.sendKeys("Heslo123");
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(),'Přihlásit')]"));
        tlacitkoPrihlasit.click();

    }

    @Test
    public void  klientMusiBytSchopenSePrihlasitNaKurzANasledneKUctu(){
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement zalozkaPrihlaseniKeKurzu=prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/1-digitalni-akademie-testovani']"));
        zalozkaPrihlaseniKeKurzu.click();

        WebElement zalozkaVytvoreniPrihlasky=prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci/pridat/21-zaklady-algoritmizace']"));
        zalozkaVytvoreniPrihlasky.click();

        prihlaseniKUctu();
        vyplneniUdajuVPrihlasceADokonceniPrihlasky();

        WebElement jdiDoZalozkyPrihlasky = prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci']"));
        jdiDoZalozkyPrihlasky.click();
        WebElement najdiPrihlasku = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]"));

        Assertions.assertNotNull(najdiPrihlasku);


    }

    public void vyplneniUdajuVPrihlasceADokonceniPrihlasky(){

        WebElement tlacitkoVyberData = prohlizec.findElement(By.xpath("//*[@data-id='term_id']"));
        tlacitkoVyberData.click();
        tlacitkoVyberData.sendKeys( " 5.06\n");
        WebElement tlacitkoJmenoZaka = prohlizec.findElement(By.id("forename"));
        tlacitkoJmenoZaka.sendKeys("Bertik");
        WebElement tlacitkoPrijmeniZaka = prohlizec.findElement(By.id("surname"));
        tlacitkoPrijmeniZaka.sendKeys("Mertik");
        WebElement tlacitkoDatumNarozeni = prohlizec.findElement(By.id("birthday"));
        tlacitkoDatumNarozeni.sendKeys("01.01.2014");
        WebElement vyberZpusobUhradyBanka = prohlizec.findElement(By.xpath("//label[text()='Bankovní převod']"));
        vyberZpusobUhradyBanka.click();
        WebElement tlacitkoSouhlasSVseobecnymiPodminkami = prohlizec.findElement(By.xpath("//label[text()='Souhlasím s všeobecnými podmínkami a zpracováním osobních údajů.']"));
        tlacitkoSouhlasSVseobecnymiPodminkami.click();
        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//input[@value='Vytvořit přihlášku']"));
        tlacitkoVytvoritPrihlasku.click();
    }

    @Test
    public void klientMusiBytSchopenSePrihlasitKUctuANasedneKeKurzu(){
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        prihlaseniKUctu();
        WebElement zalozkaDomu = prohlizec.findElement(By.xpath("/html/body/div/header/nav/div/div[1]/a[1]"));
        zalozkaDomu.click();

        WebElement zalozkaPrihlaseniKeKurzu=prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/1-digitalni-akademie-testovani']"));
        zalozkaPrihlaseniKeKurzu.click();
        WebElement zalozkaVytvoreniPrihlasky=prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci/pridat/21-zaklady-algoritmizace']"));
        zalozkaVytvoreniPrihlasky.click();

        vyplneniUdajuVPrihlasceADokonceniPrihlasky();

        WebElement jdiDoZalozkyPrihlasky = prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci']"));
        jdiDoZalozkyPrihlasky.click();
        WebElement najdiPrihlasku = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td[1]"));

        Assertions.assertNotNull(najdiPrihlasku);



    }

    @Test
    public void prihlasenyRodicMusiBytSchopenPrihlaskuUpravit(){
         prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
         prihlaseniKUctu();

         WebElement poleHledat = prohlizec.findElement(By.xpath("//input[@type='search']"));
         poleHledat.sendKeys("\nRU");
         WebElement tlacitkoUpravit = prohlizec.findElement(By.xpath("//*[text()='Upravit']"));
         tlacitkoUpravit.click();
         WebElement poleDatumNarozeni = prohlizec.findElement(By.id("birthday"));
         poleDatumNarozeni.clear();
         poleDatumNarozeni.sendKeys("12.12.2013");
         WebElement tlacitkoUpravitPrihlasku = prohlizec.findElement(By.xpath("//*[@value='Upravit přihlášku']"));
         tlacitkoUpravitPrihlasku.click();
         WebElement tlacitkoDetail = prohlizec.findElement(By.xpath("//a[@title='Zobrazit']"));
         tlacitkoDetail.click();

         WebElement kontrolaDataNarozeni = prohlizec.findElement(By.xpath("//*[text()= '12.12.2013']"));
         Assertions.assertEquals("12.12.2013", kontrolaDataNarozeni.getText());

     }

     @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
