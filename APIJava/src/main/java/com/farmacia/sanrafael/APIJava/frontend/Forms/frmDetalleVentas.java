package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class frmDetalleVentas extends JPanel {

    private JTable tblDetalle;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JTextField txtClienteId;
    private JTextField txtEmpleadoId;
    private JComboBox<String> cmbMetodoPago;
    private JLabel lblTotal;
    private JButton btnAgregar, btnEditar, btnEliminar;

    public frmDetalleVentas() {
        init();
        tblDetalle.getTableHeader().setResizingAllowed(false);
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][][grow][]"));
        setBackground(new Color(172, 212, 227));

        // Título
        JLabel lblTitulo = new JLabel("DETALLE DE VENTA");
        lblTitulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +7;");
        add(lblTitulo, "wrap, align left");

        // Panel superior con datos del cliente y empleado - VERSIÓN CORREGIDA
        JPanel panelSuperior = new JPanel(new MigLayout("insets 0", "[][100!][30][][70!][20][][100!]", "[]"));
        panelSuperior.setBackground(new Color(172, 212, 227));

        panelSuperior.add(new JLabel("ID Cliente:"), "gapright 5");
        txtClienteId = new JTextField();
        txtClienteId.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID Cliente");
        txtClienteId.putClientProperty(FlatClientProperties.STYLE, "" +
                "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:3,5,3,5;");
        panelSuperior.add(txtClienteId, "w 100!");

        // Campo para empleado
        panelSuperior.add(new JLabel("Empleado ID:"), "gapleft 30, gapright 5");
        txtEmpleadoId = new JTextField();
        txtEmpleadoId.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID");
        txtEmpleadoId.putClientProperty(FlatClientProperties.STYLE, "" +
                "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:3,5,3,5;");
        panelSuperior.add(txtEmpleadoId, "w 70!");

        // Método de pago
        panelSuperior.add(new JLabel("Pago:"), "gapleft 20, gapright 5");
        cmbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta"});
        cmbMetodoPago.putClientProperty(FlatClientProperties.STYLE, "" +
                "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:3,5,3,5;");
        panelSuperior.add(cmbMetodoPago, "w 100!");

        add(panelSuperior, "growx, wrap");

        // Barra de búsqueda y botones - VERSIÓN CORREGIDA
        JPanel panelBusqueda = new JPanel(new MigLayout("fillx, insets 0", "[grow][][][]", "[]"));
        panelBusqueda.setBackground(new Color(172, 212, 227));

        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar producto...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE, "" +
                "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:5,20,5,20;");
        panelBusqueda.add(txtBuscar, "growx");

        btnAgregar = new JButton("Agregar");
        btnAgregar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        panelBusqueda.add(btnAgregar, "gapleft 10");

        btnAgregar.addActionListener(e -> {
            frmSeleccionarProductos.mostrarDialogo(
                    SwingUtilities.getWindowAncestor(frmDetalleVentas.this)
            );
        });

        btnEditar = new JButton("Editar");
        btnEditar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        panelBusqueda.add(btnEditar, "gapleft 10");

        btnEliminar = new JButton("Eliminar");
        btnEliminar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        panelBusqueda.add(btnEliminar, "gapleft 10, wrap");

        add(panelBusqueda, "growx, wrap");

        // Tabla de detalle de venta - VERSIÓN CORREGIDA
        tblDetalle = new JTable();
        scroll = new JScrollPane(tblDetalle);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tblDetalle.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:15; background:#e9f4f3;");
        scroll.setPreferredSize(new Dimension(1300, 610));
        scroll.setMinimumSize(new Dimension(1300, 610));
        scroll.setMaximumSize(new Dimension(1300, 610));

        // Modelo de tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Producto", "Nombre", "Laboratorio", "Precio/U", "Cantidad", "Subtotal"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDetalle.setModel(model);
        tblDetalle.setRowHeight(30);
        tblDetalle.setShowHorizontalLines(true);
        tblDetalle.setGridColor(new Color(200, 200, 200));
        tblDetalle.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#ccdddc; font:bold +2");

        // Ajustar anchos de columnas
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(65);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblDetalle.getColumnModel().getColumn(5).setPreferredWidth(100);

        add(scroll, "grow, push, wrap");

        // Panel inferior con total y botón volver
        JPanel panelInferior = new JPanel(new MigLayout("fillx, insets 0", "[grow][]", "[]"));
        panelInferior.setBackground(new Color(172, 212, 227));

        lblTotal = new JLabel("Total: $0.00");
        lblTotal.putClientProperty(FlatClientProperties.STYLE, "font:bold +4");
        panelInferior.add(lblTotal);

        JButton btnVolver = new JButton("Volver a Ventas");
        btnVolver.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnVolver.addActionListener(e -> volverAVentas());
        panelInferior.add(btnVolver, "align right");

        JButton btnFinalizar = new JButton("Finalizar Venta");
        btnFinalizar.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#a5d6a7; foreground:#1b5e20;");  // Verde claro con texto oscuro
        //btnFinalizar.addActionListener(e -> finalizarVenta());
        panelInferior.add(btnFinalizar, "gapleft 10");

        add(panelInferior, "growx");

        // Datos de prueba
        agregarDatosPrueba();
    }

    private void agregarDatosPrueba() {
        DefaultTableModel model = (DefaultTableModel) tblDetalle.getModel();
        model.addRow(new Object[]{1, "Acetaminofén", "MK", 1.50, 3, 4.50});
        model.addRow(new Object[]{2, "Amoxicilina", "MK", 2.75, 2, 5.50});
        calcularTotal();
    }

    private void calcularTotal() {
        double total = 0;
        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            total += Double.parseDouble(tblDetalle.getValueAt(i, 4).toString());
        }
        lblTotal.setText(String.format("Total: $%.2f", total));
    }

    private void volverAVentas() {
        Container parent = this.getParent();
        parent.remove(this);
        parent.add(new frmVentas());
        parent.revalidate();
        parent.repaint();
    }
}
