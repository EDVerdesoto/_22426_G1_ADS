package ui;

import controller.EstudianteController;
import ui.EstudianteView;
import model.Estudiante;

/**
 * Clase principal que coordina la interacción entre el usuario,
 * el controlador y las vistas siguiendo el patrón MVC.
 */
public class Main {
    private final EstudianteController controller;
    private final EstudianteView view;

    public Main() {
        this.controller = new EstudianteController();
        this.view = new EstudianteView();
    }

    /**
     * Método principal que inicia la aplicación
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.iniciar();
    }

    /**
     * Inicia la aplicación y maneja el flujo principal
     */
    public void iniciar() {
        // Cargar datos de ejemplo
        cargarDatosEjemplo();

        boolean continuar = true;

        while (continuar) {
            int opcion = view.mostrarMenu();

            switch (opcion) {
                case 1:
                    registrarEstudiante();
                    break;
                case 2:
                    listarEstudiantes();
                    break;
                case 3:
                    buscarEstudiante();
                    break;
                case 4:
                    actualizarEstudiante();
                    break;
                case 5:
                    eliminarEstudiante();
                    break;
                case 6:
                    continuar = false;
                    view.mostrarMensaje("¡Gracias por usar el sistema!");
                    break;
                default:
                    view.mostrarMensaje("❌ Opción inválida");
            }

            if (continuar && opcion >= 1 && opcion <= 5) {
                view.pausar();
            }
        }
    }

    /**
     * Registra un nuevo estudiante
     */
    private void registrarEstudiante() {
        String[] datos = view.capturarDatosEstudiante(false);

        try {
            int edad = Integer.parseInt(datos[2]);

            if (controller.crearEstudiante(datos[0], datos[1], edad)) {
                view.mostrarMensaje("✅ Estudiante registrado exitosamente");
            } else {
                view.mostrarMensaje("❌ Error: Verifique los datos ingresados");
            }
        } catch (NumberFormatException e) {
            view.mostrarMensaje("❌ Error: La edad debe ser un número");
        }
    }

    /**
     * Lista todos los estudiantes registrados
     */
    private void listarEstudiantes() {
        view.mostrarEstudiantes(controller.obtenerTodos());
    }

    /**
     * Busca un estudiante por su ID
     */
    private void buscarEstudiante() {
        int id = view.solicitarId();

        if (id > 0) {
            Estudiante estudiante = controller.buscarEstudiante(id);
            view.mostrarEstudiante(estudiante);
        } else {
            view.mostrarMensaje("❌ ID inválido");
        }
    }

    /**
     * Actualiza los datos de un estudiante existente
     */
    private void actualizarEstudiante() {
        int id = view.solicitarId();

        if (id > 0) {
            Estudiante estudianteActual = controller.buscarEstudiante(id);

            if (estudianteActual != null) {
                view.mostrarEstudiante(estudianteActual);

                if (view.confirmar("¿Desea actualizar este estudiante?")) {
                    String[] datos = view.capturarDatosEstudiante(true);

                    try {
                        int edad = Integer.parseInt(datos[2]);

                        if (controller.actualizarEstudiante(id, datos[0], datos[1], edad)) {
                            view.mostrarMensaje("✅ Estudiante actualizado exitosamente");
                        } else {
                            view.mostrarMensaje("❌ Error al actualizar el estudiante");
                        }
                    } catch (NumberFormatException e) {
                        view.mostrarMensaje("❌ Error: La edad debe ser un número");
                    }
                }
            } else {
                view.mostrarMensaje("❌ Estudiante no encontrado");
            }
        } else {
            view.mostrarMensaje("❌ ID inválido");
        }
    }

    /**
     * Elimina un estudiante del sistema
     */
    private void eliminarEstudiante() {
        int id = view.solicitarId();

        if (id > 0) {
            Estudiante estudiante = controller.buscarEstudiante(id);

            if (estudiante != null) {
                view.mostrarEstudiante(estudiante);

                if (view.confirmar("¿Está seguro de eliminar este estudiante?")) {
                    if (controller.eliminarEstudiante(id)) {
                        view.mostrarMensaje("✅ Estudiante eliminado exitosamente");
                    } else {
                        view.mostrarMensaje("❌ Error al eliminar el estudiante");
                    }
                }
            } else {
                view.mostrarMensaje("❌ Estudiante no encontrado");
            }
        } else {
            view.mostrarMensaje("❌ ID inválido");
        }
    }

    /**
     * Carga datos de ejemplo al iniciar
     */
    private void cargarDatosEjemplo() {
        controller.crearEstudiante("García", "Ana María", 20);
        controller.crearEstudiante("Rodríguez", "Carlos Alberto", 22);
        controller.crearEstudiante("Martínez", "Laura Sofia", 19);
        controller.crearEstudiante("López", "Juan Pablo", 21);
        controller.crearEstudiante("Hernández", "María Fernanda", 23);
    }
}