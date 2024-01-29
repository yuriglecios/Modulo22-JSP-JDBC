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

    public void registrarUsuario(ModelLogin modelLogin) throws SQLException {

            String sql = "insert into model_login ( login, senha, nome, email) values (?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelLogin.getLogin());
            statement.setString(2,modelLogin.getSenha());
            statement.setString(3,modelLogin.getNome());
            statement.setString(4,modelLogin.getEmail());
            statement.execute();
            connection.commit();

    }

    public ModelLogin consultaUsuario(ModelLogin modelLogin) throws SQLException{
        String sql = "select * from model_login where upper(login) = upper(?)";
        PreparedStatement preparaSql = connection.prepareStatement(sql);
        preparaSql.setString(1,modelLogin.getLogin());
        ResultSet resultSet = preparaSql.executeQuery();
    }

}
