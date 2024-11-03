package exemplo.barzinholuyner.dao;


import exemplo.barzinholuyner.model.Usuario;

import java.sql.SQLException;

public interface UsuarioDAO {
    public void cadastrar(Usuario usuario) throws SQLException;
    public void atualizarCadastro(Usuario usuarioAtualizado) throws SQLException;
    public void removeCadastro(Usuario usuario) throws SQLException;
    public boolean verificarEntrada(Usuario usuario) throws SQLException;
}

