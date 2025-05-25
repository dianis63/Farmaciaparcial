package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.farmacia.sanrafael.APIJava.frontend.Manager.FrmsManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class frmInicio extends JPanel {

    public frmInicio() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        setBackground(new Color(172, 212, 227));

        txtUsuario=new JTextField();
        txtContrasenia=new JPasswordField();
        btnIngresar=new JButton("Ingresar");

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill, 250:280"));  //es el panel
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc: 20;" +
                "background: #e9f4f3;");

        txtContrasenia.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");

        btnIngresar.putClientProperty(FlatClientProperties.STYLE, "" +
                "background: #1c6891;" +
                "foreground: #e9f4f3;" +
                "arc:20," +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;");
        btnIngresar.addActionListener(e -> {
            FrmsManager.getInstance().showForm(new frmMenu());
        });

        txtUsuario.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Ingrese su nombre de usuario");
        txtContrasenia.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Ingrese su contraseña");

        panel.add(new JLabel(new FlatSVGIcon("Icon/iconoFarmacia.svg", 1.0f)));
        panel.add(new JLabel("Usuario"), "gapy 8");
        panel.add(txtUsuario);
        panel.add(new JLabel("Contraseña"), "gapy 8");
        panel.add(txtContrasenia);
        panel.add(btnIngresar, "gapy 10");

        add(panel);
    }

    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JButton btnIngresar;

}
