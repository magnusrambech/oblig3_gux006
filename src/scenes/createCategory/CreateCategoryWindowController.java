package scenes.createCategory;


import DAO.CategoryDAO;
import entities.Category;
import javafx.scene.control.TextField;
import scenes.Main;

import java.sql.Connection;

public class CreateCategoryWindowController {
    public TextField newCatIdFld;
    public TextField newCatNameFld;

    public Connection conn = Main.conn;
    CategoryDAO catDao;

    public void createCategory(){
        Category category = new Category();
        category.setId(Integer.parseInt(newCatIdFld.getText()));
        category.setName(newCatNameFld.getText());

        //Oppreter en ny kategori i databasem
        catDao = new CategoryDAO(conn);
        catDao.insertNewCategory(category);
    }
}
