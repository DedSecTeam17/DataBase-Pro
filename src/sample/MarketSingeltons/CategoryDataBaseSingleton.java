package sample.MarketSingeltons;

import sample.MarketModel.Category;

import java.util.List;

public class CategoryDataBaseSingleton {
    private static CategoryDataBaseSingleton ourInstance = new CategoryDataBaseSingleton();

    public static CategoryDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private CategoryDataBaseSingleton() {
    }
    public  String addCategory(Category Category)
    {
        return  "message come from server";

    }
    public  String deleteCategory(Category Category)
    {
        return  "message come from server";

    }
    public  String updateCategory(Category Category)
    {
        return  "message come from server";

    }
    public List<Category> getAllCategory()
    {
        return  null;
    }
}
