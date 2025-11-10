package com.mycompany.JacksonBarbearia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.google.gson.JsonDeserializer;

import com.google.gson.JsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.util.List;

public class GerenciadorDeArquivos {

    private static final String CAMINHO_ARQUIVO = "barbearia_dados.json";

   
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Salva as datas em formato legível (ex: "2025-11-09")

    public static void salvar(Barbearia_date novosDados) {
        
        
        try {
               Gson gson = new GsonBuilder()
    .setPrettyPrinting()
    // Adapta LocalDate
    .registerTypeAdapter(java.time.LocalDate.class,
        (JsonSerializer<java.time.LocalDate>) (data, tipo, contexto) -> new JsonPrimitive(data.toString()))
    .registerTypeAdapter(java.time.LocalDate.class,
        (JsonDeserializer<java.time.LocalDate>) (json, tipo, contexto) -> java.time.LocalDate.parse(json.getAsString()))

    // Adapta LocalDateTime
    .registerTypeAdapter(java.time.LocalDateTime.class,
        (JsonSerializer<java.time.LocalDateTime>) (data, tipo, contexto) -> new JsonPrimitive(data.toString()))
    .registerTypeAdapter(java.time.LocalDateTime.class,
        (JsonDeserializer<java.time.LocalDateTime>) (json, tipo, contexto) -> java.time.LocalDateTime.parse(json.getAsString()))
    .create();

               File arquivo = new File(CAMINHO_ARQUIVO);

               Barbearia_date dadosExistentes = null;
               if (arquivo.exists()) {
                   try (Reader reader = new FileReader(arquivo)) {
                       dadosExistentes = gson.fromJson(reader, Barbearia_date.class);
                   }
               }

               // Se já existirem dados no arquivo, mesclar evitando duplicados
               if (dadosExistentes != null) {
                   mesclarListasSemDuplicar(dadosExistentes.getListaClientes(), novosDados.getListaClientes());
                   mesclarListasSemDuplicar(dadosExistentes.getListaGerentes(), novosDados.getListaGerentes());
                   mesclarListasSemDuplicar(dadosExistentes.getListaBarbeiros(), novosDados.getListaBarbeiros());
                   mesclarListasSemDuplicar(dadosExistentes.getListaAtendentes(), novosDados.getListaAtendentes());
                   mesclarListasSemDuplicar(dadosExistentes.getListaServicos(), novosDados.getListaServicos());
                   mesclarListasSemDuplicar(dadosExistentes.getListaProdutos(), novosDados.getListaProdutos());
                   mesclarListasSemDuplicar(dadosExistentes.getListaOrdensServico(), novosDados.getListaOrdensServico());
                   mesclarListasSemDuplicar(dadosExistentes.getListaAgendamentos(), novosDados.getListaAgendamentos());
                   // ... repita para outras listas se quiser manter o padrão

                   novosDados = dadosExistentes;
               }

               try (Writer writer = new FileWriter(arquivo)) {
                   gson.toJson(novosDados, writer);
               }

               System.out.println("? Dados salvos com sucesso!");
           } catch (Exception e) {
               System.err.println("❌ Erro ao salvar os dados: " + e.getMessage());
           }
    }

    //🔁 Função utilitária para evitar duplicação
    private static <T> void mesclarListasSemDuplicar(List<T> listaExistente, List<T> listaNova) {
        for (T itemNovo : listaNova) {
            if (!listaExistente.contains(itemNovo)) {
                listaExistente.add(itemNovo);
            }
        }    
    }

    // Carrega os dados do arquivo e retorna a instância
    public static Barbearia_date carregar() {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                Barbearia_date dados = mapper.readValue(arquivo, Barbearia_date.class);
                System.out.println(" Dados carregados com sucesso!");
                return dados;
            } else {
                System.out.println("️ Arquivo não encontrado. Criando novos dados...");
                return new Barbearia_date();
            }
        } catch (IOException e) {
            System.err.println(" Erro ao carregar: " + e.getMessage());
            return new Barbearia_date();
        }
    }
}
