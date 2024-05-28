package com.projetohotel.hotel.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projetohotel.hotel.config.DatabaseConnection;
import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.model.Funcionarios;
import com.projetohotel.hotel.model.Quartos;
import com.projetohotel.hotel.model.Reserva;

public class ReservaRepository {

    public List<Reserva> listarTodasReservas() {
        List<Reserva> listaReservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Clientes cliente = new Clientes(
                    0, resultSet.getString("nome_cliente"),
                    resultSet.getInt("cpf"),
                    resultSet.getInt("telefone")
                );

                Quartos quarto = new Quartos(
                    0, resultSet.getString("tipo_quarto"),
                    resultSet.getInt("numero_quarto"),
                    resultSet.getString("status_quarto")
                );

                Funcionarios funcionario = new Funcionarios(
                    0, resultSet.getString("nome_funcionario"),
                    resultSet.getString("cargo")
                );

                Reserva reserva = new Reserva(
                    resultSet.getInt("id_reserva"),
                    cliente,
                    quarto,
                    resultSet.getString("data_entrada"),
                    resultSet.getString("data_saida"),
                    funcionario,
                    funcionario, resultSet.getString("status")
                );

                listaReservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaReservas;
    }

    public void adicionarReserva(Reserva reserva, Funcionarios funcionario, Clientes cliente, Quartos quarto) {
        
        if (!clienteExiste(reserva.getCliente().getCpf())) {
            System.out.println("Cliente não encontrado no sistema!");
            return;
        }
    
        if (!quartoExiste(reserva.getQuarto().getNumeroQuarto())) {
            System.out.println("Quarto não encontrado no sistema!");
            return;
        }
    
        if (!funcionarioExiste(funcionario.getIdFuncionario())) {
            System.out.println("Funcionário não encontrado no sistema!");
            return;
        }
    
        String sql = "INSERT INTO reserva (cpf, nome_cliente, telefone, tipo_quarto, numero_quarto, status_quarto, data_entrada, data_saida, nome_funcionario, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, reserva.getCliente().getCpf());
            statement.setString(2, reserva.getCliente().getNome());
            statement.setInt(3, reserva.getCliente().getTelefone());
            statement.setString(4, reserva.getQuarto().getTipo());
            statement.setInt(5, reserva.getQuarto().getNumeroQuarto());
            statement.setString(6, reserva.getQuarto().getStatus());
            statement.setString(7, reserva.getDataEntrada());
            statement.setString(8, reserva.getDataSaida());
            statement.setString(9, funcionario.getNomeFuncionario());
            statement.setString(10, reserva.getStatusReserva());
    
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reserva adicionada com sucesso!");
            } else {
                System.out.println("Falha ao adicionar a reserva.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean clienteExiste(int cpf) {
        Clientes cliente = null;
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean quartoExiste(int numeroQuarto) {
        String sql = "SELECT 1 FROM quartos WHERE numero_quarto = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, numeroQuarto);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean funcionarioExiste(int idFuncionario) {
        String sql = "SELECT 1 FROM funcionarios WHERE id_funcionario = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idFuncionario);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
