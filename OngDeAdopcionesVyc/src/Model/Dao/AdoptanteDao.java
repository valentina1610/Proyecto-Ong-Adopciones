package Model.Dao;
import Model.Entidades.Adoptante;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import Model.Dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdoptanteDao implements Dao<Adoptante> {
    
        private final Connection conn;
        
        public AdoptanteDao() {
        conn = Conexion.getConnection();
    }


    @Override
    public void save(Adoptante a) throws DaoException {

    String sql = "INSERT INTO Adoptante (nombre, telefono, email, direccion) VALUES (?, ?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getTelefono());
        ps.setString(3, a.getEmail());
        ps.setString(4, a.getDireccion());
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) a.setIdAdoptante(rs.getInt(1));
        }
    } catch (SQLException e) {
        throw new DaoException("Error al guardar adoptante en DB: " + e.getMessage());
    }
}

    @Override
    public List<Adoptante> findAll() throws DaoException {
        List<Adoptante> adoptantes = new ArrayList<>();
        String sql = "SELECT * FROM Adoptante";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Adoptante a = new Adoptante();
                a.setIdAdoptante(rs.getInt("idAdoptante"));
                a.setNombre(rs.getString("nombre"));
                a.setTelefono(rs.getString("telefono"));
                a.setEmail(rs.getString("email"));
                a.setDireccion(rs.getString("direccion"));
                adoptantes.add(a);
            }

        } catch (SQLException e) {
            throw new DaoException("Error al obtener adoptantes de la base de datos: " + e.getMessage(), e);
        }

        return adoptantes;
    }
    
    @Override
    public Adoptante findById(int id) throws DaoException {
        String sql = "SELECT * FROM Adoptante WHERE idAdoptante=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Adoptante a = new Adoptante();
                    a.setIdAdoptante(rs.getInt("idAdoptante"));
                    a.setNombre(rs.getString("nombre"));
                    a.setTelefono(rs.getString("telefono"));
                    a.setEmail(rs.getString("email"));
                    a.setDireccion(rs.getString("direccion"));
                    return a;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar adoptante en DB: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void update(Adoptante a) throws DaoException {
        String sql = "UPDATE Adoptante SET nombre=?, telefono=?, email=?, direccion=? WHERE idAdoptante=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getTelefono());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getDireccion());
            ps.setInt(5, a.getIdAdoptante());

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas == 0) {
                throw new DaoException("No se encontró adoptante con ID " + a.getIdAdoptante() + " para actualizar.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar adoptante en DB: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM Adoptante WHERE idAdoptante=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasEliminadas = ps.executeUpdate();

            if (filasEliminadas == 0) {
                throw new DaoException("No se encontró adoptante con ID " + id + " para eliminar.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar adoptante en DB: " + e.getMessage(), e);
        }
}
}
