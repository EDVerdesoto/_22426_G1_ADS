package ui;

import model.Estudiante;
import java.util.List;
import java.util.Scanner;

/**
 * Vista responsable de mostrar los datos de estudiantes
 * de forma clara.
 */
public class EstudianteView {

    private Scanner scanner;

    /**
     * Constructor de Estudiante que inicializa el Scanner para las entradas del usuario
     */
    public EstudianteView(){
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra la lista de estudiantes en formato tabla
     * @param estudiantes Lista de estudiantes a mostrar
     */
    public void mostrarEstudiantes(List<Estudiante> estudiantes) {
        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    LISTA DE ESTUDIANTES                       ║");
        System.out.println("╠════╦═══════════════════════╦═══════════════════════╦══════════╣");
        System.out.println("║ ID ║      APELLIDOS        ║       NOMBRES         ║   EDAD   ║");
        System.out.println("╠════╬═══════════════════════╬═══════════════════════╬══════════╣");

        if (estudiantes.isEmpty()) {
            System.out.println("║              No hay estudiantes registrados                    ║");
        } else {
            for (Estudiante e : estudiantes) {
                System.out.printf("║ %-2d ║ %-21s ║ %-21s ║   %-6d ║%n",
                        e.getId(),
                        truncar(e.getApellidos(), 21),
                        truncar(e.getNombres(), 21),
                        e.getEdad());
            }
        }

        System.out.println("╚════╩═══════════════════════╩═══════════════════════╩══════════╝");
        System.out.println("Total de estudiantes: " + estudiantes.size());
    }

    /**
     * Muestra los detalles de un estudiante específico
     * @param estudiante Estudiante a mostrar
     */
    public void mostrarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            System.out.println("\n❌ Estudiante no encontrado");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         DETALLE DEL ESTUDIANTE         ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ ID:        %-27d ║%n", estudiante.getId());
        System.out.printf("║ Apellidos: %-27s ║%n", estudiante.getApellidos());
        System.out.printf("║ Nombres:   %-27s ║%n", estudiante.getNombres());
        System.out.printf("║ Edad:      %-27d ║%n", estudiante.getEdad());
        System.out.println("╚════════════════════════════════════════╝");
    }

    /**
     * Muestra el menú principal y captura la opción seleccionada
     * @return Opción seleccionada
     */
    public int mostrarMenu() {

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE GESTIÓN ESTUDIANTIL    ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. Registrar nuevo estudiante          ║");
        System.out.println("║ 2. Listar todos los estudiantes        ║");
        System.out.println("║ 3. Buscar estudiante por ID            ║");
        System.out.println("║ 4. Actualizar estudiante               ║");
        System.out.println("║ 5. Eliminar estudiante                 ║");
        System.out.println("║ 6. Salir                               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Captura los datos de un nuevo estudiante
     * @return Array con los datos [apellidos, nombres, edad]
     */
    public String[] capturarDatosEstudiante(boolean esActualizacion) {

        String[] datos = new String[3];

        if(!esActualizacion)
            System.out.println("\n--- REGISTRO DE NUEVO ESTUDIANTE ---");
        else
            System.out.println("\n--- ACTUALIZACIÓN DEL ESTUDIANTE ---");
        System.out.print("Ingrese apellidos: ");
        datos[0] = scanner.nextLine().trim();

        System.out.print("Ingrese nombres: ");
        datos[1] = scanner.nextLine().trim();

        System.out.print("Ingrese edad: ");
        datos[2] = scanner.nextLine().trim();

        return datos;
    }

    /**
     * Solicita el ID de un estudiante
     * @return ID ingresado
     */
    public int solicitarId() {
        System.out.print("Ingrese el ID del estudiante: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }

    }

    /**
     * Muestra un mensaje de confirmación
     * @param mensaje Mensaje a mostrar
     * @return true si confirma, false si cancela
     */
    public boolean confirmar(String mensaje) {
        System.out.print(mensaje + " (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        return respuesta.equals("S") || respuesta.equals("SI");
    }

    /**
     * Pausa la ejecución hasta que el usuario presione Enter
     */
    public void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Muestra un mensaje al usuario
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println("\n➤ " + mensaje);
    }

    /**
     * Trunca un texto si excede el largo máximo
     * @param texto Texto a truncar
     * @param maxLargo Largo máximo permitido
     * @return Texto truncado
     */
    private String truncar(String texto, int maxLargo) {
        if (texto.length() <= maxLargo) {
            return texto;
        }
        return texto.substring(0, maxLargo - 3) + "...";
    }
}