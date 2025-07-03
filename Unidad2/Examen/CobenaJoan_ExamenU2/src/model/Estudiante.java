package model;

/**
 * Clase que representa a un estudiante en el sistema.
 * Contiene los atributos básicos y métodos de acceso.
 */
public class Estudiante {
    private int id;
    private String apellidos;
    private String nombres;
    private int edad;

    /**
     * Constructor de la clase Estudiante
     * @param id Identificador único del estudiante
     * @param apellidos Apellidos del estudiante
     * @param nombres Nombres del estudiante
     * @param edad Edad del estudiante
     */
    public Estudiante(int id, String apellidos, String nombres, int edad) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.edad = edad;
    }

    /**
     * @return id del Estudiante
     */
    public int getId() {
        return id;
    }

    /**
     * Cambia el valor del id del Estudiante
     * @param id nuevo del Estudiante
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return apellidos del Estudiante
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Cambia el valor de los apellidos del Estudiante
     * @param apellidos nuevos del Estudiante
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return nombres del Estudiante
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Cambia el valor de los nombres del Estudiante
     * @param nombres nuevos del Estudiante
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return edad del Estudiante
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Cambia el valor de la edad del Estudiante
     * @param edad nueva del Estudiante
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
}