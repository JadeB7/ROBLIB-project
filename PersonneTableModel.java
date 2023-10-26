import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
public class PersonneTableModel extends AbstractTableModel {
    private List<Personne> personnes;
    private String[] columnNames = {"Nom", "Prénom", "Âge"};
    public PersonneTableModel() {
        personnes = new ArrayList<>();
    }
    public void ajouterPersonne(Personne personne) {
        personnes.add(personne);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }
    public void supprimerPersonne(int rowIndex) {
        personnes.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    public void mettreAJourPersonne(int rowIndex, Personne personne) {
        personnes.set(rowIndex, personne);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    @Override
    public int getRowCount() {
        return personnes.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Personne personne = personnes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return personne.getNom();
            case 1:
                return personne.getPrenom();
            case 2:
                return personne.getAge();
            default:
                return null;
        }
    }
}
