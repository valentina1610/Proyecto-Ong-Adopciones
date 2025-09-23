package Model.Entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Mascota {
    private int idMascota; //PK
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private int edad;
    private String estado; // disponible o adoptado
    private LocalDate fechaIngreso;
    
    public Mascota(int idMascota, String nombre, String especie, String raza, int edad, String estado, String sexo, LocalDate fechaIngreso) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.estado = estado;
        this.sexo = sexo;
        this.fechaIngreso = fechaIngreso;
    }
    // Getters y Setters
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    @Override
    public String toString() {
        return idMascota + " - " + nombre + " (" + especie + ", " + raza + ", " + edad + " años) Estado: " + estado+ "Ingresado en: " + "-" + fechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idMascota;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.especie);
        hash = 29 * hash + Objects.hashCode(this.raza);
        hash = 29 * hash + Objects.hashCode(this.sexo);
        hash = 29 * hash + this.edad;
        hash = 29 * hash + Objects.hashCode(this.estado);
        hash = 29 * hash + Objects.hashCode(this.fechaIngreso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mascota other = (Mascota) obj;
        if (this.idMascota != other.idMascota) {
            return false;
        }
        if (this.edad != other.edad) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.especie, other.especie)) {
            return false;
        }
        if (!Objects.equals(this.raza, other.raza)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return Objects.equals(this.fechaIngreso, other.fechaIngreso);
    }
    
    
}
