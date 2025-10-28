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
    private List<Adoptante> listAdoptantes = new ArrayList<>();
    
        private Connection conn;
        
        public AdoptanteDao() {
        conn = Conexion.getConnection();
    }


    @Override
    public void save(Adoptante a) throws DaoException {

    listAdoptantes.add(a);

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
        return listAdoptantes; //Retorna la lista
    }

    @Override
    public Adoptante findById(int id) throws DaoException {
        return listAdoptantes.stream()
                .filter(a ->a.getIdAdoptante() == id) //Un Stream es como una tubería por donde pasan los datos de la lista y se le puede ir aplicando pasos: filtrar, transformar, ordenar, contar, etc.
                //Busca un id de la variable "Adoptantes a" que sea igual a el id pasado por parametro
                .findFirst() //Corta el stream al primero que encuentra, y devuelve el adoptante si alguno coincide
                .orElse(null); //Y si no encuentra devuelve null
    }

    @Override
    public void update(Adoptante a) throws DaoException {
    boolean encontrado = false;

    for (int i = 0; i < listAdoptantes.size(); i++) {
        if (listAdoptantes.get(i).getIdAdoptante() == a.getIdAdoptante()) {
            listAdoptantes.set(i, a);
            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        throw new DaoException("Adoptante con ID " + a.getIdAdoptante() + " no encontrado.");
    }

    // Ahora actualizamos en la base de datos
    String sql = "UPDATE Adoptante SET nombre=?, telefono=?, email=?, direccion=? WHERE idAdoptante=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getTelefono());
        ps.setString(3, a.getEmail());
        ps.setString(4, a.getDireccion());
        ps.setInt(5, a.getIdAdoptante());
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new DaoException("Error al actualizar adoptante en DB: " + e.getMessage());
    }
}

    @Override
    public void delete(int id) throws DaoException {
    
    listAdoptantes.removeIf(a -> a.getIdAdoptante() == id);

    String sql = "DELETE FROM Adoptante WHERE idAdoptante=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new DaoException("Error al eliminar adoptante en DB: " + e.getMessage());
    }
}
}
