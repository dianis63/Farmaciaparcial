package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class frmSeleccionarEmpleado extends JDialog {

    private JTable tblEmpleados;
    private JTextField txtBuscar;
    private JButton btnAceptar, btnCancelar;

    public frmSeleccionarEmpleado(Window parent) {
        super(parent, "Seleccionar Empleado", ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setSize(600, 400); // Tamaño más compacto para empleados
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public static void mostrarDialogo(Window parent) {
        frmSeleccionarEmpleado dialog = new frmSeleccionarEmpleado(parent);
        dialog.setVisible(true);
    }

    private void initComponents() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow][]"));
        getContentPane().setBackground(new Color(172, 212, 227));

        // Barra de búsqueda
        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar empleado...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE,
                "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:5,20,5,20;");
        add(txtBuscar, "growx, wrap");

        // Tabla de empleados
        tblEmpleados = new JTable();
        JScrollPane scroll = new JScrollPane(tblEmpleados);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tblEmpleados.putClientProperty(FlatClientProperties.STYLE,
                "arc:15; background:#e9f4f3;");

        // Modelo de tabla con solo 3 columnas
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Empleado", "Nombre", "Apellido"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };

        // Agregar datos de prueba
        model.addRow(new Object[]{"EMP001", "Juan", "Pérez"});
        model.addRow(new Object[]{"EMP002", "María", "González"});
        model.addRow(new Object[]{"EMP003", "Carlos", "Rodríguez"});

        tblEmpleados.setModel(model);
        configurarEncabezado();

        // Configuración visual de la tabla
        tblEmpleados.setRowHeight(30);
        tblEmpleados.setShowHorizontalLines(true);
        tblEmpleados.setGridColor(new Color(200, 200, 200));
        tblEmpleados.getTableHeader().setReorderingAllowed(false);
        tblEmpleados.getTableHeader().setResizingAllowed(false);

        // Ajustar anchos de columnas
        tblEmpleados.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblEmpleados.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblEmpleados.getColumnModel().getColumn(2).setPreferredWidth(200);

        add(scroll, "grow, push, wrap");

        // Panel de botones alineados a la derecha
        JPanel panelBotones = new JPanel(new MigLayout("insets 0, fillx", "[grow][]", "[]"));
        panelBotones.setBackground(new Color(172, 212, 227));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar = new JButton("Aceptar");
        btnAceptar.putClientProperty(FlatClientProperties.STYLE, "background:#a5d6a7; foreground:#1b5e20;");
        btnAceptar.addActionListener(e -> {
            // Lógica para seleccionar empleado
            seleccionarEmpleado();
        });

        panelBotones.add(new JLabel(), "growx, push");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar, "gapleft 10");

        add(panelBotones, "growx");
    }

    private void configurarEncabezado() {
        JTableHeader header = tblEmpleados.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14));
        header.setBackground(new Color(204, 221, 220));
    }

    private void seleccionarEmpleado() {
        int filaSeleccionada = tblEmpleados.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Obtener datos del empleado seleccionado
            String id = (String) tblEmpleados.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tblEmpleados.getValueAt(filaSeleccionada, 1);
            String apellido = (String) tblEmpleados.getValueAt(filaSeleccionada, 2);

            // Aquí puedes pasar estos datos al formulario principal
            System.out.println("Empleado seleccionado: " + id + " - " + nombre + " " + apellido);

            dispose(); // Cerrar el diálogo
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
