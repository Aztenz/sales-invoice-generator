package Control;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.TableH;
import Model.TableL;
import View.AddInvDia;
import View.AddLineDia;
import View.SIFrame;

public class SIListener implements ActionListener, ListSelectionListener {

    private final SIFrame listenerFrame;
    private AddInvDia addInvDia;
    private AddLineDia addLineDia;

    public SIListener(SIFrame frame) {
        listenerFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (null == action) {
            System.out.println("Null");
        } else {
            switch (action) {
                case "Load Files":
                    load(null, null);
                    System.out.println("Load Files");
                    break;
                case "Save Data":
                    save();
                    System.out.println("Save Data");
                    break;
                case "Add Invoice":
                    addInv();
                    System.out.println("Add Invoice");
                    break;
                case "Remove Invoice":
                    removeInv();
                    System.out.println("Remove Invoice");
                    break;
                case "Add Line":
                    addLine();
                    System.out.println("Add Line");
                    break;
                case "Remove Line":
                    removeLine();
                    System.out.println("Remove Line");
                    break;
                case "AddInvOk":
                    addInvOk();
                    break;
                case "AddLineOk":
                    addLineOk();
                    break;
                case "AddInvCancel":
                    addInvCancel();
                    break;
                case "AddLineCancel":
                    addLineCancel();
                    break;
                default:
                    System.out.println("Nothing");
                    break;
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (listenerFrame.getInvTable().getSelectedRow() > -1 && listenerFrame.getInvTable().getSelectedRow() < listenerFrame.getMyInvoices().size()) {
            ArrayList<InvoiceHeader> myInvoices = listenerFrame.getMyInvoices();
            InvoiceHeader selectedInv = myInvoices.get(listenerFrame.getInvTable().getSelectedRow());
            listenerFrame.getCustomerNameLabel().setText(selectedInv.getCustomerName());
            listenerFrame.getInvoiceDateLabel().setText(SIFrame.myForm.format(selectedInv.getInvDate()));
            listenerFrame.getInvoiceNumLabel().setText(String.valueOf(selectedInv.getNumber()));
            listenerFrame.getInvoiceTotalLabel().setText(String.valueOf(selectedInv.invoiceTotal()));
            listenerFrame.getLinesTable().setModel(new TableL(selectedInv.getInvoiceLines(), listenerFrame));
        } else if (listenerFrame.getInvTable().getSelectedRow() == -1) {
            listenerFrame.getCustomerNameLabel().setText("");
            listenerFrame.getInvoiceDateLabel().setText("");
            listenerFrame.getInvoiceNumLabel().setText("");
            listenerFrame.getInvoiceTotalLabel().setText("");
            listenerFrame.getLinesTable().setModel(new TableL(listenerFrame));
        }
    }

    public void load(String invHeader, String line) {
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
        File fheader = null, fline = null;
        if (invHeader == null) {
            JFileChooser choose = new JFileChooser();
            int myFile = choose.showOpenDialog(listenerFrame);
            if (myFile == JFileChooser.APPROVE_OPTION) {
                fheader = choose.getSelectedFile();
                myFile = choose.showOpenDialog(listenerFrame);
                if (myFile == JFileChooser.APPROVE_OPTION) {
                    fline = choose.getSelectedFile();
                }
            }
        } else {
            fheader = new File(invHeader);
            fline = new File(line);
        }
        if (fline != null && fheader != null) {
            try {
                List<String> myHeaders = Files.lines(Paths.get(fheader.getAbsolutePath())).collect(Collectors.toList());
                List<String> myLines = Files.lines(Paths.get(fline.getAbsolutePath())).collect(Collectors.toList());
                for (String myHeader : myHeaders) {
                    String[] splitted = myHeader.split(",");
                    if (splitted.length != 3) {
                        JOptionPane.showMessageDialog(null, "Header table should contain exactly 3 columns!\n"
                                + "Please re-select your files !",
                                "Error in header table", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int num = Integer.parseInt(splitted[0]);
                    Date date = SIFrame.myForm.parse(splitted[1]);
                    String custName = splitted[2];
                    invoiceHeaders.add(new InvoiceHeader(custName, num, date));
                }
                for (String myLine : myLines) {
                    String[] splitted = myLine.split(",");
                    if (splitted.length != 4) {
                        JOptionPane.showMessageDialog(null, "Lines table should contain exactly 4 columns!\n"
                                + "Please re-select your files !",
                                "Error in Lines table", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int invNum = Integer.parseInt(splitted[0]);
                    String itemName = splitted[1];
                    Double itemPrice = Double.parseDouble(splitted[2]);
                    int itmCount = Integer.parseInt(splitted[3]);
                    for (InvoiceHeader invoiceHeader : invoiceHeaders) {
                        if (invoiceHeader.getNumber() == invNum) {
                            invoiceHeader.addToInvoiceLines(new InvoiceLine(invoiceHeader, itemName, itmCount, itemPrice));
                            break;
                        }
                    }
                }
                listenerFrame.setMyInvoices(invoiceHeaders);
                listenerFrame.getInvTable().setModel(new TableH(invoiceHeaders, listenerFrame));
                ((TableH) listenerFrame.getInvTable().getModel()).fireTableDataChanged();
            } catch (HeadlessException | IOException | NumberFormatException | ParseException myException) {
            }
        }
    }

    private void save() {
    }

    private void addInv() {
        addInvDia = new AddInvDia(listenerFrame);
        addInvDia.setVisible(true);
    }

    private void removeInv() {
        int selectedInv = listenerFrame.getInvTable().getSelectedRow();
        if (selectedInv != -1) {
            listenerFrame.getMyInvoices().remove(selectedInv);
            ((TableH) listenerFrame.getInvTable().getModel()).fireTableDataChanged();
        }
    }

    private void addLine() {
        addLineDia = new AddLineDia(listenerFrame);
        addLineDia.setVisible(true);
    }

    private void removeLine() {
        int selectedLine = listenerFrame.getLinesTable().getSelectedRow();
        if (selectedLine != -1) {
            ((TableL) listenerFrame.getLinesTable().getModel()).getArr().remove(selectedLine);
            ((TableL) listenerFrame.getLinesTable().getModel()).fireTableDataChanged();
            int hr = listenerFrame.getInvTable().getSelectedRow();
            ((TableH) listenerFrame.getInvTable().getModel()).fireTableDataChanged();
            listenerFrame.getInvTable().setRowSelectionInterval(hr, hr);

        }
    }

    private void addInvOk() {
        String name = addInvDia.getNameTextField();
        String date = addInvDia.getDate();
        addInvDia.setVisible(false);
        addInvDia.dispose();
        int num = getHighestInv(1);
        try {
            Date D = SIFrame.myForm.parse(date);
            InvoiceHeader myInv = new InvoiceHeader(name, num, D);
            listenerFrame.getMyInvoices().add(myInv);
            ((TableH) listenerFrame.getInvTable().getModel()).fireTableDataChanged();
        }
        catch(ParseException p) {
            JOptionPane.showMessageDialog(listenerFrame, "Error in date format", "Error !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLineOk() {
        int selectedRow = listenerFrame.getInvTable().getSelectedRow();
        if(selectedRow>-1) {
            String name = addLineDia.getItemName();
            double price = addLineDia.getPrice();
            int count = addLineDia.getCount();
            int selectedRaw = listenerFrame.getInvTable().getSelectedRow();
            InvoiceHeader invH = listenerFrame.getMyInvoices().get(selectedRaw);
            invH.addToInvoiceLines(new InvoiceLine(invH, name, count, price));
            ((TableL) listenerFrame.getLinesTable().getModel()).fireTableDataChanged();
        }
    }

    private void addInvCancel() {
        addInvDia.setVisible(false);
        addInvDia.dispose();
    }

    private void addLineCancel() {
        addLineDia.setVisible(false);
        addLineDia.dispose();
    }
    
    private int getHighestInv(int num) {
        for (InvoiceHeader invH : listenerFrame.getMyInvoices()) {
            if (invH.getNumber() > num)
                num = invH.getNumber();
        }
        return num + 1;
    }
}
