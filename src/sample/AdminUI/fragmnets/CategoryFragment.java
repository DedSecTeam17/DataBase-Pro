package sample.AdminUI.fragmnets;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.AdminUI.AdminController;
import sample.Debugging.Log;
import sample.MarketModel.Category;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.awt.*;
import java.time.LocalDate;

public class CategoryFragment 
{
    private  ObservableList<CategorytItem> codeObservableList;
    private static final double COLUMN_WIDTH =70 ;
    private FacadeMarketProvider facadeMarketProvider=new FacadeMarketProvider();
    public  void  onTableItemSelected(JFXTreeTableView treeTableView,JFXTextField name,JFXTextField id)
    {
        treeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log.e("clicked");
                int r_index = treeTableView.getSelectionModel().getSelectedIndex();
                CategorytItem RuleItem = codeObservableList.get(r_index);
                StringProperty _name = RuleItem.cat_name;
                StringProperty _id = RuleItem.cat_id;
                name.setText(_name.getValue());
                id.setText(_id.getValue());
                id.setEditable(false);
                
            }
        });
    }
    public  void  addCategory(JFXTextField name, JFXTextField id, Label id_hint, Label name_hint, JFXTreeTableView categor_tree_table) throws Exception {
        if (!name.getText().equals("") && !id.getText().equals("")) {
            Category    category = Category.newCategory()
                    .categoryID(Integer.parseInt(id.getText()))
                    .categoryName(name.getText())
                    .build();
            facadeMarketProvider.insertCategory(category);
            CategorytTableColumn(categor_tree_table);
            clearFields(name,id,name_hint,id_hint);
        } else {
            UiValidation.validateInput(name, name_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(id, id_hint, "empty filed not allowed", "only numbers allowed", "valid", "num");
        }

    }
    public  void  deleteCategory(JFXTextField name,JFXTextField id,Label id_hint,Label name_hint,JFXTreeTableView treeTableView)
    {


        int r_index = treeTableView.getSelectionModel().getSelectedIndex();
            CategorytItem categorytItem = codeObservableList.get(r_index);
        StringProperty _id = categorytItem.cat_id;
        StringProperty _name = categorytItem.cat_id;
        String cat_id = _id.getValue();
        String cat_name = _name.getValue();
        Category product = Category.newCategory().categoryID(Integer.parseInt(cat_id)).categoryName(cat_name).build();
        facadeMarketProvider.deleteCategory(product);
        clearFields(name,id,name_hint,id_hint);
        try {
            CategorytTableColumn(treeTableView);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    public  void  updateCategory(JFXTextField name,JFXTextField id,Label id_hint,Label name_hint,JFXTreeTableView treeTableView) throws Exception {
        if (!id.getText().equals("") && !name.getText().equals("") ) {
            Category category = Category.newCategory().categoryName(name.getText()).categoryID(Integer.parseInt(id.getText())).build();

            facadeMarketProvider.updateCategory(category);
            CategorytTableColumn(treeTableView);
            clearFields(name,id,name_hint,id_hint);
        } else {
            UiValidation.validateInput(name, name_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(id, id_hint, "empty filed not allowed", "only numbers allowed", "valid", "num");


        }
    }
    private  void  clearFields(JFXTextField cat_name, JFXTextField cat_id, Label cat_name_hint,Label cat_id_hint)
    {
        cat_name.clear();
        cat_id.clear();
        cat_id_hint.setText("");
        cat_name_hint.setText("");
        cat_id.setEditable(true);
    }










    public void CategorytTableColumn(JFXTreeTableView cat_tree_table) throws Exception {
        JFXTreeTableColumn< CategorytItem, String> cat_id = new JFXTreeTableColumn<>("category id");
        cat_id.setPrefWidth(COLUMN_WIDTH);
        cat_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
                return param.getValue().getValue().cat_id;
            }
        });


        JFXTreeTableColumn< CategorytItem, String> cat_name = new JFXTreeTableColumn<>("Category name");
        cat_name.setPrefWidth(COLUMN_WIDTH);
        cat_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
                return param.getValue().getValue().cat_name;
            }
        });



        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

       codeObservableList = FXCollections.observableArrayList();
        for (Category    Category : facadeMarketProvider.getAllCategories()
                ) {

            codeObservableList.add(new  CategorytItem(    Category.getCategory_id(), Category.getCategory_name()));

        }
        final TreeItem< CategorytItem> root = new RecursiveTreeItem< CategorytItem>(codeObservableList, RecursiveTreeObject::getChildren);


        cat_tree_table.getColumns().setAll(cat_id, cat_name);
        cat_tree_table.setRoot(root);
        cat_tree_table.setShowRoot(false);


    }
    class CategorytItem extends RecursiveTreeObject<CategorytItem> {
        StringProperty cat_id;
        StringProperty cat_name;
        public CategorytItem(int cat_id, String cat_name) {
            this.cat_name = new SimpleStringProperty(cat_name);
            this.cat_id = new SimpleStringProperty(String.valueOf(cat_id));

        }
    }



}
