package com.projetohotel.hotel.controller;

import java.util.List;
import java.util.Scanner;

import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.model.Funcionarios;
import com.projetohotel.hotel.model.Quartos;
import com.projetohotel.hotel.model.Reserva;
import com.projetohotel.hotel.repository.ClientesRepository;
import com.projetohotel.hotel.repository.FuncionariosRepository;
import com.projetohotel.hotel.repository.QuartosRepository;
import com.projetohotel.hotel.repository.ReservaRepository;

public class ReservaController {
    private final ReservaRepository reservaRepository;
    private final QuartosController quartosController;
    private final ClientesController clientesController;
    private final FuncionariosController funcionariosController;

    public ReservaController(ReservaRepository reservaRepository, QuartosController quartosController, ClientesController clientesController, FuncionariosController funcionariosController){
        this.reservaRepository = reservaRepository;
        this.quartosController = quartosController;
        this.clientesController = clientesController;
        this.funcionariosController = funcionariosController;
    }

    public void listagem() {
        List<Reserva> listaDasReservas = reservaRepository.listarTodasReservas();
        for (Reserva r : listaDasReservas) {
            System.out.println("Id da Reserva: " + r.getIdReserva());
            System.out.println("Cliente: " + r.getCliente().getNome());
            System.out.println("CPF: " + r.getCliente().getCpf());
            System.out.println("Telefone: " + r.getCliente().getTelefone());
            System.out.println("Quarto: " + r.getQuarto().getTipo() + " - " + r.getQuarto().getNumeroQuarto() + " - " + r.getQuarto().getStatus());
            System.out.println("Data de Entrada: " + r.getDataEntrada());
            System.out.println("Data de Saída: " + r.getDataSaida());
            System.out.println("Funcionário: " + r.getNomeFuncionario());
            System.out.println("Status: " + r.getStatusReserva());
            System.out.println("-------------------------");
        }
    }

    public void reservaFeita() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("---------------Área da reserva-------------");
        // Cadastro cliente
        System.out.println("Cadastro cliente");
        Clientes novoCliente = clientesController.adicionarCliente();
        if (novoCliente == null) {
            System.err.println("Erro ao cadastrar cliente.");
            return;
        }
        
        QuartosRepository.quartoEscolhido();
        // Reserva do quarto
        System.out.println("Reserva do quarto");
        Quartos novoQuarto = quartosController.adicionarTipoQuarto();
        
        // Informação funcionário
        System.out.println("Informação funcionario");
        Funcionarios novoFuncionario = funcionariosController.adicionarFuncionario();
        
        // Detalhes da reserva
        System.out.println("Insira a data de entrada: ");
        String dataEntrada = leitura.nextLine();

        System.out.println("Insira a data de saida: ");
        String dataSaida = leitura.nextLine();

        System.out.println("Insira o status da reserva: ");
        String statusReserva = leitura.nextLine();

        Reserva novaReserva = new Reserva(
            0, novoCliente, novoQuarto, dataEntrada, dataSaida, novoFuncionario, novoFuncionario, statusReserva
        ); novaReserva.setCliente(novoCliente);
            reservaRepository.adicionarReserva(novaReserva, novoFuncionario, novoCliente, novoQuarto);
        
    }
    
}