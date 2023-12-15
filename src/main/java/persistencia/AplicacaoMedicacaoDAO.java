package persistencia;

import dominio.AplicacaoMedicacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AplicacaoMedicacaoDAO {
    private Connection connection;

    public AplicacaoMedicacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarAplicacaoMedicacao(AplicacaoMedicacao aplicacao) throws SQLException {
        String query = "INSERT INTO AplicacaoMedicacao(idMedicacao, idAnimal, idCuidador, dataAplicacao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, aplicacao.getIdMedicacao());
            statement.setInt(2, aplicacao.getIdAnimal());
            statement.setInt(3, aplicacao.getIdCuidador());
            statement.setDate(4, aplicacao.getDataAplicacao());

            statement.executeUpdate();
        }
    }

    public List<AplicacaoMedicacao> listarAplicacoesMedicacao() throws SQLException {
        List<AplicacaoMedicacao> aplicacoes = new ArrayList<>();
        String query = "SELECT * FROM AplicacaoMedicacao";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AplicacaoMedicacao aplicacao = new AplicacaoMedicacao();
                aplicacao.setIdAplicacao(resultSet.getInt("idAplicacao"));
                aplicacao.setIdMedicacao(resultSet.getInt("idMedicacao"));
                aplicacao.setIdAnimal(resultSet.getInt("idAnimal"));
                aplicacao.setIdCuidador(resultSet.getInt("idCuidador"));
                aplicacao.setDataAplicacao(resultSet.getDate("dataAplicacao"));

                aplicacoes.add(aplicacao);
            }
        }
        return aplicacoes;
    }

    public void excluirAplicacaoMedicacao(int id) throws SQLException {
        String query = "DELETE FROM AplicacaoMedicacao WHERE idAplicacao = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void editarAplicacaoMedicacao(AplicacaoMedicacao aplicacao) throws SQLException {
        String query = "UPDATE AplicacaoMedicacao SET idMedicacao = ?, idAnimal = ?, idCuidador = ?, dataAplicacao = ? WHERE idAplicacao = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, aplicacao.getIdMedicacao());
            statement.setInt(2, aplicacao.getIdAnimal());
            statement.setInt(3, aplicacao.getIdCuidador());
            statement.setDate(4, aplicacao.getDataAplicacao());
            statement.setInt(5, aplicacao.getIdAplicacao());

            statement.executeUpdate();
        }
    }
}
