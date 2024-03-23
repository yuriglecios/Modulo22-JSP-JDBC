package dao;

import connections.SingleConnection;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuarioRepository {

    private Connection connection;

    public DaoUsuarioRepository() {
        connection = SingleConnection.getConnection();
    }

    public ModelLogin registrarUsuario(ModelLogin modelLogin, Long idUsuarioLogado) throws SQLException {

            if (modelLogin.isNovo()){
                String sql = "insert into model_login ( login, senha, nome, email, usuario_id) values (?,?,?,?, ?);";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, modelLogin.getLogin());
                statement.setString(2,modelLogin.getSenha());
                statement.setString(3,modelLogin.getNome());
                statement.setString(4,modelLogin.getEmail());
                statement.setLong(5,idUsuarioLogado);
                statement.execute();
                connection.commit();
            }
            else {
                String sql = "update model_login set login=?, senha=?,nome=?,email=? where id = "+modelLogin.getId()+";";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, modelLogin.getLogin());
                statement.setString(2, modelLogin.getSenha());
                statement.setString(3, modelLogin.getNome());
                statement.setString(4,modelLogin.getEmail());
                statement.executeUpdate();
                connection.commit();
            }


            return this.consultaUsuario(modelLogin.getLogin());

    }

    public ModelLogin consultaUsuario(String login) throws SQLException{

        ModelLogin modelLogin = new ModelLogin();
        String sql = "select * from model_login where upper(login) = upper('"+login+"');";
        PreparedStatement preparaSql = connection.prepareStatement(sql);
        ResultSet resultSet = preparaSql.executeQuery();

        while (resultSet.next()){
            modelLogin.setId(resultSet.getLong("id"));
            modelLogin.setNome(resultSet.getString("nome"));
            modelLogin.setEmail(resultSet.getString("email"));
            modelLogin.setLogin(resultSet.getString("login"));
            modelLogin.setSenha(resultSet.getString("senha"));
        }

        return modelLogin;
    }

    public boolean validaUsuario(String login) throws Exception{
        String sql = "select count(1) >0 as existe from model_login where upper(login) = upper('"+login+"')";
        PreparedStatement preparaSql = connection.prepareStatement(sql);
        ResultSet resultSet = preparaSql.executeQuery();
        resultSet.next();
        return resultSet.getBoolean("existe");
    }

    public void deletarUsuario(String id) throws Exception{
        var sql = "delete from model_login where id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, Long.parseLong(id));
        statement.executeUpdate();
        connection.commit();
    }

    public List<ModelLogin> buscarUsuarioByNome(String nomeUsuario) throws Exception {

        List<ModelLogin> listaUsuario = new ArrayList<>();
        var sql = "select * from model_login where upper(nome) like upper(?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%"+nomeUsuario+"%");
        ResultSet resultado = statement.executeQuery();
        while (resultado.next()){
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setSenha(resultado.getString("senha"));
            listaUsuario.add(modelLogin);
        }

        return listaUsuario;
    }

    public List<ModelLogin> buscarTodosUsuarios() throws SQLException {
        List<ModelLogin> listaDeUsuarios = new ArrayList<>();
        var sql = "select * from model_login;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setId(resultSet.getLong("id"));
            modelLogin.setEmail(resultSet.getString("email"));
            modelLogin.setNome(resultSet.getString("nome"));
            modelLogin.setLogin(resultSet.getString("login"));
            modelLogin.setSenha(resultSet.getString("senha"));
            listaDeUsuarios.add(modelLogin);
        }
        return listaDeUsuarios;
    }

    public ModelLogin buscarUsuarioById(String id) throws Exception {

        ModelLogin modelLogin = new ModelLogin();
        var sql = "select * from model_login where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, Long.parseLong(id));
        ResultSet resultado = statement.executeQuery();
        while (resultado.next()){
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setLogin(resultado.getString("login"));
        }

        return modelLogin;
    }
}
