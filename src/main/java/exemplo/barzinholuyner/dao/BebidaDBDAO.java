package exemplo.barzinholuyner.dao;

import exemplo.barzinholuyner.model.Bebida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BebidaDBDAO implements IConst, BebidaDAO {
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private void open() throws SQLException {
        connection = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
    }

    private void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void insere(Bebida bebida) throws SQLException {
        open();
        sql = "INSERT INTO bebida (nome, tipo, volume, preco) VALUES (?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, bebida.getNome());
        statement.setString(2, bebida.getTipo());
        statement.setDouble(3, bebida.getVolume());
        statement.setDouble(4, bebida.getPreco());

        statement.executeUpdate();
        close();
    }

    @Override
    public void atualiza(Bebida bebida) throws SQLException {
        open();
        sql = "UPDATE bebida SET nome = ?, tipo = ?, volume = ?, preco = ? WHERE id = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, bebida.getNome());
        statement.setString(2, bebida.getTipo());
        statement.setDouble(3, bebida.getVolume());
        statement.setDouble(4, bebida.getPreco());
        statement.setInt(5, bebida.getId()); // Atualiza pelo ID

        statement.executeUpdate();
        close();
    }

    @Override
    public void remove(Bebida bebida) throws SQLException {
        open();
        sql = "DELETE FROM bebida WHERE id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, bebida.getId()); // Remove pelo ID

        statement.executeUpdate();
        close();
    }

    public List<Bebida> listTodos() throws SQLException {
        List<Bebida> bebidas = new ArrayList<>();
        open();
        sql = "SELECT * FROM bebida"; // Consulta para listar todas as bebidas
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();

        while (result.next()) {
            int id = result.getInt("id"); // Obtém o ID da bebida
            String nome = result.getString("nome");
            String tipo = result.getString("tipo");
            double volume = result.getDouble("volume");
            double preco = result.getDouble("preco");
            Bebida bebida = new Bebida(id, nome, tipo, volume, preco);
            bebidas.add(bebida);
        }

        close();
        return bebidas;
    }

    @Override
    public void marcarComoConcluida(int id) throws SQLException {
    }
}