package com.projetohotel.hotel;

import com.projetohotel.hotel.InterfaceHotel.InterfaceClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clientes implements InterfaceClientes {
    private static String nome;
    private int cpf;
    private int telefone;
    
    public Clientes(String nome, int cpf, int telefone){
        Clientes.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    // getters
    public String getNome(){
        return nome;
    }
    
    public int getCpf(){
        return cpf;
    }
    
    public int getTelefone(){
        return telefone;
    }
    
    //set
    @SuppressWarnings("static-access")
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(int cpf){
        this.cpf = cpf;
    }

    public void setTelefone(int telefone){
        this.telefone = telefone;
    }

    public static Clientes CadastroCliente() {
        try (Scanner leitura = new Scanner(System.in)) {
            System.out.println("Digite o nome do cliente: ");
            String nome = leitura.nextLine();
            
            System.out.println("Digite o CPF do cliente: ");
            int cpf = leitura.nextInt();

            System.out.println("Digite o Telefone do cliente: ");
            int telefone = leitura.nextInt();
            leitura.nextLine();
            return new Clientes(nome, cpf, telefone); 
        }
        catch (NumberFormatException e) {
            System.out.println("CPF inválido. Certifique-se de digitar apenas números.");
            return null;
        }
    }
    
    // funções da interface
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
        String sql = "SELECT * FROM cliente";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Clientes cliente = new Clientes(
                resultSet.getString("nome_cliente"),
                resultSet.getInt("cpf"),
                resultSet.getInt("telefone")
            );
            listaClientes.add(cliente); 
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } return listaClientes;
    }

    @Override
    public void removerCliente(Clientes cliente) {
        String sql = "DELETE FROM cliente WHERE nome_cliente = ? AND cpf = ? AND telefone = ?";
        
        try (Connection conexao = DatabaseConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
    
                statement.setString(1, cliente.getNome());
                statement.setInt(2, cliente.getCpf());
                statement.setInt(3, cliente.getTelefone());
        
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Falha ao remover o cliente ou cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao tentar remover cliente: ");
            e.printStackTrace();
        }
    }

    @Override
    public Clientes encontrarClientePorId(int id_cliente) {
        Clientes cliente = null;
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
    
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
    
            statement.setInt(1, id_cliente);
            ResultSet resultSet = statement.executeQuery();
    
        if (resultSet.next()) {
            cliente = new Clientes(
                resultSet.getString("nome_cliente"),
                resultSet.getInt("cpf"),
                resultSet.getInt("telefone")
                );
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
         return cliente;
        }

    public static void clienteEncontrado() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite o ID do cliente que deseja encontrar:");
        int id_cliente = leitura.nextInt();

        Clientes clienteInstancia = new Clientes("", 0, 0); 
        Clientes clienteEncontrado = clienteInstancia.encontrarClientePorId(id_cliente);

        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("Telefone: " + clienteEncontrado.getTelefone());
        } else {
            System.out.println("Cliente não encontrado.");
        } leitura.close();
    }
}