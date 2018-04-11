package scenes.editProducts;

import DAO.ProductDAO;
import entities.Product;
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

public class EditProductsWindowController {
    public Label updateIdLbl;
    public TextField updateNameLbl;
    public TextField updateDescLbl;
    public TextField updatePriceLbl;
    public TextField updateCatLbl;

    public VBox productVBox;

    Connection conn = Main.conn;
    ProductDAO productDAO = new ProductDAO(conn);

    public void initialize(){
        listAllProducts();
    }

    public void listAllProducts(){
        // Nullstiller listen fra forrige visning
        productVBox.getChildren().clear();
        //Finner alle produktene
        ArrayList<Product> products = productDAO.findAllProducts();
        for(Product p: products){
            listProduct(p);
        }
    }
    public void listProduct(Product p){
        // Lager nye labels for all product info
        Label idLbl = new Label();
        Label nameLbl = new Label();
        Label descLbl = new Label();
        Label priceLbl = new Label();
        Label catLbl = new Label();
        Button editBtn = new Button("Edit");
        HBox hBox = new HBox();

        //Setter teksten på labelene
        idLbl.setText(String.valueOf(p.getId()));
        nameLbl.setText(p.getName());
        descLbl.setText(p.getDesc());
        priceLbl.setText(String.valueOf(p.getPrice()));
        catLbl.setText(String.valueOf(p.getCategory()));

        //Setter bredden på labelene
        idLbl.setPrefWidth(20);
        nameLbl.setPrefWidth(160);
        descLbl.setPrefWidth(165);
        priceLbl.setPrefWidth(120);
        catLbl.setPrefWidth(50);
        //Gir "edit"-knappen en funkjson
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                updateIdLbl.setText(idLbl.getText());
                updateNameLbl.setText(nameLbl.getText());
                updateDescLbl.setText(descLbl.getText());
                updatePriceLbl.setText(priceLbl.getText());
                updateCatLbl.setText(catLbl.getText());
            }
        });

        hBox.getChildren().add(idLbl);
        hBox.getChildren().add(nameLbl);
        hBox.getChildren().add(descLbl);
        hBox.getChildren().add(priceLbl);
        hBox.getChildren().add(catLbl);
        hBox.getChildren().add(editBtn);

        productVBox.getChildren().add(hBox);
    }

    public void commitChanges(){
        Product product = new Product();
        product.setId(Integer.parseInt(updateIdLbl.getText()));
        product.setName(updateNameLbl.getText());
        product.setDesc(updateDescLbl.getText());
        product.setPrice(Float.parseFloat(updatePriceLbl.getText()));
        product.setCategory(Integer.parseInt(updateCatLbl.getText()));

        productDAO.alterProduct(product);
        listAllProducts();

    }
}
