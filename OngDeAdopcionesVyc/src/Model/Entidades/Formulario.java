package Model.Entidades;

import java.time.LocalDate;

public class Formulario {
    private int idFormulario; //PK
    private Mascota idMascota; //FK
    private Adoptante idAdoptante; //FK
    private LocalDate fechaAdopcion;
    
    public Formulario(int idFormulario, Mascota idMascota, Adoptante idAdoptante, LocalDate fechaAdopcion) {
        this.idFormulario = idFormulario;
        this.idMascota = idMascota;
        this.idAdoptante = idAdoptante;
        this.fechaAdopcion = fechaAdopcion;
    }
     // Getters y Setters
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Mascota getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascota idMascota) {
        this.idMascota = idMascota;
    }

    public Adoptante getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(Adoptante idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public LocalDate getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(LocalDate fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    // toString
    @Override
    public String toString() {
        return "Formulario " + idFormulario +
               " - Mascota: " + idMascota.getNombre() +
               " - Adoptante: " + idAdoptante.getNombre() +
               " - Fecha adopcion: " + fechaAdopcion;
    }
    
}
