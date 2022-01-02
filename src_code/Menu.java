import java.awt.Color;
import java.awt.Container;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Menu extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    Slang_Word words = new Slang_Word();

    Menu() throws IOException {


        Container container = getContentPane();
        container.setLayout(null);
        JLabel TitLabel = new JLabel("SLANG WORDS");
        TitLabel.setForeground(Color.yellow);
        TitLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        TitLabel.setBounds(50, 20, 300, 30);
        TitLabel.setAlignmentX(CENTER_ALIGNMENT);
        TitLabel.setFocusable(false);

        b1 = new JButton("1. Find by slang word");
        b1.addActionListener(this);
        b1.setFont(new Font("Arial", Font.PLAIN, 14));
        b1.setBounds(50, 120, 200, 30);
        b1.setFocusable(false);

        b2 = new JButton("2. Find by definition");
        b2.addActionListener(this);
        b2.setFont(new Font("Arial", Font.PLAIN, 14));
        b2.setFocusable(false);
        b2.setBounds(300, 120, 200, 30);
        b3 = new JButton("3. Display history");
        b3.addActionListener(this);
        b3.setFont(new Font("Arial", Font.PLAIN, 14));
        b3.setBounds(50, 220, 200, 30);
        b3.setFocusable(false);

        b4 = new JButton("4. Add slang word");
        b4.addActionListener(this);
        b4.setFont(new Font("Arial", Font.PLAIN, 14));
        b4.setBounds(300, 220, 200, 30);
        b4.setFocusable(false);

        b5 = new JButton("5. Edit slang word");
        b5.addActionListener(this);
        b5.setFont(new Font("Arial", Font.PLAIN, 14));
        b5.setBounds(50, 320, 200, 30);
        b5.setFocusable(false);

        b6 = new JButton("6. Delete Slang Word");
        b6.addActionListener(this);
        b6.setFont(new Font("Arial", Font.PLAIN, 14));
        b6.setBounds(300, 320, 200, 30);
        b6.setFocusable(false);

        b7 = new JButton("7. Reset Slang Word");
        b7.addActionListener(this);
        b7.setFont(new Font("Arial", Font.PLAIN, 14));
        b7.setBounds(50, 420, 200, 30);
        b7.setFocusable(false);

        b8 = new JButton("8. Random slang word");
        b8.addActionListener(this);
        b8.setFont(new Font("Arial", Font.PLAIN, 14));
        b8.setBounds(300, 420, 200, 30);
        b8.setFocusable(false);

        b9 = new JButton("8. Random slang word");
        b9.addActionListener(this);
        b9.setFont(new Font("Arial", Font.PLAIN, 14));
        b9.setBounds(50, 520, 200, 30);
        b9.setFocusable(false);

        b10 = new JButton("8. Random slang word");
        b10.addActionListener(this);
        b10.setFont(new Font("Arial", Font.PLAIN, 14));
        b10.setBounds(300, 520, 200, 30);
        b10.setFocusable(false);

        container.add(TitLabel);
        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(b4);
        container.add(b5);
        container.add(b6);
        container.add(b7);
        container.add(b8);
        container.add(b9);
        container.add(b10);
        // Setting Frame
        this.setTitle("MENU");
        this.setVisible(true);
        this.setBounds(10, 10, 600, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            try {
                words.search_with_slang_word("abc");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b2){
            words.find_slang_word_with_keyword("funny");
        }

    }
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        new Menu();

    }
}