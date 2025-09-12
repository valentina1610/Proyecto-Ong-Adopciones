package Model;

public class Mascota {
    private int idMascota; //PK
    private String nombre;
    private String tipo;
    private String color;
    private int edad;
    private String estado; // disponible o adoptado
    
    public Mascota(int idMascota, String nombre, String tipo, String color, int edad, String estado) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.edad = edad;
        this.estado = estado;
    }
    // Getters y Setters
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return idMascota + " - " + nombre + " (" + tipo + ", " + color + ", " + edad + " años) Estado: " + estado;
    }
    
}
