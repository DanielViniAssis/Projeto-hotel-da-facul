package com.projetohotel.hotel.view;

import com.projetohotel.hotel.controller.ClientesController;
import com.projetohotel.hotel.controller.FuncionariosController;
import com.projetohotel.hotel.controller.QuartosController;
import com.projetohotel.hotel.controller.ReservaController;
import com.projetohotel.hotel.repository.ClientesRepository;
import com.projetohotel.hotel.repository.FuncionariosRepository;
import com.projetohotel.hotel.repository.QuartosRepository;
import com.projetohotel.hotel.repository.ReservaRepository;
import java.util.Scanner;

public class HotelMenu {

    public static void main(String[] args) {

        ClientesRepository clientesRepository = new ClientesRepository();
        FuncionariosRepository funcionariosRepository = new FuncionariosRepository();
        QuartosRepository quartosRepository = new QuartosRepository();
        ReservaRepository reservaRepository = new ReservaRepository();

        ClientesController clientesController = new ClientesController(clientesRepository);
        FuncionariosController funcionariosController = new FuncionariosController(funcionariosRepository);
        QuartosController quartosController = new QuartosController(quartosRepository);
        ReservaController reservaController = new ReservaController(reservaRepository, quartosController, clientesController, funcionariosController);

        Scanner leitura = new Scanner(System.in);
        int opcaoMenu;

        do {
            System.out.println("-----------Hotel------------");
            System.out.println("1. Opções de Cliente");
            System.out.println("2. Opções dos Quartos");
            System.out.println("3. Opções dos Funcionarios");
            System.out.println("4. Opções das Reservas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            while (!leitura.hasNextInt()) {
                System.out.println("Opção inválida. Tente novamente.");
                leitura.next();
            }
            opcaoMenu = leitura.nextInt();
            leitura.nextLine();
            switch (opcaoMenu) {
                case 1:
                    menuCliente(leitura, clientesController);
                    break;
                case 2:
                    menuQuarto(leitura, quartosController);
                    break;
                case 3:
                    areaFuncionario(funcionariosController, reservaController, leitura);
                    break;
                case 4:
                    menuReservas(reservaController, leitura);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoMenu != 5);
        leitura.close();
    }

    public static void areaFuncionario(FuncionariosController funcionariosController, ReservaController reservaController, Scanner leitura) {
        int opcaoFuncionario;
        do {
            System.out.println("-----------Área do funcionario------------");
            System.out.println("1. Login funcionario");
            System.out.println("2. Listar todos os funcionario");
            System.out.println("3. Buscar funcionario");
            System.out.println("4. Remover funcionario");
            System.out.println("5. Entrar no menu de reservas");
            System.out.println("6. Voltar um menu");
            System.out.println("Escolha a opção: ");

            opcaoFuncionario = leitura.nextInt();
            leitura.nextLine();
            switch (opcaoFuncionario) {
                case 1:
                    funcionariosController.adicionarFuncionario();
                    break;
                case 2:
                    funcionariosController.listarTodosFuncionarios();
                    break;
                case 3:
                    funcionariosController.encontrarFuncionarioPorNome();
                    break;
                case 4:
                    funcionariosController.removerFuncionario();
                    break;
                case 5:
                    menuReservas(reservaController, leitura);
                    break;
            }
        } while (opcaoFuncionario != 6);
    }

    public static void menuCliente(Scanner leitura, ClientesController clientesController) {
        int opcaoCliente;
        do {
            System.out.println("-------------Menu Cliente-------------");
            System.out.println("1. Listar Todos os Clientes");
            System.out.println("2. Remover Cliente");
            System.out.println("3. Encontrar Cliente por ID");
            System.out.println("4. Voltar para o menu do hotel");
            System.out.print("Escolha uma opção: ");

            opcaoCliente = leitura.nextInt();
            leitura.nextLine();

            switch (opcaoCliente) {
                case 1:
                    clientesController.listarTodosClientes();
                    break;
                case 2:
                    clientesController.removerCliente();
                    break;
                case 3:
                    clientesController.encontrarClientePorCpf();
                    break;
            }
        } while (opcaoCliente != 4);
    }

    public static void menuReservas(ReservaController reservaController, Scanner leitura) {
        int opcaoReserva;
        do {
            System.out.println("----------Menu Reservas----------");
            System.out.println("1. Fazer Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("3. Voltar para o menu do hotel");

            opcaoReserva = leitura.nextInt();
            leitura.nextLine();

            switch (opcaoReserva) {
                case 1:
                    reservaController.reservaFeita();
                    break;
                case 2:
                    reservaController.listagem();
                    break;
            }
        } while (opcaoReserva != 3);
    }

    public static void menuQuarto(Scanner leitura, QuartosController quartosController) {
        int opcaoCliente;
        do {
            System.out.println("-------------Menu Quartos-------------");
            System.out.println("1. Listar Todos os Quartos");
            System.out.println("2. Remover Quarto");
            System.out.println("3. Encontrar Quarto por numero");
            System.out.println("4. Voltar para o menu do hotel");
            System.out.print("Escolha uma opção: ");

            opcaoCliente = leitura.nextInt();
            leitura.nextLine();

            switch (opcaoCliente) {
                case 1:
                    quartosController.listarTodosQuartos();
                    break;
                case 2:
                    quartosController.removerQuarto();
                    break;
                case 3:
                    quartosController.encontrarQuartoPorNumero();
                    break;
            }
        } while (opcaoCliente != 4);
    }

}
