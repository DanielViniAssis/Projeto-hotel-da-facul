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
                    resultSet.getInt("id_cliente"),
                    resultSet.getString("nome_cliente"),
                    resultSet.getInt("cpf"),
                    resultSet.getInt("telefone")
                );

                Quartos quarto = new Quartos(
                    resultSet.getInt("id_quarto"),
                    resultSet.getString("tipo_quarto"),
                    resultSet.getInt("numero_quarto"),
                    resultSet.getString("status_quarto")
                );

                Funcionarios funcionario = new Funcionarios(
                    resultSet.getInt("id_funcionario"),
                    resultSet.getString("nome_funcionario"),
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

    public void adicionarReserva(Reserva reserva, Funcionarios funcionario) {
        String sql = "INSERT INTO reserva (id_cliente, cpf, nome_cliente, telefone, id_quarto, tipo_quarto, status_quarto, data_entrada, data_saida, nome_funcionario, status, numero_quarto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, reserva.getCliente().getIdCliente());
            statement.setInt(2, reserva.getCliente().getCpf());
            statement.setString(3, reserva.getCliente().getNome());
            statement.setInt(4, reserva.getCliente().getTelefone());
            statement.setInt(5, reserva.getQuarto().getIdQuarto());
            statement.setString(6, reserva.getQuarto().getTipo());
            statement.setInt(7, reserva.getQuarto().getNumeroQuarto());
            statement.setString(8, reserva.getQuarto().getStatus());
            statement.setString(9, reserva.getDataEntrada());
            statement.setString(10, reserva.getDataSaida());
            statement.setString(12, funcionario.getNomeFuncionario());
            statement.setString(13, reserva.getStatusReserva());
    
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
    
}