package serverapp.selenium.waterstones;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import serverapp.models.Product;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaterstonesOrder {

    private WaterstonesHelper waterstonesHelper;

    public static WebDriver driver = null;
    private String storeName = "waterstones.com";

    private String chromedriverPath = "drivers/chromedriver.exe";

    public String getStoreName() {
        return storeName;
    }


    public double checkLinks(List<Product> productsList) {
        double sum = 0;

        System.out.println("Working on links" + productsList);

        System.setProperty("webdriver.chrome.driver", chromedriverPath);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        waterstonesHelper = new WaterstonesHelper(driver);
        waterstonesHelper.goToHomePage();

        for (Product product : productsList) {
            double price = waterstonesHelper.visitItem(product.getUrl());
            product.setPrice(price);
            sum += price;
        }

        driver.close();

        return sum;
    }

    public void makeAnOrder(List<Product> productsList) {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

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
