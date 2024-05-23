package com.projetohotel.hotel.model;

public class Funcionarios{
    private int idFuncionario;
    private String nomeFuncionario;
    private String cargo;
    
    public Funcionarios(int idFuncionario, String nomeFuncionario, String cargo){
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
    }
// getters
    public int getIdFuncionario(){
        return idFuncionario;
    }
    public String getNome(){
        return nomeFuncionario;
    }
    
    public String getCargo(){
        return cargo;
    }
    
//set para editar dados
    public void setIdFuncionario(int idFuncionario){
        this.idFuncionario = idFuncionario;
    }
    public void setNome(String nomeFuncionario){
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setCargo(String cargo){
        this.cargo = cargo;
    }
}
