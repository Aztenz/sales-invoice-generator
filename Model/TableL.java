package Model;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import View.SIFrame;
public class TableL extends AbstractTableModel{
    List<InvoiceLine> myLines;
    SIFrame myFrame;
    
    public TableL(SIFrame frame) {
        this.myLines = new ArrayList<>();
        this.myFrame = frame;
    }
    
    public TableL(ArrayList<InvoiceLine> Lines, SIFrame frame) {
        this.myLines = Lines;
        this.myFrame = frame;
    }
    
    public List<InvoiceLine> getArr() {
        return myLines;
    }
    
    @Override
    public int getRowCount() {
        return myLines.size();
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }
    
    String[] columnHeaders = {"Invoice Number", "Item Name", "Item Price", "Item Count", "SubTotal"};
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnHeaders[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine myLine = myLines.get(rowIndex);
        switch(columnIndex) {
            case 0: return myLine.getMyInvoice().getNumber();
            case 1: return myLine.getItemName();
            case 2: return myLine.getPriceOnePiece();
            case 3: return myLine.getItemCount();
            case 4: return myLine.lineTotal();
        }
        return "";
    }
}