package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String fileName = "products.data";
    private static ProductDao instance = null;

    private ProductDaoImpl() {
        try {
            FileUtils.createNewFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }

        return instance;
    }


    public void saveProduct(Product product) throws IOException {
        List<Product> products = getAllProducts();
        products.add(product);
        saveProducts(products);
    }

    public void saveProducts(List<Product> products) throws FileNotFoundException {
        FileUtils.clearFile(fileName);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, true));
        for(Product product : products) {
            printWriter.write(product.toString() + "\n");
        }
        printWriter.close();
    }

    public void removeProductById(Long productId) throws IOException {
        List<Product> products = getAllProducts();

        for(int i=0;i<products.size(); i++) {
            boolean isFoundProduct = products.get(i).getId().equals(productId);
            if (isFoundProduct) {
                products.remove(i);
            }
        }

        saveProducts(products);
    }

    public void removeProductByName(String productName) throws IOException {
        List<Product> products = getAllProducts();

        for(int i=0;i<products.size(); i++) {
            boolean isFoundProduct = products.get(i).getProductName().equals(productName);
            if (isFoundProduct) {
                products.remove(i);
            }
        }

        saveProducts(products);
    }

    public List<Product> getAllProducts() throws IOException {
        List<Product> products = new ArrayList<Product>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        String readLine = bufferedReader.readLine();
        while(readLine != null) {
            Product product = ProductParser.stringToProduct(readLine);
            if (product != null) {
                products.add(product);
            }
            readLine = bufferedReader.readLine();
        }
        bufferedReader.close();

        return products;
    }


}