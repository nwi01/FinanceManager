package financesoftware.gui.base;

import financesoftware.tools.GUIHelper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author nwi01
 */
public class PermissionCheck extends JDialog implements ActionListener {
    private GUIHelper helper;
    private JButton saveB = new JButton("OK");
    private JPanel mainPanel = new JPanel();
    private JTextField nameTF = new JTextField();
    private JTextField passwordTF = new JTextField();

    public PermissionCheck(GUIHelper helper) {
        this.helper = helper;
        
        this.setModal(true);

        Point p = this.getParent().getLocation();
        this.setLocation(p.x + 250, p.y + 50);
        this.setSize(300, 120);
        this.setLayout(new BorderLayout());

        //Standardangaben fuer das JFrame
        this.setLocation(100, 300);
        this.setResizable(true);
        this.setSize(250, 180);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setResizable(false);

        //Layout
//        GridLayout layout = new GridLayout(4,3);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        //mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(0, 25, 25, 25));
        this.add(mainPanel, BorderLayout.CENTER);

        this.showLogin();

        this.setVisible(true);
    }

    public void showLogin() {
        //JLabels
        JLabel loginL = new JLabel("Login");
        JLabel nameL = new JLabel("Name:");
        JLabel passwordL = new JLabel("Passwort:");

        // JTextFields
        nameTF = new JTextField();
        //TextField zu PasswordField geaendert MM 23.11.2013
        passwordTF = new JPasswordField(); 

        
        //JButtons
        this.saveB.addActionListener(this);

        //Page_Start
        JPanel panelStart = new JPanel();
        panelStart.setBorder(new EmptyBorder(0, 0, 5, 0));
        panelStart.setLayout(new GridLayout(1, 3));
        panelStart.add(Box.createRigidArea(new Dimension(30, 30)));
        panelStart.add(loginL);
        panelStart.add(Box.createRigidArea(new Dimension(30, 30)));
        mainPanel.add(panelStart, BorderLayout.PAGE_START);

        // Center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //2.
        JPanel panelName = new JPanel();
        panelName.setLayout(new GridLayout(1, 3));
        panelName.add(nameL);
        panelName.add(nameTF);
        centerPanel.add(panelName);

        //3.
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new GridLayout(1, 3));
        panelPassword.add(passwordL);
        panelPassword.add(passwordTF);
        centerPanel.add(panelPassword);

        //4.
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1, 3));
        panelBottom.add(saveB);
        mainPanel.add(panelBottom, BorderLayout.PAGE_END);
    }

    public boolean checkPermission() {
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.saveB) {
            String name = this.nameTF.getText();
            String password = this.passwordTF.getText();
            boolean permission = this.helper.checkPermission(name, password);
            if(permission){
            this.dispose();
            }
        }
    }
}
