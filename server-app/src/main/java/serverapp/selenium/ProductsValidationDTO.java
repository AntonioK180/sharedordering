package serverapp.selenium;

import serverapp.models.Product;

import java.util.List;

public class ProductsValidationDTO {

    private String storeName;
    private List<Product> products;


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
