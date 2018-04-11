package scenes.editAddresses;

import DAO.AddressDAO;
import entities.Address;
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

public class EditAddressesWindowController {
    public Label updateIdLbl;
    public TextField updateStreetNumberFld;
    public TextField updateStreetNameFld;
    public TextField updatePostalCodeFld;
    public TextField updatePostalTownFld;
    public VBox addressesVBox;

    Connection conn = Main.conn;
    AddressDAO addressDAO;

    public void initialize(){
        listAllAddresses();
    }

    public void listAllAddresses(){
        //Nullstieller listen til å begynne med
        addressesVBox.getChildren().clear();
        //Finner alle addresser
        addressDAO = new AddressDAO(conn);
        ArrayList<Address> addresses = addressDAO.findAllAddresses();
        for(Address a:addresses){
            listAddress(a);
        }
    }
    public void listAddress(Address a){
        // Lager nye labels for all addresse info
        Label idLbl = new Label();
        Label nameLbl = new Label();
        Label numberLbl = new Label();
        Label postalCodeLbl = new Label();
        Label postalTownLbl = new Label();
        Button editBtn = new Button("Edit");
        HBox hBox = new HBox();

        //Setter teksten på labelene
        idLbl.setText(String.valueOf(a.getId()));
        nameLbl.setText(a.getStreetName());
        numberLbl.setText(a.getStreetNumber());
        postalCodeLbl.setText(a.getPostalCode());
        postalTownLbl.setText(a.getPostalTown());

        //Setter bredden på labelene
        idLbl.setPrefWidth(20);
        nameLbl.setPrefWidth(190);
        numberLbl.setPrefWidth(100);
        postalCodeLbl.setPrefWidth(130);
        postalTownLbl.setPrefWidth(50);


        //Gir "edit"-knappen en funkjson
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                updateIdLbl.setText(idLbl.getText());
                updateStreetNameFld.setText(nameLbl.getText());
                updateStreetNumberFld.setText(numberLbl.getText());
                updatePostalCodeFld.setText(postalCodeLbl.getText());
                updatePostalTownFld.setText(postalTownLbl.getText());
            }
        });

        hBox.getChildren().add(idLbl);
        hBox.getChildren().add(nameLbl);
        hBox.getChildren().add(numberLbl);
        hBox.getChildren().add(postalCodeLbl);
        hBox.getChildren().add(postalTownLbl);
        hBox.getChildren().add(editBtn);

        addressesVBox.getChildren().add(hBox);

    }
    public void commitChanges(){
        Address address = new Address();
        address.setId(Integer.parseInt(updateIdLbl.getText()));
        address.setStreetName(updateStreetNameFld.getText());
        address.setStreetNumber(updateStreetNumberFld.getText());
        address.setPostalTown(updatePostalTownFld.getText());
        address.setPostalCode(updatePostalCodeFld.getText());

        //Legger til endringene i databasen og laster listen på nytt
        addressDAO.alterAddress(address);
        listAllAddresses();
    }
}
