package com.projetohotel.hotel.view;

import com.projetohotel.hotel.controller.ClientesController;
import com.projetohotel.hotel.controller.FuncionariosController;
import com.projetohotel.hotel.controller.QuartosController;
import com.projetohotel.hotel.repository.ClientesRepository;
import com.projetohotel.hotel.repository.FuncionariosRepository;
import com.projetohotel.hotel.repository.QuartosRepository;

import java.util.Scanner;


public class HotelMenu {
    
    public static void areaFuncionario(){
        FuncionariosController FuncionariosController = new FuncionariosController(new FuncionariosRepository());
        Scanner leitura = new Scanner(System.in);
        int opcaoFuncionario;
        do{
            System.out.println("-----------Área do funcionario------------");
            System.out.println("1. Login funcionario");
            System.out.println("2. Listar todos os funcionario");
            System.out.println("3. Buscar funcionario");
            System.out.println("4. Remover funcionario");
            System.out.println("5. Entrar no menu de reservas");
            System.out.println("6. Entrar no menu do hotel");
            System.out.println("Escolha a opção: ");
            
            opcaoFuncionario = leitura.nextInt();
            leitura.nextLine();
            switch (opcaoFuncionario){
                case 1: 
                    FuncionariosController.adicionarFuncionario();
                    break;
                case 2: 
                    FuncionariosController.listarTodosFuncionarios();
                break;
                case 3: 
                    FuncionariosController.encontrarFuncionarioPorNome();
                    break;
                case 4: 
                    FuncionariosController.removerFuncionario();
                    break;
                case 5:
                    menuReservas(leitura);
                    break;
                case 6:
                    break;
            }
        } while (opcaoFuncionario != 6);
    }
    public static void main(String[] args) {
        areaFuncionario();
        
        Scanner leitura = new Scanner(System.in);
        int opcaoMenu;
        
        do {
            System.out.println("-----------Hotel------------");
            System.out.println("1. Opções de Cliente");
            System.out.println("2. Opções dos Quartos");
            System.out.println("3. Opções dos Funcionarios");
            System.out.println("4. Opções das Reservas");
            System.out.println("5. Voltar um Menu");
            System.out.println("6. Sair");
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
                case 5:
                    System.out.println("Voltando....");
                    areaFuncionario();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
                }
            } while (opcaoMenu != 6);
        leitura.close();
    }

    public static void menuCliente(Scanner leitura) {
        ClientesController ClientesController = new ClientesController(new ClientesRepository());
        int opcaoCliente = 0;  
        do {
            System.out.println("-------------Menu Cliente-------------");
            System.out.println("1. Listar Todos os Clientes");
            System.out.println("2. Remover Cliente");
            System.out.println("3. Encontrar Cliente por ID");
            System.out.println("4. Voltar Um Menu");
            System.out.print("Escolha uma opção: ");
        
            opcaoCliente = leitura.nextInt(); 
            
                
                switch (opcaoCliente) {
                    case 1:
                        ClientesController.listarTodosClientes();
                        continue;   
                    case 2:
                        ClientesController.removerCliente();
                        break;
                    case 3:
                        ClientesController.encontrarClientePorCpf();
                }
            } while (opcaoCliente != 4);
            leitura.close();
        }

    public static void menuReservas(Scanner leitura){
        ClientesController ClientesController = new ClientesController(new ClientesRepository());
        QuartosController QuartosController = new QuartosController(new QuartosRepository());
        
        int opcaoRerseva;
        
        do{
            System.out.println("----------Menu Reservas----------");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Reservar Quarto");
           
            opcaoRerseva = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcaoRerseva) {
                case 1:
                    ClientesController.adicionarCliente();
                    break;
                case 2:
                    QuartosController.adicionarTipoQuarto();
                    break;
                case 3:
                break;
                
                }
        }while (opcaoRerseva != 5);
            leitura.close();
    }
}
