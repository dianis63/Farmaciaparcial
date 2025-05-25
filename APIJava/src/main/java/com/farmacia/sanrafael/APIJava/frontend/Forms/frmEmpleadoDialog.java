package com.farmacia.sanrafael.APIJava.frontend.Forms;


import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class frmEmpleadoDialog extends JDialog {

    private JTextField txtIdEmpleado;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JComboBox<String> cmbCargo;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JButton btnAceptar;
    private JButton btnCancelar;

    // Opciones para el combobox de cargos
    private final String[] CARGOS = {"Administrador", "Cajero", "Vendedor", "Almacenista", "Gerente"};

    public frmEmpleadoDialog(Window parent, String titulo) {
        super(parent, titulo, ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setSize(450, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        // Layout optimizado
        setLayout(new MigLayout("fill, insets 15", "[grow]", "[][][][][][][]"));
        getContentPane().setBackground(new Color(172, 212, 227));

        // 1. Campo ID Empleado
        add(new JLabel("ID Empleado:"), "gapbottom 3");
        txtIdEmpleado = new JTextField();
        configurarCampo(txtIdEmpleado);
        txtIdEmpleado.setEditable(false); // El ID generalmente no es editable
        add(txtIdEmpleado, "growx, wrap");

        // 2. Campo Nombre
        add(new JLabel("Nombre:"), "gapbottom 3");
        txtNombre = new JTextField();
        configurarCampo(txtNombre);
        add(txtNombre, "growx, wrap");

        // 3. Campo Apellido
        add(new JLabel("Apellido:"), "gapbottom 3");
        txtApellido = new JTextField();
        configurarCampo(txtApellido);
        add(txtApellido, "growx, wrap");

        // 4. Campo Cargo (Combobox)
        add(new JLabel("Cargo:"), "gapbottom 3");
        cmbCargo = new JComboBox<>(CARGOS);
        cmbCargo.putClientProperty(FlatClientProperties.STYLE,
                "background:#e9f4f3; arc:10; borderWidth:0; focusWidth:1;");
        add(cmbCargo, "growx, wrap");

        // 5. Campo Email
        add(new JLabel("Email:"), "gapbottom 3");
        txtEmail = new JTextField();
        configurarCampo(txtEmail);
        add(txtEmail, "growx, wrap");

        // 6. Campo Teléfono
        add(new JLabel("Teléfono:"), "gapbottom 3");
        txtTelefono = new JTextField();
        configurarCampo(txtTelefono);
        add(txtTelefono, "growx, wrap");

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
        // Validación básica de campos obligatorios
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

        // Validación básica de email
        String email = txtEmail.getText().trim();
        if (!email.isEmpty() && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "El email no tiene un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        return true;
    }

    // Métodos para obtener los valores del formulario
    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getApellido() {
        return txtApellido.getText().trim();
    }

    public String getCargo() {
        return cmbCargo.getSelectedItem().toString();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }

    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    public void setDatos(int id, String nombre, String apellido, String cargo, String email, String telefono) {
        txtIdEmpleado.setText(String.valueOf(id));
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        cmbCargo.setSelectedItem(cargo);
        txtEmail.setText(email);
        txtTelefono.setText(telefono);
    }

    public static frmEmpleadoDialog mostrarDialogoAgregar(Window parent) {
        frmEmpleadoDialog dialog = new frmEmpleadoDialog(parent, "Agregar Empleado");
        dialog.setVisible(true);
        return dialog;
    }

    public static frmEmpleadoDialog mostrarDialogoEditar(Window parent, int id, String nombre,
                                                         String apellido, String cargo, String email,
                                                         String telefono) {
        frmEmpleadoDialog dialog = new frmEmpleadoDialog(parent, "Editar Empleado");
        dialog.setDatos(id, nombre, apellido, cargo, email, telefono);
        dialog.setVisible(true);
        return dialog;
    }
}
