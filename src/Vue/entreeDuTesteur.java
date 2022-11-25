package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class entreeDuTesteur extends JPanel {
    JPanel form;
    JPanel buttonsLayout;
    JTextField numero;
    JTextField instructions;
    JButton[] btn_nombre;
    JPanel tousLesBoutonsNumeriques;
    JPanel entreeNumero;
    public entreeDuTesteur(){
        initPanneau();
        this.add(form);
    }

    private void initPanneau(){
        form = new JPanel();

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

        buttonsLayout.setPreferredSize(new Dimension(5, 100));

        form.add(instructions);
        form.add(entreeNumero);
        form.add(buttonsLayout);

    }

    private void nombresInit() {
        btn_nombre = new JButton[10];
        tousLesBoutonsNumeriques = new JPanel(new GridLayout(1, 10));
        for (int i = 0; i < 10; i++) {
            btn_nombre[i] = new JButton("" + i);
            JButton current = btn_nombre[i];
            current.setPreferredSize(new Dimension(100, 100));
            current.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String currentTxt = numero.getText();
                    numero.setText(currentTxt + current.getText());
                }
            });
            tousLesBoutonsNumeriques.add(current);
        }
    }


    public String getText(){
        return this.numero.getText();
    }

}
