package com.farmacia.sanrafael.APIJava.frontend.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class frmClientes extends JPanel {

    private JTable tblClientes;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JLabel lblTitulo;
    private JButton btnAgregar, btnEditar, btnEliminar;

    public frmClientes() {
        init();
        tblClientes.getTableHeader().setResizingAllowed(false);
    }

    private void init() {
        // Layout principal - fill total, padding de 20px
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow]"));
        setBackground(new Color(172, 212, 227));

        // Título grande
        lblTitulo = new JLabel("CLIENTES");
        lblTitulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +7;");
        add(lblTitulo, "wrap, align left");

        // Barra de búsqueda con icono y estilo
        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE, ""
                + "background: #e9f4f3;"
                + "foreground: #000000;"
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;");

        // Botones con estilos consistentes
        btnAgregar = crearBoton("Agregar");
        btnEditar = crearBoton("Editar");
        btnEliminar = crearBoton("Eliminar");

        // Eventos de botones (igual que antes)
        btnAgregar.addActionListener(e -> {
            frmClienteDialog.mostrarDialogoAgregar(SwingUtilities.getWindowAncestor(frmClientes.this));
        });

        btnEditar.addActionListener(e -> {
            int filaSeleccionada = tblClientes.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int id = (int) tblClientes.getValueAt(filaSeleccionada, 0);
                String nombre = (String) tblClientes.getValueAt(filaSeleccionada, 1);
                String apellido = (String) tblClientes.getValueAt(filaSeleccionada, 2);
                String email = (String) tblClientes.getValueAt(filaSeleccionada, 3);
                String telefono = (String) tblClientes.getValueAt(filaSeleccionada, 4);
                String direccion = (String) tblClientes.getValueAt(filaSeleccionada, 5);

                frmClienteDialog.mostrarDialogoEditar(
                        SwingUtilities.getWindowAncestor(this),
                        id, nombre, apellido, email, telefono, direccion
                );
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente para editar",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tblClientes.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de eliminar el cliente seleccionado?", "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
                    model.removeRow(filaSeleccionada);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Panel superior con búsqueda y botones
        JPanel topPanel = new JPanel(new MigLayout("fill, insets 0", "[grow][pref][pref][pref]", "[]"));
        topPanel.putClientProperty(FlatClientProperties.STYLE, "background:#acd4e3;");
        topPanel.add(txtBuscar, "growx, push");
        topPanel.add(btnAgregar, "gapleft 10, h 30!");
        topPanel.add(btnEditar, "gapleft 10, h 30!");
        topPanel.add(btnEliminar, "gapleft 10, h 30!");
        add(topPanel, "growx, wrap");

        // Tabla de clientes
        tblClientes = new JTable();
        scroll = new JScrollPane(tblClientes);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Aquí quitamos tamaños fijos para que la tabla se adapte al contenedor
        scroll.setPreferredSize(null);
        scroll.setMinimumSize(null);
        scroll.setMaximumSize(null);

        // Modelo tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID CLIENTE", "NOMBRE", "APELLIDO", "EMAIL", "TELÉFONO", "DIRECCIÓN"}
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblClientes.setModel(model);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Ajuste para evitar que la tabla se salga horizontalmente
        tblClientes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Anchos de columnas
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(65);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(175);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(5).setPreferredWidth(300);

        // Cabecera alineada a la izquierda y estilo
        JTableHeader header = tblClientes.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        header.putClientProperty(FlatClientProperties.STYLE, "background:#ccdddc;");

        // Estilo de filas
        tblClientes.setRowHeight(30);
        tblClientes.setShowHorizontalLines(true);
        tblClientes.setShowVerticalLines(false);
        tblClientes.setGridColor(new Color(200, 200, 200));

        // Añadimos la tabla con scroll que ocupa todo el espacio restante
        add(scroll, "grow, push");

        // Datos de prueba para verificar tabla
        cargarDatosPrueba();

        // Aquí podrías agregar filtro de búsqueda con txtBuscar
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        boton.setPreferredSize(new Dimension(90, 30)); // Tamaño uniforme para botones
        return boton;
    }

    private void cargarDatosPrueba() {
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.addRow(new Object[]{1, "Moisés", "Calderón", "moises.calderon4@catolica.edu.sv", "7244-5808", "km 123/2 frontera angüiatú metpán"});
        model.addRow(new Object[]{2, "Ana", "Martínez", "ana.martinez@email.com", "7000-1234", "Col. Escalón, San Salvador"});
    }
}
