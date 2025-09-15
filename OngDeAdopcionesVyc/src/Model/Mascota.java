package Model;

public class Mascota {
    private int idMascota; //PK
    private String nombre;
    private String tipo;
    private String raza;
    private String sexo;
    private int edad;
    private String estado; // disponible o adoptado
    
    public Mascota(int idMascota, String nombre, String tipo, String raza, int edad, String estado, String sexo) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.estado = estado;
        this.sexo = sexo;
    }
    // Getters y Setters
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return idMascota + " - " + nombre + " (" + tipo + ", " + raza + ", " + edad + " años) Estado: " + estado;
    }
    
}
