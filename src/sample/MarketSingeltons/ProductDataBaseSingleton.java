package sample.MarketSingeltons;

import sample.MarketModel.Product;

import java.util.List;

public class ProductDataBaseSingleton {
    private static ProductDataBaseSingleton ourInstance = new ProductDataBaseSingleton();

    public static ProductDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private ProductDataBaseSingleton() {
    }

    public  String addProduct(Product Product)
    {
        return  "message come from server";

    }
    public  String deleteProduct(Product Product)
    {
        return  "message come from server";

    }
    public  String updateProduct(Product Product)
    {
        return  "message come from server";

    }
    public List<Product> getAllProduct()
    {
        return  null;
    }
}
