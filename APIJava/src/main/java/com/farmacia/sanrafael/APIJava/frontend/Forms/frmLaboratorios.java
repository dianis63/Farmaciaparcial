package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class frmLaboratorios extends JPanel {

    private JTable tblLaboratorio;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JLabel lblTitulo;
    private JButton btnAgregar, btnEditar, btnEliminar;

    public frmLaboratorios() {
        init();
        tblLaboratorio.getTableHeader().setResizingAllowed(false);
    }

    private void init() {
        // Usar MigLayout en el panel principal
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow]"));
        setBackground(new Color(172, 212, 227));

        // Título
        lblTitulo = new JLabel("LABORATORIOS");
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

        btnAgregar.addActionListener(e -> {
            frmLaboratorioDialog.mostrarDialogoAgregar(
                    SwingUtilities.getWindowAncestor(frmLaboratorios.this)
            );
        });

        btnEditar = new JButton("Editar");
        btnEditar.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#e9f4f3;");

        btnEditar.addActionListener(e -> {
            int filaSeleccionada = tblLaboratorio.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int id = (int) tblLaboratorio.getValueAt(filaSeleccionada, 0);
                String nombre = (String) tblLaboratorio.getValueAt(filaSeleccionada, 1);
                String direccion = (String) tblLaboratorio.getValueAt(filaSeleccionada, 2);
                String telefono = (String) tblLaboratorio.getValueAt(filaSeleccionada, 3);
                String email = (String) tblLaboratorio.getValueAt(filaSeleccionada, 4);

                frmLaboratorioDialog dialog = frmLaboratorioDialog.mostrarDialogoEditar(
                        SwingUtilities.getWindowAncestor(this),
                        id, nombre, direccion, telefono, email
                );
                // Aquí puedes obtener los datos editados después de que se cierre el diálogo
            }
        });

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
        tblLaboratorio = new JTable();
        scroll = new JScrollPane(tblLaboratorio);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tblLaboratorio.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:15;" +
                "background:#e9f4f3;");

        // Permitir redimensionamiento manual del JScrollPane
        scroll.setPreferredSize(new Dimension(1300, 675)); // Tamaño inicial
        scroll.setMinimumSize(new Dimension(1300, 675));   // Tamaño mínimo
        scroll.setMaximumSize(new Dimension(1300, 675));  // Tamaño máximo

        // Configurar la tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID LABORATORIO", "NOMBRE", "DIRECCIÓN", "TELEFONO", "EMAIL"} // Sin la columna "SELECCIONAR"
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class; // Todas las columnas son de tipo Object
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false; // Ninguna celda es editable
            }
        };

        tblLaboratorio.setModel(model);
        tblLaboratorio.getTableHeader().setReorderingAllowed(false);
        tblLaboratorio.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Ajustar el ancho de las columnas
        tblLaboratorio.getColumnModel().getColumn(0).setPreferredWidth(65);
        tblLaboratorio.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblLaboratorio.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblLaboratorio.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblLaboratorio.getColumnModel().getColumn(4).setPreferredWidth(150);

        // Alinear los títulos de las columnas a la izquierda
        JTableHeader header = tblLaboratorio.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT); // Alinear a la izquierda
        header.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:#ccdddc;");

        // Añadir la tabla al panel
        add(scroll, "grow, push"); // La tabla ocupa todo el espacio restante

        tblLaboratorio.setRowHeight(30); // Ajusta este valor según necesites

        tblLaboratorio.setShowHorizontalLines(true);
        tblLaboratorio.setShowVerticalLines(false); // Puedes mantener las verticales ocultas si prefieres
        tblLaboratorio.setGridColor(new Color(200, 200, 200)); // Color gris claro para las líneas
        // Añadir datos de prueba
        testData();
    }

    private void testData() {
        DefaultTableModel model = (DefaultTableModel) tblLaboratorio.getModel();
        model.addRow(new Object[]{1, "MK", "2da calle oriente Santa Ana", "7244-5834", "laboratorio.mk@gmail.com"});
    }
}
