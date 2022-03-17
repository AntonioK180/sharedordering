package serverapp.selenium.waterstones;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import serverapp.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaterstonesOrder {

    private WaterstonesHelper waterstonesHelper;

    public static WebDriver driver = null;
    private String storeName = "waterstones.com";

    private String chromedriverPath = "drivers/chromedriver.exe";

    public WaterstonesOrder() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
    }

    public String getStoreName() {
        return storeName;
    }


    public List<Product> checkLinks(List<Product> productsList) {
        ArrayList<Product> validProducts = new ArrayList<>();

        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("headless");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        waterstonesHelper = new WaterstonesHelper(driver);
        waterstonesHelper.goToHomePage();

        for (Product product : productsList) {
            try {
                double price = waterstonesHelper.visitItem(product.getUrl());
                product.setPrice(price);
                validProducts.add(product);
            } catch(Exception e) {
                System.out.println("ERROR FROM ME: " + e.getMessage());
                continue;
            }

        }

        driver.close();

        return validProducts;
    }

    public void makeAnOrder(List<Product> productsList) {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        waterstonesHelper = new WaterstonesHelper(driver);

        waterstonesHelper.goToHomePage();
        waterstonesHelper.signInAccount();

        for (Product product : productsList) {
            System.out.println("Ordering from: " + product.getUrl());
            waterstonesHelper.addToCart(product.getUrl());
        }

        waterstonesHelper.proceedToCheckout();

        driver.close();
    }

}
