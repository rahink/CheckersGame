

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class MainMenu extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMenu frame = new MainMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 645, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.RED);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        
        
        JButton btnCustom = new JButton("Custom");
        btnCustom.setForeground(Color.RED);
        btnCustom.setFont(new Font("Arial Black", Font.ITALIC, 13));
        
        
        
        btnCustom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BoardView view = new BoardView();
                Board controller = new Board(view, false);
                setVisible(false);
                
            }
        });
        
        
      
        
        JButton btnClassic = new JButton("Classic");
        
        JFormattedTextField frmtdtxtfldCheckers = new JFormattedTextField();
        frmtdtxtfldCheckers.setFont(new Font("Lucida Grande", Font.PLAIN, 38));
        frmtdtxtfldCheckers.setBackground(Color.RED);
        frmtdtxtfldCheckers.setText("                  Checkers!");
        
        JButton btnNewButton = new JButton("Classic Mode");
        btnNewButton.setForeground(Color.RED);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 13));
       
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BoardView view = new BoardView();
                Board controller = new Board(view, true);
                setVisible(false);
            }
        });
        
        JButton btnQuit = new JButton("Quit?");
        
        JRadioButton rdbtnHi = new JRadioButton("2 Player ");
        
        JRadioButton rdbtnVsAi = new JRadioButton("VS AI");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(frmtdtxtfldCheckers, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnClassic, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(37)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(rdbtnHi)
                        .addComponent(rdbtnVsAi))
                    .addGap(18)
                    .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(26)
                    .addComponent(btnCustom, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(276)
                    .addComponent(btnQuit))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(frmtdtxtfldCheckers, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                    .addGap(35)
                    .addComponent(btnCustom, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(rdbtnHi)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(rdbtnVsAi))
                        .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                    .addGap(34)
                    .addComponent(btnQuit)
                    .addGap(236)
                    .addComponent(btnClassic))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
