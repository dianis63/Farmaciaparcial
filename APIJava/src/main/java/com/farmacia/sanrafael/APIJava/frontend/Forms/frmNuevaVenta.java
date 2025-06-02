package com.farmacia.sanrafael.APIJava.frontend.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class frmNuevaVenta extends JPanel {

    private JTable tblProductos;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JTextField txtClienteId;
    private JTextField txtEmpleadoId;
    private JLabel lblTitulo;
    private JButton btnAgregarCliente, btnAgregarEmpleado;
    private JButton btnAgregarProducto, btnEditarProducto, btnEliminarProducto;
    private JButton btnGuardarVenta;
    private JLabel lblTotal;

    public frmNuevaVenta() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][][grow][]"));
        setBackground(new Color(172, 212, 227));

        // Título
        lblTitulo = new JLabel("NUEVA VENTA");
        lblTitulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +7;");
        add(lblTitulo, "wrap, align left");

        // Panel superior con datos del cliente y empleado
        JPanel panelSuperior = new JPanel(new MigLayout("insets 0", "[][100!][][][70!][]", "[]"));
        panelSuperior.setBackground(new Color(172, 212, 227));

        panelSuperior.add(new JLabel("ID Cliente:"), "gapright 5");
        txtClienteId = new JTextField();
        txtClienteId.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID Cliente");
        txtClienteId.putClientProperty(FlatClientProperties.STYLE, "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:3,5,3,5;");
        panelSuperior.add(txtClienteId, "w 100!");

        btnAgregarCliente = new JButton("Agregar");
        btnAgregarCliente.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        panelSuperior.add(btnAgregarCliente, "gapleft 4, w 80!");
        btnAgregarCliente.addActionListener(e -> frmSeleccionarCliente.mostrarDialogo(SwingUtilities.getWindowAncestor(this)));

        panelSuperior.add(new JLabel("ID Empleado:"), "gapleft 20, gapright 5");
        txtEmpleadoId = new JTextField();
        txtEmpleadoId.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID Empleado");
        txtEmpleadoId.putClientProperty(FlatClientProperties.STYLE, "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:3,5,3,5;");
        panelSuperior.add(txtEmpleadoId, "w 100!");

        btnAgregarEmpleado = new JButton("Agregar");
        btnAgregarEmpleado.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        panelSuperior.add(btnAgregarEmpleado, "gapleft 34, w 80!");
        btnAgregarEmpleado.addActionListener(e -> frmSeleccionarEmpleado.mostrarDialogo(SwingUtilities.getWindowAncestor(this)));

        add(panelSuperior, "growx, wrap");

        // Panel búsqueda
        JPanel panelBusqueda = new JPanel(new MigLayout("fillx, insets 0", "[grow][][][]", "[]"));
        panelBusqueda.setBackground(new Color(172, 212, 227));

        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar producto...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE, "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:5,20,5,20;");
        panelBusqueda.add(txtBuscar, "growx");

        btnAgregarProducto = new JButton("Agregar");
        btnAgregarProducto.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnAgregarProducto.addActionListener(e -> frmSeleccionarProductos.mostrarDialogo(SwingUtilities.getWindowAncestor(this)));
        panelBusqueda.add(btnAgregarProducto, "gapleft 10");

        btnEditarProducto = new JButton("Editar");
        btnEditarProducto.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        panelBusqueda.add(btnEditarProducto, "gapleft 10");

        btnEliminarProducto = new JButton("Eliminar");
        btnEliminarProducto.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        panelBusqueda.add(btnEliminarProducto, "gapleft 10, wrap");

        add(panelBusqueda, "growx, wrap");

        // Tabla productos
        tblProductos = new JTable();
        scroll = new JScrollPane(tblProductos);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tblProductos.putClientProperty(FlatClientProperties.STYLE, "background: #e9f4f3;");
        add(scroll, "grow, push, span, wrap");

        // Modelo tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Producto", "Nombre", "Laboratorio", "Precio/U", "Cantidad", "Subtotal"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProductos.setModel(model);
        configurarEncabezadoTabla();
        tblProductos.setRowHeight(30);
        tblProductos.setShowHorizontalLines(true);
        tblProductos.setGridColor(new Color(200, 200, 200));
        tblProductos.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "background:#ccdddc; font:bold +2");
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.getTableHeader().setResizingAllowed(false);

        // Panel inferior
        JPanel panelInferior = new JPanel(new MigLayout("fillx, insets 0", "[grow][]", "[]"));
        panelInferior.setBackground(new Color(172, 212, 227));

        lblTotal = new JLabel("Total: $0.00");
        lblTotal.putClientProperty(FlatClientProperties.STYLE, "font:bold +4");
        panelInferior.add(lblTotal);

        btnGuardarVenta = new JButton("Guardar Venta");
        btnGuardarVenta.putClientProperty(FlatClientProperties.STYLE, "background:#a5d6a7; foreground:#1b5e20; font:bold");
        panelInferior.add(btnGuardarVenta, "gapleft 10, align right");

        add(panelInferior, "growx, wrap");

        // Datos de prueba y total
        agregarDatosPrueba();
        calcularTotal();
    }

    private void configurarEncabezadoTabla() {
        JTableHeader header = tblProductos.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14));
    }

    private void agregarDatosPrueba() {
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
        model.addRow(new Object[]{1, "Acetaminofén", "MK", 1.50, 3, 4.50});
        model.addRow(new Object[]{2, "Amoxicilina", "MK", 2.75, 2, 5.50});
    }

    private void calcularTotal() {
        double total = 0;
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                double precio = Double.parseDouble(model.getValueAt(i, 3).toString());
                int cantidad = Integer.parseInt(model.getValueAt(i, 4).toString());
                double subtotal = precio * cantidad;
                model.setValueAt(subtotal, i, 5);
                total += subtotal;
            } catch (NumberFormatException e) {
                System.err.println("Error en formato numérico en la fila " + i);
            }
        }

        lblTotal.setText(String.format("Total: $%.2f", total));
    }
}
