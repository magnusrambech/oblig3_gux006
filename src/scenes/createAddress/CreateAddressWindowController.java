package scenes.createAddress;

import DAO.AddressDAO;
import entities.Address;
import javafx.scene.control.TextField;
import scenes.Main;

import java.sql.Connection;

public class CreateAddressWindowController {
    public TextField newAddIdFld;
    public TextField newAddStNumFld;
    public TextField newAddNameFld;
    public TextField newAddPCodeFld;
    public TextField newAddPTownFld;

    public Connection conn = Main.conn;
    AddressDAO addDao;

    /**
     * Oppretter ny addresse basert på hva som er skrevet i tekstfeltene
     */
    public void createAddress(){
        Address address = new Address();
        address.setId(Integer.parseInt(newAddIdFld.getText()));
        address.setStreetNumber(newAddStNumFld.getText());
        address.setStreetName(newAddNameFld.getText());
        address.setPostalTown(newAddPTownFld.getText());
        address.setPostalCode(newAddPCodeFld.getText());

        //Oppretter en ny addresse i databasen
        addDao = new AddressDAO(conn);
        addDao.insertNewAddress(address);
    }
}
