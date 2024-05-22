package com.projetohotel.hotel;

import com.projetohotel.hotel.InterfaceHotel.InterfaceClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Clientes implements InterfaceClientes {
    private int idCliente;
    private String nome;
    private int cpf;
    private int telefone;
    
    public Clientes(int idCliente, String nome, int cpf, int telefone){
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    // getters
    public int getIdCliente(){
        return idCliente;
    }

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
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(int cpf){
        this.cpf = cpf;
    }

    public void setTelefone(int telefone){
        this.telefone = telefone;
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

    public static Clientes cadastroCliente() {
        Scanner leitura = new Scanner(System.in);
        try{
            System.out.println("Digite o nome do cliente: ");
            String nome = leitura.nextLine();
            
            System.out.println("Digite o CPF do cliente: ");
            while (!leitura.hasNextInt()) {
                System.out.println("CPF inválido. Certifique-se de digitar apenas números.");
                leitura.nextInt();
            }
            int cpf = leitura.nextInt();
            
            
            System.out.println("Digite o Telefone do cliente: ");
            while (!leitura.hasNextInt()) {
                System.out.println("Telefone inválido. Certifique-se de digitar apenas números.");
                leitura.nextInt();
            }
            int telefone = leitura.nextInt();
            
            return new Clientes(0, nome, cpf, telefone);

        }catch (NoSuchElementException e) {
            System.out.println("Entrada inválida, tente novamente.");
            e.printStackTrace();
            return null;
        } finally {
            
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

    public static void listagem(){
        Clientes clientes = new Clientes(0, "", 0, 0);
        List<Clientes> listaClientes = clientes.listarTodosClientes();
        for (Clientes cliente : listaClientes) {
            System.out.println("Id: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("-------------------------");
        }
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
    
    public static void clienteRemovido() {
        Scanner leitura = new Scanner(System.in);
        try {
            Clientes clientes = new Clientes(0,"", 0, 0);
            System.out.print("Digite o CPF do cliente a ser removido: ");
            while (!leitura.hasNextInt()) {
                System.out.println("Digite um CPF válido!");
                leitura.next(); 
            }
            int cpfRemover = leitura.nextInt();
            leitura.nextLine();
        
        List<Clientes> listaClientes = clientes.listarTodosClientes();
        for (Clientes cliente : listaClientes) {
            if (cliente.getCpf() == cpfRemover) {
                clientes.removerCliente(cliente);
                return; 
            }
        }
        System.out.println("Cliente não encontrado.");
    }catch (NoSuchElementException e) {
        System.out.println("Entrada inválida, tente novamente.");
        e.printStackTrace();
    } finally {
        
    }
}
    
    @Override
    public Clientes encontrarClientePorId(int id_cliente) {
        Clientes cliente = new Clientes(0,"",0,0);
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
    
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
    
            statement.setInt(1, id_cliente);
            ResultSet resultSet = statement.executeQuery();
    
        if (resultSet.next()) {
            cliente = new Clientes(
                resultSet.getInt("id_cliente"),
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

        Clientes clienteInstancia = new Clientes(0, "", 0, 0); 
        Clientes clienteEncontrado = clienteInstancia.encontrarClientePorId(id_cliente);

        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("Telefone: " + clienteEncontrado.getTelefone());
        } else {
            System.out.println("Cliente não encontrado.");
        } 
    }
}