import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MenuFrame {
    private JButton findButton;
    private JComboBox comboBox1;
    private JButton refreshButton;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField textField2;
    private JButton quiz1Button;
    private JButton quiz2Button;
    private JButton editButton;
    private JTextPane textPane1;
    private JPanel panel1;
    private JTextPane historyPane;
    private JTextPane randomPane;
    private JScrollPane scrollpane1;
    private JTextPane findpanel;
    private JScrollPane Scpane1;
    Slang_Word words;

    {
        try {
            words = new Slang_Word();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MenuFrame(){
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = comboBox1.getSelectedItem().toString();
                if (value.equals("Word")){
                    String temp = textField2.getText().toString();
                    try {
                        List<String> search1= words.search_with_slang_word(temp);
                        String []data = search1.toArray(new String[0]);
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("Word",data);
                        JTable table = new JTable(model);
                        scrollpane1.setViewportView(table);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
    }
    public static void main (String[] args){
        JFrame frame = new JFrame("MenuFrame");
        frame.setContentPane(new MenuFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
