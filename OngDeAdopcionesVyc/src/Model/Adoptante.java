package Model;

public class Adoptante {
    private int idAdoptante; //PK
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    
    public Adoptante(int idAdoptante, String nombre, String telefono, String email, String direccion) {
        this.idAdoptante = idAdoptante;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
    // Getters y setters
    public int getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(int idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // toString sobrescrito
    @Override
    public String toString() {
        return idAdoptante + " - " + nombre + " (" + telefono + ", " + email + ")";
    }
}
