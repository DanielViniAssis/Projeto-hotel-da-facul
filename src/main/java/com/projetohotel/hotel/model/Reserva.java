package com.projetohotel.hotel.model;

import com.projetohotel.hotel.config.DatabaseConnection;
import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.model.Funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
    private int idReserva;
    private Clientes cliente;
    private int cpf;
    private String nomeCliente;
    private int telefone;
    private Quartos quarto;
    private String tipoQuarto;
    private int numeroQuarto;
    private String statusQuarto;
    private int dataEntrada;
    private int dataSaida;
    private Funcionarios funcionario;
    private String nomeFuncionario;
    private String status;

    public Reserva(int idReserva, Clientes cliente, int cpf, String nomeCliente, int telefone, Quartos quarto, String tipoQuarto, int numeroQuarto, String statusQuarto, int dataEntrada, int dataSaida, Funcionarios funcionario, String nomeFuncionario, String status) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.cpf = cpf;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.quarto = quarto;
        this.tipoQuarto = tipoQuarto;
        this.numeroQuarto = numeroQuarto;
        this.statusQuarto = statusQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.funcionario = funcionario;
        this.nomeFuncionario = nomeFuncionario;
        this.status = status;
    }
    // Getters e Setters 
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
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

    public Quartos getQuarto() {
        return quarto;
    }

    public void setQuarto(Quartos quarto) {
        this.quarto = quarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public String getStatusQuarto() {
        return statusQuarto;
    }

    public void setStatusQuarto(String statusQuarto) {
        this.statusQuarto = statusQuarto;
    }

    public int getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(int dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(int dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reserva> listarTodasReservas() {
        List<Reserva> listaReservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Reserva reserva = new Reserva(
                    resultSet.getInt("id_reserva"),
                    null,
                    resultSet.getInt("cpf"),
                    resultSet.getString("nome_cliente"),
                    resultSet.getInt("telefone"),
                    null, 
                    resultSet.getString("tipo_quarto"),
                    resultSet.getInt("numero_quarto"),
                    resultSet.getString("status_quarto"),
                    resultSet.getInt("data_entrada"),
                    resultSet.getInt("data_saida"),
                    null, 
                    resultSet.getString("nome_funcionario"),
                    resultSet.getString("status")
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
        String sql = "INSERT INTO reserva (id_cliente, cpf, nome_cliente, telefone, id_quarto, tipo_quarto, numero_quarto,  status_quarto, data_entrada, data_saida, id_funcionario, nome_funcionario, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reserva.getCliente().getIdCliente());
            statement.setInt(2, reserva.getCpf());
            statement.setString(3, reserva.getNome());
            statement.setInt(4, reserva.getTelefone());
            statement.setInt(5, reserva.getQuarto().getIdQuarto());
            statement.setString(6, reserva.getTipoQuarto());
            statement.setInt(6, reserva.getNumeroQuarto());
            statement.setString(7, reserva.getStatusQuarto());
            statement.setInt(8,  reserva.getDataEntrada());
            statement.setInt(9,  reserva.getDataSaida());
            statement.setInt(10, reserva.getFuncionario().getIdFuncionario());
            statement.setString(11, reserva.getNomeFuncionario());
            statement.setString(12, reserva.getStatus());

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
        Reserva reserva = new Reserva(0, null, 0, "", 0, null, "", 0, "", 0, 0, null, "", "");
        List<Reserva> listaReservas = reserva.listarTodasReservas();
        for (Reserva r : listaReservas) {
            System.out.println("Id da Reserva: " + r.getIdReserva());
            System.out.println("Cliente: " + r.getNome());
            System.out.println("CPF: " + r.getCpf());
            System.out.println("Telefone: " + r.getTelefone());
            System.out.println("Quarto: " + r.getQuarto() + " - " + r.getTipoQuarto() + " - " + r.getNumeroQuarto() + " - " + r.getStatusQuarto());
            System.out.println("Data de Entrada: " + r.getDataEntrada());
            System.out.println("Data de Saída: " + r.getDataSaida());
            System.out.println("Funcionário: " + r.getNomeFuncionario());
            System.out.println("Status: " + r.getStatus());
            System.out.println("-------------------------");
        }
    }
}
