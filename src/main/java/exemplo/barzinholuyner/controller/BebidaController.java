package exemplo.barzinholuyner.controller;

import exemplo.barzinholuyner.dao.BebidaDAO;
import exemplo.barzinholuyner.dao.BebidaDBDAO;
import exemplo.barzinholuyner.model.Bebida;
import exemplo.barzinholuyner.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class BebidaController {
    private BebidaDAO bebidaDAO;
    private Usuario usuarioLogado;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField tipoField;

    @FXML
    private TextField volumeField;

    @FXML
    private TextField precoField;

    @FXML
    private TableView<Bebida> tabelaBebidas;

    @FXML
    private TableColumn<Bebida, Integer> idColuna;

    @FXML
    private TableColumn<Bebida, String> nomeColuna;

    @FXML
    private TableColumn<Bebida, String> tipoColuna;

    @FXML
    private TableColumn<Bebida, Double> volumeColuna;

    @FXML
    private TableColumn<Bebida, Double> precoColuna;

    // Lista observável para armazenar as bebidas
    private ObservableList<Bebida> listaBebidas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializa o DAO
        bebidaDAO = new BebidaDBDAO();

        // Configuração das colunas da tabela
        idColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoColuna.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        volumeColuna.setCellValueFactory(new PropertyValueFactory<>("volume"));
        precoColuna.setCellValueFactory(new PropertyValueFactory<>("preco"));

        // Inicializa a tabela com a lista vazia
        tabelaBebidas.setItems(listaBebidas);
        atualizarTabelaBebidas(); // Atualiza a tabela ao iniciar
    }

    @FXML
    void adicionarBebida(ActionEvent event) throws SQLException {
        // Cria uma nova bebida a partir dos campos de texto
        Bebida bebida = new Bebida(
                0, // ID gerado automaticamente pelo banco de dados
                nomeField.getText(),
                tipoField.getText(),
                Double.parseDouble(volumeField.getText()),
                Double.parseDouble(precoField.getText())
        );


        bebidaDAO.insere(bebida);


        atualizarTabelaBebidas();


        limparCampos();
    }

    @FXML
    void editarBebida(ActionEvent event) {
        // Obtém a bebida selecionada na tabela
        Bebida bebidaSelecionada = tabelaBebidas.getSelectionModel().getSelectedItem();

        if (bebidaSelecionada != null) {
            // Atualiza os campos de texto com os dados da bebida selecionada
            nomeField.setText(bebidaSelecionada.getNome());
            tipoField.setText(bebidaSelecionada.getTipo());
            volumeField.setText(String.valueOf(bebidaSelecionada.getVolume()));
            precoField.setText(String.valueOf(bebidaSelecionada.getPreco()));

        } else {
            // Exibir mensagem de erro se nenhuma bebida estiver selecionada
            System.out.println("Selecione uma bebida para editar.");
        }
    }

    @FXML
    void excluirBebida(ActionEvent event) {
        // Obtém a bebida selecionada na tabela
        Bebida bebidaSelecionada = tabelaBebidas.getSelectionModel().getSelectedItem();

        if (bebidaSelecionada != null) {
            try {

                bebidaDAO.remove(bebidaSelecionada);
                // Atualiza a tabela após a exclusão
                atualizarTabelaBebidas();
            } catch (SQLException e) {
                e.printStackTrace();
                // Exibir mensagem de erro
                System.out.println("Erro ao excluir a bebida: " + e.getMessage());
            }
        } else {
            // Exibir mensagem de erro se nenhuma bebida estiver selecionada
            System.out.println("Selecione uma bebida para excluir.");
        }
    }


    private void atualizarTabelaBebidas() {
        try {
            List<Bebida> bebidas = bebidaDAO.listTodos();
            tabelaBebidas.getItems().clear();
            tabelaBebidas.getItems().addAll(bebidas);
        } catch (SQLException e) {
            e.printStackTrace();
            // Exibir mensagem de erro
            System.out.println("Erro ao atualizar a tabela de bebidas: " + e.getMessage());
        }
    }

    private void limparCampos() {
        nomeField.clear();
        tipoField.clear();
        volumeField.clear();
        precoField.clear();
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}