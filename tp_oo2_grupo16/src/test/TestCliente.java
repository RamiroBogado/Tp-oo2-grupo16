package test;

import negocio.*;

public class TestCliente{
    public static void main(String[] args) {
        ClienteAbm clienteAbm = ClienteAbm.getInstance();
        
        try {
            // ==============================================
            // 1. ALTAS DE VARIOS CLIENTES
            // ==============================================
            
            System.out.println("\n=== Registrando nuevos clientes ===");
            
            // Cliente 1
            int id1 = clienteAbm.altaCliente("Ramiro", "Bogado", "42113660", false, 
                                           "1139003592", "federama2@gmail.com");
            
            // Cliente 2
            int id2 = clienteAbm.altaCliente("Ana", "García", "28987456", true, 
                                           "1156781234", "ana.garcia@empresa.com");
            
            // Cliente 3
            int id3 = clienteAbm.altaCliente("Luis", "Martínez", "33555111", false, 
                                           "1167894521", "luis.m@dominio.com");
            
            // Cliente 4 (concurrente)
            int id4 = clienteAbm.altaCliente("María", "López", "27888999", true, 
                                           "1145678912", "mlopez@gmail.com");
            
            // ==============================================
            // 2. MODIFICACIONES
            // ==============================================
            
            System.out.println("\n=== Modificando clientes ===");
            
            // Modificar el primer cliente
            clienteAbm.modificarNombre(id1, "Ramiro Carlos");
            clienteAbm.modificarApellido(id1, "Bogado Martínez");
            clienteAbm.modificarContacto(id1, "1139003592", "rami.bogado@nuevomail.com");
            clienteAbm.modificarEstadoConcurrente(id1, true);
            
            
            // Modificar solo el teléfono del segundo cliente
            clienteAbm.modificarContacto(id2, "1198765432", "ana.garcia@empresa.com");
            
            // ==============================================
            // 3. CONSULTAS INDIVIDUALES
            // ==============================================
            
            System.out.println("\n=== Consultando clientes ===");
            
            System.out.println("\nCliente 1:");
            System.out.println(clienteAbm.traerCliente(id1));
            
            System.out.println("\nCliente por DNI 28987456:");
            System.out.println(clienteAbm.traerClientePorDni("28987456"));
            
            // ==============================================
            // 4. LISTADOS
            // ==============================================
            
            System.out.println("\n=== Listados especiales ===");
            
            System.out.println("\nTodos los clientes:");
            (clienteAbm.traerTodosClientes()).forEach(System.out::println);
            
            System.out.println("\nClientes concurrentes (true):");
            (clienteAbm.traerClientesConcurrentes(true)).forEach(System.out::println);
            
            System.out.println("\nClientes llamados 'Maria' o similares:");
            (clienteAbm.traerClientesPorNombre("Mar")).forEach(System.out::println);
            
            // ==============================================
            // 5. ELIMINACIONES
            // ==============================================
            
            System.out.println("\n=== Eliminando clientes ===");
            
            // Eliminar por ID
            clienteAbm.eliminarClientePorId(id3);
            
            // Eliminar por DNI
            System.out.println(id4);
            clienteAbm.eliminarClientePorDni("27888999");
            
            // ==============================================
            // 6. VERIFICACIÓN FINAL
            // ==============================================
            
            System.out.println("\n=== Estado final ===");
            System.out.println("Clientes restantes (" + clienteAbm.traerTodosClientes().size() + "):");
            clienteAbm.traerTodosClientes();
            
        } catch (Exception e) {
            System.err.println("\nError durante las operaciones: " + e.getMessage());
            e.printStackTrace();
        }
        
        
        
    }
}