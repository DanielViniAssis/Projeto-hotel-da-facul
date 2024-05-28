package com.projetohotel.hotel.view;

import java.util.List;

import com.projetohotel.hotel.model.Clientes;
import com.projetohotel.hotel.model.Funcionarios;
import com.projetohotel.hotel.model.Quartos;

public interface InterfaceHotel {

     interface Interfacefuncionarios {
        // funções para o funcionario
         void removerFuncionario(Funcionarios funcionario);
         void adicionarFuncionario(Funcionarios funcionario);
        List<Funcionarios> listarTodosFuncionarios();
        Funcionarios encontrarFuncionarioPorNome(String nomeFuncionario);
    }

     //funções para o cliente
     interface InterfaceClientes {
         void adicionarCliente(Clientes cliente);
         void removerCliente(Clientes cliente);
        List<Clientes> listarTodosClientes();
        Clientes encontrarClientePorCpf(int cpf);
    }
     interface InterfaceQuartos {
        //funções para os quartos
         void adicionarTipoQuarto(Quartos quarto);
        List<Quartos> listarTodosQuartos();
        Quartos encontrarQuartoPorNumero(int numeroQuarto);
    }
}