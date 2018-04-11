package scenes.editInvoices;

import DAO.InvoiceDAO;
import entities.Invoice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scenes.Main;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

public class EditInvoicesWindowController {
    public Label updateIdLbl;
    public TextField updateCustomerIdFld;
    public DatePicker updateDateFld;
    public VBox invoiceVBox;

    public Connection conn = Main.conn;
    public InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

    public void initialize(){
        listAllInvoices();
    }
    public void listAllInvoices(){
        //Nullstiller listen fra forrige visning
        invoiceVBox.getChildren().clear();
        //Finner alle invoices
        ArrayList<Invoice> invoices = invoiceDAO.findAllInvoices();
        for(Invoice i:invoices){
            listInvoice(i);
        }
    }

    public void listInvoice(Invoice i){
        //Lager nye labels for all invoice info
        Label idLbl = new Label();
        Label custIdLbl = new Label();
        Label dateLbl = new Label();
        Button editBtn = new Button("Edit");
        HBox hBox = new HBox();

        //Setter teksten på labelene
        idLbl.setText(String.valueOf(i.getId()));
        custIdLbl.setText(String.valueOf(i.getCustId()));
        dateLbl.setText(i.getDate());

        //Setter bredden på labelene
        idLbl.setPrefWidth(100);
        custIdLbl.setPrefWidth(70);
        dateLbl.setPrefWidth(80);



        //Gir "edit"-knappen en funkjson
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                updateIdLbl.setText(idLbl.getText());
                updateCustomerIdFld.setText(custIdLbl.getText());
                updateDateFld.getEditor().setText(String.valueOf(dateLbl.getText()));
            }
        });

        hBox.getChildren().add(idLbl);
        hBox.getChildren().add(custIdLbl);
        hBox.getChildren().add(dateLbl);
        hBox.getChildren().add(editBtn);

        invoiceVBox.getChildren().add(hBox);

    }

    public void commitChanges(){
        Invoice invoice = new Invoice();
        invoice.setId(Integer.parseInt(updateIdLbl.getText()));
        invoice.setCustId(Integer.parseInt(updateCustomerIdFld.getText()));
        invoice.setDate(String.valueOf(updateDateFld.getEditor().getText()));

        //Legger til endringene i databasen og laster listen på nytt
        invoiceDAO.alterInvoice(invoice);
        listAllInvoices();
    }
}
