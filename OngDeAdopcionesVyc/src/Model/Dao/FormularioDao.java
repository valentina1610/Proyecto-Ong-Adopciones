package Model.Dao;
import Model.Entidades.Formulario;
import java.util.ArrayList;
import java.util.List;
public class FormularioDao implements Dao<Formulario>{
    private List<Formulario> listFormularios = new ArrayList<>();

    @Override
    public void save(Formulario f) throws DaoException {
        listFormularios.add(f);
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
        for (int i = 0; i < listFormularios.size(); i++) {
            if (listFormularios.get(i).getIdFormulario() == f.getIdFormulario()) {
                listFormularios.set(i, f);
                return;
            }
        }
        throw new DaoException("Formulario con ID " + f.getIdFormulario() + " no encontrado.");
    }

    @Override
    public void delete(int id) throws DaoException {
        for (int i = 0; i < listFormularios.size(); i++) {
            if (listFormularios.get(i).getIdFormulario() == id) {
                listFormularios.remove(i);
                return;
            }
        }
        throw new DaoException("Formulario con ID " + id + " no encontrado.");
    }
}
