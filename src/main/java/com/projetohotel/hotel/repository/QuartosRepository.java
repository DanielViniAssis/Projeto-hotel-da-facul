package com.projetohotel.hotel.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projetohotel.hotel.config.DatabaseConnection;
import com.projetohotel.hotel.view.InterfaceHotel.InterfaceQuartos;
import com.projetohotel.hotel.model.Quartos;

public class QuartosRepository implements InterfaceQuartos{

    @Override
    public void adicionarTipoQuarto(Quartos quarto) {
        String sql = "INSERT INTO quartos (tipo_quarto, numero_quarto, status_quarto) VALUES (?, ?, ?)";
        
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, quarto.getTipo());
            statement.setInt(2, quarto.getNumeroQuarto());
            statement.setString(3, quarto.getStatus());
    
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quarto reservado com sucesso!");
            } else {
                System.out.println("Falha ao reservar o quarto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean verificarNumeroQuartoExistente(int numeroQuarto) {
        String sql = "SELECT COUNT(*) FROM quartos WHERE numero_quarto = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, numeroQuarto);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        return false;
    }
    
    @Override
    public List<Quartos> listarTodosQuartos(){
        List<Quartos> listaQuartos = new ArrayList<>();
        String sql = "SELECT id_quarto, tipo_quarto, numero_quarto, status_quarto FROM quartos";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Quartos quarto = new Quartos(
                resultSet.getInt("id_quarto"),
                resultSet.getString("tipo_quarto"),
                resultSet.getInt("numero_quarto"),    
                resultSet.getString("status_quarto")
            );
            listaQuartos.add(quarto); 
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } if ( listaQuartos.isEmpty()){
            System.out.println("Não existe nenhum quarto cadastrado no sistema!!");
        }
        return listaQuartos;
        
    }
    @Override
    public Quartos encontrarQuartoPorNumero(int numeroQuarto){
        Quartos quarto = null;
        String sql = "SELECT * FROM quartos WHERE numero_quarto = ?";
    
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
    
            statement.setInt(1, numeroQuarto);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    quarto = new Quartos(
                        resultSet.getInt("id_quarto"),
                        resultSet.getString("tipo_quarto"),
                        resultSet.getInt("numero_quarto"),
                        resultSet.getString("status_quarto")
                        );
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return quarto;
    }
    @Override
    public void removerQuartos(Quartos quarto){
        String sql = "DELETE FROM quartos WHERE numero_quarto = ?";
        
        try (Connection conexao = DatabaseConnection.getConnection();
            PreparedStatement statement = conexao.prepareStatement(sql)) {
    
                statement.setInt(1, quarto.getNumeroQuarto());
        
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quarto removido com sucesso!");
            } else {
                System.out.println("Falha ao remover o quarto ou quarto não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao tentar remover quarto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void quartoEscolhido(){
        List<String>quartoEscolhido = new ArrayList<>();
        quartoEscolhido.add("Tipo do Quarto: casal - Quarto Numero: 01");
        quartoEscolhido.add("\n Tipo do Quarto: solteiro - Quarto Numero: 02");
        quartoEscolhido.add("\n Tipo do Quarto: duplo de solteiro - Quarto Numero: 03");
        quartoEscolhido.add("\n Tipo do Quarto: deluxe casal - Quarto Numero: 04");
        quartoEscolhido.add("\n Tipo do Quarto: deluxe solteiro - Quarto Numero: 05");
        System.out.println(quartoEscolhido);
    }
}
