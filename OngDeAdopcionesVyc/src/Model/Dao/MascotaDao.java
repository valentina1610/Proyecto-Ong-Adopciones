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
    private List<Mascota> listMascotas = new ArrayList<>();
    
    private Connection conn; 

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
        return listMascotas; //Retorna la lista
    }

    @Override
    public Mascota findById(int id) throws DaoException {
        return listMascotas.stream()
                .filter(m ->m.getIdMascota() == id) //Un Stream es como una tubería por donde pasan los datos de la lista y se le puede ir aplicando pasos: filtrar, transformar, ordenar, contar, etc.
                //Busca un id de la variable "Mascotas m" que sea igual a el id pasado por parametro
                .findFirst() //Corta el stream al primero que encuentra, y devuelve el adoptante si alguno coincide
                .orElse(null); //Y si no encuentra devuelve null
    }

    @Override
public void update(Mascota m) throws DaoException {
    boolean encontrado = false;

    // Primero actualizamos la lista en memoria
    for (int i = 0; i < listMascotas.size(); i++) {
        if (listMascotas.get(i).getIdMascota() == m.getIdMascota()) {
            listMascotas.set(i, m);
            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        throw new DaoException("Mascota con ID " + m.getIdMascota() + " no encontrado.");
    }

    // Ahora actualizamos en la base de datos
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
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new DaoException("Error al actualizar mascota en DB: " + e.getMessage());
    }
}

    @Override
    public void delete(int id) throws DaoException {

    listMascotas.removeIf(m -> m.getIdMascota() == id);

    // Ahora eliminamos de la base de datos
    String sql = "DELETE FROM Mascota WHERE idMascota=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new DaoException("Error al eliminar mascota en DB: " + e.getMessage());
    }
}
    
}
