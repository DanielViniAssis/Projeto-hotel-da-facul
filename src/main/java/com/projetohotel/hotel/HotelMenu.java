package com.projetohotel.hotel;

import java.util.Scanner;

public class HotelMenu {
    
    public static void areaFuncionario(){
        Scanner leitura = new Scanner(System.in);
        int opcaoFuncionario;
        do{
            System.out.println("-----------Área do funcionario------------");
            System.out.println("1. Login funcionario");
            System.out.println("2. Entrar no menu de reservas");
            System.out.println("3. Entrar no menu do hotel");
            System.out.println("Escolha a opção: ");
            
            opcaoFuncionario = leitura.nextInt();
            leitura.nextLine();
            switch (opcaoFuncionario){
                case 1: 
                    Funcionarios funcionario = Funcionarios.incluirFuncionario();
                        if (funcionario != null) {
                            funcionario.adicionarFuncionario(funcionario);
                        }
                    break;
                case 2:
                    menuReservas(leitura);
                    break;
                case 3:
                    break;
            }
        } while (opcaoFuncionario != 3);
    }
    public static void main(String[] args) {
        areaFuncionario();

        Scanner leitura = new Scanner(System.in);
        int opcaoMenu;
        
        do {
            System.out.println("-----------Hotel------------");
            System.out.println("1. Opções de Cliente");
            System.out.println("2. Adicionar um funcionario");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            while (!leitura.hasNextInt()) {
                System.out.println("Opção inválida. Tente novamente.");
                leitura.next();
            }
            opcaoMenu = leitura.nextInt();
            leitura.nextLine();
            switch (opcaoMenu) {
                case 1:
                    menuCliente(leitura);
                    break;
                case 2:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
                }
            } while (opcaoMenu != 2);
        leitura.close();
    }

    public static void menuCliente(Scanner leitura) {
        int opcaoCliente = 0;  
        do {
            System.out.println("-------------Menu Cliente-------------");
            System.out.println("1. Listar Todos os Clientes");
            System.out.println("2. Remover Cliente");
            System.out.println("3. Encontrar Cliente por ID");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
        
            opcaoCliente = leitura.nextInt(); 
            
                
                switch (opcaoCliente) {
                    case 1:
                        Clientes.listagem();
                        continue;
                    case 2:
                        Clientes.clienteRemovido();
                        break;
                    case 3:
                        Clientes.clienteEncontrado();
                }
            } while (opcaoCliente != 4);
            leitura.close();
        }

    public static void menuReservas(Scanner leitura){
        int opcaoRerseva;
        
        do{
            System.out.println("----------Menu Reservas----------");
            System.out.println("1. Cadastrar Cliente");
           
            opcaoRerseva = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcaoRerseva) {
                case 1:
                    Clientes cliente = Clientes.cadastroCliente();
                    if (cliente != null) {
                        cliente.adicionarCliente(cliente);
                    }
                    break;
                
                }
        }while (opcaoRerseva != 5);
            leitura.close();
    }
}
