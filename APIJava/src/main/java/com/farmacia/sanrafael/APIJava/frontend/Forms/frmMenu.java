package com.farmacia.sanrafael.APIJava.frontend.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class frmMenu extends JPanel {

    private final String[][] itemsMenu = {
            {"Nueva Venta"},
            {"Inventario"},
            {"Clientes"},
            {"Empleados"},
            {"Ventas"},
            {"Reporte"},
            {"Salir"}
    };

    private final String[] rutasIconos = {
            "Icon/iconoNuevaVenta.svg",
            "Icon/iconoInventario.svg",
            "Icon/iconoLaboratorios.svg",
            "Icon/iconoClientes.svg",
            "Icon/iconoEmpleados.svg",
            "Icon/iconoVentas.svg",
            "Icon/iconoReportes.svg",
            "Icon/iconoSalir.svg"
    };

    private JPanel contentPanel;
    private JPanel currentPanel;

    public frmMenu() {
        inicializar();
    }

    private void inicializar() {
        setLayout(new MigLayout("insets 0, fill", "[200!][grow]", "[grow]"));
        setBackground(new Color(172, 212, 227));

        // Panel lateral del menú
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 20 15 20 15", "fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, "background:#1c6891;");

        JLabel logo = new JLabel(new FlatSVGIcon("Icon/iconoMenu.svg", 0.55f));
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(233, 244, 243)));

        panel.add(logo);
        panel.add(separador, "growx, gapy 15 15");

        for (int i = 0; i < itemsMenu.length - 1; i++) {
            String nombreItem = itemsMenu[i][0];
            String rutaIcono = rutasIconos[i];

            FlatSVGIcon icono = new FlatSVGIcon(rutaIcono, 30, 30);
            JButton btn = crearBoton(nombreItem, icono);

            switch (nombreItem) {
                case "Nueva Venta" -> btn.addActionListener(e -> showPanel(new frmNuevaVenta()));
                case "Inventario" -> btn.addActionListener(e -> showPanel(new frmInventario()));
                case "Clientes" -> btn.addActionListener(e -> showPanel(new frmClientes()));
                case "Empleados" -> btn.addActionListener(e -> showPanel(new frmEmpleados()));
                case "Ventas" -> btn.addActionListener(e -> showPanel(new frmVentas()));
            }

            panel.add(btn);
        }

        JSeparator separadorSalir = new JSeparator(SwingConstants.HORIZONTAL);
        separadorSalir.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(233, 244, 243)));
        panel.add(separadorSalir, "growx, gapy 15 15");

        JButton btnSalir = crearBoton("Salir", new FlatSVGIcon(rutasIconos[itemsMenu.length - 1], 30, 30));
        btnSalir.addActionListener(e -> System.exit(0));
        panel.add(btnSalir);

        // Panel lateral a la izquierda
        add(panel, "cell 0 0, growy");

        // Panel de contenido dinámico
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Mantiene el fondo consistente
        add(contentPanel, "cell 1 0, grow"); // ¡Esto hace que se llene bien a la derecha!
    }

    private JButton crearBoton(String texto, FlatSVGIcon icono) {
        JButton btn = new JButton(texto, icono);
        btn.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#2884b5;"
                + "foreground:#e9f4f3;"
                + "selectedBackground:#1c6891;"
                + "selectedForeground:#e9f4f3;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "arc:10;"
                + "iconTextGap:15;"
                + "margin:10,15,10,15");
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setPreferredSize(new Dimension(200, 40));
        return btn;
    }

    private void showPanel(JPanel panel) {
        if (currentPanel != null && currentPanel.getClass().equals(panel.getClass())) {
            return;
        }
        contentPanel.removeAll();
        currentPanel = panel;
        contentPanel.add(currentPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
