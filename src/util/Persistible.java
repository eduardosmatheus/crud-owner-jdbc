package util;

import java.sql.SQLException;
import java.util.List;

/**
 * Contém as operações básicas de CRUD para um objeto específico.
 * @param <P> Um objeto específico.
 */
public interface Persistible<P> {
    
    P getOne(ConexaoApi conn, int id) throws SQLException, ClassNotFoundException;
    
    List<P> getAll(ConexaoApi conn) throws SQLException, ClassNotFoundException;
    
    void save(ConexaoApi conn, P p) throws SQLException, ClassNotFoundException;
    
    void delete(ConexaoApi conn, P p) throws SQLException, ClassNotFoundException;
}
