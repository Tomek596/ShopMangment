package api;

import entity.Product;

import java.io.IOException;

public interface ProductService {

    Integer getCountProducts() throws IOException;

    Product getProductByProductName(String productName) throws IOException;

    boolean isProductOnWarehouse(String productName) throws IOException;

    boolean isProductExist(String productName) throws IOException;

    boolean isProductExist(Long productId);
}