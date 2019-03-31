package api;

import entity.Product;
import exceptions.ProductCountNegativeException;
import exceptions.ProductNameEmptyException;
import exceptions.ProductPriceNoPositiveException;
import exceptions.ProductWeightNoPositiveException;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws IOException;
    Integer getCountProducts() throws IOException;
    Product getProductByProductName(String productName) throws IOException;

    boolean isProductOnWarehouse(String productName) throws IOException;
    boolean isProductExist(String productName) throws IOException;
    boolean isProductExist(Long productId) throws IOException;

    boolean saveProduct(Product product) throws IOException, ProductNameEmptyException, ProductWeightNoPositiveException, ProductCountNegativeException, ProductPriceNoPositiveException;
    void removeProduct(String productName) throws Exception;
}