package exemplo.barzinholuyner.dao;

import exemplo.barzinholuyner.model.Bebida;

import java.sql.SQLException;
import java.util.List;

public interface BebidaDAO {
    void insere(Bebida bebida) throws SQLException;
    void atualiza(Bebida bebida) throws SQLException;
    void remove(Bebida bebida) throws SQLException;
    List<Bebida> listTodos() throws SQLException; // Remover o parâmetro
    void marcarComoConcluida(int id) throws SQLException;
}
