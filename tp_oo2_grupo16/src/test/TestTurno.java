package test;

import negocio.*;
import modelo.*;
import java.util.Date;
import java.util.Calendar;

public class TestTurno {
    public static void main(String[] args) {
        // Obtener instancias de los ABMs necesarios
        TurnoAbm turnoAbm = TurnoAbm.getInstance();
        ProfesionalAbm profesionalAbm = ProfesionalAbm.getInstance();
        ClienteAbm clienteAbm = ClienteAbm.getInstance();
        ServicioAbm servicioAbm = ServicioAbm.getInstance();
        
        try {
            // ==============================================
            // 1. PREPARACIÓN DE DATOS BÁSICOS
            // ==============================================
            
            System.out.println("\n=== Creando datos de prueba ===");
            
            // Crear un profesional
            int idProf = profesionalAbm.altaProfesional("Dr. Carlos", "Gimenez", "33444555", "MP87654",null);
            Profesional profesional = profesionalAbm.traerProfesional(idProf);
            
            // Crear un cliente
            int idCliente = clienteAbm.altaCliente("Ana", "Perez", "25666777", false, "1145678901", "ana@mail.com");
            Cliente cliente = clienteAbm.traerCliente(idCliente);
            
            // Crear un servicio
            int idServicio = servicioAbm.altaServicio("Consulta Médica", "Consulta general con especialista");
            Servicio servicio = servicioAbm.traerServicio(idServicio);
            
            // Preparar fechas para los turnos
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            Date manana = cal.getTime();
            
            cal.add(Calendar.HOUR, 2);
            Date mananaTarde = cal.getTime();
            
            // ==============================================
            // 2. ALTAS DE TURNOS
            // ==============================================
            
            System.out.println("\n=== Registrando nuevos turnos ===");
            
            // Turno 1
            int idTurno1 = turnoAbm.altaTurno(profesional, cliente, servicio, 
                                              manana, "Primera consulta", "pendiente");
            System.out.println("Turno creado con ID: " + idTurno1);
            
            // Turno 2
            int idTurno2 = turnoAbm.altaTurno(profesional, cliente, servicio, 
                                              mananaTarde, "Control", "pendiente");
            System.out.println("Turno creado con ID: " + idTurno2);
            
            // ==============================================
            // 3. MODIFICACIONES
            // ==============================================
            
            System.out.println("\n=== Modificando turnos ===");
            
            // Cambiar estado de un turno
            turnoAbm.modificarEstado(idTurno1, "confirmado");
            System.out.println("Estado del turno " + idTurno1 + " actualizado a 'confirmado'");
            
            // ==============================================
            // 4. CONSULTAS INDIVIDUALES
            // ==============================================
            
            System.out.println("\n=== Consultando turnos ===");
            
            System.out.println("\nTurno 1:");
            System.out.println(turnoAbm.traerTurno(idTurno1));
            
            // ==============================================
            // 5. LISTADOS ESPECIALES
            // ==============================================
            
            System.out.println("\n=== Listados especiales ===");
            
            System.out.println("\nTodos los turnos del profesional:");
            turnoAbm.traerTurnosPorProfesionalCompletos(idProf).forEach(System.out::println);

            System.out.println("\nTurnos para mañana:");
            turnoAbm.traerTurnosPorFechaCompletos(manana, mananaTarde).forEach(System.out::println);
            
            // ==============================================
            // 6. ELIMINACIONES
            // ==============================================
            
            System.out.println("\n=== Eliminando turnos ===");
            
            // Eliminar un turno
            turnoAbm.eliminarTurno(idTurno2);
            System.out.println("Turno " + idTurno2 + " eliminado");
            
            // ==============================================
            // 7. VERIFICACIÓN FINAL
            // ==============================================
            
            System.out.println("\n=== Estado final ===");
            System.out.println("Turnos activos:");
            turnoAbm.traerTurnosPorProfesionalCompletos(idProf).forEach(System.out::println);
            
        } catch (Exception e) {
            System.err.println("\nError durante las operaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
}