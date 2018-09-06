package sample.MarketModel;

public class Product {


    private String productName;
    private String productPrice;
    private String productionDate;
    private String expiredDate;
    private String productedCompany;
    private int quantity;

    public Product(ProductBuilder productBuilder) {
        setProductName(productBuilder.productName);
        setProductPrice(productBuilder.productPrice);
        setProductionDate(productBuilder.productionDate);
        setExpiredDate(productBuilder.expiredDate);
        setProductedCompany(productBuilder.productedCompany);
        setQuantity(productBuilder.quantity);

    }


    public ProductBuilder newProduct() {
        return new ProductBuilder();
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getProductedCompany() {
        return productedCompany;
    }

    public void setProductedCompany(String productedCompany) {
        this.productedCompany = productedCompany;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static class ProductBuilder {
        private String productName;
        private String productPrice;
        private String productionDate;
        private String expiredDate;
        private String productedCompany;
        private int quantity;


        public ProductBuilder productName(String productName) {
            this.productName = productName;
            return this;

        }

        public ProductBuilder productPrice(String productPrice) {
            this.productPrice = productPrice;
            return this;

        }

        public ProductBuilder productionDate(String productionDate) {
            this.productionDate = productionDate;
            return this;

        }

        public ProductBuilder expiredDate(String expiredDate) {
            this.expiredDate = expiredDate;
            return this;

        }

        public ProductBuilder productedCompany(String productedCompany) {
            this.productedCompany = productedCompany;
            return this;

        }

        public ProductBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;

        }


        public Product build() {
            return new Product(this);
        }
    }
}
