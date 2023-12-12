package dao;

import connections.SingleConnection;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAORepository {
    private Connection connection;

    public LoginDAORepository() {
        connection = SingleConnection.getConnection();
    }

    public boolean validaLogin(ModelLogin modelLogin) throws Exception{

        String sql = " select * from model_login where login = ? and senha = ?";

            PreparedStatement preparaSql = connection.prepareStatement(sql);
            preparaSql.setString(1, modelLogin.getLogin());
            preparaSql.setString(2, modelLogin.getSenha());
            ResultSet resultSet = preparaSql.executeQuery();

            if (resultSet.next()){
                return true;
            }

        return false;
    }
}
