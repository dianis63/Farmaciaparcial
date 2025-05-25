package com.farmacia.sanrafael.APIJava.frontend.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class frmClienteDialog extends JDialog {

    private JTextField txtIdCliente;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextArea txtDireccion;
    private JScrollPane scrollDireccion;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public frmClienteDialog(Window parent, String titulo) {
        super(parent, titulo, ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setSize(500, 450);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new MigLayout("fill, insets 15", "[grow]", "[][][][][][grow][]"));
        getContentPane().setBackground(new Color(172, 212, 227));

        // ID Cliente (no editable)
        add(new JLabel("ID Cliente:"), "gapbottom 3");
        txtIdCliente = new JTextField();
        configurarCampo(txtIdCliente);
        txtIdCliente.setEditable(false);
        add(txtIdCliente, "growx, wrap");

        // Nombre (obligatorio)
        add(new JLabel("Nombre:"), "gapbottom 3");
        txtNombre = new JTextField();
        configurarCampo(txtNombre);
        add(txtNombre, "growx, wrap");

        // Apellido (obligatorio)
        add(new JLabel("Apellido:"), "gapbottom 3");
        txtApellido = new JTextField();
        configurarCampo(txtApellido);
        add(txtApellido, "growx, wrap");

        // Teléfono (opcional)
        add(new JLabel("Teléfono:"), "gapbottom 3");
        txtTelefono = new JTextField();
        configurarCampo(txtTelefono);
        add(txtTelefono, "growx, wrap");

        // Correo (opcional) - cambia de Email a Correo
        add(new JLabel("Correo:"), "gapbottom 3");
        txtCorreo = new JTextField();
        configurarCampo(txtCorreo);
        add(txtCorreo, "growx, wrap");

        // Dirección (opcional)
        add(new JLabel("Dirección:"), "gapbottom 3, top");
        txtDireccion = new JTextArea(4, 20);
        txtDireccion.setLineWrap(true);
        txtDireccion.setWrapStyleWord(true);
        scrollDireccion = new JScrollPane(txtDireccion);
        scrollDireccion.setBorder(BorderFactory.createEmptyBorder());
        txtDireccion.putClientProperty(FlatClientProperties.STYLE,
                "background:#e9f4f3; arc:10; borderWidth:0; focusWidth:1;");
        add(scrollDireccion, "grow, wrap");

        // Panel de botones
        JPanel panelBotones = new JPanel(new MigLayout("insets 0", "[grow]", "[]"));
        panelBotones.setBackground(new Color(172, 212, 227));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.putClientProperty(FlatClientProperties.STYLE, "background:#e9f4f3;");
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar = new JButton("Aceptar");
        btnAceptar.putClientProperty(FlatClientProperties.STYLE, "background:#a5d6a7; foreground:#1b5e20;");
        btnAceptar.addActionListener(e -> {
            if (validarCampos()) {
                dispose();
            }
        });

        panelBotones.add(new JLabel(), "growx, push");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar, "gapleft 10");

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

        if (txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El apellido es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            txtApellido.requestFocus();
            return false;
        }

        String correo = txtCorreo.getText().trim();
        if (!correo.isEmpty() && !correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "El correo no tiene un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
            txtCorreo.requestFocus();
            return false;
        }

        return true;
    }

    // Métodos para obtener los valores
    public String getNombre() { return txtNombre.getText().trim(); }
    public String getApellido() { return txtApellido.getText().trim(); }
    public String getTelefono() { return txtTelefono.getText().trim(); }
    public String getCorreo() { return txtCorreo.getText().trim(); }
    public String getDireccion() { return txtDireccion.getText().trim(); }

    public void setDatos(int id, String nombre, String apellido, String correo, String telefono, String direccion) {
        txtIdCliente.setText(String.valueOf(id));
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtCorreo.setText(correo);
        txtTelefono.setText(telefono);
        txtDireccion.setText(direccion);
    }

    public static frmClienteDialog mostrarDialogoAgregar(Window parent) {
        frmClienteDialog dialog = new frmClienteDialog(parent, "Agregar Cliente");
        dialog.setDatos(0, "", "", "", "", "");
        dialog.txtIdCliente.setText("Auto generado");
        return dialog;
    }

    public static frmClienteDialog mostrarDialogoEditar(Window parent, int id, String nombre,
                                                        String apellido, String correo, String telefono,
                                                        String direccion) {
        frmClienteDialog dialog = new frmClienteDialog(parent, "Editar Cliente");
        dialog.setDatos(id, nombre, apellido, correo, telefono, direccion);
        return dialog;
    }
}
