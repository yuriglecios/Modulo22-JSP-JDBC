package dao;

import connections.SingleConnection;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuarioRepository {

    private Connection connection;

    public DaoUsuarioRepository() {
        connection = SingleConnection.getConnection();
    }

    public ModelLogin registrarUsuario(ModelLogin modelLogin) throws SQLException {

            if (modelLogin.isNovo()){
                String sql = "insert into model_login ( login, senha, nome, email) values (?,?,?,?);";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, modelLogin.getLogin());
                statement.setString(2,modelLogin.getSenha());
                statement.setString(3,modelLogin.getNome());
                statement.setString(4,modelLogin.getEmail());
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
}
