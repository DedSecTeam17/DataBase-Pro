package sample.MarketModel;

public class Product {


    private String productName;
    private int productPrice;
    private String productionDate;
    private String expiredDate;
    private String productedCompany;
    private int quantity;
    private  String image_path;
    private  int Cat_id;

    public int getCat_id() {
        return Cat_id;
    }

    public void setCat_id(int Cat_id) {
        this.Cat_id = Cat_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Product(ProductBuilder productBuilder) {
        setProductName(productBuilder.productName);
        setProductPrice(productBuilder.productPrice);
        setProductionDate(productBuilder.productionDate);
        setExpiredDate(productBuilder.expiredDate);
        setProductedCompany(productBuilder.productedCompany);
        setQuantity(productBuilder.quantity);
        setImage_path(productBuilder.image_path);
        setCat_id(productBuilder.Cat_id);
    }


    public static ProductBuilder newProduct() {
        return new ProductBuilder();
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
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
        private int productPrice;
        private String productionDate;
        private String expiredDate;
        private String productedCompany;
        private int quantity;
        private String image_path;
        private  int Cat_id;



        public ProductBuilder productName(String productName) {
            this.productName = productName;
            return this;

        }

        public ProductBuilder productPrice(int productPrice) {
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
        public ProductBuilder imagePath(String image_path) {
            this.image_path = image_path;
            return this;
        }

        public ProductBuilder Cat_id(int Cat_id) {
            this.Cat_id = Cat_id;
            return this;
        }



        public Product build() {
            return new Product(this);
        }
    }
}
