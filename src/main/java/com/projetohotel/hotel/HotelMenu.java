package com.projetohotel.hotel;

import java.util.List;
import java.util.Scanner;

public class HotelMenu {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        Clientes clientesInstancia = new Clientes("", 0, 0);
        int opcaoMenu;

        do {
            System.out.println("-----------Hotel------------");
            System.out.println("1. Opções de Cliente");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
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
        int opcaoCliente;
        do {
            System.out.println("Menu do Cliente");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Todos os Clientes");
            System.out.println("3. Remover Cliente");
            System.out.println("4. Encontrar Cliente por ID");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoCliente = leitura.nextInt();
            leitura.nextLine();

            switch (opcaoCliente) {
                case 1:
                    Clientes novoCliente = Clientes.CadastroCliente();
                    clientesInstancia.adicionarCliente(novoCliente);
                    leitura.nextLine();
                    break;
                case 2:
                    List<Clientes> listaClientes = clientesInstancia.listarTodosClientes();
                    for (Clientes cliente : listaClientes) {
                        System.out.println("Nome: " + cliente.getNome());
                        System.out.println("CPF: " + cliente.getCpf());
                        System.out.println("Telefone: " + cliente.getTelefone());
                        System.out.println("-------------------------");
                    }
                    break;
                case 3:
                    System.out.print("Digite o CPF do cliente a ser removido: ");
                    int cpfRemover = leitura.nextInt();
                    leitura.nextLine();
                    Clientes clienteARemover = null;
                    for (Clientes cliente : clientesInstancia.listarTodosClientes()) {
                        if (cliente.getCpf() == cpfRemover) {
                            clienteARemover = cliente;
                            break;
                        }
                    }
                    if (clienteARemover != null) {
                        clientesInstancia.removerCliente(clienteARemover);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do cliente que deseja encontrar: ");
                    int id_cliente = leitura.nextInt();
                    leitura.nextLine();
                    Clientes clienteEncontrado = clientesInstancia.encontrarClientePorId(id_cliente);
                    if (clienteEncontrado != null) {
                        System.out.println("Cliente encontrado:");
                        System.out.println("Nome: " + clienteEncontrado.getNome());
                        System.out.println("CPF: " + clienteEncontrado.getCpf());
                        System.out.println("Telefone: " + clienteEncontrado.getTelefone());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoCliente != 5);
        leitura.nextLine();
    }
}
