package Model.Dao;
import Model.Entidades.Mascota;
import java.util.ArrayList;
import java.util.List;
public class MascotaDao implements Dao<Mascota>{
    private List<Mascota> listMascotas = new ArrayList<>();

    @Override
    public void save(Mascota m) throws DaoException {
        listMascotas.add(m); //Agrega a la lista de mascotas
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
        for (int i = 0; i<listMascotas.size(); i++) { //recorre la lista
            if (listMascotas.get(i).getIdMascota() == m.getIdMascota()) { //Si un id coincide con el id de el obj pasado por parametro
                listMascotas.set(i,m); //lo modifica en esa posicion
                return; 
            }
            throw new DaoException("Mascota con ID " + m.getIdMascota() + " no encontrado."); //si no se encontro ninguna mascota con dicho id, salta la excepcion para avisar al usuario

        }
    }

    @Override
    public void delete(int id) throws DaoException {
        for (int i = 0; i< listMascotas.size(); i++) {
            if (listMascotas.get(i).getIdMascota() == id) {
                listMascotas.remove(i); //cuando encuentra un id que coincida con el obj pasado por parametro lo remueve de la lista
                return;
            }
            throw new DaoException("Mascota con ID " + id + " no encontrado.");
        }
    }
    
    
}
