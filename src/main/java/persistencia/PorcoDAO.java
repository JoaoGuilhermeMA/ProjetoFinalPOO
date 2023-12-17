package persistencia;

import dominio.Cuidador;
import dominio.Medicacao;
import dominio.Porco;
import dominio.Vivedouro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PorcoDAO {
    private Connection connection;

    public PorcoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarPorco(Porco porco) throws SQLException {
        String query = "INSERT INTO Porco(sexo, peso, idade, raca, vendido, idVacina, idCuidador, idVivedouro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, porco.getSexo());
            statement.setString(2, porco.getPeso());
            statement.setInt(3, porco.getIdade());
            statement.setString(4, porco.getRaca());
            statement.setBoolean(5, porco.isVendido());
            statement.setInt(6, porco.getMedicacao().getIdMedicacao()); // Supondo que Porco tenha uma referência para Medicacao
            statement.setInt(7, porco.getCuidador().getIdCuidador()); // Supondo que Porco tenha uma referência para Cuidador
            statement.setInt(8, porco.getVivedouro().getIdVivedouro()); // Supondo que Porco tenha uma referência para Vivedouro

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                porco.setIdAnimal(generatedKeys.getInt(1));
            }
        }
    }


    public List<Porco> listarPorcosComRelacoes() throws SQLException {
        List<Porco> porcos = new ArrayList<>();
        String query = "SELECT Porco.idAnimal, Porco.sexo, Porco.peso, Porco.idade, Porco.raca, Porco.vendido, " +
                "Medicacao.idMedicacao, Medicacao.nomeMedicacao, Medicacao.mlPorKg, " +
                "Cuidador.idCuidador, Cuidador.salario, Cuidador.profissao, Cuidador.nome, Cuidador.telefone, Cuidador.cpf, " +
                "Vivedouro.idVivedouro, Vivedouro.vivedouro, Vivedouro.tamanho " +
                "FROM Porco " +
                "LEFT JOIN Medicacao ON Porco.idVacina = Medicacao.idMedicacao " +
                "LEFT JOIN Cuidador ON Porco.idCuidador = Cuidador.idCuidador " +
                "LEFT JOIN Vivedouro ON Porco.idVivedouro = Vivedouro.idVivedouro";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Porco porco = new Porco();
                porco.setIdAnimal(resultSet.getInt("idAnimal"));
                porco.setSexo(resultSet.getString("sexo"));
                porco.setPeso(resultSet.getString("peso"));
                porco.setIdade(resultSet.getInt("idade"));
                porco.setRaca(resultSet.getString("raca"));
                porco.setVendido(resultSet.getBoolean("vendido"));

                Medicacao medicacao = new Medicacao();
                medicacao.setIdMedicacao(resultSet.getInt("idMedicacao"));
                medicacao.setNomeMedicacao(resultSet.getString("nomeMedicacao"));
                medicacao.setMlPorKg(resultSet.getFloat("mlPorKg"));

                Cuidador cuidador = new Cuidador();
                cuidador.setIdCuidador(resultSet.getInt("idCuidador"));
                cuidador.setSalario(resultSet.getFloat("salario"));
                cuidador.setProfissao(resultSet.getString("profissao"));
                cuidador.setNome(resultSet.getString("nome"));
                cuidador.setTelefone(resultSet.getString("telefone"));
                cuidador.setCpf(resultSet.getString("cpf"));

                Vivedouro vivedouro = new Vivedouro();
                vivedouro.setIdVivedouro(resultSet.getInt("idVivedouro"));
                vivedouro.setVivedouro(resultSet.getString("vivedouro"));
                vivedouro.setTamanho(resultSet.getString("tamanho"));

                porco.setMedicacao(medicacao);
                porco.setCuidador(cuidador);
                porco.setVivedouro(vivedouro);

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
        String query = "UPDATE Porco SET sexo = ?, peso = ?, idade = ?, raca = ?, vendido = ? WHERE idAnimal = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, porco.getSexo());
            statement.setString(2, porco.getPeso());
            statement.setInt(3, porco.getIdade());
            statement.setString(4, porco.getRaca());
            statement.setBoolean(5, porco.isVendido());
            statement.setInt(6, porco.getIdAnimal());

            statement.executeUpdate();
        }
    }
}
