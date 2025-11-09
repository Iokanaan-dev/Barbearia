    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.barbearia.modelos;

   
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;

    /**
     * Representa uma tabela de de batidas de ponro.
     * Cada usuario esta associado a uma lista com 
     * suas batidas de entrada e saida. As entrada e 
     * saidas sao representadas pelo atributos do 
     * tipo ParBatida
     * @author intalo
     */
    public class TabelaPonto {
        private final Map<String, ArrayList<ParBatida>> tabelaPontos = new HashMap(); // A chave é o ID do Usuario (String), e as horas sao um par de objetos de entrada e saida.


        public TabelaPonto(){}

        /**
         * Retorna a lista de pares (entrada, saida) associadas a um cliente especificado pelo id
         * @param id
         * @return 
         */
        public ArrayList<ParBatida> getListaParBatida(String id){
            return tabelaPontos.getOrDefault(id, null);
        }

        /**
         * Retorna a tabela completa
         * @return
         */
        public Map<String, ArrayList<ParBatida>> getTabelaPontos() {
            return tabelaPontos;
        }


        /**
         * Associa uma lista de pontos a um usuario especificado pelo id
         * @param idUsuario
         */
        public void setListaDePontos(String idUsuario){
            ArrayList<ParBatida> listaDePontos = new ArrayList<>();
            tabelaPontos.put(idUsuario, listaDePontos);
        }

        /**
         * Verifica se a tabela contem um usuario especificado pelo id
         * @param id
         * @return
         */
        public boolean contemUsuario(String id) {
            return tabelaPontos.containsKey(id);
        }
    }
