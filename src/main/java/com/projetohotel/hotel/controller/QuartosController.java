package com.projetohotel.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


import com.projetohotel.hotel.model.Quartos;
import com.projetohotel.hotel.repository.QuartosRepository;


public class QuartosController {
    private final QuartosRepository quartosRepository;

    public QuartosController(QuartosRepository quartosRepository) {
        this.quartosRepository = quartosRepository;
    }

    public Quartos adicionarTipoQuarto() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite o tipo do quarto: ");
        String tipo = leitura.nextLine();

        int numeroQuarto = 0;
        boolean numeroQuartoValido = false;
        do {
            System.out.println("Digite o número do quarto: ");
            numeroQuarto = leitura.nextInt();
            leitura.nextLine();

            if (quartosRepository.verificarNumeroQuartoExistente(numeroQuarto)) {
                System.out.println("Número do quarto já existe! Por favor, insira um número diferente.");
            } else {
                numeroQuartoValido = true;
            }
        } while (!numeroQuartoValido);

        System.out.println("Digite o status do quarto (Ocupado/Desocupado): ");
        String status = leitura.nextLine();

        Quartos novoQuarto = new Quartos(0, tipo, numeroQuarto, status);
        quartosRepository.adicionarTipoQuarto(novoQuarto);
        return novoQuarto;
    }

    public void listarTodosQuartos(){
        Quartos quarto = new Quartos (0,"",0, "");
        List<Quartos> listaQuartos = quartosRepository.listarTodosQuartos();
        for (Quartos quartos : listaQuartos) {
            System.out.println("Id: " + quartos.getIdQuarto());
            System.out.println("Tipo: " + quartos.getTipo());
            System.out.println("Numero: " + quartos.getNumeroQuarto());
            System.out.println("Status: " + quartos.getStatus());
            System.out.println("-------------------------");
        }
    }

    public void encontrarQuartoPorNumero() {
       Scanner leitura = new Scanner(System.in);
       System.out.println("Insira o numero do quarto que deseja encontrar: ");
       int numeroQuarto = leitura.nextInt(); 
       Quartos quartoEncontrado = quartosRepository.encontrarQuartoPorNumero(numeroQuarto);
       
       if (quartoEncontrado != null){
        System.out.println("Quarto Encontrado:");
        System.out.println("ID: " + quartoEncontrado.getIdQuarto());
        System.out.println("Tipo: " + quartoEncontrado.getTipo());
        System.out.println("Numero: " + quartoEncontrado.getNumeroQuarto());
        System.out.println("Status: " + quartoEncontrado.getStatus());
       }else{
        System.out.println("Quarto não encontrado");
       }
    }

    public void removerQuarto() {
        Scanner leitura = new Scanner(System.in);
        try {
            System.out.println("-------- Remover Quarto --------");
            System.out.println("Digite o numero do Quarto que deseja excluir: ");
            int numeroQuarto = leitura.nextInt();

          Quartos quarto =  quartosRepository.encontrarQuartoPorNumero(numeroQuarto);
          if(quarto != null){
            quartosRepository.removerQuarto(numeroQuarto);
          }else{
            System.out.println("Quarto não encontrado!");
          }
        }catch (NoSuchElementException e) {
        System.out.println("Entrada inválida, tente novamente.");
        e.printStackTrace();
        } 
    }
    
    
}
