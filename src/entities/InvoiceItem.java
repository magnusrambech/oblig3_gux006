package entities;

public class InvoiceItem {
    int invoiceId;
    int productId;

    public InvoiceItem(int invoiceId, int productId) {
        this.invoiceId = invoiceId;
        this.productId = productId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getProductId() {
        return productId;
    }
}
