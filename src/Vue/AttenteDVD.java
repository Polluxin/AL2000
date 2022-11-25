package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UNUSED !
 */
public class AttenteDVD extends JPanel {
    JPanel form;
    JPanel buttonsLayout;
    JTextField numero;
    JTextField instructions;
    JButton annuler;
    JButton valider;
    JButton[] btn_nombre;
    JPanel tousLesBoutonsNumeriques;
    JPanel entreeNumero;
    public AttenteDVD(){
        int borderSize = 100;
        this.setBackground(new Color(0,0,0,100));
        this.setLayout(new BorderLayout());

        form = new JPanel(new GridLayout(3,1));
        form.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.BLACK));
        form.setBackground(ourColors.fond2());


        instructions = new JTextField("Veuillez entrer le numero du Blu-ray :");
        instructions.setOpaque(false);
        instructions.setEditable(false);
        instructions.setPreferredSize(new Dimension(500, 100));
        instructions.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        instructions.setAlignmentX(CENTER_ALIGNMENT);
        instructions.setAlignmentY(CENTER_ALIGNMENT);

        entreeNumero = new JPanel(new BorderLayout());
        numero = new JTextField();

        nombresInit();
        entreeNumero.add(numero, BorderLayout.CENTER);
        entreeNumero.add(tousLesBoutonsNumeriques,BorderLayout.NORTH);
        entreeNumero.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        entreeNumero.setOpaque(false);

        buttonsLayout = new JPanel(new GridLayout(1,2));
        buttonsLayout.setOpaque(false);
        annuler = ourTools.transparentButtonWithIcon("src/ressources/annuler.png");
        valider = ourTools.transparentButtonWithIcon("src/ressources/valider.png");
        buttonsLayout.add(annuler);
        buttonsLayout.add(valider);

        buttonsLayout.setPreferredSize(new Dimension(5, 100));

        form.add(instructions);
        form.add(entreeNumero);
        form.add(buttonsLayout);


        this.buttonsLinking();
        this.add(form);
        this.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
    }

    private void nombresInit(){
        btn_nombre = new JButton[10];
        tousLesBoutonsNumeriques = new JPanel(new GridLayout(1,10));
        for(int i=0; i<10; i++){
            btn_nombre[i] = new JButton(""+i);
            JButton current = btn_nombre[i];
            current.setPreferredSize(new Dimension(100,100));
            current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String currentTxt = numero.getText();
                    numero.setText(currentTxt+current.getText());
                }
            });
            tousLesBoutonsNumeriques.add(current);
        }
    }

    private void buttonsLinking(){
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numero.setText("");
            }
        });
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"ANNULER",
                        "VALIDER"};
                int n = JOptionPane.showConfirmDialog(null,
                        loadingScreen(),
                        "A Silly Question",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                ); //default button title
            }
        });
    }

    private JPanel loadingScreen(){
        JPanel loading = new JPanel();
        JLabel loadingGif = new JLabel();
        loadingGif.setIcon(ourPictures.getPicture("src/ressources/Spinner-2.gif"));
        loading.add(loadingGif);
        return loading;
    }
}
