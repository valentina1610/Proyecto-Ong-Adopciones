package Model.Entidades;

import java.time.LocalDate;

public class Formulario {
    private int idFormulario; //PK
    private Mascota Mascota; //FK
    private Adoptante Adoptante; //FK
    private LocalDate fechaAdopcion;
    
    public Formulario() {
    }
    
    public Formulario(int idFormulario, Mascota Mascota, Adoptante Adoptante, LocalDate fechaAdopcion) {
        this.idFormulario = idFormulario;
        this.Mascota = Mascota;
        this.Adoptante = Adoptante;
        this.fechaAdopcion = fechaAdopcion;
    }
     // Getters y Setters
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Mascota getMascota() {
        return Mascota;
    }

    public void setMascota(Mascota Mascota) {
        this.Mascota = Mascota;
    }

    public Adoptante getAdoptante() {
        return Adoptante;
    }

    public void setAdoptante(Adoptante Adoptante) {
        this.Adoptante = Adoptante;
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
               " - Mascota: " + Mascota.getNombre() +
               " - Adoptante: " + Adoptante.getNombre() +
               " - Fecha adopcion: " + fechaAdopcion;
    }
    
}
