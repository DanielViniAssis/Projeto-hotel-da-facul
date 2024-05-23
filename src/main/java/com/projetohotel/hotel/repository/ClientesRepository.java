package com.projetohotel.hotel.repository;

import com.projetohotel.hotel.config.DatabaseConnection;
import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.view.InterfaceHotel.InterfaceClientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientesRepository implements InterfaceClientes {
    
    @Override
    public void adicionarCliente(Clientes cliente) {
        String sql = "INSERT INTO cliente (nome_cliente, cpf, telefone) VALUES (?, ?, ?)";
        
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
    
            statement.setString(1, cliente.getNome());
            statement.setInt(2, cliente.getCpf());
            statement.setInt(3, cliente.getTelefone());
    
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o Cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clientes> listarTodosClientes() {
        List<Clientes> listaClientes = new ArrayList<>();
        String sql = "SELECT id_cliente, nome_cliente, cpf, telefone FROM cliente";

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
                        listaClientes.add(cliente); 
                    }
                }catch (SQLException e) {
                    e.printStackTrace(); 
                } 
                if ( listaClientes.isEmpty()){
                    System.out.println("Não existe nenhum cliente cadastrado no sistema!!");
                }
                return listaClientes;
                
        }

    @Override
    public void removerCliente(Clientes cliente) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";
        
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
    
                statement.setInt(1, cliente.getCpf());
        
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Falha ao remover o cliente ou cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao tentar remover cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Clientes encontrarClientePorCpf(int cpf) {
        Clientes cliente = null;
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
    
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
    
            statement.setInt(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    cliente = new Clientes(
                        resultSet.getInt("id_cliente"),
                        resultSet.getString("nome_cliente"),
                        resultSet.getInt("cpf"),
                        resultSet.getInt("telefone")
                        );
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return cliente;
    }

}
