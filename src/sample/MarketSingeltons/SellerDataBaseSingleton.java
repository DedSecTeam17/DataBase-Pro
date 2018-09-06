package sample.MarketSingeltons;

import sample.MarketModel.User;

import java.util.List;

public class SellerDataBaseSingleton {
    private static SellerDataBaseSingleton ourInstance = new SellerDataBaseSingleton();

    public static SellerDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private SellerDataBaseSingleton() {
    }

    public  String addSeller(User User)
    {
        return  "message come from server";

    }
    public  String deleteSeller(User User)
    {
        return  "message come from server";

    }
    public  String updateSeller(User User)
    {
        return  "message come from server";

    }

    public List<User> getAllSeller()
    {
        return  null;
    }
}
