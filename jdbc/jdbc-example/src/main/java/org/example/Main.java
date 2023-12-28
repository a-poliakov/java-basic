package org.example;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/test2";

    private  static final String SELECT_USERS_SQL = "SELECT u.login, u.password FROM Users u";
    private static final String SELECT_ROLES_FOR_USER = """
    SELECT r.id, r.name from usertorole left join roles r on r.id = UserToRole.role_id where user_id = ?
    """;

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "postgres")) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery(SELECT_USERS_SQL)) {
                    while (resultSet.next()) {
                        String login = resultSet.getString(1);
                        String password = resultSet.getString(2);
                        User user = new User(login, password);
                        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLES_FOR_USER)) {
                            preparedStatement.setString(1, login);
                            try(ResultSet rs = preparedStatement.executeQuery()) {
                                Set<Role> roles = new HashSet<>();
                                while (rs.next()) {
                                    Integer id = rs.getInt(1);
                                    String name = rs.getString(2);
                                    Role role = new Role(id, name);
                                    roles.add(role);
                                }
                                user.setRoles(roles);
                            }
                        }
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(users);
    }
}
