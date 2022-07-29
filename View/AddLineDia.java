package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class AddLineDia extends JDialog {

    public String getItemName() {
        return name.getText();
    }

    public int getCount() {
        return count.getAccessibleContext().getAccessibleValue().getCurrentAccessibleValue().intValue();
    }

    public double getPrice() {
        return price.getAccessibleContext().getAccessibleValue().getCurrentAccessibleValue().doubleValue();
    }
    
    private final JButton ok;
    private final JButton cancel;
    private JLabel nameL;
    private JLabel countL;
    private JLabel priceL;
    private JTextField name;
    private JSpinner count;
    private JSpinner price;
    
    public AddLineDia(SIFrame myFrame) {
        SpinnerNumberModel model1 = new SpinnerNumberModel(0.1, 0.1, Double.POSITIVE_INFINITY, 0.1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1); 
        nameL = new JLabel("Item Name");
        countL = new JLabel("Quantity");
        priceL = new JLabel("Item Price");
        name = new JTextField(20);
        count = new JSpinner(model2);
        price = new JSpinner(model1);
        ok = new JButton("Confirm");
        ok.addActionListener(myFrame.getLsr());
        ok.setActionCommand("AddLineOk");
        cancel = new JButton("Cancel");
        cancel.addActionListener(myFrame.getLsr());
        cancel.setActionCommand("AddLineCancel");
        setLayout(new GridLayout(4, 2));
        add(nameL);
        add(name);
        add(countL);
        add(count);
        add(priceL);
        add(price);
        add(ok);
        add(cancel);
        setModal(true);
        pack();
    }
}
