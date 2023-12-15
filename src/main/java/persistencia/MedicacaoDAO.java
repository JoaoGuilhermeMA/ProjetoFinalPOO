package persistencia;

import dominio.Medicacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicacaoDAO {
    private Connection connection;

    public MedicacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarMedicacao(Medicacao medicacao) throws SQLException {
        String query = "INSERT INTO Medicacao(nomeMedicacao, mlPorKg) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, medicacao.getNomeMedicacao());
            statement.setFloat(2, medicacao.getMlPorKg());

            statement.executeUpdate();
        }
    }

    public List<Medicacao> listarMedicacoes() throws SQLException {
        List<Medicacao> medicacoes = new ArrayList<>();
        String query = "SELECT * FROM Medicacao";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Medicacao medicacao = new Medicacao();
                medicacao.setIdMedicacao(resultSet.getInt("idMedicacao"));
                medicacao.setNomeMedicacao(resultSet.getString("nomeMedicacao"));
                medicacao.setMlPorKg(resultSet.getFloat("mlPorKg"));

                medicacoes.add(medicacao);
            }
        }
        return medicacoes;
    }

    public void excluirMedicacao(int id) throws SQLException {
        String query = "DELETE FROM Medicacao WHERE idMedicacao = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void editarMedicacao(Medicacao medicacao) throws SQLException {
        String query = "UPDATE Medicacao SET nomeMedicacao = ?, mlPorKg = ? WHERE idMedicacao = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, medicacao.getNomeMedicacao());
            statement.setFloat(2, medicacao.getMlPorKg());
            statement.setInt(3, medicacao.getIdMedicacao());

            statement.executeUpdate();
        }
    }
}
