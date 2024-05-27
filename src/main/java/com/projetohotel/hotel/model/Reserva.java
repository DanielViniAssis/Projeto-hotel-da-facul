package com.projetohotel.hotel.model;

public class Reserva {
    private int idReserva;
    private Clientes cliente;
    private Quartos quarto;
    private String dataEntrada;
    private String dataSaida;
    private Funcionarios idFuncionario;
    private Funcionarios nomeFuncionario;
    private String status;

    public Reserva(int idReserva, Clientes cliente, Quartos quarto, String dataEntrada, String dataSaida, Funcionarios idFuncionario, Funcionarios nomeFuncionario, String status) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.idFuncionario = idFuncionario;
        this.status = status;
    }

    // Getters and setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Quartos getQuarto() {
        return quarto;
    }

    public void setQuarto(Quartos quarto) {
        this.quarto = quarto;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Funcionarios getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionarios idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Funcionarios getNomeFuncionario(){
        return nomeFuncionario;
    }

    public void setNomeFuncionario(Funcionarios nomeFuncionario){
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getStatusReserva() {
        return status;
    }
    
    public void setStatusReserva(String status) {
        this.status = status;
    }
}
