import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class FenetrePrincipale extends JFrame {
    private JTable table;
    private PersonneTableModel tableModel;
    public FenetrePrincipale() {
        setTitle("Gestion de Personnes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableModel = new PersonneTableModel();
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(2).setCellRenderer(new AgeRenderer());
        TableRowSorter<PersonneTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = table.columnAtPoint(e.getPoint());
                sorter.toggleSortOrder(columnIndex);
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JPanel boutonPanel = new JPanel();
        JButton ajouterButton = new JButton("Ajouter");
        JButton supprimerButton = new JButton("Supprimer");
        JButton mettreAJourButton = new JButton("Mettre à jour");
        boutonPanel.add(ajouterButton);
        boutonPanel.add(supprimerButton);
        boutonPanel.add(mettreAJourButton);
        add(boutonPanel, BorderLayout.SOUTH);
        ajouterButton.addActionListener(e -> {
            Personne personne = afficherBoiteDialogueAjout();
            if (personne != null) {
                tableModel.ajouterPersonne(personne);
            }
        });
        supprimerButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.supprimerPersonne(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une personne à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        mettreAJourButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int modelRowIndex = table.convertRowIndexToModel(selectedRow);
                String nom = (String) tableModel.getValueAt(modelRowIndex, 0); // Colonne 0 (nom)
                String prenom = (String) tableModel.getValueAt(modelRowIndex, 1); // Colonne 1 (prénom)
                int age = (int) tableModel.getValueAt(modelRowIndex, 2); // Colonne 2 (âge)
                Personne personne = new Personne(nom, prenom, age);
                afficherBoiteDialogueMiseAJour(personne, modelRowIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une personne à mettre à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton activerFiltreButton = new JButton("Activer le filtre");
        JButton desactiverFiltreButton = new JButton("Désactiver le filtre");
        activerFiltreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RowFilter<PersonneTableModel, Integer> ageFilter = new RowFilter<PersonneTableModel, Integer>() {
                    @Override
                    public boolean include(Entry<? extends PersonneTableModel, ? extends Integer> entry) {
                        Integer age = (Integer) entry.getModel().getValueAt(entry.getIdentifier(), 2);
                        return age > 40;
                    }
                };
                sorter.setRowFilter(ageFilter);
            }
        });
        desactiverFiltreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null);
            }
        });
        boutonPanel.add(activerFiltreButton);
        boutonPanel.add(desactiverFiltreButton);
    }
    // Méthode pour afficher la boîte de dialogue d'ajout
    private Personne afficherBoiteDialogueAjout() {
        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField ageField = new JTextField();
        Object[] message = {
                "Nom:", nomField,
                "Prénom:", prenomField,
                "Âge:", ageField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Ajouter une personne", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                int age = Integer.parseInt(ageField.getText());
                return new Personne(nom, prenom, age);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "L'âge doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
    private void afficherBoiteDialogueMiseAJour(Personne personne, int rowIndex) {
        JTextField nomField = new JTextField(personne.getNom());
        JTextField prenomField = new JTextField(personne.getPrenom());
        JTextField ageField = new JTextField(Integer.toString(personne.getAge()));
        Object[] message = {
                "Nom:", nomField,
                "Prénom:", prenomField,
                "Âge:", ageField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Mettre à jour une personne", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                int age = Integer.parseInt(ageField.getText());
                Personne updatedPerson = new Personne(nom, prenom, age);
                tableModel.mettreAJourPersonne(rowIndex, updatedPerson);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "L'âge doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new FenetrePrincipale();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
