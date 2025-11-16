    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.barbearia.modelos;

    import com.fasterxml.jackson.annotation.JsonIdentityInfo;
    import com.fasterxml.jackson.annotation.ObjectIdGenerators;
    import com.mycompany.Utilidades.TipoEstacao;
    import java.util.UUID;

    /**
     * Representa um Servico no Sistema. A duração é medida em slots que duram 30 minutos. EX: 2 slots 1 hora
     * @author intalo
     */
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public class Servico extends Modelo{
        private double preco;
        private String descricao;
        private  int tempoSlots;
        private TipoEstacao tipoEstacaoRequerido;

        /**
           * Construtor completo de Servico
           * @param nome
           * @param preco
           * @param descricao
           * @param temp
           * @param tipoRequerido
           */
        public Servico(String nome, double preco, String descricao, int temp, TipoEstacao tipoRequerido){
        super(nome);

            if (nome == null || nome.trim().isEmpty()){
                throw new IllegalArgumentException("O nome não pode ser nulo");
            }

            if(preco <= 0) {
                throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
            }

            if(temp <= 0){
                throw new IllegalArgumentException("O tempo deve ser maior que 0");
            }

            this.preco = preco;
            this.descricao = descricao;
            this.tempoSlots = temp;
            this.tipoEstacaoRequerido = tipoRequerido;
        }

        /**
          * Construtor sem parametros
          */
        public Servico(){}

        /**
         * Obtem o preco
         * @return
         */
        public double getPreco() {
            return preco;
        }

        /**
         * Obtem o tipo de estacao do servico
         * @return
         */
        public TipoEstacao getTipoEstacaoRequerido() {
                return tipoEstacaoRequerido;
            }

        /**
         * Define o tipo de estacao do servico
         * @param tipoEstacaoRequerido
         */
        public void setTipoEstacaoRequerido(TipoEstacao tipoEstacaoRequerido) {
                this.tipoEstacaoRequerido = tipoEstacaoRequerido;
            }

        /**
         * Obtem a descricao do servico
         * @return
         */
        public String getDescricao() {
                return descricao;
            }

        /**
         * Define a descricao do servico
         * @param descricao
         */
        public void setDescricao(String descricao) {
                this.descricao = descricao;
            }

        /**
         * Obtem o tempo do servico em minutos
         * @return
         */
        public int getTempoEmMinutos() {
                return tempoSlots * 10;
            }

        /**
         * Define o tempo do servico em minutos
         * @param temp
         */
        public void setTempoEmMinutos(int temp) {
                if(temp <= 0){
                    throw new IllegalArgumentException("O tempo definido é invalido!");
                }        
                this.tempoSlots = temp;
        }

        /**
         *
         * @param preco
         */
        public void setPreco(double preco){
            if(preco <= 0) {
                throw new IllegalArgumentException("O valor não pode ser 0 ou negativo");
            }
            this.preco = preco;
        }       

       /**
         * Gera o ID
         * @return
         */
        @Override
        public String gerarId() {
            return "SE-" + UUID.randomUUID().toString().substring(0, 10);
        }

        /**
         * Obtem a representacao em String de um servico
         * @return
         */
        @Override
        public String toString() {
            return String.format("%n%sPreco: %s", super.toString(),preco);
        }
    }