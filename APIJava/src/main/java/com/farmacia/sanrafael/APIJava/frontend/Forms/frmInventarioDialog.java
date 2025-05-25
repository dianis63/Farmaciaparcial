package com.farmacia.sanrafael.APIJava.frontend.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class frmInventarioDialog extends JDialog {

    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JFormattedTextField txtFechaVencimiento;
    private JTextArea txtDescripcion;
    private JScrollPane scrollDescripcion;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private boolean datosGuardados = false;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public frmInventarioDialog(Window parent, String titulo) {
        super(parent, titulo, ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setSize(520, 520);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new MigLayout("fill, insets 20", "[grow]", "[][][][][][][]"));
        getContentPane().setBackground(new Color(172, 212, 227));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(labelFont);
        add(lblNombre, "gapbottom 6");
        txtNombre = new JTextField();
        txtNombre.setFont(inputFont);
        configurarCampo(txtNombre);
        add(txtNombre, "growx, wrap");

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(labelFont);
        add(lblCantidad, "gapbottom 6");
        txtCantidad = new JTextField();
        txtCantidad.setFont(inputFont);
        configurarCampo(txtCantidad);
        add(txtCantidad, "growx, wrap");

        JLabel lblPrecio = new JLabel("Precio por Unidad:");
        lblPrecio.setFont(labelFont);
        add(lblPrecio, "gapbottom 6");
        txtPrecio = new JTextField();
        txtPrecio.setFont(inputFont);
        configurarCampo(txtPrecio);
        add(txtPrecio, "growx, wrap");

        JLabel lblVencimiento = new JLabel("Fecha Vencimiento (dd/MM/aaaa):");
        lblVencimiento.setFont(labelFont);
        add(lblVencimiento, "gapbottom 6");

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            txtFechaVencimiento = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            txtFechaVencimiento = new JFormattedTextField();
        }
        txtFechaVencimiento.setFont(inputFont);
        configurarCampo(txtFechaVencimiento);
        add(txtFechaVencimiento, "growx, wrap");

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(labelFont);
        add(lblDescripcion, "gapbottom 6, top");

        txtDescripcion = new JTextArea(5, 20);
        txtDescripcion.setFont(inputFont);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBorder(BorderFactory.createEmptyBorder());
        add(scrollDescripcion, "grow, wrap, gapbottom 0");

        JPanel panelBotones = new JPanel(new MigLayout("insets 0", "[grow][]10[]", "0[]0"));
        panelBotones.setBackground(new Color(172, 212, 227));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar = new JButton("Aceptar");
        btnAceptar.putClientProperty(FlatClientProperties.STYLE, "background:#a5d6a7; foreground:#1b5e20;");
        btnAceptar.addActionListener(e -> {
            if (validarCampos()) {
                datosGuardados = true;
                dispose();
            }
        });

        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);

        add(panelBotones, "growx, south, hmin 40");
    }

    private void configurarCampo(JTextField campo) {
        campo.putClientProperty(FlatClientProperties.STYLE,
                "background:#e9f4f3; arc:10; borderWidth:0; focusWidth:1;");
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                txtCantidad.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            txtCantidad.requestFocus();
            return false;
        }

        try {
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                txtPrecio.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            txtPrecio.requestFocus();
            return false;
        }

        String fechaStr = txtFechaVencimiento.getText().trim();
        if (!fechaStr.isEmpty() && !fechaStr.contains("_")) { // si no está vacío y no tiene placeholders sin rellenar
            try {
                dateFormat.setLenient(false);
                Date fecha = dateFormat.parse(fechaStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "La fecha debe tener el formato válido dia/Mes/año", "Error", JOptionPane.ERROR_MESSAGE);
                txtFechaVencimiento.requestFocus();
                return false;
            }
        } else if (!fechaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha está incompleta o inválida", "Error", JOptionPane.ERROR_MESSAGE);
            txtFechaVencimiento.requestFocus();
            return false;
        }

        return true;
    }

    // Getters
    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public int getCantidad() {
        return Integer.parseInt(txtCantidad.getText().trim());
    }

    public double getPrecio() {
        return Double.parseDouble(txtPrecio.getText().trim());
    }

    public Date getVencimiento() {
        String fechaStr = txtFechaVencimiento.getText().trim();
        if (!fechaStr.isEmpty() && !fechaStr.contains("_")) {
            try {
                return dateFormat.parse(fechaStr);
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public boolean wasSaved() {
        return datosGuardados;
    }

    public void setDatos(int id, String nombre, int cantidad, double precio, Date vencimiento, String descripcion) {
        txtNombre.setText(nombre);
        txtCantidad.setText(String.valueOf(cantidad));
        txtPrecio.setText(String.valueOf(precio));
        if (vencimiento != null) {
            txtFechaVencimiento.setText(dateFormat.format(vencimiento));
        } else {
            txtFechaVencimiento.setValue(null);
        }
        txtDescripcion.setText(descripcion);
    }

    public static frmInventarioDialog mostrarDialogoAgregar(Window parent) {
        frmInventarioDialog dialog = new frmInventarioDialog(parent, "Agregar Producto al Inventario");
        dialog.setVisible(true);
        return dialog;
    }

    public static frmInventarioDialog mostrarDialogoEditar(
            Window parent, int id, String nombre, int cantidad, double precio, String vencimiento, String descripcion) {
        frmInventarioDialog dialog = new frmInventarioDialog(parent, "Editar Producto del Inventario");
        try {
            Date fecha = dialog.dateFormat.parse(vencimiento);
            dialog.setDatos(id, nombre, cantidad, precio, fecha, descripcion);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(parent, "Error al convertir la fecha: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        dialog.setVisible(true);
        return dialog;
    }

}
