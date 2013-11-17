package financesoftware.gui.components;

import financesoftware.gui.base.ViewComponent;
import financesoftware.tools.GUIHelper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author nwi01
 */
public class AnalysisManagementComponent extends BaseComponent implements ViewComponent, ActionListener {

    private JPanel mainPanel = new JPanel();
    private JPanel showPanel = new JPanel(); // Hier werden die einzelnen Pages/Arbeitsschritte angezeigt
    private JPanel nextAndSavePanel = new JPanel();
    private ArrayList<JPanel> sections = new ArrayList();
    private int currentPage = 0;
    private JButton previous = new JButton("Zurück");
    private JButton next = new JButton("Weiter");
    private JButton save = new JButton("Speichern");

    public AnalysisManagementComponent() {
        super(true);

        this.createSections();
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        BorderLayout layout2 = new BorderLayout();
        this.mainPanel.setLayout(layout2);

        //ActionListener
        previous.addActionListener(this);
        next.addActionListener(this);
        save.addActionListener(this);
        
        
        //Buttons vorbereiten
        this.save.setVisible(false);
        this.save.setToolTipText("Auswertung speichern");
        this.next.setToolTipText("Einen Schritt weiter");
        this.previous.setToolTipText("Einen Schritt zurück");
        
        //Speichern und Vorblaettern
        this.nextAndSavePanel.add(this.next);
        this.nextAndSavePanel.add(this.save);

        BorderLayout layout3 = new BorderLayout();
        this.showPanel.setLayout(layout3);
        this.showPanel.add(this.sections.get(this.currentPage));

        this.mainPanel.add(showPanel, BorderLayout.CENTER);
        this.mainPanel.add(createControllBar(), BorderLayout.PAGE_END);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private JPanel createControllBar() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridLayout layout = new GridLayout(1, 5);
        panel.setLayout(layout);

        this.previous.setVisible(false);
        if (this.sections.size() == 1) {
            this.next.setVisible(false);
        }
        
        JPanel panel2 = new JPanel();
        panel2.add(previous);
        
        panel.add(panel2);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(nextAndSavePanel);

        return panel;
    }

    /**
     * Erstellen der verschiedene Komponenten (Arbeitschritte) für die GUI;
     */
    private void createSections() {
        this.createBasicStepPanel();
        JPanel panel = new JPanel();
        panel.add(new JLabel("efefe"));

//        this.mainPanel.add(panel, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("efefe2222222"));
        sections.add(panel);
        sections.add(panel2);
    }
    
    private void createBasicStepPanel(){
        JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);
        
        JPanel top = new JPanel();
        BoxLayout layoutBox = new BoxLayout(top, BoxLayout.Y_AXIS);
        
        top.add(new JLabel("Auswertung"));
        
        top.add(Box.createRigidArea(new Dimension(20, 5)));
        
        JComboBox comboBox = new JComboBox(new String[]{"neue Auswertung","1", "2"});
        
        top.add(comboBox);
        top.setVisible(true);
        
        this.sections.add(top);                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mainPanel.setVisible(false);
        if (e.getSource() == this.previous) {
            if (this.currentPage == 1) {
                this.previous.setVisible(false);
            }
            if (this.currentPage == this.sections.size() - 1) {
                this.next.setVisible(true);
                this.save.setVisible(false);
            }
            this.showPanel.removeAll();
            currentPage--;
            this.showPanel.add(this.sections.get(this.currentPage));

        }

        if (e.getSource() == this.next) {
            if (this.currentPage == 0) {
                this.previous.setVisible(true);
            }

            if (this.currentPage == this.sections.size() - 2) {
                this.next.setVisible(false);
                this.save.setVisible(true);
            }
                        
            this.showPanel.removeAll();
            currentPage++;
            this.showPanel.add(this.sections.get(this.currentPage));
        }
        
        if(e.getSource() == this.save){
//            GUIHelper.createAndSaveAnalysis();
        }
        this.mainPanel.setVisible(true);
    }
}
