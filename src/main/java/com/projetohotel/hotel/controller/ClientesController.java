package com.projetohotel.hotel.controller;

import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.repository.ClientesRepository;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class ClientesController {
    private final ClientesRepository clientesRepository;

    public ClientesController(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
     }

    public  void adicionarCliente() {
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
            
            Clientes novoCliente = new Clientes(0, nome, cpf, telefone);
            clientesRepository.adicionarCliente(novoCliente);

        }catch (NoSuchElementException e) {
            System.out.println("Entrada inválida, tente novamente.");
            e.printStackTrace();
        }
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

    public void removerCliente() {
        Scanner leitura = new Scanner(System.in);
        try {
            System.out.println("-------- Remover Cliente --------");
            System.out.println("Digite o CPF do cliente que deseja excluir: ");
            int cpf = leitura.nextInt();

          Clientes cliente =  clientesRepository.encontrarClientePorCpf(cpf);
          if(cliente != null){
            clientesRepository.removerCliente(cliente);
            System.out.println("Cliente removido com sucesso!!");
          }else{
            System.out.println("Cliente não encontrado!");
          }
        }catch (NoSuchElementException e) {
        System.out.println("Entrada inválida, tente novamente.");
        e.printStackTrace();
        } 
    }
    
    public void encontrarClientePorCpf() {
       Scanner leitura = new Scanner(System.in);
       System.out.println("Insira o CPF do cliente que deseja encontrar: ");
       int cpf = leitura.nextInt(); 
       Clientes clienteEncontrado = clientesRepository.encontrarClientePorCpf(cpf);
       
       if (clienteEncontrado != null){
        System.out.println("Cliente Encontrado:");
        System.out.println("ID: " + clienteEncontrado.getIdCliente());
        System.out.println("Nome: " + clienteEncontrado.getNome());
        System.out.println("CPF: " + clienteEncontrado.getCpf());
        System.out.println("Telefone: " + clienteEncontrado.getTelefone());
       }else{
        System.out.println("Cliente não encontrado");
       }
    }
}