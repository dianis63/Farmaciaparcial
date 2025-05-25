package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmSeleccionarProductos extends JDialog {

    private JTable tblProductosDisponibles;
    private JTextField txtBuscar;
    private JButton btnAceptar, btnCancelar;

    public frmSeleccionarProductos(Window parent) {
        super(parent, "Seleccionar Productos", ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][grow][]"));
        getContentPane().setBackground(new Color(172, 212, 227));

        // Barra de búsqueda
        txtBuscar = new JTextField();
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar producto...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Icon/iconoBuscar.svg"));
        txtBuscar.putClientProperty(FlatClientProperties.STYLE,
                "background: #e9f4f3; arc:15; borderWidth:0; focusWidth:0; margin:5,20,5,20;");
        add(txtBuscar, "growx, wrap");

        // Tabla de productos disponibles
        tblProductosDisponibles = new JTable();
        JScrollPane scroll = new JScrollPane(tblProductosDisponibles);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tblProductosDisponibles.putClientProperty(FlatClientProperties.STYLE,
                "arc:15; background:#e9f4f3;");

        tblProductosDisponibles.setCellSelectionEnabled(true);
        tblProductosDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblProductosDisponibles.setColumnSelectionAllowed(false);
        tblProductosDisponibles.setRowSelectionAllowed(false);

        tblProductosDisponibles.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                int row = tblProductosDisponibles.rowAtPoint(p);
                int col = tblProductosDisponibles.columnAtPoint(p);

                if (col == 4) { // Solo para la columna de Cantidad
                    tblProductosDisponibles.editCellAt(row, col);
                    Component editor = tblProductosDisponibles.getEditorComponent();
                    if (editor != null) {
                        editor.requestFocusInWindow();
                    }
                }
            }
        });

        // Modelo de tabla
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Laboratorio", "Precio", "Cantidad"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna Cantidad es editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Double.class; // Precio
                if (columnIndex == 4) return Integer.class; // Cantidad
                return Object.class;
            }
        };

        // Agregar datos de prueba
        model.addRow(new Object[]{1, "Paracetamol 500mg", "MK", 12.50, 0});
        model.addRow(new Object[]{2, "Ibuprofeno 400mg", "MK", 15.75, 0});

        tblProductosDisponibles.setModel(model);
        configurarEncabezado();

        // Configurar el editor con JSpinner para la columna de cantidad
        tblProductosDisponibles.getColumnModel().getColumn(4).setCellEditor(new SpinnerEditor());

        // Configurar renderizador para la columna de cantidad
        tblProductosDisponibles.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                return label;
            }
        });

        // Configurar alineación de contenido
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

// Aplicar renderizador izquierdo a todas las columnas
        for (int i = 0; i < tblProductosDisponibles.getColumnCount(); i++) {
            tblProductosDisponibles.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
        }

        // Configuración visual de la tabla
        tblProductosDisponibles.setRowHeight(40);
        tblProductosDisponibles.setShowHorizontalLines(true);
        tblProductosDisponibles.setGridColor(new Color(200, 200, 200));
        tblProductosDisponibles.getTableHeader().setReorderingAllowed(false);
        tblProductosDisponibles.getTableHeader().setResizingAllowed(false);

        // Ajustar anchos de columnas
        tblProductosDisponibles.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        tblProductosDisponibles.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nombre
        tblProductosDisponibles.getColumnModel().getColumn(2).setPreferredWidth(100);  // Laboratorio
        tblProductosDisponibles.getColumnModel().getColumn(3).setPreferredWidth(70);   // Precio
        tblProductosDisponibles.getColumnModel().getColumn(4).setPreferredWidth(100);  // Cantidad

        add(scroll, "grow, push, wrap");

        // Panel de botones
        JPanel panelBotones = new JPanel(new MigLayout("insets 0, fillx", "[grow][]", "[]"));
        panelBotones.setBackground(new Color(172, 212, 227));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar = new JButton("Aceptar");
        btnAceptar.putClientProperty(FlatClientProperties.STYLE, "background:#a5d6a7; foreground:#1b5e20;");
        btnAceptar.addActionListener(e -> {
            // Lógica para aceptar la selección
            dispose();
        });

        panelBotones.add(new JLabel(), "growx, push");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar, "gapleft 10");

        add(panelBotones, "growx");
    }

    private void configurarEncabezado() {
        JTableHeader header = tblProductosDisponibles.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14));
        header.setBackground(new Color(204, 221, 220));
    }

    // Clase para el editor con JSpinner
    private class SpinnerEditor extends DefaultCellEditor {
        private JSpinner spinner;

        public SpinnerEditor() {
            super(new JTextField());
            spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

            JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "#");
            spinner.setEditor(editor);

            // Alinear a la izquierda
            editor.getTextField().setHorizontalAlignment(SwingConstants.LEFT);

            spinner.putClientProperty(FlatClientProperties.STYLE,
                    "background:#e9f4f3; arc:10;");
            spinner.setPreferredSize(new Dimension(80, 25));

            // Hacer que el spinner se active con un solo clic
            editor.getTextField().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    spinner.requestFocusInWindow();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            spinner.setValue(value != null ? value : 0);
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }

    public static void mostrarDialogo(Window parent) {
        frmSeleccionarProductos dialog = new frmSeleccionarProductos(parent);
        dialog.setVisible(true);
    }
}
