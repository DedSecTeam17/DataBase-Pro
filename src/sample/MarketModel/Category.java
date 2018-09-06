package sample.MarketModel;
// Builder
public class Category {
    private  int category_id;
    private  String category_name;
    public  Category(CategoryBuilder categoryBuilder)
    {
        setCategory_id(categoryBuilder.category_id);
        setCategory_name(categoryBuilder.category_name);
    }
    public  static  CategoryBuilder newCategory()
    {
        return  new CategoryBuilder();
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }


    public  static  class  CategoryBuilder
    {

        private  int category_id;
        private  String category_name;


        public  CategoryBuilder categoryID(int category_id)
        {
            this.category_id=category_id;
            return  this;
        }
        public  CategoryBuilder categoryName(String category_name)
        {
            this.category_name=category_name;
            return  this;
        }
        public  Category build(){
            return  new Category(this);
        }




    }
}
