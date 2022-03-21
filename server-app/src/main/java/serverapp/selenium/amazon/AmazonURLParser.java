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

    private void driverConfig() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        amazonHelper = new AmazonHelper(driver);
    }

    @Override
    public List<Product> checkLinks(List<Product> productsList) {
        driverConfig();
        ArrayList<Product> checkedProducts = new ArrayList<>();

        amazonHelper.goToHomePage();

        for (Product product : productsList) {
            try {
                amazonHelper.visitItem(product.getUrl());
                double price = amazonHelper.getPrice();
                String displayName = amazonHelper.getDisplayName();
                product.setPrice(price);
                product.setDisplayName(displayName);
                checkedProducts.add(product);
            } catch(Exception e) {
                System.out.println("AMAZON URL CHECK ERROR: " + e.getMessage());
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
        amazonHelper.goToHomePage();
        amazonHelper.signInAccount();

        for(Product product : productsList) {
            System.out.println("Ordering from: " + product.getUrl());

            amazonHelper.addToCart(product.getUrl());
        }

        driver.navigate().to("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart");
        amazonHelper.proceedToCheckout();

        driver.close();
    }

    @Override
    public String getStoreName() {
        return this.storeName;
    }
}
