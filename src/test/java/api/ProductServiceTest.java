package api;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import org.junit.Assert;
import org.junit.Test;
import service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest {

    @Test
    public void testGetAllProductsPositive() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Cloth(1L, "T-SHIRT", 35.0F, 0.3F, "Black", 4, "XL", "COTTON"));
        products.add(new Boots(1L, "Boots", 35.0F, 0.3F, "Black", 4, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        List<Product> listFromTestClass = productService.getAllProducts();

        Assert.assertEquals(products, listFromTestClass);
    }

    @Test
    public void testGetAllProductsNegative() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Cloth(1L, "T-SHIRT", 35.0F, 0.3F, "Black", 4, "XL", "COTTON"));
        products.add(new Boots(1L, "Boots", 35.0F, 0.3F, "Black", 4, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(new ArrayList<Product>(products));
        products.add(new Cloth(3L, "Trousers", 44.F, 0.f, "Black", 4, "S", "COTTON"));
        List<Product> listFromTestClass = productService.getAllProducts();

        Assert.assertNotEquals(products, listFromTestClass);
    }

    @Test
    public void testGetCountProductsWithProducts() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Cloth(1L, "T-SHIRT", 35.0F, 0.3F, "Black", 4, "XL", "COTTON"));
        products.add(new Boots(1L, "Boots", 35.0F, 0.3F, "Black", 4, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final int result = productService.getCountProducts();

        Assert.assertEquals(2, result);
    }

    @Test
    public void testGetCountProductsWithoutProducts() {
        ProductServiceImpl productService = new ProductServiceImpl();

        final int result = productService.getCountProducts();

        Assert.assertEquals(0, result);
    }

    @Test
    public void testGetProductByProductnameWhenExist() {
        List<Product> products = new ArrayList<Product>();
        Product cloth = new Cloth(1L, "T-SHIRT", 35.0F, 0.3F, "Black", 4, "XL", "COTTON");
        products.add(cloth);
        products.add(new Boots(2L, "Boots", 35.0F, 0.3F, "Black", 4, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final Product product = productService.getProductByProductName("JAKIS-PRODUKT");

        Assert.assertNull(product);
    }

    @Test
    public void testIsProductOnWareHouseWhenIs() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Boots(2L, "Boots", 35.0F, 0.3F, "Black", 4, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isProductOnWareHouse = productService.isProductOnWarehouse("Boots");

        Assert.assertTrue(isProductOnWareHouse);
    }


    @Test
    public void testIsProductOnWareHouseWhenIsNot() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Boots(2L, "Boots", 35.0F, 0.3F, "Black", 0, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isProductOnWareHouse = productService.isProductOnWarehouse("Boots");

        Assert.assertFalse(isProductOnWareHouse);
    }

    @Test
    public void testIsProductExistByNameWhenExist() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Boots(2L, "Boots", 35.0F, 0.3F, "Black", 0, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isProductExist = productService.isProductExist("Boots");

        Assert.assertTrue(isProductExist);
    }

    @Test
    public void testIsProductExistByNameWhenNoExist() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Boots(2L, "Boots", 35.0F, 0.3F, "Black", 0, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isProductExist = productService.isProductExist("Inny produkt");

        Assert.assertFalse(isProductExist);
    }

    @Test
    public void testIsProductExistByIdWhenExist() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Boots(2L, "Boots", 35.0F, 0.3F, "Black", 0, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isProductExist = productService.isProductExist(2L);

        Assert.assertTrue(isProductExist);
    }

    @Test
    public void testIsProductExistByIdWhenNoExist() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Boots(2L, "Boots", 35.0F, 0.3F, "Black", 0, 38, true));

        ProductServiceImpl productService = new ProductServiceImpl(products);
        final boolean isProductExist = productService.isProductExist(5L);

        Assert.assertFalse(isProductExist);
    }
}
