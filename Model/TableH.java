package Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import View.SIFrame;
public class TableH extends AbstractTableModel{
    List<InvoiceHeader> invs;
    SIFrame myFrame;
    public TableH() {
    }
    
    public TableH(List<InvoiceHeader> invs, SIFrame frame) {
        this.invs = invs;
        this.myFrame = frame;
    }
    
    @Override
    public int getRowCount() {
        return invs.size();
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    String[] columnHeaders = {"Invoice Number", "Invoice Date", "Customer Name", "Invoice Total"};
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnHeaders[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader myInv = invs.get(rowIndex);
        switch(columnIndex) {
            case 0: return myInv.getNumber();
            case 1: return SIFrame.myForm.format(myInv.getInvDate());
            case 2: return myInv.getCustomerName();
            case 3: return myInv.invoiceTotal();
        }
        return "";
    }
}