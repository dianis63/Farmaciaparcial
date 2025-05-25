package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class frmLaboratorioDialog extends JDialog {

    private JTextField txtIdLaboratorio;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public frmLaboratorioDialog(Window parent, String titulo) {
        super(parent, titulo, ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setSize(450, 250);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        // Layout optimizado
        setLayout(new MigLayout("fill, insets 15", "[grow]", "[][][][][][grow][]"));
        getContentPane().setBackground(new Color(172, 212, 227));

        // 1. Campo ID Laboratorio
        add(new JLabel("ID Laboratorio:"), "gapbottom 3");
        txtIdLaboratorio = new JTextField();
        configurarCampo(txtIdLaboratorio);
        txtIdLaboratorio.setEditable(false); // Generalmente el ID no es editable
        add(txtIdLaboratorio, "growx, wrap");

        // 2. Campo Nombre
        add(new JLabel("Nombre:"), "gapbottom 3");
        txtNombre = new JTextField();
        configurarCampo(txtNombre);
        add(txtNombre, "growx, wrap");

        // 3. Campo Dirección
        add(new JLabel("Dirección:"), "gapbottom 3");
        txtDireccion = new JTextField();
        configurarCampo(txtDireccion);
        add(txtDireccion, "growx, wrap");

        // 4. Campo Teléfono
        add(new JLabel("Teléfono:"), "gapbottom 3");
        txtTelefono = new JTextField();
        configurarCampo(txtTelefono);
        add(txtTelefono, "growx, wrap");

        // 5. Campo Email
        add(new JLabel("Email:"), "gapbottom 3");
        txtEmail = new JTextField();
        configurarCampo(txtEmail);
        add(txtEmail, "growx, wrap");

        // Panel de botones - Versión perfectamente alineada
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
        panelBotones.add(btnAceptar, "gapleft 5");

        add(panelBotones, "growx");
    }

    private void configurarCampo(JTextField campo) {
        campo.putClientProperty(FlatClientProperties.STYLE,
                "background:#e9f4f3; arc:10; borderWidth:0; focusWidth:1;");
    }

    private boolean validarCampos() {
        // Validación básica de campos obligatorios
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Puedes agregar más validaciones según sea necesario
        return true;
    }

    // Métodos para obtener los valores del formulario
    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getDireccion() {
        return txtDireccion.getText().trim();
    }

    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }

    public void setDatos(int id, String nombre, String direccion, String telefono, String email) {
        txtIdLaboratorio.setText(String.valueOf(id));
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
        txtTelefono.setText(telefono);
        txtEmail.setText(email);
    }

    public static frmLaboratorioDialog mostrarDialogoAgregar(Window parent) {
        frmLaboratorioDialog dialog = new frmLaboratorioDialog(parent, "Agregar Laboratorio");
        dialog.setVisible(true);
        return dialog;
    }

    public static frmLaboratorioDialog mostrarDialogoEditar(Window parent, int id, String nombre,
                                                             String direccion, String telefono, String email) {
        frmLaboratorioDialog dialog = new frmLaboratorioDialog(parent, "Editar Laboratorio");
        dialog.setDatos(id, nombre, direccion, telefono, email);
        dialog.setVisible(true);
        return dialog;
    }
}
