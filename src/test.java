import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test {
    private JFrame fenetre;
    private JPanel panneauVert;
    private JPanel panneauBleu;
    private JPanel panneauJaune;
    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champAnneeNaissance;
    private JButton boutonAge;
    private JButton boutonReverse;
    private JLabel affichageLabel;

    public test() {
        fenetre = new JFrame("Informations Personnelles");
        panneauVert = new JPanel(new GridLayout(3, 2));
        panneauBleu = new JPanel();
        panneauJaune = new JPanel(new FlowLayout(FlowLayout.CENTER));
        champNom = new JTextField(10);
        champPrenom = new JTextField(10);
        champAnneeNaissance = new JTextField(10);
        boutonAge = new JButton("Age?");
        boutonReverse = new JButton("Reverse");
        affichageLabel = new JLabel();

        panneauVert.add(new JLabel("Votre nom: "));
        panneauVert.add(champNom);
        panneauVert.add(new JLabel("Votre prénom: "));
        panneauVert.add(champPrenom);
        panneauVert.add(new JLabel("Votre année de naissance: "));
        panneauVert.add(champAnneeNaissance);

        boutonAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculerAge();
            }
        });

        boutonReverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverseInformations();
            }
        });

        panneauJaune.add(boutonAge);
        panneauJaune.add(boutonReverse);

        fenetre.setLayout(new BorderLayout());
        fenetre.add(panneauVert, BorderLayout.NORTH);
        fenetre.add(panneauBleu, BorderLayout.CENTER);
        fenetre.add(panneauJaune, BorderLayout.SOUTH);
        fenetre.setSize(400, 200);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    private void calculerAge() {
        try {
            int anneeNaissance = Integer.parseInt(champAnneeNaissance.getText());
            int age = 2023 - anneeNaissance;
            affichageLabel.setText("En 2023, vous aurez " + age + " ans.");
        } catch (NumberFormatException e) {
            affichageLabel.setText("Veuillez entrer une année de naissance valide.");
        }
    }

    private void reverseInformations() {
        String nomReverse = new StringBuilder(champNom.getText()).reverse().toString();
        String prenomReverse = new StringBuilder(champPrenom.getText()).reverse().toString();
        String anneeNaissanceReverse = new StringBuilder(champAnneeNaissance.getText()).reverse().toString();

        affichageLabel.setText("Nom: " + nomReverse + ", Prénom: " + prenomReverse + ", Année de naissance: " + anneeNaissanceReverse);
    }

    public static void main(String[] args) {
        new test();
    }
}
