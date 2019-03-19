package service;

import api.ProductDao;
import api.ProductService;
import dao.ProductDaoImpl;
import entity.Product;
import validator.ProductValidator;

import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static ProductServiceImpl instance = null;
    private ProductDao productDao = ProductDaoImpl.getInstance();
    private ProductValidator productValidator = ProductValidator.getInstance();

    private ProductServiceImpl() {
    }

    public static ProductServiceImpl getInstance() {
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
        List<Product> products = getAllProducts();

        for (Product product : products) {
            boolean isFoundProduct = product.getProductName().equals(productName);
            if (isFoundProduct) {
                return product;
            }
        }
        return null;
    }

    public Product getProductById(Long productId) throws IOException {
        List<Product> products = getAllProducts();

        for (Product product : products) {
            boolean isFoundProduct = product.getId().equals(productId);
            if (isFoundProduct) {
                return product;
            }
        }
        return null;
    }

    public boolean isProductExist(String productName) {
        Product product = null;

        try {
            product = getProductByProductName(productName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (product == null) return false;

        return true;
    }

    public boolean isProductExist(Long productId) {
        Product product = null;

        try {
            product = getProductById(productId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (product == null) return false;

        return true;
    }

    public boolean isProductOnWarehouse(String productName) {
        try {
            for (Product product : getAllProducts()) {
                if (isProductExist(productName) && product.getProductCount() > 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveProduct(Product product) {
        try {
            if (productValidator.isValidate(product)) {
                productDao.saveProduct(product);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}