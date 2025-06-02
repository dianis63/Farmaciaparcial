package com.farmacia.sanrafael.APIJava.frontend.Forms;

import com.farmacia.sanrafael.APIJava.frontend.Client.ProductoClient;
import com.farmacia.sanrafael.APIJava.frontend.models.ProductoDTO;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class frmInventario extends JPanel {

    private JTable tblInventario;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JLabel lblTitulo;
    private JButton btnAgregar, btnEditar, btnEliminar;

    public frmInventario() {
        init();
        tblInventario.getTableHeader().setResizingAllowed(false);
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow]"));
        setBackground(new Color(172, 212, 227));

        lblTitulo = new JLabel("INVENTARIO");
        lblTitulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +7;");
        add(lblTitulo, "wrap, align left");

        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar por Nombre de Producto...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE, "" + "background: #e9f4f3;" + "foreground: #000000;" + "arc:15;" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0;" + "margin:5,20,5,20;");
        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                buscar();
            }

            private void buscar() {
                String texto = txtBuscar.getText().trim();
                cargarProductosDesdeBackend(texto);
            }
        });

        btnAgregar = new JButton("Agregar");
        btnAgregar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnAgregar.addActionListener(e -> {
            Window parent = SwingUtilities.getWindowAncestor(frmInventario.this);
            frmInventarioDialog dialog = frmInventarioDialog.mostrarDialogoAgregar(parent);
            if (dialog.wasSaved()) {
                agregarProducto(dialog.getNombre(), dialog.getCantidad(), dialog.getPrecio(), dialog.getVencimiento(), dialog.getDescripcion());
            }
        });

        btnEditar = new JButton("Editar");
        btnEditar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnEditar.addActionListener(e -> editarProducto());

        btnEliminar = new JButton("Eliminar");
        btnEliminar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnEliminar.addActionListener(e -> eliminarProducto());

        JPanel topPanel = new JPanel(new MigLayout("fillx, insets 5", "[grow][][][]", "[]"));
        topPanel.putClientProperty(FlatClientProperties.STYLE, "background:#acd4e3;");
        topPanel.add(txtBuscar, "growx, pushx, wmin 100");
        topPanel.add(btnAgregar, "gapleft 10");
        topPanel.add(btnEditar, "gapleft 10");
        topPanel.add(btnEliminar, "gapleft 10, wrap");
        add(topPanel, "growx, wrap");

        tblInventario = new JTable();
        scroll = new JScrollPane(tblInventario);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tblInventario.setDefaultRenderer(Double.class, new DefaultTableCellRenderer() {
            private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column == 3 && value instanceof Number) {
                    value = currencyFormat.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "NOMBRE", "CANTIDAD", "PRECIO/U", "VENCIMIENTO", "DESCRIPCIÓN"}) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        tblInventario.setModel(model);
        tblInventario.getTableHeader().setReorderingAllowed(false);
        tblInventario.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Fijar el auto resize OFF para que use los anchos personalizados
        tblInventario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Mejor escuchar el resize del panel principal (frmInventario) para ajustar columnas
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarAnchoColumnas();
            }
        });

        JTableHeader header = tblInventario.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        header.putClientProperty(FlatClientProperties.STYLE, "background:#ccdddc;");

        add(scroll, "grow, push");
        tblInventario.setRowHeight(30);
        tblInventario.setShowHorizontalLines(true);
        tblInventario.setShowVerticalLines(false);
        tblInventario.setGridColor(new Color(200, 200, 200));

        // Establecer tamaño mínimo para que no quede demasiado pequeño el panel
        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(800, 600));

        cargarProductosDesdeBackend();

        SwingUtilities.invokeLater(this::ajustarAnchoColumnas);
    }

    private void ajustarAnchoColumnas() {
        int totalWidth = scroll.getViewport().getWidth();
        if (totalWidth <= 0) return;

        // Porcentaje de cada columna (debe sumar 100)
        int[] porcentajes = {5, 25, 10, 10, 20, 35};
        int columnas = tblInventario.getColumnCount();
        int anchoUsado = 0;

        for (int i = 0; i < columnas; i++) {
            int ancho;
            if (i == columnas - 1) {
                ancho = totalWidth - anchoUsado;
            } else {
                ancho = (int) (totalWidth * (porcentajes[i] / 100.0));
                anchoUsado += ancho;
            }
            tblInventario.getColumnModel().getColumn(i).setPreferredWidth(ancho);
        }
    }

    private void editarProducto() {
        int filaSeleccionada = tblInventario.getSelectedRow();
        if (filaSeleccionada >= 0) {
            try {
                int id = Integer.parseInt(tblInventario.getValueAt(filaSeleccionada, 0).toString());
                String nombre = tblInventario.getValueAt(filaSeleccionada, 1).toString();
                int cantidad = Integer.parseInt(tblInventario.getValueAt(filaSeleccionada, 2).toString().replaceAll("[^0-9]", ""));
                double precio = Double.parseDouble(tblInventario.getValueAt(filaSeleccionada, 3).toString().replaceAll("[^0-9.]", ""));
                String vencimiento = tblInventario.getValueAt(filaSeleccionada, 4).toString();
                String descripcion = tblInventario.getValueAt(filaSeleccionada, 5).toString();

                Window parentWindow = SwingUtilities.getWindowAncestor(this);
                frmInventarioDialog dialog = frmInventarioDialog.mostrarDialogoEditar(parentWindow, id, nombre, cantidad, precio, vencimiento, descripcion);
                if (dialog.wasSaved()) {
                    ProductoDTO productoActualizado = new ProductoDTO();

                    String nuevoNombre = dialog.getNombre();
                    int nuevaCantidad = dialog.getCantidad();
                    double nuevoPrecio = dialog.getPrecio();
                    Date vencimientoDate = dialog.getVencimiento();
                    String nuevaDescripcion = dialog.getDescripcion();

                    productoActualizado.setIdProducto((long) id);
                    productoActualizado.setNombre(nuevoNombre);
                    productoActualizado.setStock(nuevaCantidad);
                    productoActualizado.setPrecio(nuevoPrecio);
                    productoActualizado.setFecha_vencimiento(vencimientoDate);
                    productoActualizado.setDescripcion(nuevaDescripcion);

                    DefaultTableModel model = (DefaultTableModel) tblInventario.getModel();
                    model.setValueAt(nuevoNombre, filaSeleccionada, 1);
                    model.setValueAt(nuevaCantidad, filaSeleccionada, 2);
                    model.setValueAt(nuevoPrecio, filaSeleccionada, 3);

                    SimpleDateFormat formatoTabla = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaMostrar = (vencimientoDate != null) ? formatoTabla.format(vencimientoDate) : "";
                    model.setValueAt(fechaMostrar, filaSeleccionada, 4);
                    model.setValueAt(nuevaDescripcion, filaSeleccionada, 5);

                    ProductoClient client = new ProductoClient();
                    try {
                        ProductoDTO respuesta = client.actualizarProducto(id, productoActualizado);
                        JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al actualizar producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cargarProductosDesdeBackend() {
        cargarProductosDesdeBackend(null);
    }

    private void cargarProductosDesdeBackend(String filtro) {
        DefaultTableModel model = (DefaultTableModel) tblInventario.getModel();
        model.setRowCount(0);

        try {
            ProductoClient client = new ProductoClient();
            var productos = filtro == null || filtro.isEmpty()
                    ? client.obtenerProductos()
                    : client.buscarPorNombre(filtro);

            // Formateador de fecha compatible con el diálogo
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (var p : productos) {
                String fechaFormateada = p.getFecha_vencimiento() != null
                        ? sdf.format(p.getFecha_vencimiento())
                        : "";

                model.addRow(new Object[]{
                        p.getIdProducto(),
                        p.getNombre(),
                        p.getStock(),
                        "$"+ p.getPrecio(),
                        fechaFormateada,
                        p.getDescripcion()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = tblInventario.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro que desea eliminar el producto seleccionado?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (respuesta == JOptionPane.YES_OPTION) {

                try {
                    long idProducto = Long.parseLong(tblInventario.getValueAt(filaSeleccionada, 0).toString());
                    ProductoClient client = new ProductoClient();
                    client.eliminarProducto(idProducto);
                    DefaultTableModel model = (DefaultTableModel) tblInventario.getModel();
                    model.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(this, "Producto eliminado", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void agregarProducto(String nombre, int cantidad, double precio, Date vencimiento, String descripcion) {
        try {
            ProductoDTO producto = new ProductoDTO();
            producto.setNombre(nombre);
            producto.setStock(cantidad);
            producto.setPrecio(precio);
            producto.setFecha_vencimiento(vencimiento);
            producto.setDescripcion(descripcion);

            ProductoClient client = new ProductoClient();
            ProductoDTO productoGuardado = client.guardarProducto(producto);

            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
            cargarProductosDesdeBackend(); // refrescar tabla
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


}
