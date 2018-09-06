package sample.MarketSingeltons;

public class ProductDataBaseSingleton {
    private static ProductDataBaseSingleton ourInstance = new ProductDataBaseSingleton();

    public static ProductDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private ProductDataBaseSingleton() {
    }
}
