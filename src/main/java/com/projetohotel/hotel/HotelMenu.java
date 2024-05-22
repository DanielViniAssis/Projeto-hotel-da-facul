package com.projetohotel.hotel;

import java.util.Scanner;

public class HotelMenu {
    
    public static void areaFuncionario(){
        System.out.println("-----------Área do funcionario------------");
        Funcionarios.incluirFuncionario();
    }
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        Clientes clientesInstancia = new Clientes(0, "", 0, 0);
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
                    menuCliente(leitura, clientesInstancia);
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

    public static void menuCliente(Scanner leitura, Clientes clientesInstancia) {
        int opcaoCliente = 0;  
        do {
            System.out.println("Menu do Cliente");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Todos os Clientes");
            System.out.println("3. Remover Cliente");
            System.out.println("4. Encontrar Cliente por ID");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
        
            opcaoCliente = leitura.nextInt(); 
            
                
                switch (opcaoCliente) {
                    case 1:
                        Clientes cliente = Clientes.cadastroCliente();
                        if (cliente != null) {
                            cliente.adicionarCliente(cliente);
                        }
                        break;
                    case 2:
                        Clientes.listagem();
                        continue;
                    case 3:
                        Clientes.clienteRemovido();
                        break;
                    case 4:
                        Clientes.clienteEncontrado();
                }
            } while (opcaoCliente != 5);
            leitura.close();
        }
    }
