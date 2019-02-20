package service;

import api.ProductDao;
import api.ProductService;
import dao.ProductDaoImpl;
import entity.Product;

import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static ProductServiceImpl instance = null;
    private ProductDao productDao = new ProductDaoImpl("products.data", "PRODUCT");

    private ProductServiceImpl() throws IOException {
    }

    public static ProductServiceImpl getInstance() throws IOException {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }

        return instance;
    }

    public List<Product> getAllProducts() throws IOException {
        return productDao.getAllProducts();
    }

    public Integer getCountProducts() throws IOException {
        return getAllProducts().size();
    }

    public Product getProductByProductName(String productName) throws IOException {
        return productDao.getProductByProductName(productName);
    }

    public boolean isProductExist(String productName) {
        Product product = null;

        try {
            product = productDao.getProductByProductName(productName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (product == null) return false;

        return true;
    }

    public boolean isProductExist(Long productId) {
        Product product = null;

        try {
            product = productDao.getProductById(productId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (product == null) return false;

        return true;
    }

    public boolean isProductOnWarehouse(String productName) {
        try {
            for(Product product : getAllProducts()) {
                if (isProductExist(productName) && product.getProductCount() > 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}