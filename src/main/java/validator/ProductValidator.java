package validator;

import entity.Product;
import exceptions.ProductCountNegativeException;
import exceptions.ProductNameEmptyException;
import exceptions.ProductPriceNoPositiveException;
import exceptions.ProductWeightNoPositiveException;

public class ProductValidator {
    private static ProductValidator instance = null;

    public ProductValidator() {
    }

    public static ProductValidator getInstance(){
        if(instance == null){
            instance = new ProductValidator();
        }
        return instance;
    }

    public boolean isValidate(Product product) throws
            ProductNameEmptyException, ProductWeightNoPositiveException,
            ProductCountNegativeException, ProductPriceNoPositiveException {
        if(isPriceNoPositive(product.getPrice())){
            throw new ProductPriceNoPositiveException("Product price is no positive.");
        }
        if (isCountNegative(product.getProductCount())){
            throw new ProductCountNegativeException("Product count is less than 0.");
        }
        if(isWeightNoPositive(product.getWeight())){
            throw new ProductWeightNoPositiveException("Product weight is less or equals 0.");
        }
        if (isNameEmpty(product.getProductName())){
            throw new ProductNameEmptyException("Product name cannot be empty.");
        }
        return true;
    }
    private boolean isNameEmpty(String productName){
        return productName.length() == 0;
    }
    private boolean isWeightNoPositive(Float weight){
        return weight <= 0.0f;
    }
    private boolean isCountNegative(Integer productCount){
        return productCount < 0;
    }
    private boolean isPriceNoPositive(Float price){
        return price <= 0.0f;
    }
}
