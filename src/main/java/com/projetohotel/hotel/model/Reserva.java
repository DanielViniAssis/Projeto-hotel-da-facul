package com.projetohotel.hotel.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.projetohotel.hotel.config.DatabaseConnection;

public class Reserva {
    private int idReserva;
    private int idCliente;
    private int cpf;
    private String nomeCliente;
    private int telefone;
    private int idQuarto;
    private String tipoQuarto;
    private String statusQuarto;
    private Date dataEntrada;
    private Date dataSaida;
    private int idFuncionario;
    private String nomeFuncionario;
    private int status;

 public Reserva(int idReserva, int idCliente, int cpf, String nomeCliente, int telefone, int idQuarto, String tipoQuarto, String statusQuarto, Date dataEntrada, Date dataSaida, int idFuncionario, String nomeFuncionario, int status) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.idQuarto = idQuarto;
        this.tipoQuarto = tipoQuarto;
        this.statusQuarto = statusQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.status = status;
    }

    // Getters e Setters para todos os campos
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public String getStatusQuarto() {
        return statusQuarto;
    }

    public void setStatusQuarto(String statusQuarto) {
        this.statusQuarto = statusQuarto;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Método para listar todas as reservas
    public List<Reserva> listarTodasReservas() {
        List<Reserva> listaReservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Reserva reserva = new Reserva(
                    resultSet.getInt("id_reserva"),
                    resultSet.getInt("id_cliente"),
                    resultSet.getInt("cpf"),
                    resultSet.getString("nome_cliente"),
                    resultSet.getInt("telefone"),
                    resultSet.getInt("id_quarto"),
                    resultSet.getString("tipo_quarto"),
                    resultSet.getString("status_quarto"),
                    resultSet.getDate("data_entrada"),
                    resultSet.getDate("data_saida"),
                    resultSet.getInt("id_funcionario"),
                    resultSet.getString("nome_funcionario"),
                    resultSet.getInt("status")
                );
                listaReservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (listaReservas.isEmpty()) {
            System.out.println("Não existe nenhuma reserva cadastrada no sistema!");
        }

        return listaReservas;
    }

    public void adicionarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (id_cliente, cpf, nome_cliente, telefone, id_quarto, tipo_quarto, status_quarto, data_entrada, data_saida, id_funcionario, nome_funcionario, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reserva.getIdCliente());
            statement.setInt(2, reserva.getCpf());
            statement.setString(3, reserva.getNomeCliente());
            statement.setInt(4, reserva.getTelefone());
            statement.setInt(5, reserva.getIdQuarto());
            statement.setString(6, reserva.getTipoQuarto());
            statement.setString(7, reserva.getStatusQuarto());
            statement.setDate(8, new java.sql.Date(reserva.getDataEntrada().getTime()));
            statement.setDate(9, new java.sql.Date(reserva.getDataSaida().getTime()));
            statement.setInt(10, reserva.getIdFuncionario());
            statement.setString(11, reserva.getNomeFuncionario());
            statement.setInt(12, reserva.getStatus());

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

    public static void listagem() {
        Reserva reserva = new Reserva(0, 0, 0, "", 0, 0, "", "", null, null, 0, "", 0);
        List<Reserva> listaReservas = reserva.listarTodasReservas();
        for (Reserva r : listaReservas) {
            System.out.println("Id da Reserva: " + r.getIdReserva());
            System.out.println("Cliente: " + r.getNomeCliente());
            System.out.println("CPF: " + r.getCpf());
            System.out.println("Telefone: " + r.getTelefone());
            System.out.println("Quarto: " + r.getIdQuarto() + " - " + r.getTipoQuarto() + " - " + r.getStatusQuarto());
            System.out.println("Data de Entrada: " + r.getDataEntrada());
            System.out.println("Data de Saída: " + r.getDataSaida());
            System.out.println("Funcionário: " + r.getNomeFuncionario());
            System.out.println("Status: " + r.getStatus());
            System.out.println("-------------------------");
        }
    }
}
