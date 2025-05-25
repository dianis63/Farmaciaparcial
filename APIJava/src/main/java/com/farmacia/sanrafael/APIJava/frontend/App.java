package com.farmacia.sanrafael.APIJava.frontend;

import com.farmacia.sanrafael.APIJava.frontend.Forms.frmMenu;
import com.farmacia.sanrafael.APIJava.frontend.Manager.FrmsManager;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {
        init();
    }

    private void init() {
        setTitle("Farmacia San Rafael");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Usamos un contenedor para asegurar que frmMenu se expanda correctamente
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(new frmMenu(), BorderLayout.CENTER);

        setContentPane(wrapper);

        FrmsManager.getInstance().initApp(this);
    }

    public static void main(String[] args) {
        // Fuente y estilo visual
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();

        // Lanzar interfaz
        EventQueue.invokeLater(() -> new App().setVisible(true));
    }
}
