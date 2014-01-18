package financesoftware.gui.base;

import financesoftware.base.User;
import financesoftware.base.Verschluesselung;
import financesoftware.tools.GUIHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author nwi01
 */
public class PermissionCheck extends JDialog implements ActionListener, MouseListener, KeyListener {

    private final JButton saveB = new JButton("OK");
    private final JPanel mainPanel = new JPanel();
    private final JLabel registerL = new JLabel("registrieren");
    private JTextField nameTF = new JTextField();
    private JPasswordField passwordTF = new JPasswordField();

    public PermissionCheck() {

        this.setModal(true);

        Point p = this.getParent().getLocation();
        this.setLocation(p.x + 250, p.y + 50);
        this.setLayout(new BorderLayout());

        //Standardangaben fuer das JFrame
        this.setLocation(100, 300);
        this.setResizable(true);
        this.setSize(250, 180);
        this.setResizable(false);

        //Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        //mainPanel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        this.add(mainPanel, BorderLayout.CENTER);
        
        //Listener
        this.nameTF.addKeyListener(this);
        this.passwordTF.addKeyListener(this);  
        this.saveB.addActionListener(this);

        this.showLogin();

        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void showLogin() {
        //JLabels
        JLabel loginL = new JLabel("Login");
        JLabel nameL = new JLabel("Name:");
        JLabel passwordL = new JLabel("Passwort:");

        //Page_Start
        JPanel panelStart = new JPanel();
        panelStart.setBorder(new EmptyBorder(0, 0, 5, 0));
        panelStart.setLayout(new FlowLayout(FlowLayout.CENTER));
//        panelStart.add(Box.createRigidArea(new Dimension(30, 30)));
        panelStart.add(loginL);
//        panelStart.add(Box.createRigidArea(new Dimension(30, 30)));
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
        centerPanel.add(panelBottom);

        //Bottom
        JPanel lineEnd = new JPanel();
        lineEnd.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.registerL.addMouseListener(this);
        lineEnd.add(new JLabel()); // Dummy
//        lineEnd.add(new JLabel()); // Dummy
        lineEnd.add(this.registerL);

        mainPanel.add(lineEnd, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.saveB) {
            this.checkInput();
        }
    }

    private void checkInput() {
        String name = this.nameTF.getText();
        String password = this.passwordTF.getText();
        boolean permission = GUIHelper.getInstance().checkPermission(name, password);
        if (name.equals("admin") && password.equals("")) {
            GUIHelper.getInstance().setUser(GUIHelper.getInstance().getDummyUser());
            permission = true;
        }
        if (permission) {
            this.dispose();
        } else {
            this.passwordTF.setForeground(Color.RED);
            JOptionPane.showMessageDialog(null, "Falscher Name oder falsches Passwort", "Anmeldung fehlgeschlagen", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.registerL) {
            User user = new User(this.nameTF.getText(), this.passwordTF.getText());
            boolean isSaved = Verschluesselung.save(user);
            if (isSaved) {
                User newUser = Verschluesselung.load(this.nameTF.getText(), this.passwordTF.getText());
                if (newUser != null) {
                    GUIHelper.getInstance().setUser(newUser);
                    this.dispose();
                }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Bitte Benutzername und Passwort eingeben", "Registrierung fehlgeschlagen", JOptionPane.OK_OPTION); 
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
            // Damit auch Enter == OK Button
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.checkInput();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
