package entities;

public class InvoiceItem {
    int invoiceId;
    int productId;

    public InvoiceItem() {

    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
