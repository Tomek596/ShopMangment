package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final String fileName;
    private final String productType;


    public ProductDaoImpl(String fileName, String productType) throws IOException{
        this.fileName = fileName;
        this.productType = productType;
        FileUtils.createNewFile(fileName);
    }

    @Override
    public void saveProduct(Product product) throws IOException {
        List<Product> productList = getAllProducts();
        productList.add(product);
        saveProducts(productList);
    }

    public void saveProducts(List<Product> products) throws FileNotFoundException {
        FileUtils.clearFile(fileName);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, true));
        for (Product product : products){
            printWriter.write(product.toString() + "\r\n");
        }
        printWriter.close();
    }

    public void removeProductById(Long productId)  throws IOException{
        List<Product> productList = getAllProducts();

        for(int i= 0; i < productList.size(); i++){
            boolean isFoundProduct = productList.get(i).getId().equals(productId);
            if(isFoundProduct){
                productList.remove(i);
            }
        }
        saveProducts(productList);
    }

    public void removeProductByName(String productName) throws IOException{
        List<Product> productList = getAllProducts();

        for (int i = 0; i < productList.size(); i++){
            boolean isFoundProduct = productList.get(i).getProductName().equals(productName);
            if(isFoundProduct){
                productList.remove(i);
            }
        }
        saveProducts(productList);
    }

    public List<Product> getAllProducts() throws IOException{
        List<Product> productList = new ArrayList<Product>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String readLine = bufferedReader.readLine();
        while (readLine != null){
            Product product = ProductParser.stringToProduct(readLine, productType);
            if(product != null){
                productList.add(product);
            }
        }
        bufferedReader.close();
        return productList;
    }

    public Product getProductById(Long productId) throws IOException{
        List<Product> productList = getAllProducts();

        for(Product product : productList){
            boolean isFoundProduct = product.getId().equals(productId);
            if(isFoundProduct){
                return product;
            }
        }
        return null;
    }

    public Product getProductByProductName(String productName) throws IOException{
        List<Product> productList = getAllProducts();

        for (Product product : productList){
            boolean isFoundProduct = product.getProductName().equals(productName);
            if(isFoundProduct){
                return product;
            }
        }
        return null;
    }
}
