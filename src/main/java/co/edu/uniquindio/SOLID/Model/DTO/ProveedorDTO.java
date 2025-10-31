package co.edu.uniquindio.SOLID.Model.DTO;

public class ProveedorDTO {
    private String nit;
    private String nombre;
    private String contacto;
    private String email;
    private String telefono;
    private boolean activo;

    public ProveedorDTO(String nit, String nombre, String contacto, String email, String telefono) {
        this.nit = nit;
        this.nombre = nombre;
        this.contacto = contacto;
        this.email = email;
        this.telefono = telefono;
        this.activo = true;
    }

    public String getNit() { return nit; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public boolean isActivo() { return activo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void inactivar() { this.activo = false; }
    public void activar() { this.activo = true; }

}
