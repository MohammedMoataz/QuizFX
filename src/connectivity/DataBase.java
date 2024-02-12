package connectivity;

import java.sql.*;

public class DataBase {
    private final String DATABASE_NAME = "quiz",
            USERNAME = "root",
            PASSWORD = "root123";

    private Statement statement;

    public DataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/" + this.DATABASE_NAME, this.USERNAME, this.PASSWORD);
            this.statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet setQuery(String sql) throws SQLException {
        ResultSet rs = this.statement.executeQuery(sql);
        return rs;
    }

    public int setUpdate(String sql) throws SQLException {
        int rs = this.statement.executeUpdate(sql);
        return rs;
    }
}
