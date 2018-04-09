package scenes;

import DAO.CustomerDAO;
import entities.Customer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;

public class EditCustomerWindowController {
    public Label updateIdLbl;
    public TextField updateNameLbl;
    public TextField updateAddressLbl;
    public TextField updatePhoneLbl;
    public TextField updateBillingLbl;

    public VBox customerVBox;


    Connection conn = Main.conn;
    CustomerDAO custDAO;

    public void initialize(){
        listAllCustomers();
    }
    public void listAllCustomers(){
        customerVBox.getChildren().clear();
        custDAO = new CustomerDAO(conn);
        ArrayList<Customer> customers = custDAO.findAllCustomers();
        for(Customer e:customers){
            listCustomer(e);
        }
    }
    public void listCustomer(Customer c){
        // Lager nye labels for all customer info
        Label idLbl = new Label();
        Label nameLbl = new Label();
        Label addressLbl = new Label();
        Label phoneLbl = new Label();
        Label billingLbl = new Label();
        Button editBtn = new Button("Edit");
        HBox hBox = new HBox();

        //Setter teksten på labelene
        idLbl.setText(String.valueOf(c.getCustId()));
        nameLbl.setText(c.getName());
        addressLbl.setText(String.valueOf(c.getAdressId()));
        phoneLbl.setText(c.getPhoneNr());
        billingLbl.setText(c.getBillingAcc());

        //Setter bredden på labelene
        idLbl.setPrefWidth(20);
        nameLbl.setPrefWidth(160);
        addressLbl.setPrefWidth(110);
        phoneLbl.setPrefWidth(120);
        billingLbl.setPrefWidth(100);

        //Gir "edit"-knappen en funkjson
        editBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                updateIdLbl.setText(idLbl.getText());
                updateNameLbl.setText(nameLbl.getText());
                updateAddressLbl.setText(addressLbl.getText());
                updatePhoneLbl.setText(phoneLbl.getText());
                updateBillingLbl.setText(billingLbl.getText());
            }
        });

        //Putter labelene inn i en HBox som så legges itl VBoxen i vinduet
        hBox.getChildren().add(idLbl);
        hBox.getChildren().add(nameLbl);
        hBox.getChildren().add(addressLbl);
        hBox.getChildren().add(phoneLbl);
        hBox.getChildren().add(billingLbl);
        hBox.getChildren().add(editBtn);

        customerVBox.getChildren().add(hBox);
    }
    public void commitChanges(){
        Customer customer = new Customer();
        customer.setName(updateNameLbl.getText());
        customer.setCustId(Integer.parseInt(updateIdLbl.getText()));
        customer.setAdressId(Integer.parseInt(updateAddressLbl.getText()));
        customer.setPhoneNr(updatePhoneLbl.getText());
        customer.setBillingAcc(updateBillingLbl.getText());
        custDAO.alterCustomer(customer);
        listAllCustomers();
    }


}
