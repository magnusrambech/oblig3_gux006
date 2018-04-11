package scenes.createInvoice;

import DAO.InvoiceDAO;
import DAO.InvoiceItemsDAO;
import entities.Invoice;
import entities.InvoiceItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scenes.Main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CreateInvoiceWindowController {
   public TextField newInvIdFld;
   public TextField newInvCustFld;
   public TextField newInvDateFld;
   public VBox newInvProdBox;
   final int MAX_PRODUCTS = 10;
   int currentProducts = 1;

   public ArrayList<TextField> productFlds;

   public Connection conn = Main.conn;
   public InvoiceDAO invDao;
   public InvoiceItemsDAO invItemDao;

   public void createInvoice(){
       Invoice invoice = new Invoice();
       invoice.setId(Integer.parseInt(newInvIdFld.getText()));
       invoice.setCustId(Integer.parseInt(newInvCustFld.getText()));
       invoice.setDate(newInvDateFld.getText());

       //Oppretter invoice i databasen
       invDao = new InvoiceDAO(conn);
       invDao.insertNewInvoice(invoice);

       //Oppretter invoice-item i databasen for hvert produkt på fakturaen
       ArrayList<Integer> products = getProducts();
       invItemDao = new InvoiceItemsDAO(conn);
       int invoiceId = Integer.parseInt(newInvIdFld.getText());
       for(int e: products){
           InvoiceItem invoiceItem = new InvoiceItem();
           invoiceItem.setInvoiceId(invoiceId);
           invoiceItem.setProductId(e);
           invItemDao.insertNewInvoiceItems(invoiceItem);
       }

   }

    //henter ut alle produktene
    public ArrayList<Integer> getProducts(){
       ArrayList<Integer> products = new ArrayList<Integer>();

       for(Node node :newInvProdBox.getChildren()){
            HBox box = (HBox)node;
            for(Node t : box.getChildren()){

                try{
                    TextField tf = (TextField)t;
                    if(!tf.getText().isEmpty()){
                        products.add(Integer.parseInt(tf.getText()));
                    }

                }catch (ClassCastException e){
                    System.out.println("this is a label");
                }

            }
       }
       return products;
    }

    public void addNewProductLine(){
      if(currentProducts<MAX_PRODUCTS){
          Label lbl = new Label();
          TextField txt = new TextField();
          Button btn = new Button();
          btn.setText("-");
          lbl.setText("Product ID");
          HBox box = new HBox();
          btn.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  newInvProdBox.getChildren().remove(box);
                  currentProducts--;
              }
          });
          box.getChildren().add(lbl);
          box.getChildren().add(txt);
          box.getChildren().add(btn);
          txt.setPrefWidth(70);
          box.setAlignment(Pos.TOP_CENTER);
          box.setSpacing(30);
          newInvProdBox.getChildren().add(box);
          currentProducts++;
      }
      else {
          System.out.println("Can't have more than 10 products on the same invoice");
      }

    }
}
