package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class frmVentas extends JPanel {

    private JTable tblVentas;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JLabel lblTitulo;
    private JButton btnAgregar, btnEditar, btnEliminar;

    public frmVentas() {
        init();
        tblVentas.getTableHeader().setResizingAllowed(false);
    }

    private void init() {
        // Usar MigLayout en el panel principal
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow]"));
        setBackground(new Color(172, 212, 227));

        // Título
        lblTitulo = new JLabel("VENTAS");
        lblTitulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +7;");
        add(lblTitulo, "wrap, align left"); // "wrap" para pasar a la siguiente fila

        // Barra de búsqueda y botones
        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: #e9f4f3;"  // Color de fondo más oscuro
                + "foreground: #000000;"  // Color del texto (blanco)
                + "arc:15;"               // Bordes redondeados
                + "borderWidth:0;"        // Sin borde
                + "focusWidth:0;"         // Sin borde al enfocarse
                + "innerFocusWidth:0;"    // Sin borde interno al enfocarse
                + "margin:5,20,5,20;");

        btnAgregar = new JButton("Agregar");
        btnAgregar.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#e9f4f3;");
        btnEditar = new JButton("Editar");
        btnEditar.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#e9f4f3;");
        btnEliminar = new JButton("Eliminar");
        btnEliminar.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#e9f4f3;");

        // Panel para la barra de búsqueda y botones
        JPanel topPanel = new JPanel(new MigLayout("fill, insets 0", "[grow][][]", "[]"));
        topPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#acd4e3;");

        topPanel.add(txtBuscar, "grow, push"); // La barra de búsqueda crece y empuja los botones
        topPanel.add(btnAgregar, "gapleft 10"); // Espacio a la izquierda del botón Agregar
        topPanel.add(btnEditar, "gapleft 10"); // Espacio a la izquierda del botón Editar
        topPanel.add(btnEliminar, "gapleft 10, wrap"); // Espacio a la izquierda del botón Eliminar

        add(topPanel, "grow, wrap"); // Añadir el topPanel en la parte superior

        // Tabla de inventario
        tblVentas = new JTable();
        scroll = new JScrollPane(tblVentas);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Permitir redimensionamiento manual del JScrollPane
        scroll.setPreferredSize(new Dimension(1300, 675)); // Tamaño inicial
        scroll.setMinimumSize(new Dimension(1300, 675));   // Tamaño mínimo
        scroll.setMaximumSize(new Dimension(1300, 675));  // Tamaño máximo

        // Configurar la tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID VENTA", "ID USUARIO", "ID EMPLEADO", "TOTAL", "FECHA", "ESTADO", "DETALLE"} // Nueva columna "DETALLE"
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) { // La columna "DETALLE" contendrá botones
                    return JButton.class;
                }
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 6; // Solo la columna "DETALLE" será editable (para el botón)
            }
        };

        tblVentas.setDefaultRenderer(JButton.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof JButton) {
                    return (JButton) value;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

// Editor para manejar los clics en el botón
        tblVentas.setDefaultEditor(JButton.class, new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (value instanceof JButton) {
                    return (JButton) value;
                }
                return super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }
        });

        tblVentas.setModel(model);
        tblVentas.getTableHeader().setReorderingAllowed(false);
        tblVentas.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Ajustar el ancho de las columnas
        tblVentas.getColumnModel().getColumn(0).setPreferredWidth(65);
        tblVentas.getColumnModel().getColumn(1).setPreferredWidth(65);
        tblVentas.getColumnModel().getColumn(2).setPreferredWidth(65);
        tblVentas.getColumnModel().getColumn(3).setPreferredWidth(155);
        tblVentas.getColumnModel().getColumn(4).setPreferredWidth(155);
        tblVentas.getColumnModel().getColumn(5).setPreferredWidth(155);
        tblVentas.getColumnModel().getColumn(5).setPreferredWidth(155);

        // Alinear los títulos de las columnas a la izquierda
        JTableHeader header = tblVentas.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT); // Alinear a la izquierda
        header.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#ccdddc;");

        // Añadir la tabla al panel
        add(scroll, "grow, push"); // La tabla ocupa todo el espacio restante

        tblVentas.setRowHeight(30); // Ajusta este valor según necesites

        tblVentas.setShowHorizontalLines(true);
        tblVentas.setShowVerticalLines(false); // Puedes mantener las verticales ocultas si prefieres
        tblVentas.setGridColor(new Color(200, 200, 200)); // Color gris claro para las líneas
        // Añadir datos de prueba
        testData();
    }

    private void testData() {
        DefaultTableModel model = (DefaultTableModel) tblVentas.getModel();

        // Datos de ejemplo
        Object[] row1 = {1, 1, 1, "$25.50", "24/03/25", "finalizado", crearBotonDetalle(1)};
        Object[] row2 = {2, 2, 2, "$50.00", "25/03/25", "pendiente", crearBotonDetalle(2)};

        model.addRow(row1);
        model.addRow(row2);
    }

    private JButton crearBotonDetalle(int idVenta) {
        JButton btnDetalle = new JButton("Ver Detalle");
        btnDetalle.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#e9f4f3;");

        btnDetalle.addActionListener(e -> {
            // Abrir el formulario de detalle con el ID de la venta
            abrirDetalleVenta(idVenta);
        });

        return btnDetalle;
    }

    private void abrirDetalleVenta(int idVenta) {
        Container parentContainer = this.getParent();
        parentContainer.remove(this);

        // Usamos el constructor sin parámetros por ahora
        frmDetalleVentas detallePanel = new frmDetalleVentas();
        parentContainer.add(detallePanel);

        parentContainer.revalidate();
        parentContainer.repaint();

        // Opcional: Mostrar el ID en consola para verificar que funciona
        System.out.println("Venta seleccionada ID: " + idVenta);
    }
}
