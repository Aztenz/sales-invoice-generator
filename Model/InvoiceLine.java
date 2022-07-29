package Model;
public class InvoiceLine {
    private InvoiceHeader myInvoice;
    private String itemName;
    private int itemCount;
    private double priceOnePiece;

    public InvoiceLine(InvoiceHeader myInvoice, String itemName, int itemCount, double priceOnePiece) {
        this.myInvoice = myInvoice;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.priceOnePiece = priceOnePiece;
    }
    
    public double lineTotal() {
        return itemCount*priceOnePiece;
    }
    
    public InvoiceHeader getMyInvoice() {
        return myInvoice;
    }

    public void setMyInvoice(InvoiceHeader myInvoice) {
        this.myInvoice = myInvoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getPriceOnePiece() {
        return priceOnePiece;
    }

    public void setPriceOnePiece(double priceOnePiece) {
        this.priceOnePiece = priceOnePiece;
    }

    public String toString() {
        return "Name: " + this.itemName + ", Count: " +this.itemCount + ", Price: " + this.priceOnePiece;
    }
}
