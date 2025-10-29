package Model.Dao;
public class DaoException extends Exception {
    public DaoException(String msj) {
        super(msj);
    }
    
     public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
