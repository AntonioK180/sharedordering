package serverapp.selenium.waterstones;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import serverapp.models.Product;
import serverapp.selenium.StoreURLParser;
import serverapp.selenium.StoreURLParserBuilder;
import serverapp.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaterStonesURLParser implements StoreURLParser {

    private WaterstonesHelper waterstonesHelper;
    private String storeName = "waterstones.com";
    public WebDriver driver;

    private void driverConfig() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        waterstonesHelper = new WaterstonesHelper(driver);
    }

    @Override
    public List<Product> checkLinks(List<Product> productsList) {
        driverConfig();
        ArrayList<Product> checkedProducts = new ArrayList<>();

        for (Product product : productsList) {
            try {
                waterstonesHelper.visitItem(product.getUrl());
                double price = waterstonesHelper.getPrice();
                String displayName = waterstonesHelper.getDisplayName();
                product.setPrice(price);
                product.setDisplayName(displayName);
                checkedProducts.add(product);
            } catch(Exception e) {
                System.out.println("WATERSTONES URL CHECK ERROR: " + e.getMessage());
                product.setId((long) -1);
                checkedProducts.add(product);
            }
        }

        driver.close();

        return checkedProducts;
    }


    @Override
    public void executeOrder(List<Product> productsList) {
        driverConfig();
        waterstonesHelper.goToHomePage();
        waterstonesHelper.signInAccount();

        for (Product product : productsList) {
            System.out.println("Ordering from: " + product.getUrl());
            waterstonesHelper.addToCart(product.getUrl());
        }

        waterstonesHelper.proceedToCheckout();

        driver.close();

    }

    @Override
    public String getStoreName() {
        return this.storeName;
    }
}
