package sample.MarketSingeltons;

import sample.MarketModel.User;

import java.util.List;

public class AdminDataBaseSingleton {
    private static AdminDataBaseSingleton ourInstance = new AdminDataBaseSingleton();

    public static AdminDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private AdminDataBaseSingleton() {
    }



    public  String addAdmin(User user)
    {
        return  "message come from server";

    }
    public  String deleteAdmin(User user)
    {
        return  "message come from server";

    }
    public  String updateAdmin(User user)
    {
        return  "message come from server";
    }







}
