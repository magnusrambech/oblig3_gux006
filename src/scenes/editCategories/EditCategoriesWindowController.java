package scenes.editCategories;

import DAO.CategoryDAO;
import entities.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scenes.Main;

import java.sql.Connection;
import java.util.ArrayList;

public class EditCategoriesWindowController {
    public Label updateIdLbl;
    public TextField updateNameFld;
    public VBox categoryVBox;

    public Connection conn = Main.conn;
    public CategoryDAO categoryDAO = new CategoryDAO(conn);

    public void initialize(){
        listAllCategories();
    }

    /**
     * Viser alle kategorier i en liste
     */
    public void listAllCategories(){
        //Nullsteiller listen fra forrige visning
        categoryVBox.getChildren().clear();
        //Finner alle kategoriene
        ArrayList<Category> categories = categoryDAO.findAllCategories();
        for (Category c: categories){
            listCategory(c);
        }
    }

    /**
     * Legger til en kategori på listen
     * @param c kategorien som skal vises
     */
    public void listCategory(Category c){
        //Lager nye labels for all info om en category
        Label idLbl = new Label();
        Label nameLbl = new Label();
        Button editBtn = new Button("Edit");
        HBox hBox = new HBox();

        //Setter teksten på labelene
        idLbl.setText(String.valueOf(c.getId()));
        nameLbl.setText(c.getName());

        //Setter bredden på labelen
        idLbl.setPrefWidth(90);
        nameLbl.setPrefWidth(60);

        //Gir "edit"-knappen en funkjson
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                updateIdLbl.setText(idLbl.getText());
                updateNameFld.setText(nameLbl.getText());
            }
        });

        //Putter labelene inn i en HBox som så legges itl VBoxen i vinduet
        hBox.getChildren().add(idLbl);
        hBox.getChildren().add(nameLbl);
        hBox.getChildren().add(editBtn);

        categoryVBox.getChildren().add(hBox);

    }

    /**
     * Utfører endringer i databasen basert på brukerinput
     */
    public void commitChanges(){
        Category category = new Category();
        category.setId(Integer.parseInt(updateIdLbl.getText()));
        category.setName(updateNameFld.getText());


        //Legger til endringene i databasen og laster listen på nytt
        categoryDAO.alterCategory(category);
        listAllCategories();
    }
}
