package Model.Dao;
import Model.Entidades.Formulario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import Model.Dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioDao implements Dao<Formulario>{
    private List<Formulario> listFormularios = new ArrayList<>();

    private Connection conn;
    
    public FormularioDao() { 
    conn = Conexion.getConnection();
}
    
    @Override
    public void save(Formulario f) throws DaoException {

    listFormularios.add(f);

    String sql = "INSERT INTO Formulario (idMascota, idAdoptante, fechaAdopcion) VALUES (?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, f.getMascota().getIdMascota());  
        ps.setInt(2, f.getAdoptante().getIdAdoptante()); 
        ps.setDate(3, f.getFechaAdopcion() != null ? java.sql.Date.valueOf(f.getFechaAdopcion()) : null);
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) f.setIdFormulario(rs.getInt(1)); // Guardamos el ID generado
        }
    } catch (SQLException e) {
        throw new DaoException("Error al guardar formulario en DB: " + e.getMessage());
    }
}

    @Override
    public List<Formulario> findAll() throws DaoException {
        return new ArrayList<>(listFormularios); // devuelve copia de la lista
    }

    @Override
    public Formulario findById(int id) throws DaoException {
        return listFormularios.stream()
                .filter(f -> f.getIdFormulario() == id) // comparamos con ==
                .findFirst()
                .orElse(null);
    }

    @Override
public void update(Formulario f) throws DaoException {
    boolean encontrado = false;

    // Primero actualizamos la lista en memoria
    for (int i = 0; i < listFormularios.size(); i++) {
        if (listFormularios.get(i).getIdFormulario() == f.getIdFormulario()) {
            listFormularios.set(i, f);
            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        throw new DaoException("Formulario con ID " + f.getIdFormulario() + " no encontrado.");
    }

    // Ahora actualizamos en la base de datos
    String sql = "UPDATE Formulario SET idMascota=?, idAdoptante=?, fechaAdopcion=? WHERE idFormulario=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, f.getMascota().getIdMascota());
        ps.setInt(2, f.getAdoptante().getIdAdoptante());
        ps.setDate(3, f.getFechaAdopcion() != null ? java.sql.Date.valueOf(f.getFechaAdopcion()) : null);
        ps.setInt(4, f.getIdFormulario());
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new DaoException("Error al actualizar formulario en DB: " + e.getMessage());
    }
}

    @Override
    public void delete(int id) throws DaoException {

    listFormularios.removeIf(a -> a.getIdFormulario() == id);

    String sql = "DELETE FROM Adoptante WHERE idAdoptante=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new DaoException("Error al eliminar adoptante en DB: " + e.getMessage());
    }
}
    
    
}
