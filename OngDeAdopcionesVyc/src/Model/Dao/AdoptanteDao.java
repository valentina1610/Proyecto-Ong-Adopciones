package Model.Dao;
import Model.Entidades.Adoptante;
import java.util.ArrayList;
import java.util.List;
public class AdoptanteDao implements Dao<Adoptante> {
    private List<Adoptante> listAdoptantes = new ArrayList<>();

    @Override
    public void save(Adoptante a) throws DaoException {
        listAdoptantes.add(a); //Agrega a la lista de adoptantes
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
        for (int i = 0; i<listAdoptantes.size(); i++) { //recorre la lista
            if (listAdoptantes.get(i).getIdAdoptante() == a.getIdAdoptante()) { //Si un id coincide con el id de el obj pasado por parametro
                listAdoptantes.set(i,a); //lo modifica en esa posicion
                return; 
            }
            throw new DaoException("Adoptante con ID " + a.getIdAdoptante() + " no encontrado."); //si no se encontro ninguno adoptante con dicho id, salta la excepcion para avisar al usuario

        }
    }

    @Override
    public void delete(int id) throws DaoException {
        for (int i = 0; i< listAdoptantes.size(); i++) {
            if (listAdoptantes.get(i).getIdAdoptante() == id) {
                listAdoptantes.remove(i); //cuando encuentra un id que coincida con el obj pasado por parametro lo remueve de la lista
                return;
            }
            throw new DaoException("Adoptante con ID " + id + " no encontrado.");
        }
    }
    
    
}
