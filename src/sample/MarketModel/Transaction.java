package sample.MarketModel;

public class Transaction {


    private int id;
    private String productName;
    private int sellingPrice;
    private int quantity;
    private int profit;
    private String created_at;

    public Transaction(TransactionBuilder transactionBuilder) {
        setId(transactionBuilder.id);
        setProductName(transactionBuilder.productName);
        setProfit(transactionBuilder.profit);
        setCreated_at(transactionBuilder.created_at);
        setSellingPrice(transactionBuilder.sellingPrice);
        setQuantity(transactionBuilder.quantity);
    }

    public static TransactionBuilder newTransaction() {
        return new TransactionBuilder();
    }

    //#region Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getProfit() {
        return profit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    //#endregion

    public static class TransactionBuilder {
        private int id;
        private String productName;
        private int profit;
        private String created_at;
        private int sellingPrice;
        private int quantity;


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

        public TransactionBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public TransactionBuilder sellingPrioce(int sellingprice) {
            this.sellingPrice = sellingprice;
            return this;
        }

        public TransactionBuilder id(int id) {
            this.id = id;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
