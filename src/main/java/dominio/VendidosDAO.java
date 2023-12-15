package persistencia;

import dominio.Vendidos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendidosDAO {
    private Connection connection;

    public VendidosDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarVenda(Vendidos venda) throws SQLException {
        String query = "INSERT INTO Vendidos(idAnimal, cpfComprador, dataVenda, valorTotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, venda.getIdAnimal());
            statement.setString(2, venda.getCpfComprador());
            statement.setDate(3, venda.getDataVenda());
            statement.setFloat(4, venda.getValorTotal());

            statement.executeUpdate();
        }
    }

    public List<Vendidos> listarVendas() throws SQLException {
        List<Vendidos> vendas = new ArrayList<>();
        String query = "SELECT * FROM Vendidos";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Vendidos venda = new Vendidos();
                venda.setIdVenda(resultSet.getInt("idVenda"));
                venda.setIdAnimal(resultSet.getInt("idAnimal"));
                venda.setCpfComprador(resultSet.getString("cpfComprador"));
                venda.setDataVenda(resultSet.getDate("dataVenda"));
                venda.setValorTotal(resultSet.getFloat("valorTotal"));

                vendas.add(venda);
            }
        }
        return vendas;
    }

    public void excluirVenda(int id) throws SQLException {
        String query = "DELETE FROM Vendidos WHERE idVenda = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void editarVenda(Vendidos venda) throws SQLException {
        String query = "UPDATE Vendidos SET idAnimal = ?, cpfComprador = ?, dataVenda = ?, valorTotal = ? WHERE idVenda = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, venda.getIdAnimal());
            statement.setString(2, venda.getCpfComprador());
            statement.setDate(3, venda.getDataVenda());
            statement.setFloat(4, venda.getValorTotal());
            statement.setInt(5, venda.getIdVenda());

            statement.executeUpdate();
        }
    }
}
