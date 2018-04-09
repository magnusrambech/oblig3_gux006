package scenes;

import DAO.*;
import entities.Address;
import entities.Customer;
import entities.Invoice;
import entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InvoiceWindowController {

    public Button nextFakturaButton;
    public Label invoiceIdText;
    public Label customerIdText;
    public Label datoText;
    public VBox summaryBox;
    public Label sumLbl;
    public Label customerNameLbl;
    public Label customerPhoneLbl;
    public Label customerAddressLbl;


    public Connection conn;
    private int currentInvoice;
    private int[] invoiceIDs;

    public InvoiceWindowController() throws FileNotFoundException, SQLException {
        ConnectionDAO connDao = new ConnectionDAO("ryddig.db");
        conn = connDao.getConnection();
    }

    @FXML
    protected void initialize() throws FileNotFoundException, SQLException {
        findInvoiceIds();
        if(invoiceIDs != null){
            loadFaktura(invoiceIDs[0]);
            currentInvoice = 0;
        }
        else{
            System.out.println("Ingen fakturaer å vise!");
        }

    }

    public void loadFaktura(int invoiceId) throws FileNotFoundException, SQLException {

        InvoiceDAO invoiceDAO = new InvoiceDAO(conn);
        Invoice inv = invoiceDAO.createInvoiceFromId(invoiceId);

        if (inv.getId() > 0) {

            //henter ut data fra faktura-tabellen
            datoText.setText(inv.getDate());
            invoiceIdText.setText(String.valueOf(inv.getId()));
            customerIdText.setText(String.valueOf(inv.getCustId()));

            //henter ut data fra kunden med tilsvarende customer_id som i faktura
            CustomerDAO custDAO = new CustomerDAO(conn);
            Customer cust = custDAO.findCustomerById(inv.getCustId());
            customerNameLbl.setText(cust.getName());
            customerPhoneLbl.setText(cust.getPhoneNr());

            // Henter ut adressen til kunden på fakturaen
            AddressDAO addDAO = new AddressDAO(conn);
            Address address = addDAO.createAddressEntityFromId(cust.getAdressId());
            customerAddressLbl.setText(address.getStreetName() +" " + address.getStreetNumber() + "\n" + address.getPostalCode() + " " + address.getPostalTown());

            //henter ut ID til produktene kjøpt av kunden
            InvoiceItemsDAO invoiceItemsDAO = new InvoiceItemsDAO(conn);
            ArrayList<Integer> prodIdList = invoiceItemsDAO.findProductsOnInvoice(inv.getId());
            Iterator prodIDit = prodIdList.iterator();

            //Henter ut alle produktene på fakturaen basert på invoice ID.
            ProductDAO prodDAO = new ProductDAO(conn);
            ArrayList<Product> prodList = new ArrayList<Product>();
            //Lager produktobjekt basert på alle produkt-idene
            while(prodIDit.hasNext()){
                prodList.add(prodDAO.createProductEntitiesFromID((Integer)prodIDit.next()));
            }
            float totalPrice = 0;
            //Putter alle produktene på fakturaen.
            for(Product e : prodList){
                //lager en label for produktnavn
                Label prodNameLbl = new Label();
                prodNameLbl.setText(e.getName());

                Label prodDesc = new Label();
                prodDesc.setText(e.getDesc());

                Label prodPrice = new Label();
                prodPrice.setText(String.valueOf(e.getPrice()));

                //Legger til prisen på totalsummen for fakturaen.
                totalPrice += e.getPrice();

                //Legger elementene inn i oppsummerings-vinduet.
                HBox newBox = new HBox();


                newBox.getChildren().add(prodNameLbl);
                newBox.getChildren().add(prodDesc);
                newBox.getChildren().add(prodPrice);
                newBox.setSpacing(15);
                summaryBox.getChildren().add(newBox);

            }

            sumLbl.setText(String.valueOf(totalPrice));
        }
        else {
            System.out.println("Ingen faktura funnet...");
        }
    }

    public void findInvoiceIds(){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        InvoiceDAO idDAO = new InvoiceDAO(conn);
        List<Invoice> invoices = idDAO.createInvoiceEntities();
        Iterator iterator = invoices.iterator();
        while(iterator.hasNext()){
            Invoice invoice = (Invoice)iterator.next();
            ids.add(invoice.getId());
        }
        int[] temp = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            temp[i] = ids.get(i).intValue();
        }
        invoiceIDs = temp;

    }

    public void loadNextInvoice() throws FileNotFoundException, SQLException {
        try{
            currentInvoice++;
            loadFaktura(invoiceIDs[currentInvoice]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("NO MORE FAKTURAS");
            currentInvoice--;
            System.out.println("Viser fortsatt faktura på på index 0" + currentInvoice);
        }
    }
}
