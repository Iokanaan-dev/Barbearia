package com.mycompany.barbearia;

import com.mycompany.Gerenciamento.*;
import com.mycompany.JacksonBarbearia.*;
import com.mycompany.Utilidades.*;
import com.mycompany.barbearia.modelos.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Classe principal para testes de integração de todo o sistema.
 * @author italo
 */
public class Barbearia {
    
    // (Seu método totalOrdensServico() está ótimo)
    
    public static void main(String[] args) {
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);
        
        // 🔹 Carrega os dados existentes ou cria novos
        Barbearia_date dados = GerenciadorDeArquivos.carregar();

        // 🔹 Manipula normalmente
        
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
        GestaoClientes gestaoC = GestaoClientes.getInstancia();
        gestaoU.cadastrar("itim123", "Pedrim1234", "pedrim", "28391293921", "38998070792", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("itao123", "Pedrim1234", "pedrim", "28391293921", "38998070792", data1, TipoUsuario.ATENDENTE);
        gestaoU.cadastrar("pedrim123", "Pedrim1234", "pedrim", "28391293921", "38998070792", data1, TipoUsuario.GERENTE, "9090");
        Usuario pedrim = gestaoU.buscarUsername("pedrim123");
        Usuario itim = gestaoU.buscarUsername("itao123");
        Usuario itao = gestaoU.buscarUsername("itim123");
        
        
        
       dados.listaGerentes.add((Gerente) pedrim);
       dados.listaClientes.add(new Cliente("Italo", "12755050667", "38998060657", data1 , "Italof4531@gmail.com")); 

        // 🔹 Salva as mudanças
        GerenciadorDeArquivos.salvar(dados);
    }
}