package View;


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddInvDia extends JDialog {
    
    private final JButton ok;
    private final JButton cancel;
    private JLabel nameL;
    private JLabel dateL;
    private JTextField name;
    private JTextField date;

    public String getNameTextField() {
        return name.getText();
    }

    public String getDate() {
        return date.getText();
    }
    
    public AddInvDia(SIFrame myFrame) {
        nameL = new JLabel("Customer Name");
        dateL = new JLabel("Invoice Date");
        name = new JTextField(25);
        date = new JTextField(25);
        ok = new JButton("Confirm");
        ok.addActionListener(myFrame.getLsr());
        ok.setActionCommand("AddInvOk");
        cancel = new JButton("Cancel");
        cancel.addActionListener(myFrame.getLsr());
        cancel.setActionCommand("AddInvCancel");
        setLayout(new GridLayout(3, 2));
        add(nameL);
        add(name);
        add(dateL);
        add(date);
        add(ok);
        add(cancel);
        setModal(true);
        pack();
    }
}
