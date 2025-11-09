/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.JacksonBarbearia;
    
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *  Utilitário genérico para salvar e carregar listas de objetos em JSON.
 * @author italo
 */
public class Jackson {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // suporte a java.time
            .enable(SerializationFeature.INDENT_OUTPUT) // JSON bonito
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // datas legíveis

    /**
     * Salva uma lista de objetos em um arquivo JSON.
     *
     * @param lista Lista a ser salva
     * @param caminhoArquivo Caminho do arquivo JSON
     */
    public static <T> void salvarLista(List<T> lista, String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);

            // Cria o arquivo e diretórios se não existirem
            if (arquivo.getParentFile() != null && !arquivo.getParentFile().exists()) {
                arquivo.getParentFile().mkdirs();
            }

            mapper.writeValue(arquivo, lista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista em JSON: " + e.getMessage());
        }
    }

    /**
     * Carrega uma lista de objetos de um arquivo JSON.
     * Se o arquivo não existir, retorna uma lista vazia.
     *
     * @param caminhoArquivo Caminho do arquivo JSON
     * @param tipo Classe dos objetos contidos na lista
     * @return Lista carregada do arquivo, ou vazia se não houver arquivo
     */
    public static <T> List<T> carregarLista(String caminhoArquivo, Class<T> tipo) {
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(
                    arquivo,
                    mapper.getTypeFactory().constructCollectionType(List.class, tipo)
            );
        } catch (IOException e) {
            System.err.println("Erro ao carregar lista do JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }    
    
    /* Salva um ÚNICO objeto em um arquivo JSON.
     *
     * @param objeto O objeto a ser salvo
     * @param caminhoArquivo Caminho do arquivo JSON
     */
    public static <T> void salvarObjeto(T objeto, String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);

            if (arquivo.getParentFile() != null && !arquivo.getParentFile().exists()) {
                arquivo.getParentFile().mkdirs();
            }
            mapper.writeValue(arquivo, objeto);
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar OBJETO em JSON: " + e.getMessage());
        }
    }

    /**
     *
     * Carrega um ÚNICO objeto de um arquivo JSON.
     *
     * @param caminhoArquivo Caminho do arquivo JSON
     * @param tipo A Classe do objeto (ex: DadosDaBarbearia.class)
     * @return O objeto carregado, ou null se não houver arquivo
     */
    public static <T> T carregarObjeto(String caminhoArquivo, Class<T> tipo) {
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            return null; 
        }

        try {
            return mapper.readValue(arquivo, tipo);
            
        } catch (IOException e) {
            System.err.println("Erro ao carregar OBJETO do JSON: " + e.getMessage());
            return null;
        }
    }
    
}
