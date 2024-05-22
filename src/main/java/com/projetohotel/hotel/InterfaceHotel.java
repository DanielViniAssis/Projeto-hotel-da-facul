package com.projetohotel.hotel;

import java.util.List;

public interface InterfaceHotel {
     interface Interfacefuncionarios {
        // funções para o funcionario
         void removerFuncionario(Funcionarios funcionario);
         void adicionarFuncionario(Funcionarios funcionario);
        List<Funcionarios> listarTodosFuncionarios();
        Funcionarios encontrarFuncionarioPorId(int id_funcionario);
    }

     //funções para o cliente
     interface InterfaceClientes {
         void adicionarCliente(Clientes cliente);
         void removerCliente(Clientes cliente);
        List<Clientes> listarTodosClientes();
        Clientes encontrarClientePorId(int id_cliente);
    }
     interface InterfaceQuartos {
        //funções para os quartos
         void adicionarQuarto(Quartos quarto);
        List<Quartos> listarTodosQuartos();
        Quartos encontrarQuarto(int numeroQuarto);
    }
}