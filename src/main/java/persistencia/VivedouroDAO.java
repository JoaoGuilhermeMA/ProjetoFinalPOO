package persistencia;

import dominio.Vivedouro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VivedouroDAO {
    private Connection connection;

    public VivedouroDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarVivedouro(Vivedouro vivedouro) throws SQLException {
        String query = "INSERT INTO Vivedouro(vivedouro, tamanho) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, vivedouro.getVivedouro());
            statement.setString(2, vivedouro.getTamanho());

            statement.executeUpdate();
        }
    }

    public List<Vivedouro> listarVivedouros() throws SQLException {
        List<Vivedouro> vivedouros = new ArrayList<>();
        String query = "SELECT * FROM Vivedouro";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Vivedouro vivedouro = new Vivedouro();
                vivedouro.setIdVivedouro(resultSet.getInt("idVivedouro"));
                vivedouro.setVivedouro(resultSet.getString("vivedouro"));
                vivedouro.setTamanho(resultSet.getString("tamanho"));

                vivedouros.add(vivedouro);
            }
        }
        return vivedouros;
    }

    public void excluirVivedouro(int id) throws SQLException {
        String query = "DELETE FROM Vivedouro WHERE idVivedouro = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void editarVivedouro(Vivedouro vivedouro) throws SQLException {
        String query = "UPDATE Vivedouro SET vivedouro = ?, tamanho = ? WHERE idVivedouro = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, vivedouro.getVivedouro());
            statement.setString(2, vivedouro.getTamanho());
            statement.setInt(3, vivedouro.getIdVivedouro());

            statement.executeUpdate();
        }
    }
}
