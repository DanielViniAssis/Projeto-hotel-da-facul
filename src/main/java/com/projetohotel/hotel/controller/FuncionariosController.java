package com.projetohotel.hotel.controller;

import com.projetohotel.hotel.repository.FuncionariosRepository;
import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.model.Funcionarios;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class FuncionariosController {
    private final FuncionariosRepository funcionariosRepository;

    public FuncionariosController(FuncionariosRepository funcionariosRepository){
        this.funcionariosRepository = funcionariosRepository;
    }

    public void adicionarFuncionario() {
        Scanner leitura = new Scanner(System.in);
        try {
            System.out.println("Digite o seu nome: ");
            String nome = leitura.nextLine();
            
            System.out.println("Digite o seu cargo: ");
            String cargo = leitura.next();

            Funcionarios novoFuncionario = new Funcionarios(0, nome, cargo );
            funcionariosRepository.adicionarFuncionario(novoFuncionario);

        } catch (NoSuchElementException e) {
            System.out.println("Entrada inválida, tente novamente.");
            e.printStackTrace();}
    }

    public void listarTodosFuncionarios(){
        Funcionarios funcionarios = new Funcionarios(0, "", "");
        List<Funcionarios> listaFuncionarios = funcionariosRepository.listarTodosFuncionarios();
        for (Funcionarios funcionario : listaFuncionarios) {
            System.out.println("Id: " + funcionario.getIdFuncionario());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Cargo: " + funcionario.getCargo());
            System.out.println("-------------------------");
        }
    }

    public void encontrarFuncionarioPorNome() {
       Scanner leitura = new Scanner(System.in);
       System.out.println("Insira o nome do funcionario que deseja encontrar: ");
       String nomeFuncionario = leitura.nextLine(); 
       Funcionarios funcionarioEncontrado = funcionariosRepository.encontrarFuncionarioPorNome(nomeFuncionario);
       
       if (funcionarioEncontrado != null){
        System.out.println("Funcionario Encontrado:");
        System.out.println("ID: " + funcionarioEncontrado.getIdFuncionario());
        System.out.println("Nome: " + funcionarioEncontrado.getNome());
        System.out.println("Cargo: " + funcionarioEncontrado.getCargo());
       }else{
        System.out.println("Funcionario não encontrado");
       }
    }

    public void removerFuncionario() {
        Scanner leitura = new Scanner(System.in);
        try {
            System.out.println("-------- Remover Funcionario --------");
            System.out.println("Digite o nome do Funcionario que deseja excluir: ");
            String nomeFuncionario = leitura.nextLine();

          Funcionarios funcionario =  funcionariosRepository.encontrarFuncionarioPorNome(nomeFuncionario);
          if(funcionario != null){
            funcionariosRepository.removerFuncionario(funcionario);
          }else{
            System.out.println("Funcionario não encontrado!");
          }
        }catch (NoSuchElementException e) {
        System.out.println("Entrada inválida, tente novamente.");
        e.printStackTrace();
        } 
    }
    
}

