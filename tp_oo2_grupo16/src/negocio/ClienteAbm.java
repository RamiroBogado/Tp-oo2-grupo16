package negocio;

import dao.ClienteDao;
import modelo.Cliente;
import modelo.Contacto;
import java.util.List;

public class ClienteAbm {
    private static ClienteAbm instancia = null;

    private ClienteAbm() {}

    public static ClienteAbm getInstance() {
        if (instancia == null) {
            instancia = new ClienteAbm();
        }
        return instancia;
    }

    // ALTA
    public int altaCliente(String nombre, String apellido, String dni, 
                         boolean concurrente, String telefono, String email) {
    	
        validarDatosObligatorios(nombre, apellido, dni, telefono, email);
        
        if (existeClientePorDni(dni)) {
            throw new RuntimeException("El cliente con DNI " + dni + " ya existe");
        }
        
        Contacto contacto = new Contacto(telefono, email);
        Cliente cliente = new Cliente(nombre, apellido, dni, concurrente, contacto);
        
        int id = 0;
        
        id = ClienteDao.getInstance().agregar(cliente);
        
        System.out.println("Cliente creado con ID: " + id);
        
        return id;
    }

    // BAJAS
    public void eliminarClientePorId(int id) {
        Cliente cliente = traerCliente(id);
        if (cliente != null) {
            ClienteDao.getInstance().eliminar(cliente);
        } else {
            throw new RuntimeException("No existe cliente con ID: " + id);
        }
    }

    public void eliminarClientePorDni(String dni) {
        Cliente cliente = traerClientePorDni(dni);
        if (cliente != null) {
            ClienteDao.getInstance().eliminar(cliente);
            System.out.println("Cliente eliminado " + cliente);
        } else {
            throw new RuntimeException("No existe cliente con DNI: " + dni);
        }
    }

    // MODIFICACIONES
    public void modificarNombre(int id, String nuevoNombre) {
        Cliente cliente = traerCliente(id);
        if (cliente != null) {
        	String viejoNombre = cliente.getNombre();
            cliente.setNombre(nuevoNombre);
            ClienteDao.getInstance().actualizar(cliente);
            System.out.println("Cliente modificado de " + viejoNombre + " a " + nuevoNombre);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    public void modificarApellido(int id, String nuevoApellido) {
        Cliente cliente = traerCliente(id);
        if (cliente != null) {
        	String viejoApellido = cliente.getApellido();
            cliente.setApellido(nuevoApellido);
            ClienteDao.getInstance().actualizar(cliente);
            System.out.println("Cliente modificado de " + viejoApellido + " a " + nuevoApellido);
        }
    }

    public void modificarContacto(int id, String nuevoTelefono, String nuevoEmail) {
        Cliente cliente = traerCliente(id);
        if (cliente != null) {
            Contacto contacto = cliente.getContacto();
            String viejoTelefono = contacto.getTelefono();
            String viejoEmail = contacto.getEmail();
            contacto.setTelefono(nuevoTelefono);
            contacto.setEmail(nuevoEmail);
            ClienteDao.getInstance().actualizar(cliente); // Cascada actualiza el contacto
            System.out.println("Cliente modificado de " + viejoTelefono + " a " + nuevoTelefono + " y de " + viejoEmail + " a " + nuevoEmail );
        }
    }

    public void modificarEstadoConcurrente(int id, boolean nuevoEstado) {
        Cliente cliente = traerCliente(id);
        if (cliente != null) {
        	boolean viejoEstado = cliente.isConcurrente();
            cliente.setConcurrente(nuevoEstado);
            ClienteDao.getInstance().actualizar(cliente);
            System.out.println("Cliente concurrente de " +  viejoEstado + " a " + nuevoEstado);
        }
    }

    // CONSULTAS
    public Cliente traerCliente(int id) {
        return ClienteDao.getInstance().traerCliente(id);
    }

    public Cliente traerClientePorDni(String dni) {
        return ClienteDao.getInstance().traerClientePorDni(dni);
    }

    public List<Cliente> traerClientesPorNombre(String nombre) {
        return ClienteDao.getInstance().traerClientesPorNombre(nombre);
    }

    public List<Cliente> traerClientesConcurrentes(boolean concurrente) {
        return ClienteDao.getInstance().traerClientesConcurrentes(concurrente);
    }

    public List<Cliente> traerTodosClientes() {
        return ClienteDao.getInstance().traerTodos();
    }

    // VALIDACIONES
    private boolean existeClientePorDni(String dni) {
        return traerClientePorDni(dni) != null;
    }

    private void validarDatosObligatorios(String nombre, String apellido, String dni, 
                                        String telefono, String email) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Apellido no puede estar vacío");
        }
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI no puede estar vacío");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("Teléfono no puede estar vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email no puede estar vacío");
        }
    }
}