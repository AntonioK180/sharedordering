package serverapp.selenium.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import static serverapp.selenium.StoreURLParser.credentialsFilePath;

public class AmazonHelper {

    private String email_value = " ";
    private String password_value = " ";

    private String startUrl = "https://www.amazon.com/";
    private WebDriver driver;

    public AmazonHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        driver.navigate().to(startUrl);
    }

    private void initializeCredentials() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(credentialsFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);
        this.email_value = (String) data.get("amazonEmail");
        this.password_value = (String) data.get("amazonPassword");
    }

    public void signInAccount() {
        this.initializeCredentials();

        this.goToHomePage();
        driver.findElement(By.id("nav-link-accountList")).click();

        WebElement emailInput = driver.findElement(By.id("ap_email"));
        emailInput.clear();
        emailInput.sendKeys(email_value);

        driver.findElement(By.id("continue")).click();

        WebElement passwordInput = driver.findElement(By.id("ap_password"));
        passwordInput.clear();
        passwordInput.sendKeys(password_value);

        driver.findElement(By.id("signInSubmit")).click();
    }

    public double getPrice() throws Exception {
        WebElement price = driver.findElement(By.cssSelector("#corePrice_feature_div > div > span > span:nth-child(2)"));
        String priceString = price.getText().substring(1);

        return Double.parseDouble(priceString);
    }

    public String getDisplayName() throws Exception {
        WebElement name = driver.findElement(By.cssSelector("#productTitle"));
        return name.getText();
    }

    public void visitItem(String url)  {
        driver.navigate().to(url);
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

