package serverapp.selenium.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import serverapp.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonOrder {
    public static WebDriver driver = null;
    private String storeName = "amazon.com";

    private String chromedriverPath = "drivers/chromedriver.exe";

    public String getStoreName() {
        return storeName;
    }

    public AmazonOrder() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
    }

    public void doStuff(List<Product> linkList) {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        options.addArguments("--no-sandbox");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        AmazonHelper amazon = new AmazonHelper(driver);

        amazon.goToHomePage();
        amazon.signInAccount();

        for(Product link : linkList) {
            System.out.println("Ordering from: " + link.getUrl());

            amazon.addToCart(link.getUrl());
        }

        driver.navigate().to("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart");
        amazon.proceedToCheckout();

        driver.close();
    }

    public List<Product> checkLinks(List<Product> linkList) {
        ArrayList<Product> validProducts = new ArrayList<>();

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        options.addArguments("--no-sandbox");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        AmazonHelper amazon = new AmazonHelper(driver);
        amazon.goToHomePage();

        for (Product product : linkList) {
            try {
                double price = amazon.visitItem(product.getUrl());

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
}
