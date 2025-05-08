package test;

import negocio.*;
import modelo.*;
import java.util.HashSet;
import java.util.Set;

public class TestProfesional {
    public static void main(String[] args) {
        ProfesionalAbm profesionalAbm = ProfesionalAbm.getInstance();
        EspecialidadAbm especialidadAbm = EspecialidadAbm.getInstance();
        
        try {
            // ==============================================
            // 1. CREACIÓN DE ESPECIALIDADES
            // ==============================================
            
            System.out.println("\n=== Registrando especialidades ===");
            
            especialidadAbm.altaEspecialidad("Cardiología", "Especialidad médica del corazón");
            especialidadAbm.altaEspecialidad("Pediatría", "Medicina para niños");
            especialidadAbm.altaEspecialidad("Traumatología", "Tratamiento de lesiones óseas");
            
            Especialidad cardio = especialidadAbm.traerEspecialidadPorNombre("Cardiología");
            Especialidad pediatria = especialidadAbm.traerEspecialidadPorNombre("Pediatría");
            Especialidad trauma = especialidadAbm.traerEspecialidadPorNombre("Traumatología");
            
            // ==============================================
            // 2. ALTAS DE PROFESIONALES
            // ==============================================
            
            System.out.println("\n=== Registrando nuevos profesionales ===");
            
            // Profesional 1 (con 2 especialidades)
            Set<Especialidad> especialidades1 = new HashSet<>();
            especialidades1.add(cardio);
            especialidades1.add(trauma);
            
            int id1 = profesionalAbm.altaProfesional("Juan", "Pérez", "30123456", 
                                                   "MP12345", especialidades1);
            
            // Profesional 2 (con 1 especialidad)
            Set<Especialidad> especialidades2 = new HashSet<>();
            especialidades2.add(pediatria);
            
            int id2 = profesionalAbm.altaProfesional("María", "Gómez", "28987654", 
                                                   "MP54321", especialidades2);        
            
            // Profesional 3 (sin especialidades iniciales)
            Set<Especialidad> especialidades3 = new HashSet<>();
            
            int id3 = profesionalAbm.altaProfesional("Carlos", "López", "33445566", 
                                                   "MP98765", especialidades3);
            
            // ==============================================
            // 3. MODIFICACIONES
            // ==============================================
            
            System.out.println("\n=== Modificando profesionales ===");
            
            // Modificar nombre y matrícula del primer profesional
            profesionalAbm.modificarNombre(id1, "Juan Carlos");
            profesionalAbm.modificarMatricula(id1, "MP12346");
            
            // Agregar especialidad al tercer profesional
            profesionalAbm.agregarEspecialidad(id3, pediatria);
            
            // ==============================================
            // 4. CONSULTAS INDIVIDUALES
            // ==============================================
            
            System.out.println("\n=== Consultando profesionales ===");
            
            System.out.println("\nProfesional 1:");
            System.out.println(profesionalAbm.traerProfesional(id1));
            
            System.out.println("\nProfesional 2:");
            System.out.println(profesionalAbm.traerProfesional(id2));
            
            System.out.println("\nProfesional por DNI 28987654:");
            System.out.println(profesionalAbm.traerProfesionalPorDni("28987654"));
            
            System.out.println("\nProfesional por matrícula MP54321:");
            System.out.println(profesionalAbm.traerProfesionalPorMatricula("MP54321"));
            
            // ==============================================
            // 5. LISTADOS ESPECIALES
            // ==============================================
            
            System.out.println("\n=== Listados especiales ===");
            
            System.out.println("\nTodos los profesionales:");
            profesionalAbm.traerTodosProfesionales().forEach(System.out::println);
            
            System.out.println("\nProfesionales de Pediatría:");
            profesionalAbm.traerProfesionalesPorEspecialidad("Pediatría").forEach(System.out::println);
            
            System.out.println("\nProfesionales llamados 'Carlos' o similares:");
            profesionalAbm.traerProfesionalesPorNombre("Car").forEach(System.out::println);
            
            // ==============================================
            // 6. MANEJO DE ESPECIALIDADES
            // ==============================================
            
            System.out.println("\n=== Modificando especialidades ===");
            
            // Agregar nueva especialidad al profesional 2
            profesionalAbm.agregarEspecialidad(id2, trauma);
            System.out.println("\nProfesional 2 con especialidad agregada:");
            System.out.println(profesionalAbm.traerProfesional(id2));
            
            // Eliminar especialidad del profesional 1
            profesionalAbm.eliminarEspecialidad(id1, trauma);
           	System.out.println("\nProfesional 1 con especialidad eliminada:");
            System.out.println(profesionalAbm.traerProfesional(id1));
            
            // ==============================================
            // 7. ELIMINACIONES
            // ==============================================
            
            System.out.println("\n=== Eliminando profesionales ===");
            
            // Eliminar por ID
            profesionalAbm.eliminarProfesionalPorId(id3);
            
            // Eliminar por DNI
            profesionalAbm.eliminarProfesionalPorDni("28987654");
            
            // ==============================================
            // 8. VERIFICACIÓN FINAL
            // ==============================================
            
            System.out.println("\n=== Estado final ===");
            System.out.println("Profesionales restantes:");
            profesionalAbm.traerTodosProfesionales().forEach(System.out::println);
            
            System.out.println("\nEspecialidades registradas:");
            especialidadAbm.listarEspecialidades();
            
        } catch (Exception e) {
            System.err.println("\nError durante las operaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
}