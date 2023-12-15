package persistencia;

import dominio.Cuidador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuidadoDAO {
    private Connection connection;

    public CuidadoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarCuidador(Cuidador cuidador) throws SQLException {
        String query = "INSERT INTO Cuidador(salario, profissao, nome, telefone, cpf) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, cuidador.getSalario());
            statement.setString(2, cuidador.getProfissao());
            statement.setString(3, cuidador.getNome());
            statement.setString(4, cuidador.getTelefone());
            statement.setString(5, cuidador.getCpf());

            statement.executeUpdate();
        }
    }

    public List<Cuidador> listarCuidadores() throws SQLException {
        List<Cuidador> cuidadores = new ArrayList<>();
        String query = "SELECT * FROM Cuidador";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Cuidador cuidador = new Cuidador();
                cuidador.setIdCuidador(resultSet.getInt("idCuidador"));
                cuidador.setSalario(resultSet.getFloat("salario"));
                cuidador.setProfissao(resultSet.getString("profissao"));
                cuidador.setNome(resultSet.getString("nome"));
                cuidador.setTelefone(resultSet.getString("telefone"));
                cuidador.setCpf(resultSet.getString("cpf"));

                cuidadores.add(cuidador);
            }
        }
        return cuidadores;
    }

    public void excluirCuidador(int id) throws SQLException {
        String query = "DELETE FROM Cuidador WHERE idCuidador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void editarCuidador(Cuidador cuidador) throws SQLException {
        String query = "UPDATE Cuidador SET salario = ?, profissao = ?, nome = ?, telefone = ?, cpf = ? WHERE idCuidador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, cuidador.getSalario());
            statement.setString(2, cuidador.getProfissao());
            statement.setString(3, cuidador.getNome());
            statement.setString(4, cuidador.getTelefone());
            statement.setString(5, cuidador.getCpf());
            statement.setInt(6, cuidador.getIdCuidador());

            statement.executeUpdate();
        }
    }
}
