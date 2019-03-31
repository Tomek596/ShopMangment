package service;

import api.ProductDao;
import api.ProductService;
import dao.ProductDaoImpl;
import entity.Product;
import exceptions.ProductCountNegativeException;
import exceptions.ProductNameEmptyException;
import exceptions.ProductPriceNoPositiveException;
import exceptions.ProductWeightNoPositiveException;
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

    private Product getProductById(Long productId) throws IOException {
        List<Product> products = getAllProducts();

        for (Product product : products) {
            boolean isFoundProduct = product.getId().equals(productId);
            if (isFoundProduct) {
                return product;
            }
        }
        return null;
    }

    public boolean isProductExist(String productName) throws IOException {
        Product product = getProductByProductName(productName);

        return product != null;
    }

    public boolean isProductExist(Long productId) throws IOException {
        Product product = getProductById(productId);

        return product != null;
    }

    public boolean isProductOnWarehouse(String productName) throws IOException {
        for (Product product : getAllProducts()) {
            if (isProductExist(productName) && product.getProductCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean saveProduct(Product product) throws IOException,
            ProductNameEmptyException, ProductWeightNoPositiveException,
            ProductCountNegativeException, ProductPriceNoPositiveException {

        if (productValidator.isValidate(product)) {
            productDao.saveProduct(product);
            return true;
        }
        return false;
    }

    public void removeProduct(String productName) throws Exception {
        productDao.removeProductByName(productName);
    }
}