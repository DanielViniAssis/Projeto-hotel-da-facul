package com.projetohotel.hotel.repository;

import com.projetohotel.hotel.config.DatabaseConnection;
import com.projetohotel.hotel.model.Funcionarios;
import com.projetohotel.hotel.view.InterfaceHotel.Interfacefuncionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FuncionariosRepository implements Interfacefuncionarios {

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
        String sql = "SELECT id_funcionario, nome_funcionario, cargo FROM funcionarios";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Funcionarios funcionario = new Funcionarios(
                resultSet.getInt("id_funcionario"),    
                resultSet.getString("nome_funcionario"),
                resultSet.getString("cargo")
            );
            listaFuncioanrios.add(funcionario); 
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } if ( listaFuncioanrios.isEmpty()){
            System.out.println("Não existe nenhum cliente cadastrado no sistema!!");
        }
        return listaFuncioanrios;
        
    }
    
    @Override
    public Funcionarios encontrarFuncionarioPorNome(String nomeFuncionario) {
        Funcionarios funcionario = null;
        String sql = "SELECT * FROM funcionarios WHERE nome_funcionario = ?";
        
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
            
            statement.setString(1, nomeFuncionario);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                funcionario = new Funcionarios(
                    resultSet.getInt("id_funcionario"),
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
    String sql = "DELETE FROM funcionarios WHERE nome_funcionario = ?";
    
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {

                statement.setString(1, funcionario.getNome());
        
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
