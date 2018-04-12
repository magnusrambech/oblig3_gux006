package scenes.createProduct;

import DAO.AddressDAO;
import DAO.ProductDAO;
import entities.Product;
import javafx.scene.control.TextField;
import scenes.Main;

import java.sql.Connection;

public class CreateProductWindowController {
    public TextField newProdIdFld;
    public TextField newProdDescFld;
    public TextField newProdNameFld;
    public TextField newProdPriceFld;
    public TextField newProdCatFld;

    public Connection conn = Main.conn;
    ProductDAO prodDao;

    /**
     * Oppretter nytt produkt basert på hva som er skrevet i tekstfeltene
     */
    public void createProduct(){
        Product product = new Product();
        product.setId(Integer.parseInt(newProdIdFld.getText()));
        product.setName(newProdNameFld.getText());
        product.setDesc(newProdDescFld.getText());
        product.setPrice(Integer.parseInt(newProdPriceFld.getText()));
        product.setCategory(Integer.parseInt(newProdCatFld.getText()));

        //Oppretter et nytt produkt i databasen
        prodDao = new ProductDAO(conn);
        prodDao.insertNewProduct(product);
    }
}
