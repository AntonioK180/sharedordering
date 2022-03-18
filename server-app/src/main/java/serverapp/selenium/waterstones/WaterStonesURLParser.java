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
        ArrayList<Product> validProducts = new ArrayList<>();

        for (Product product : productsList) {
            try {
                double price = waterstonesHelper.visitItem(product.getUrl());
                product.setPrice(price);
                validProducts.add(product);
            } catch(Exception e) {
                System.out.println("WATERSTONES URL CHECK ERROR: " + e.getMessage());
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
