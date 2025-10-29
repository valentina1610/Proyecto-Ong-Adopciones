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
import Model.Entidades.Formulario;
import Model.Entidades.Mascota;
import Model.Entidades.Adoptante;


public class FormularioDao implements Dao<Formulario>{

    private final Connection conn;
    
    public FormularioDao() { 
    conn = Conexion.getConnection();
}
    
    @Override
    public void save(Formulario f) throws DaoException {

    String sql = "INSERT INTO Formulario (idMascota, idAdoptante, fechaAdopcion) VALUES (?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, f.getMascota().getIdMascota());  
        ps.setInt(2, f.getAdoptante().getIdAdoptante()); 
        ps.setDate(3, f.getFechaAdopcion() != null ? java.sql.Date.valueOf(f.getFechaAdopcion()) : null);
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) f.setIdFormulario(rs.getInt(1)); 
        }
    } catch (SQLException e) {
        throw new DaoException("Error al guardar formulario en DB: " + e.getMessage());
    }
}

    @Override
    public List<Formulario> findAll() throws DaoException {
        List<Formulario> formularios = new ArrayList<>();
        String sql = "SELECT * FROM Formulario";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Formulario f = new Formulario();
                f.setIdFormulario(rs.getInt("idFormulario"));

                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idMascota"));
                f.setMascota(m);

                Adoptante a = new Adoptante();
                a.setIdAdoptante(rs.getInt("idAdoptante"));
                f.setAdoptante(a);

                if (rs.getDate("fechaAdopcion") != null) {
                    f.setFechaAdopcion(rs.getDate("fechaAdopcion").toLocalDate());
                }

                formularios.add(f);
            }

        } catch (SQLException e) {
            throw new DaoException("Error al obtener formularios de la base de datos: " + e.getMessage(), e);
        }

        return formularios;
    }

    @Override
    public Formulario findById(int id) throws DaoException {
        String sql = "SELECT * FROM Formulario WHERE idFormulario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Formulario f = new Formulario();
                f.setIdFormulario(rs.getInt("idFormulario"));

                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idMascota"));
                f.setMascota(m);

                Adoptante a = new Adoptante();
                a.setIdAdoptante(rs.getInt("idAdoptante"));
                f.setAdoptante(a);

                if (rs.getDate("fechaAdopcion") != null) {
                    f.setFechaAdopcion(rs.getDate("fechaAdopcion").toLocalDate());
                }

                return f;
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar formulario por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void update(Formulario f) throws DaoException {
        String sql = "UPDATE Formulario SET idMascota=?, idAdoptante=?, fechaAdopcion=? WHERE idFormulario=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, f.getMascota().getIdMascota());
            ps.setInt(2, f.getAdoptante().getIdAdoptante());
            ps.setDate(3, f.getFechaAdopcion() != null ? java.sql.Date.valueOf(f.getFechaAdopcion()) : null);
            ps.setInt(4, f.getIdFormulario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar formulario en DB: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM Formulario WHERE idFormulario=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar formulario en DB: " + e.getMessage(), e);
        }
}

}
