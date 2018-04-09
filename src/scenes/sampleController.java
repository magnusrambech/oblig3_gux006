package scenes;

import DAO.ConnectionDAO;
import DAO.InvoiceDAO;
import entities.Invoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


public class sampleController {
    public void openInvoiceWindow(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InvoiceWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Invoice");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't load new window");
        }

    }
    public void openCreateCustomerWindow(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCustomerWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new customer");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't load new window");
        }
    }
    public void openCreateAddressWindow(ActionEvent actionEvent){
        System.out.println("test1");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateAddressWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new Address");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't load new window");
        }

    }
    public void openCreateProductWindow(ActionEvent actionEvent){
        System.out.println("test2");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateProductWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new Product");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't load new window");
        }
    }
    public void openCreateCategoryWindow(ActionEvent actionEvent){
        System.out.println("test3");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCategoryWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new Category");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't load new window");
        }
    }
    public void openCreateInvoiceWindow(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateInvoiceWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new Invoice");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't load new window");
        }
        System.out.println("test4");
    }

}
