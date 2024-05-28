    package com.projetohotel.hotel.model;

    public class Quartos{
        public int idQuarto;
        public String tipoQuarto;
        public int numeroQuarto;
        public String statusQuarto;
        
        public Quartos(int idQuarto, String tipo, int numeroQuarto, String status){
            this.idQuarto = idQuarto;
            this.numeroQuarto = numeroQuarto;
            this.tipoQuarto = tipo;
            this.statusQuarto = status;
        }
        // getters
        public int getIdQuarto(){
            return idQuarto;
        }
        
        public String getTipo(){
            return tipoQuarto;
        }
        
        public int getNumeroQuarto(){
            return numeroQuarto;
        }

        public String getStatus(){
            return statusQuarto;
        }
        
        //set para editar dados
        public void setIdQuarto(int idQuarto){
            this.idQuarto = idQuarto;
        }

        public void setTipo(String tipo){
            this.tipoQuarto = tipo;
        }

        public void setNumeroQuarto(int numeroQuarto){
            this.numeroQuarto = numeroQuarto;
        }

        public void setStatus(String status){
            this.statusQuarto = status;
        }


    }
