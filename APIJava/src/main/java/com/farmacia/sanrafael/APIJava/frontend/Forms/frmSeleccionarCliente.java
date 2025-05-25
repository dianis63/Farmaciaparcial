package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class frmSeleccionarCliente extends JDialog {

    private JTable tblClientes;
    private JTextField txtBuscar;
    private JButton btnAceptar, btnCancelar;

    public frmSeleccionarCliente(Window parent) {
        super(parent, "Seleccionar Cliente", ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public static void mostrarDialogo(Window parent) {
        frmSeleccionarCliente dialog = new frmSeleccionarCliente(parent);
        dialog.setVisible(true);
    }

    private void initComponents() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow][]"));
        getContentPane().setBackground(new Color(172, 212, 227));

        // Barra de búsqueda
        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar cliente...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE,
                "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:5,20,5,20;");
        add(txtBuscar, "growx, wrap");

        // Tabla de clientes
        tblClientes = new JTable();
        JScrollPane scroll = new JScrollPane(tblClientes);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tblClientes.putClientProperty(FlatClientProperties.STYLE,
                "arc:15; background:#e9f4f3;");

        // Modelo de tabla con columnas para clientes
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Cliente", "Nombre", "Apellido", "Teléfono"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };

        // Agregar datos de prueba
        model.addRow(new Object[]{"CLI001", "Ana", "Martínez", "555-1234"});
        model.addRow(new Object[]{"CLI002", "Luis", "Gómez", "555-5678"});
        model.addRow(new Object[]{"CLI003", "Sofía", "Hernández", "555-9012"});

        tblClientes.setModel(model);
        configurarEncabezado();

        // Configuración visual de la tabla
        tblClientes.setRowHeight(30);
        tblClientes.setShowHorizontalLines(true);
        tblClientes.setGridColor(new Color(200, 200, 200));
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.getTableHeader().setResizingAllowed(false);

        // Ajustar anchos de columnas
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(150); // Apellido
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(100); // Teléfono

        add(scroll, "grow, push, wrap");

        // Panel de botones
        JPanel panelBotones = new JPanel(new MigLayout("insets 0, fillx", "[grow][]", "[]"));
        panelBotones.setBackground(new Color(172, 212, 227));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar = new JButton("Aceptar");
        btnAceptar.putClientProperty(FlatClientProperties.STYLE, "background:#a5d6a7; foreground:#1b5e20;");
        btnAceptar.addActionListener(e -> seleccionarCliente());

        panelBotones.add(new JLabel(), "growx, push");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar, "gapleft 10");

        add(panelBotones, "growx");
    }

    private void configurarEncabezado() {
        JTableHeader header = tblClientes.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14));
        header.setBackground(new Color(204, 221, 220));
    }

    private void seleccionarCliente() {
        int filaSeleccionada = tblClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String id = (String) tblClientes.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tblClientes.getValueAt(filaSeleccionada, 1);
            String apellido = (String) tblClientes.getValueAt(filaSeleccionada, 2);

            // Aquí puedes pasar estos datos al formulario principal
            System.out.println("Cliente seleccionado: " + id + " - " + nombre + " " + apellido);

            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un cliente",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
