package serverapp.selenium.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import serverapp.models.Product;
import serverapp.selenium.StoreURLParser;
import serverapp.selenium.waterstones.WaterstonesHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonURLParser implements StoreURLParser {

    private AmazonHelper amazonHelper;
    private String storeName = "amazon.com";
    public WebDriver driver;

    public AmazonURLParser() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        amazonHelper = new AmazonHelper(driver);
    }

    @Override
    public List<Product> checkLinks(List<Product> productsList) {
        ArrayList<Product> validProducts = new ArrayList<>();

        amazonHelper.goToHomePage();

        for (Product product : productsList) {
            try {
                double price = amazonHelper.visitItem(product.getUrl());

                System.out.println("Item costs: " + price);

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

    @Override
    public void executeOrder(List<Product> productsList) {

    }

    @Override
    public String getStoreName() {
        return this.storeName;
    }
}
