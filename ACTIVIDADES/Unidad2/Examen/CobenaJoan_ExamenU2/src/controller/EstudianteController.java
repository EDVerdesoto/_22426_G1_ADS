package controller;

import dao.EstudianteDAO;
import model.Estudiante;
import java.util.List;

/**
 * Controlador que gestiona la lógica de negocio para estudiantes.
 * Actúa como intermediario entre la vista y el modelo utilizando el DAO.
 */
public class EstudianteController {
    private final EstudianteDAO dao;

    public EstudianteController() {
        this.dao = new EstudianteDAO();
    }

    /**
     * Crea un nuevo estudiante
     * @return true si se creó exitosamente, false si hubo un error
     */
    public boolean crearEstudiante(String apellidos, String nombres, int edad) {
        try {
            if (apellidos == null || apellidos.trim().isEmpty() ||
                    nombres == null || nombres.trim().isEmpty() ||
                    edad <= 0 || edad > 100) {
                return false;
            }

            Estudiante estudiante = new Estudiante(0, apellidos, nombres, edad);
            dao.agregar(estudiante);
            return true;
        } catch (Exception e) {
            System.err.println("Error al crear estudiante: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los estudiantes
     * @return Lista de estudiantes
     */
    public List<Estudiante> obtenerTodos() {
        return dao.listar();
    }

    /**
     * Busca un estudiante por ID
     * @param id ID del estudiante
     * @return Estudiante encontrado o null
     */
    public Estudiante buscarEstudiante(int id) {
        return dao.buscarPorId(id);
    }

    /**
     * Actualiza los datos de un estudiante
     * @return true si se actualizó exitosamente
     */
    public boolean actualizarEstudiante(int id, String apellidos, String nombres, int edad) {
        try {
            Estudiante estudiante = new Estudiante(id, apellidos, nombres, edad);
            return dao.actualizar(estudiante);
        } catch (Exception e) {
            System.err.println("Error al actualizar estudiante: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un estudiante
     * @param id ID del estudiante a eliminar
     * @return true si se eliminó exitosamente
     */
    public boolean eliminarEstudiante(int id) {
        return dao.eliminar(id);
    }
}


