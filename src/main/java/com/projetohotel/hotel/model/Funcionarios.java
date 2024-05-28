package com.projetohotel.hotel.model;

public class Funcionarios {
    private int idFuncionario;
    private String nomeFuncionario;
    private String cargo;

    public Funcionarios() {
    }

    public Funcionarios(int idFuncionario, String nomeFuncionario, String cargo) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
    }

	// Getters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    // Setters
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setNome(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
