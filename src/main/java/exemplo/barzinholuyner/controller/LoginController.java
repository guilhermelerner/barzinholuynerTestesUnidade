package exemplo.barzinholuyner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import exemplo.barzinholuyner.dao.UsuarioDBDAO;
import exemplo.barzinholuyner.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Label mensagemErro;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void abrirCadastro(ActionEvent event) throws IOException {
        carregarTela("/exemplo/barzinholuyner/view/CadastroView.fxml", "Cadastro - Gerenciar de Bebidas");
    }

    @FXML
    void entrar(ActionEvent event) throws SQLException, IOException {
        Usuario usuario = new Usuario(usernameField.getText(), passwordField.getText());
        UsuarioDBDAO usuarioDB = new UsuarioDBDAO();

        if (usuarioDB.verificarEntrada(usuario)) {
            irCrud(usuario);
        } else {
            mensagemErro.setText("ERRO!\nUsuário ou Senha INCORRETOS");
        }
    }

    void irCrud(Usuario usuario) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/exemplo/barzinholuyner/view/BebidaView.fxml"));
        Parent root = loader.load();

        // Passando o usuário para o controlador da tela CRUD
        BebidaController bebidaController = loader.getController();
        bebidaController.setUsuarioLogado(usuario);

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Gerenciar Bebidas");
        stage.show();
    }

    private void carregarTela(String fxmlPath, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.show();
    }
}