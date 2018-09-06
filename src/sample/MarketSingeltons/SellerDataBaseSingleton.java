package sample.MarketSingeltons;

public class SellerDataBaseSingleton {
    private static SellerDataBaseSingleton ourInstance = new SellerDataBaseSingleton();

    public static SellerDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private SellerDataBaseSingleton() {
    }
}
