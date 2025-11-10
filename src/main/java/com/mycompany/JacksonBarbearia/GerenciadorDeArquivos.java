package com.mycompany.JacksonBarbearia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature; 
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;

public class GerenciadorDeArquivos {

    private static final String CAMINHO_ARQUIVO = "barbearia_dados.json";

   
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Salva as datas em formato legível (ex: "2025-11-09")

    public static void salvar(Barbearia_date dados) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), dados);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar: " + e.getMessage());
        }
    }

    // Carrega os dados do arquivo e retorna a instância
    public static Barbearia_date carregar() {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                Barbearia_date dados = mapper.readValue(arquivo, Barbearia_date.class);
                System.out.println("📂 Dados carregados com sucesso!");
                return dados;
            } else {
                System.out.println("⚠️ Arquivo não encontrado. Criando novos dados...");
                return new Barbearia_date();
            }
        } catch (IOException e) {
            System.err.println("❌ Erro ao carregar: " + e.getMessage());
            return new Barbearia_date();
        }
    }
}
