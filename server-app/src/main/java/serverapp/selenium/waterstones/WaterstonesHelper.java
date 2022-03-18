package serverapp.selenium.waterstones;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
public class WaterstonesHelper {

//    @Value("${waterstones.email}")
    private String email_value = "";
//    @Value("${waterstones.password}")
    private String password_value;


    private String startUrl = "https://www.waterstones.com/";
    private WebDriver driver;

//    @Autowired
    public WaterstonesHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        driver.navigate().to(startUrl);
    }

    private void acceptCookies() {
        WebElement acceptCookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptCookies.click();
    }

    public void signInAccount() {
        this.goToHomePage();
        driver.navigate().to(startUrl + "/signin");

        this.acceptCookies();


        WebElement emailInput = driver.findElement(By.id("login_form_email"));
        emailInput.clear();
        emailInput.sendKeys(email_value);
        System.out.println("EMAIL VALUE: " + email_value);

        WebElement passwordInput = driver.findElement(By.id("login_form_password"));
        passwordInput.clear();
        passwordInput.sendKeys(password_value);

        driver.findElement(By.name("Login")).click();
    }

    private double getPrice() throws Exception {
        WebElement priceInBox = driver.findElement(By.xpath("//b[@itemprop='price']"));
        String priceString = priceInBox.getText().substring(1);
        return Double.parseDouble(priceString);
    }

    public double visitItem(String url) throws Exception {
        driver.navigate().to(url);

        return getPrice();
    }

    public void addToCart(String url) {
        driver.navigate().to(url);
        driver.findElement(By.className("basket-form")).submit();
    }

    public void proceedToCheckout() {
        driver.navigate().to("https://www.waterstones.com/checkout/basket");

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("scroll(0, 250)");

        driver.findElement(By.xpath("//input[@value='Next: Delivery']")).click();

        driver.findElement(By.xpath("//input[@value='Next: Payment']")).click();

        driver.findElement(By.id("creditCardTitle")).click();
    }

}
