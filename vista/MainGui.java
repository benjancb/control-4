package vista;

import controlador.ProyectoControlador;
import excepciones.ProyectoException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGui extends JFrame {

    private JPanel panel;

    public MainGui() {

        ProyectoControlador.getInstance().createDatosParaPrueba();

        setTitle("Sistema de gestión de proyectos y tareas");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        add(panel);

        mostrarMenu();

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(MainGui::new);

    }

    private void limpiarVentana() {

        panel.removeAll();
        panel.repaint();
        panel.revalidate();

    }

    private ImageIcon icono(String nombre) {

        java.net.URL url = getClass().getResource("/vista/iconos/32x32/" + nombre);

        if (url == null) {
            System.out.println("No se encontró: " + nombre);
            return null;
        }

        return new ImageIcon(url);
    }

    private void mostrarMenu(){

        limpiarVentana();

        setSize(650,450);
        setLocationRelativeTo(null);
        JLabel titulo = new JLabel("Sistema de gestión de proyectos y tareas");

        titulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        titulo.setBounds(70, 35, 520, 30);

        panel.add(titulo);

        JButton btnProyecto = new JButton("Crear proyecto", icono("creaProyecto.png"));
        JButton btnPersona = new JButton("Crear persona", icono("creaPersona.png"));
        JButton btnTarea = new JButton("Crear tarea", icono("creaTarea.png"));
        JButton btnListado = new JButton("Personas y tareas de un proyecto", icono("listado.png"));
        JButton btnSalir = new JButton("Salir", icono("salir.png"));

        btnProyecto.setBounds(220, 90, 180, 40);
        btnPersona.setBounds(220, 140, 180, 40);
        btnTarea.setBounds(220, 190, 180, 40);
        btnListado.setBounds(150, 240, 320, 40);
        btnSalir.setBounds(250, 300, 120, 40);

        panel.add(btnProyecto);
        panel.add(btnPersona);
        panel.add(btnTarea);
        panel.add(btnListado);
        panel.add(btnSalir);

        btnProyecto.addActionListener(e -> mostrarCrearProyecto());

        btnPersona.addActionListener(e -> mostrarCrearPersona());

        btnTarea.addActionListener(e -> mostrarCrearTarea());

        btnListado.addActionListener(e -> mostrarListado());

        btnSalir.addActionListener(e -> System.exit(0));

    }

    private void mostrarCrearProyecto() {

        limpiarVentana();

        JLabel imagen = new JLabel(new ImageIcon(
                getClass().getResource("/vista/iconos/128x128/creaProyecto.png")));
        imagen.setBounds(20, 20, 128, 128);
        panel.add(imagen);

        JLabel titulo = new JLabel("Nuevo Proyecto");
        titulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        titulo.setBounds(170, 60, 250, 35);
        panel.add(titulo);

        JLabel lblId = new JLabel("Id");
        lblId.setBounds(25, 170, 80, 25);
        panel.add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(120, 170, 220, 25);
        panel.add(txtId);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(25, 210, 80, 25);
        panel.add(lblDescripcion);

        JTextField txtDescripcion = new JTextField();
        txtDescripcion.setBounds(120, 210, 220, 25);
        panel.add(txtDescripcion);

        JLabel lblMonto = new JLabel("Monto");
        lblMonto.setBounds(25, 250, 80, 25);
        panel.add(lblMonto);

        JTextField txtMonto = new JTextField();
        txtMonto.setBounds(120, 250, 220, 25);
        panel.add(txtMonto);

        JButton btnCrear = new JButton("Crear", icono("ok.png"));
        btnCrear.setBounds(85, 305, 120, 35);
        btnCrear.setHorizontalAlignment(SwingConstants.LEFT);
        btnCrear.setIconTextGap(8);
        panel.add(btnCrear);

        JButton btnVolver = new JButton("Volver", icono("volver.png"));
        btnVolver.setBounds(215, 305, 130, 35);
        btnVolver.setHorizontalAlignment(SwingConstants.LEFT);
        btnVolver.setIconTextGap(8);
        panel.add(btnVolver);

        btnCrear.addActionListener(e -> {

            try {

                if (txtId.getText().trim().isEmpty()
                        || txtDescripcion.getText().trim().isEmpty()
                        || txtMonto.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
                    return;
                }

                ProyectoControlador.getInstance().createProyecto(
                        Integer.parseInt(txtId.getText()),
                        txtDescripcion.getText(),
                        Long.parseLong(txtMonto.getText()));

                JOptionPane.showMessageDialog(this, "Proyecto creado correctamente.");

                mostrarMenu();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this, "Id y monto inválidos.");

            } catch (ProyectoException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        });

        btnVolver.addActionListener(e -> mostrarMenu());

        panel.repaint();
        panel.revalidate();

    }

    private void mostrarCrearPersona() {

        limpiarVentana();

        JLabel imagen = new JLabel(new ImageIcon(
                getClass().getResource("/vista/iconos/128x128/creaPersona.png")));
        imagen.setBounds(20, 20, 128, 128);
        panel.add(imagen);

        JLabel titulo = new JLabel("Nueva Persona");
        titulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        titulo.setBounds(170, 60, 250, 35);
        panel.add(titulo);

        JLabel lblRut = new JLabel("Rut");
        lblRut.setBounds(25, 180, 80, 25);
        panel.add(lblRut);

        JTextField txtRut = new JTextField();
        txtRut.setBounds(120, 180, 220, 25);
        panel.add(txtRut);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(25, 220, 80, 25);
        panel.add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(120, 220, 220, 25);
        panel.add(txtNombre);

        JButton btnCrear = new JButton("Crear", icono("ok.png"));
        btnCrear.setBounds(85, 300, 120, 35);
        btnCrear.setHorizontalAlignment(SwingConstants.LEFT);
        btnCrear.setIconTextGap(8);
        panel.add(btnCrear);

        JButton btnVolver = new JButton("Volver", icono("volver.png"));
        btnVolver.setBounds(215, 300, 130, 35);
        btnVolver.setHorizontalAlignment(SwingConstants.LEFT);
        btnVolver.setIconTextGap(8);
        panel.add(btnVolver);

        btnCrear.addActionListener(e -> {

            try {

                if (txtRut.getText().trim().isEmpty()
                        || txtNombre.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(this,
                            "Debe completar todos los campos.");
                    return;
                }

                ProyectoControlador.getInstance().createPersona(
                        txtRut.getText().trim(),
                        txtNombre.getText().trim());

                JOptionPane.showMessageDialog(this,
                        "Persona creada correctamente.");

                mostrarMenu();

            } catch (ProyectoException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

        });

        btnVolver.addActionListener(e -> mostrarMenu());

        panel.repaint();
        panel.revalidate();

    }

    private void mostrarCrearTarea() {

        limpiarVentana();

        JLabel imagen = new JLabel(new ImageIcon(
                getClass().getResource("/vista/iconos/128x128/creaTarea.png")));
        imagen.setBounds(20, 20, 128, 128);
        panel.add(imagen);

        JLabel titulo = new JLabel("Nueva Tarea");
        titulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        titulo.setBounds(170, 60, 250, 35);
        panel.add(titulo);

        JLabel lblId = new JLabel("Id");
        lblId.setBounds(25, 170, 90, 25);
        panel.add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(120, 170, 220, 25);
        panel.add(txtId);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(25, 205, 90, 25);
        panel.add(lblDescripcion);

        JTextField txtDescripcion = new JTextField();
        txtDescripcion.setBounds(120, 205, 220, 25);
        panel.add(txtDescripcion);

        JLabel lblProyecto = new JLabel("Id Proyecto");
        lblProyecto.setBounds(25, 240, 90, 25);
        panel.add(lblProyecto);

        JTextField txtProyecto = new JTextField();
        txtProyecto.setBounds(120, 240, 220, 25);
        panel.add(txtProyecto);

        JLabel lblRut = new JLabel("Rut");
        lblRut.setBounds(25, 275, 90, 25);
        panel.add(lblRut);

        JTextField txtRut = new JTextField();
        txtRut.setBounds(120, 275, 220, 25);
        panel.add(txtRut);

        JLabel lblDuracion = new JLabel("Duración");
        lblDuracion.setBounds(25, 310, 90, 25);
        panel.add(lblDuracion);

        JRadioButton rb15 = new JRadioButton("15");
        JRadioButton rb30 = new JRadioButton("30");
        JRadioButton rb45 = new JRadioButton("45");
        JRadioButton rb60 = new JRadioButton("60");
        JRadioButton rbOtro = new JRadioButton("Otro");

        ButtonGroup grupo = new ButtonGroup();

        grupo.add(rb15);
        grupo.add(rb30);
        grupo.add(rb45);
        grupo.add(rb60);
        grupo.add(rbOtro);

        rb15.setBounds(120, 310, 50, 25);
        rb30.setBounds(170, 310, 50, 25);
        rb45.setBounds(220, 310, 50, 25);
        rb60.setBounds(270, 310, 50, 25);
        rbOtro.setBounds(320, 310, 60, 25);

        panel.add(rb15);
        panel.add(rb30);
        panel.add(rb45);
        panel.add(rb60);
        panel.add(rbOtro);

        JTextField txtOtro = new JTextField();
        txtOtro.setBounds(120, 345, 80, 25);
        txtOtro.setEnabled(false);
        panel.add(txtOtro);

        rbOtro.addActionListener(e -> txtOtro.setEnabled(true));
        rb15.addActionListener(e -> txtOtro.setEnabled(false));
        rb30.addActionListener(e -> txtOtro.setEnabled(false));
        rb45.addActionListener(e -> txtOtro.setEnabled(false));
        rb60.addActionListener(e -> txtOtro.setEnabled(false));

        JButton btnCrear = new JButton("Crear", icono("ok.png"));
        btnCrear.setBounds(85, 390, 120, 35);
        btnCrear.setHorizontalAlignment(SwingConstants.LEFT);
        btnCrear.setIconTextGap(8);
        panel.add(btnCrear);

        JButton btnVolver = new JButton("Volver", icono("volver.png"));
        btnVolver.setBounds(215, 390, 130, 35);
        btnVolver.setHorizontalAlignment(SwingConstants.LEFT);
        btnVolver.setIconTextGap(8);
        panel.add(btnVolver);

        btnCrear.addActionListener(e -> {

            try {

                if (txtId.getText().trim().isEmpty()
                        || txtDescripcion.getText().trim().isEmpty()
                        || txtProyecto.getText().trim().isEmpty()
                        || txtRut.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
                    return;
                }

                int duracion;

                if (rb15.isSelected())
                    duracion = 15;
                else if (rb30.isSelected())
                    duracion = 30;
                else if (rb45.isSelected())
                    duracion = 45;
                else if (rb60.isSelected())
                    duracion = 60;
                else if (rbOtro.isSelected()) {

                    if (txtOtro.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Ingrese la duración.");
                        return;
                    }

                    duracion = Integer.parseInt(txtOtro.getText());

                } else {

                    JOptionPane.showMessageDialog(this, "Seleccione una duración.");
                    return;
                }

                ProyectoControlador.getInstance().createTarea(
                        Integer.parseInt(txtId.getText()),
                        txtDescripcion.getText(),
                        duracion,
                        Integer.parseInt(txtProyecto.getText()),
                        txtRut.getText());

                JOptionPane.showMessageDialog(this, "Tarea creada correctamente.");

                mostrarMenu();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this, "Datos numéricos inválidos.");

            } catch (ProyectoException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        });

        btnVolver.addActionListener(e -> mostrarMenu());

        panel.repaint();
        panel.revalidate();

    }

    private void mostrarListado() {

            limpiarVentana();

            setSize(620, 430);
            setLocationRelativeTo(null);
        JLabel imagen = new JLabel(new ImageIcon(
                getClass().getResource("/vista/iconos/128x128/listado.png")));
        imagen.setBounds(20, 35, 128, 128);
        panel.add(imagen);

        JLabel titulo = new JLabel("Listado de tareas y personas de un proyecto");
        titulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
        titulo.setBounds(150, 80, 450, 35);
        panel.add(titulo);

        JLabel lblProyecto = new JLabel("Id Proyecto");
        lblProyecto.setBounds(25, 175, 80, 25);
        panel.add(lblProyecto);

        JTextField txtProyecto = new JTextField();
        txtProyecto.setBounds(95, 175, 250, 25);
        panel.add(txtProyecto);

        String[] columnas = {
                "Rut",
                "Nombre",
                "Tiempo",
                "% Part.",
                "Monto"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(25, 210, 560, 80);
        panel.add(scroll);

        JButton btnGenerar = new JButton("Generar Listado", icono("ok.png"));
        btnGenerar.setBounds(220, 315, 150, 35);
        btnGenerar.setHorizontalAlignment(SwingConstants.LEFT);
        btnGenerar.setIconTextGap(8);
        panel.add(btnGenerar);

        JButton btnVolver = new JButton("Volver", icono("volver.png"));
        btnVolver.setBounds(380, 315, 130, 35);
        btnVolver.setHorizontalAlignment(SwingConstants.LEFT);
        btnVolver.setIconTextGap(8);
        panel.add(btnVolver);

        btnGenerar.addActionListener(e -> {

            try {

                if (txtProyecto.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(this,
                            "Ingrese el Id del proyecto.");
                    return;

                }

                modelo.setRowCount(0);

                String[][] datos =
                        ProyectoControlador.getInstance()
                                .listPersonasYSusTareasDeProyecto(
                                        Integer.parseInt(txtProyecto.getText()));

                for (String[] fila : datos) {
                    modelo.addRow(fila);
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Id inválido.");

            } catch (ProyectoException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

        });

        btnVolver.addActionListener(e -> mostrarMenu());

        panel.repaint();
        panel.revalidate();

    }
}