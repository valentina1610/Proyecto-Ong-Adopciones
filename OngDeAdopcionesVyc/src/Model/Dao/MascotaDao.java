package Model.Dao;
import Model.Entidades.Mascota;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import Model.Dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MascotaDao implements Dao<Mascota>{
    
    // conexion con base de datos
    private final Connection conn; 

    public MascotaDao() {
    conn = Conexion.getConnection();
    }

    @Override
    public void save(Mascota m) throws DaoException {
    
    listMascotas.add(m);

    String sql = "INSERT INTO Mascota (nombre, especie, raza, sexo, edad, estado, fechaIngreso) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, m.getNombre());
        ps.setString(2, m.getEspecie());
        ps.setString(3, m.getRaza());
        ps.setString(4, m.getSexo());
        ps.setInt(5, m.getEdad());
        ps.setString(6, m.getEstado());
        ps.setDate(7, m.getFechaIngreso() != null ? java.sql.Date.valueOf(m.getFechaIngreso()) : null);
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) m.setIdMascota(rs.getInt(1));
        }
    } catch (SQLException e) {
        throw new DaoException("Error al guardar mascota en DB: " + e.getMessage());
    }
}

     @Override
    public List<Mascota> findAll() throws DaoException {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM Mascota";
        
    try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idMascota"));
                m.setNombre(rs.getString("nombre"));
                m.setEspecie(rs.getString("especie"));
                m.setRaza(rs.getString("raza"));
                m.setSexo(rs.getString("sexo"));
                m.setEdad(rs.getInt("edad"));
                m.setEstado(rs.getString("estado"));
                m.setFechaIngreso(rs.getDate("fechaIngreso") != null
                        ? rs.getDate("fechaIngreso").toLocalDate()
                        : null);

                mascotas.add(m);
            }

        } catch (SQLException e) {
            throw new DaoException("Error al obtener mascotas de la base de datos: " + e.getMessage());
        }
    
        return mascotas;
    }
    
    @Override
    public Mascota findById(int id) throws DaoException {
    String sql = "SELECT * FROM Mascota WHERE idMascota=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idMascota"));
                m.setNombre(rs.getString("nombre"));
                m.setEspecie(rs.getString("especie"));
                m.setRaza(rs.getString("raza"));
                m.setSexo(rs.getString("sexo"));
                m.setEdad(rs.getInt("edad"));
                m.setEstado(rs.getString("estado"));
                m.setFechaIngreso(rs.getDate("fechaIngreso") != null
                        ? rs.getDate("fechaIngreso").toLocalDate()
                        : null);
                return m;
            }
        }
    } catch (SQLException e) {
        throw new DaoException("Error al buscar mascota por ID: " + e.getMessage());
    }
    return null;
    }
    
    @Override
    public void update(Mascota m) throws DaoException {
    String sql = "UPDATE Mascota SET nombre=?, especie=?, raza=?, sexo=?, edad=?, estado=?, fechaIngreso=? WHERE idMascota=?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, m.getNombre());
        ps.setString(2, m.getEspecie());
        ps.setString(3, m.getRaza());
        ps.setString(4, m.getSexo());
        ps.setInt(5, m.getEdad());
        ps.setString(6, m.getEstado());
        ps.setDate(7, m.getFechaIngreso() != null ? java.sql.Date.valueOf(m.getFechaIngreso()) : null);
        ps.setInt(8, m.getIdMascota());

        int filasActualizadas = ps.executeUpdate();

        if (filasActualizadas == 0) {
            throw new DaoException("No se encontró ninguna mascota con ID " + m.getIdMascota() + " para actualizar.");
        }
    } catch (SQLException e) {
        throw new DaoException("Error al actualizar mascota en DB: " + e.getMessage(), e);
    }
}

   

    @Override
    public void delete(int id) throws DaoException {

    listMascotas.removeIf(m -> m.getIdMascota() == id);

    // eliminamos de la base de datos
    String sql = "DELETE FROM Mascota WHERE idMascota=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new DaoException("Error al eliminar mascota en DB: " + e.getMessage());
    }
}
    
}
