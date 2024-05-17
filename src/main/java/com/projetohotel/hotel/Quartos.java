package com.projetohotel.hotel;

import java.util.List;

import com.projetohotel.hotel.InterfaceHotel.InterfaceQuartos;

public class Quartos implements InterfaceQuartos{
    public String tipo_quarto;
    public String status_quarto;
    
    public Quartos(String tipo, String status){
        this.tipo_quarto = tipo;
        this.status_quarto = status;
    }
    // getters
    public String getTipo(){
        return tipo_quarto;
    }
    
    public String getStatus(){
        return status_quarto;
    }
    
    //set para editar dados
    public void setTipo(String tipo){
        this.tipo_quarto = tipo;
    }

    public void setStatus(String status){
        this.status_quarto = status;
    }
    @Override
    public void adicionarQuarto(Quartos quarto) {
        throw new UnsupportedOperationException("Unimplemented method 'adicionarQuarto'");
    }
    @Override
    public List<Quartos> listarTodosQuartos() {
        throw new UnsupportedOperationException("Unimplemented method 'listarTodosQuartos'");
    }
    @Override
    public Quartos encontrarQuarto(int numeroQuarto) {
        throw new UnsupportedOperationException("Unimplemented method 'encontrarQuarto'");
    }

}
