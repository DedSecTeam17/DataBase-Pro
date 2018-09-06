package sample.MarketModel;

public class Transaction {


    private String productName;
    private int profit;
    private String created_at;

    public Transaction(TransactionBuilder transactionBuilder) {
        setProductName(transactionBuilder.productName);
        setProfit(transactionBuilder.profit);
        setCreated_at(transactionBuilder.created_at);
    }

    public static TransactionBuilder newTransaction() {
        return new TransactionBuilder();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    public static class TransactionBuilder {

        private String productName;
        private int profit;
        private String created_at;


        public TransactionBuilder productName(String productName) {

            this.productName = productName;
            return this;
        }

        public TransactionBuilder profit(int profit) {

            this.profit = profit;
            return this;
        }

        public TransactionBuilder created_at(String created_at) {
            this.created_at = created_at;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
