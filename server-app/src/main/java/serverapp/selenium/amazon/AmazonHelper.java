package serverapp.selenium.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonHelper {

    private String accountEmail = " ";
    private String accountPassword = " ";

    private String startUrl = "https://www.amazon.com/";
    private WebDriver driver;

    public AmazonHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        driver.navigate().to(startUrl);
    }

    public void signInAccount() {
        this.goToHomePage();
        driver.findElement(By.id("nav-link-accountList")).click();

        WebElement emailInput = driver.findElement(By.id("ap_email"));
        emailInput.clear();
        emailInput.sendKeys(accountEmail);

        driver.findElement(By.id("continue")).click();

        WebElement passwordInput = driver.findElement(By.id("ap_password"));
        passwordInput.clear();
        passwordInput.sendKeys(accountPassword);

        driver.findElement(By.id("signInSubmit")).click();
    }

    private double getPrice() throws Exception {
        WebElement price = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[9]/div[6]/div[1]/div[4]/div/div/div/div/div/form/div/div/div/div/div[1]/div[1]/div/div[1]/div/span/span[2]"));

        String priceString = price.getText().substring(1);

        return Double.parseDouble(priceString);
    }

    public double visitItem(String url) throws Exception {
        driver.navigate().to(url);

        return getPrice();
    }

    public void addToCart(String url) {
        driver.navigate().to(url);
        driver.findElement(By.id("add-to-cart-button")).click();
    }

    public void proceedToCheckout() {
        driver.navigate().to("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart");
        driver.findElement(By.name("proceedToRetailCheckout")).click();
    }


}

