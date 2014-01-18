/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financesoftware.gui.base;

import financesoftware.base.User;
import financesoftware.base.Verschluesselung;
import financesoftware.gui.components.BaseComponent;
import financesoftware.tools.GUIHelper;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Hiermit koennen standardisierte Verwaltungungs Komponenten erzeugt werden. Es
 * ist moeglich mehrere "Seiten" anzeigen zu lassen (Paging)
 *
 * @author niels
 */
public abstract class ManagementBaseComponent extends BaseComponent implements ViewComponent, ActionListener {

    public User user = GUIHelper.getInstance().getUser();

    private final JPanel showPanel = new JPanel(); // Hier werden die einzelnen Pages/Arbeitsschritte angezeigt
    private final JPanel nextAndSavePanel = new JPanel();
    public ArrayList<JPanel> sections = new ArrayList();
    private int currentPage = 0;
    private final JButton previous = new JButton("Zurück");
    private final JButton next = new JButton("Weiter");
    private final JButton save = new JButton("Speichern");

    public ManagementBaseComponent() {
        super(true);
        this.initFields();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        BorderLayout layout2 = new BorderLayout();
        this.mainPanel.setLayout(layout2);
        this.sections = getSectionPanels();
        //ActionListener
        previous.addActionListener(this);
        next.addActionListener(this);

        //Buttons vorbereiten
        this.save.setVisible(false);
        this.save.addActionListener(this);
        this.save.setToolTipText("speichern");
        this.next.setToolTipText("Einen Schritt weiter");
        this.previous.setToolTipText("Einen Schritt zurück");

        this.showPanel.add(this.sections.get(this.currentPage));
        this.showPanel.setVisible(true);

        this.mainPanel.add(showPanel, BorderLayout.CENTER);
        this.mainPanel.add(createControllBar(), BorderLayout.PAGE_END);
        this.mainPanel.setVisible(true);
        this.add(this.mainPanel, BorderLayout.CENTER);
    }

    private JPanel createControllBar() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.previous.setVisible(false);
        if (this.sections.size() == 1) {
            this.getNext().setVisible(false);
            this.save.setVisible(true);
        } else {
            this.getNext().setVisible(true);
            this.save.setVisible(false);
        }

        //Layout
        GridBagLayout layout = new GridBagLayout();

        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 500);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(this.previous, constraints);

        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx++;
        panel.add(this.getNext(), constraints);
        panel.add(this.save, constraints);
        return panel;
    }

    @Override
    public JComponent getComponent() {
        this.updateContent();
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mainPanel.setVisible(false);
        if (!this.basicActions(e)) {
            this.specialAction(e);
        }
        Verschluesselung.save(this.user);
        this.mainPanel.setVisible(true);
    }

    /**
     * Basis Aktionen fuer die Komponente
     *
     * @param e
     * @return
     */
    private boolean basicActions(ActionEvent e) {
        if (e.getSource() == this.previous) {
            if (this.currentPage == 1) {
                this.previous.setVisible(false);
            }
            if (this.currentPage == this.sections.size() - 1) {
                this.getNext().setVisible(true);
                this.save.setVisible(false);
            }
            this.showPanel.removeAll();
            currentPage--;
            this.showPanel.add(this.sections.get(this.currentPage));
            return true;
        }

        if (e.getSource() == this.getNext()) {
            if (this.currentPage == 0) {
                this.previous.setVisible(true);
            }

            if (this.currentPage == this.sections.size() - 2) {
                this.getNext().setVisible(false);
                this.save.setVisible(true);
            }

            this.showPanel.removeAll();
            currentPage++;
            this.showPanel.add(this.sections.get(this.currentPage));
            return true;
        }

        if (e.getSource() == this.save) {
            this.saveOrUpdate();
            if (Verschluesselung.save(this.user)) {
                //showSaved();
            }
            return true;
        }

        return false;
    }

    public void showSaved() {
        this.showPanel.removeAll();
        currentPage = 0;
        this.showPanel.add(this.sections.get(this.currentPage));
        this.getNext().setVisible(true);
        this.save.setVisible(false);
    }

    /**
     * Methode, damit in den speziellen Klassen, auch auf Events reagiert werden
     * kann
     *
     * @param event
     */
    public abstract void specialAction(ActionEvent event);

    /**
     * Spezielle Methode, um das zu bearbeitende Objekt/ neu erstellte zu
     * speichern
     */
    public abstract void saveOrUpdate();

    /**
     * Mit dieser Methode werden die verschiedenen JPanels erzeugt Wird im
     * Konstruktor verwendet um "sections" zu setzen
     *
     * @return
     */
    public abstract ArrayList<JPanel> getSectionPanels();

    public abstract void initFields();

    /**
     * @return the next
     */
    public JButton getNext() {
        return next;
    }

}
