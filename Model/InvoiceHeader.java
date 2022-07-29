package Model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private String customerName;
    private int number;
    private Date invDate;
    ArrayList<InvoiceLine> invoiceLines;

    public InvoiceHeader(String customerName, int number, Date invDate) {
        this.customerName = customerName;
        this.number = number;
        this.invDate = invDate;
        invoiceLines = new ArrayList<>();
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }
    
    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
    
    public void addToInvoiceLines(InvoiceLine invLine) {
        this.invoiceLines.add(invLine);
    }
    
    public double invoiceTotal() {
        double tempTotal = 0;
        for (int i = 0; i<getInvoiceLines().size(); i++) {
            tempTotal = tempTotal + getInvoiceLines().get(i).lineTotal();
        }
        return tempTotal;
    }
    
    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString() {
        return "Name: " + this.customerName + ", Date: " +this.invDate + ", Number: " +this.number;
    }
}
