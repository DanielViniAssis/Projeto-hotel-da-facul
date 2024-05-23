package com.projetohotel.hotel.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.model.Funcionarios;

public class FuncionariosController {

    public void  incluirFuncionario() {
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

    public void listarTodosClientes(){
        Clientes clientes = new Clientes(0, "", 0, 0);
        List<Clientes> listaClientes = clientesRepository.listarTodosClientes();
        for (Clientes cliente : listaClientes) {
            System.out.println("Id: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("-------------------------");
        }
    }

}
