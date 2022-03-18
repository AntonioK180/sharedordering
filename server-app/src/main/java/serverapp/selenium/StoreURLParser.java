package serverapp.selenium;

import org.springframework.beans.factory.annotation.Autowired;
import serverapp.models.Product;
import serverapp.services.ProductService;

import java.util.List;

public interface StoreURLParser {

    String chromedriverPath = "drivers/chromedriver.exe";
    String credentialsFilePath = "src/main/resources/credentials.yaml";

    default List<Product> checkLinks(List<Product> productsList) {
        return productsList;
    }

    void executeOrder(List<Product> productsList);

    String getStoreName();

}
