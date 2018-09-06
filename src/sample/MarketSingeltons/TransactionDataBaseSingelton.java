package sample.MarketSingeltons;

public class TransactionDataBaseSingelton {
    private static TransactionDataBaseSingelton ourInstance = new TransactionDataBaseSingelton();

    public static TransactionDataBaseSingelton getInstance() {
        return ourInstance;
    }

    private TransactionDataBaseSingelton() {
    }
}
