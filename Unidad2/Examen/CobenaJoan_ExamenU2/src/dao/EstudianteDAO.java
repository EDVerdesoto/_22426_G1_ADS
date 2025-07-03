package dao;

import model.Estudiante;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para gestionar estudiantes en memoria.
 * Implementa todas las operaciones CRUD.
 */
public class EstudianteDAO {
    private final List<Estudiante> estudiantes;
    private int ultimoId;

    /**
     * Constructor del DAO que inicializa la lista de datos
     */
    public EstudianteDAO() {
        this.estudiantes = new ArrayList<>();
        this.ultimoId = 0;
    }

    /**
     * Agrega un nuevo estudiante a la lista
     * @param estudiante Estudiante a agregar
     */
    public void agregar(Estudiante estudiante) {
        if (estudiante.getId() == 0) {
            estudiante.setId(++ultimoId);
        }
        estudiantes.add(estudiante);
    }

    /**
     * Lista todos los estudiantes
     * @return Lista de estudiantes
     */
    public List<Estudiante> listar() {
        return new ArrayList<>(estudiantes);
    }

    /**
     * Busca un estudiante por su ID
     * @param id ID del estudiante
     * @return Estudiante encontrado o null
     */
    public Estudiante buscarPorId(int id) {
        return estudiantes.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Actualiza los datos de un estudiante
     * @param estudiante Estudiante con datos actualizados
     * @return true si se actualizó, false si no se encontró
     */
    public boolean actualizar(Estudiante estudiante) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId() == estudiante.getId()) {
                estudiantes.set(i, estudiante);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un estudiante por su ID
     * @param id ID del estudiante a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminar(int id) {
        return estudiantes.removeIf(e -> e.getId() == id);
    }
}