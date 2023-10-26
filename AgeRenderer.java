import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Font;

public class AgeRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Vérifiez si la valeur est un entier (âge)
        if (value instanceof Integer) {
            int age = (int) value;

            // Personnalisez l'apparence pour les personnes âgées de plus de 50 ans
            if (age > 50) {
                setFont(getFont().deriveFont(Font.BOLD)); // Texte en gras
            } else {
                setFont(getFont().deriveFont(Font.PLAIN)); // Texte normal
            }
        }

        return this;
    }
}
