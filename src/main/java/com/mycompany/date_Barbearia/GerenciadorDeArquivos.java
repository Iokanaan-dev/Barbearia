package com.mycompany.date_Barbearia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GerenciadorDeArquivos {

    private static final String CAMINHO_ARQUIVO = "barbearia_dados.json";

    // ✅ Configuração do Jackson
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule()) // Suporte a LocalDate / LocalDateTime
            .enable(SerializationFeature.INDENT_OUTPUT) // JSON bonito
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Datas legíveis (ex: "2025-11-10")

    // 🔹 Salva (com merge automático)
    public static void salvar(Barbearia_date novosDados) {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);

            Barbearia_date dadosExistentes = null;
            if (arquivo.exists()) {
                dadosExistentes = mapper.readValue(arquivo, Barbearia_date.class);
            }

            // 🔁 Mescla os dados para não duplicar
            if (dadosExistentes != null) {
                mesclarListasSemDuplicar(dadosExistentes.getListaClientes(), novosDados.getListaClientes());
                mesclarListasSemDuplicar(dadosExistentes.getListaGerentes(), novosDados.getListaGerentes());
                mesclarListasSemDuplicar(dadosExistentes.getListaBarbeiros(), novosDados.getListaBarbeiros());
                mesclarListasSemDuplicar(dadosExistentes.getListaAtendentes(), novosDados.getListaAtendentes());
                mesclarListasSemDuplicar(dadosExistentes.getListaServicos(), novosDados.getListaServicos());
                mesclarListasSemDuplicar(dadosExistentes.getListaProdutos(), novosDados.getListaProdutos());
                mesclarListasSemDuplicar(dadosExistentes.getListaOrdensServico(), novosDados.getListaOrdensServico());
                mesclarListasSemDuplicar(dadosExistentes.getListaAgendamentos(), novosDados.getListaAgendamentos());

                novosDados = dadosExistentes;
            }

            mapper.writeValue(arquivo, novosDados);
            System.out.println("✅ Dados salvos com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // 🔁 Função genérica para mesclar listas sem duplicar
    private static <T> void mesclarListasSemDuplicar(List<T> listaExistente, List<T> listaNova) {
        if (listaExistente == null || listaNova == null) return;
        for (T itemNovo : listaNova) {
            if (!listaExistente.contains(itemNovo)) {
                listaExistente.add(itemNovo);
            }
        }
    }

    // 🔹 Carrega os dados do arquivo e retorna a instância
    public static Barbearia_date carregar() {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                Barbearia_date dados = mapper.readValue(arquivo, Barbearia_date.class);
                System.out.println("📂 Dados carregados com sucesso!");
                return dados;
            } else {
                System.out.println("🆕 Arquivo não encontrado. Criando novos dados...");
                return new Barbearia_date();
            }
        } catch (IOException e) {
            System.err.println("⚠️ Erro ao carregar: " + e.getMessage());
            return new Barbearia_date();
        }
    }
}
