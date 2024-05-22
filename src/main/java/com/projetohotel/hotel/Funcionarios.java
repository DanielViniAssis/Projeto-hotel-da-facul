package com.projetohotel.hotel;

import com.projetohotel.hotel.InterfaceHotel.Interfacefuncionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Funcionarios implements Interfacefuncionarios{
        private String nome_funcionario;
        private String cargo;
        
        public Funcionarios(String nome, String cargo){
            this.nome_funcionario = nome;
            this.cargo = cargo;
        }
    // getters
        public String getNome(){
            return nome_funcionario;
        }
        
        public String getCargo(){
            return cargo;
        }
        
    //set para editar dados
        public void setNome(String nome){
            this.nome_funcionario = nome;
        }
    
        public void setCargo(String cargo){
            this.cargo = cargo;
        }
    
    //funções da interface
        
        public static Funcionarios incluirFuncionario() {
            Scanner leitura = new Scanner(System.in);
            try {
                System.out.println("Digite o seu nome: ");
                String nome = leitura.nextLine();
                
                System.out.println("Digite o seu cargo: ");
                String cargo = leitura.next();
      
                return new Funcionarios(nome, cargo);
            } catch (NoSuchElementException e) {
                System.out.println("Entrada inválida, tente novamente.");
                e.printStackTrace();}
                finally {
                }
            return null;
        }
        
        @Override
        public void adicionarFuncionario(Funcionarios funcionario) {
            String sql = "INSERT INTO funcionarios (nome_funcionario, cargo) VALUES (?, ?)";
            
            try (Connection conexao = DatabaseConnection.getConnection();
                 PreparedStatement statement = conexao.prepareStatement(sql)) {
        
                statement.setString(1, funcionario.getNome());
                statement.setString(2, funcionario.getCargo());
        
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Funcionario cadastrado com sucesso!");
                } else {
                    System.out.println("Falha ao cadastrar o funcionario.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        @Override
        public List<Funcionarios> listarTodosFuncionarios() {
            List<Funcionarios> listaFuncioanrios = new ArrayList<>();
            String sql = "SELECT * FROM funcionarios";
    
            try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
    
                while (resultSet.next()) {
                    Funcionarios funcionario = new Funcionarios(
                    resultSet.getString("nome_funcionario"),
                    resultSet.getString("cargo")
                );
                listaFuncioanrios.add(funcionario); 
                }
            }catch (SQLException e) {
                e.printStackTrace();
            } return listaFuncioanrios;
        }
        
        @Override
        public  Funcionarios encontrarFuncionarioPorId(int id_funcionario) {
            Funcionarios funcionario = null;
            String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";
            
            try (Connection conexao = DatabaseConnection.getConnection();
                 PreparedStatement statement = conexao.prepareStatement(sql)) {
                
                statement.setInt(1, id_funcionario);
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    funcionario = new Funcionarios(
                        resultSet.getString("nome_funcionario"),
                        resultSet.getString("cargo")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return funcionario;
        }
    
        @Override
        public void removerFuncionario(Funcionarios funcionario) {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario = ? AND nome_funcionario = ? AND cargo = ?";
        
        try (Connection conexao = DatabaseConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {
    
                statement.setString(1, funcionario.getNome());
                statement.setString(2, funcionario.getCargo());
        
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Funcionario removido com sucesso!");
            } else {
                System.out.println("Falha ao remover o Funcionario ou Funcionario não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao tentar remover Funcionario: ");
            e.printStackTrace();
        }
    }
}
