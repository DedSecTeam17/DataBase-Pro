package sample.MarketModel;

public class Transaction {


    private int id;
    private String userEmail;
    private String productName;
    private int sellingPrice;
    private int quantity;
    private int profit;
    private String created_at;
    private  String user_email;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public Transaction(TransactionBuilder transactionBuilder) {
        setId(transactionBuilder.id);
        setUserEmail(transactionBuilder.user_email);
        setProductName(transactionBuilder.productName);
        setProfit(transactionBuilder.profit);
        setCreated_at(transactionBuilder.created_at);
        setSellingPrice(transactionBuilder.sellingPrice);
        setQuantity(transactionBuilder.quantity);
        setUser_email(transactionBuilder.user_email);
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
        private  String user_email;



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
        public TransactionBuilder email(String user_email) {
            this.user_email = user_email;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
