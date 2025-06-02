package com.farmacia.sanrafael.APIJava.frontend.Forms;

import com.farmacia.sanrafael.APIJava.frontend.Client.VentaClient;
import com.farmacia.sanrafael.APIJava.frontend.models.DetalleVentasDTO;
import com.farmacia.sanrafael.APIJava.frontend.models.VentasDTO;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class frmVentas extends JPanel {

    private JTable tblVentas;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JLabel lblTitulo;
    private JButton btnEditar, btnEliminar;

    public frmVentas() {
        init();
        tblVentas.getTableHeader().setResizingAllowed(false);
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow]"));
        setBackground(new Color(172, 212, 227));

        // Título
        lblTitulo = new JLabel("VENTAS");
        lblTitulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +7;");
        add(lblTitulo, "wrap, align left");

        // Barra de búsqueda y botones
        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar por Fecha...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE, "" + "background: #e9f4f3;" + "foreground: #000000;" + "arc:15;" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0;" + "margin:5,20,5,20;");
        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                mostrarVentas();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                mostrarVentas();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                mostrarVentas();
            }
        });

        btnEditar = new JButton("Editar");
        btnEditar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnEditar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "No se puede modificar una venta finalizada.", "Acción no permitida", JOptionPane.WARNING_MESSAGE);
        });

        btnEliminar = new JButton("Eliminar");
        btnEliminar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnEliminar = new JButton("Eliminar");
        btnEliminar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tblVentas.getSelectedRow();
            if (filaSeleccionada >= 0) {
                long idVenta = (long) tblVentas.getValueAt(filaSeleccionada, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar esta venta?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        VentaClient cliente = new VentaClient();
                        cliente.eliminarVenta(idVenta);
                        JOptionPane.showMessageDialog(this, "Venta eliminada con éxito.");
                        mostrarVentas(); // Refresca la tabla
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al eliminar la venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una venta primero.");
            }
        });

        JPanel topPanel = new JPanel(new MigLayout("fillx, insets 5", "[grow][][][]", "[]"));
        topPanel.putClientProperty(FlatClientProperties.STYLE, "background:#acd4e3;");
        topPanel.add(txtBuscar, "growx, pushx, wmin 150");
        topPanel.add(btnEditar, "gapleft 10");
        topPanel.add(btnEliminar, "gapleft 10, wrap");

        add(topPanel, "grow, wrap");

        // Tabla de ventas
        tblVentas = new JTable();
        scroll = new JScrollPane(tblVentas);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(null);

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"ID VENTA", "CLIENTE", "EMPLEADO", "FECHA VENTA", "ESTADO", "TOTAL", "DETALLE"}) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) return JButton.class;
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 6;
            }
        };

        tblVentas.setModel(model);
        tblVentas.setDefaultRenderer(JButton.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof JButton) {
                    return (JButton) value;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

        tblVentas.setDefaultEditor(JButton.class, new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (value instanceof JButton) {
                    return (JButton) value;
                }
                return super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }
        });

        tblVentas.getTableHeader().setReorderingAllowed(false);
        tblVentas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblVentas.setRowHeight(30);
        tblVentas.setShowHorizontalLines(true);
        tblVentas.setShowVerticalLines(false);
        tblVentas.setGridColor(new Color(200, 200, 200));

        // Ajuste de columnas
        tblVentas.getColumnModel().getColumn(0).setPreferredWidth(65);
        tblVentas.getColumnModel().getColumn(1).setPreferredWidth(65);
        tblVentas.getColumnModel().getColumn(2).setPreferredWidth(65);
        tblVentas.getColumnModel().getColumn(3).setPreferredWidth(155);
        tblVentas.getColumnModel().getColumn(4).setPreferredWidth(155);
        tblVentas.getColumnModel().getColumn(5).setPreferredWidth(155);
        tblVentas.getColumnModel().getColumn(6).setPreferredWidth(120); // Detalle

        JTableHeader header = tblVentas.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        header.putClientProperty(FlatClientProperties.STYLE, "background:#ccdddc;");

        add(scroll, "grow, push");

        mostrarVentas();
    }

    private void mostrarVentas() {
        String filtro = txtBuscar.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tblVentas.getModel();
        model.setRowCount(0);

        try {
            VentaClient cliente = new VentaClient();
            List<VentasDTO> lista;

            if (filtro.isEmpty()) {
                lista = cliente.obtenerVentas();
            } else {
                lista = cliente.buscarPorFecha(filtro);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (VentasDTO v : lista) {
                String fechaFormateada = v.getFecha() != null ? sdf.format(v.getFecha()) : "";
                String estadoFormateado = "A".equalsIgnoreCase(v.getEstado()) ? "Activo" : "Cancelado";
                JButton btnDetalle = crearBotonDetalle(v.getid_venta());

                model.addRow(new Object[]{
                        v.getid_venta(),
                        v.getNombre_cliente(),
                        v.getNombre_empleado(),
                        fechaFormateada,
                        estadoFormateado,
                        "$" + v.getTotal(),
                        btnDetalle
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar ventas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private JButton crearBotonDetalle(long idVenta) {
        JButton btnDetalle = new JButton("Ver Detalle");
        btnDetalle.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");

        btnDetalle.addActionListener(e -> {
            try {
                VentaClient cliente = new VentaClient();
                List<DetalleVentasDTO> detalles = cliente.obtenerDetalleVenta(idVenta);

                String[] columnas = {"Producto", "Cantidad", "Precio"};
                DefaultTableModel detalleModel = new DefaultTableModel(columnas, 0);

                for (DetalleVentasDTO d : detalles) {
                    detalleModel.addRow(new Object[]{d.getNombre_producto(), d.getcantidad(), "$" + d.getprecio_unitario()});
                }

                JTable detalleTable = new JTable(detalleModel);
                detalleTable.setEnabled(false);
                JScrollPane scrollPane = new JScrollPane(detalleTable);
                scrollPane.setPreferredSize(new Dimension(400, 200));

                JOptionPane.showMessageDialog(this, scrollPane, "Detalle de Venta - ID: " + idVenta, JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener detalle de venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        return btnDetalle;
    }

}
