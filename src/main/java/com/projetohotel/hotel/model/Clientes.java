package com.projetohotel.hotel.model;


public class Clientes  {
    private int idCliente;
    private String nome;
    private int cpf;
    private int telefone;
    
    public Clientes(int idCliente, String nome, int cpf, int telefone){
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    // getters
    public int getIdCliente(){
        return idCliente;
    }

    public String getNome(){
        return nome;
    }
    
    public int getCpf(){
        return cpf;
    }
    
    public int getTelefone(){
        return telefone;
    }
    
    //set
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(int cpf){
        this.cpf = cpf;
    }

    public void setTelefone(int telefone){
        this.telefone = telefone;
    }
   
}