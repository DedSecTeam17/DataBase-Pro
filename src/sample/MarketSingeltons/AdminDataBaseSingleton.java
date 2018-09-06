package sample.MarketSingeltons;

public class AdminDataBaseSingleton {
    private static AdminDataBaseSingleton ourInstance = new AdminDataBaseSingleton();

    public static AdminDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private AdminDataBaseSingleton() {
    }


}
