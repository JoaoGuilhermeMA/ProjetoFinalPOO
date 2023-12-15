package persistencia;

import dominio.Porco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PorcoDAO {
    private Connection connection;

    public PorcoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um Porco ao banco de dados
    public void adicionarPorco(Porco porco) throws SQLException {
        String query = "INSERT INTO Porco(sexo, peso, idade, raca, idVacina, idCuidador, idVivedouro, vendido) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, porco.getSexo());
            statement.setString(2, porco.getPeso());
            statement.setInt(3, porco.getIdade());
            statement.setString(4, porco.getRaca());
            statement.setInt(5, porco.getIdVacina());
            statement.setInt(6, porco.getIdCuidador());
            statement.setInt(7, porco.getIdVivedouro());
            statement.setBoolean(8, porco.isVendido());

            statement.executeUpdate();
        }
    }

    // Método para recuperar todos os Porcos do banco de dados
    public List<Porco> listarPorcos() throws SQLException {
        List<Porco> porcos = new ArrayList<>();
        String query = "SELECT * FROM Porco";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Porco porco = new Porco();
                porco.setIdAnimal(resultSet.getInt("idAnimal"));
                porco.setSexo(resultSet.getString("sexo"));
                porco.setPeso(resultSet.getString("peso"));
                porco.setIdade(resultSet.getInt("idade"));
                porco.setRaca(resultSet.getString("raca"));
                porco.setIdVacina(resultSet.getInt("idVacina"));
                porco.setIdCuidador(resultSet.getInt("idCuidador"));
                porco.setIdVivedouro(resultSet.getInt("idVivedouro"));
                porco.setVendido(resultSet.getBoolean("vendido"));

                porcos.add(porco);
            }
        }
        return porcos;
    }
    public void excluirPorco(int id) throws SQLException {
        String query = "DELETE FROM Porco WHERE idAnimal = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    public void editarPorco(Porco porco) throws SQLException {
        String query = "UPDATE Porco SET sexo = ?, peso = ?, idade = ?, raca = ?, idVacina = ?, idCuidador = ?, idVivedouro = ?, vendido = ? WHERE idAnimal = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, porco.getSexo());
            statement.setString(2, porco.getPeso());
            statement.setInt(3, porco.getIdade());
            statement.setString(4, porco.getRaca());
            statement.setInt(5, porco.getIdVacina());
            statement.setInt(6, porco.getIdCuidador());
            statement.setInt(7, porco.getIdVivedouro());
            statement.setBoolean(8, porco.isVendido());
            statement.setInt(9, porco.getIdAnimal());

            statement.executeUpdate();
        }
    }


}
