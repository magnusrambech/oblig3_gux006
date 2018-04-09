package scenes;

import DAO.CustomerDAO;
import entities.Customer;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class CreateCustomerWindowController {
    public TextField newCustNameFld;
    public TextField newCustIdFld;
    public TextField newCustAddressFld;
    public TextField newCustPhoneFld;
    public TextField newCustBillAccFld;

    Connection conn = Main.conn;
    CustomerDAO custDao;

    public void createCustomer(){
        Customer customer = new Customer();
        customer.setName( newCustNameFld.getText());
        customer.setCustId(Integer.parseInt(newCustIdFld.getText()));
        customer.setPhoneNr( newCustPhoneFld.getText());
        customer.setBillingAcc(newCustBillAccFld.getText());
        customer.setAdressId(Integer.parseInt(newCustIdFld.getText()));

        //Oppretter ny customer i databasen
        custDao = new CustomerDAO(conn);
        custDao.insertNewCustomer(customer);

    }
}
