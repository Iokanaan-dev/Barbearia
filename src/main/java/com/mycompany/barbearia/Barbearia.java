package com.mycompany.barbearia;

import com.mycompany.date_Barbearia.GerenciadorDeArquivos;
import com.mycompany.date_Barbearia.Barbearia_date;
import com.mycompany.Gerenciamento.*;
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
    
    public static void main(String[] args) throws Exception {
        
        LocalDate data1 = LocalDate.of(1991, 12, 31);
        
        // 🔹 Carrega os dados existentes ou cria novos
        Barbearia_date dados = GerenciadorDeArquivos.carregar();
        GestaoClientes.inicializar(dados);

        // 🔹 Manipula normalmente
        
        
        GestaoClientes gestaoC = GestaoClientes.getInstancia();
        gestaoC.cadastrar("Italo", "12755050667", "38998060657", data1, "Italof5631@gmail.com");
        
        Cliente italo = gestaoC.buscarCPF("12755050667");
        
        GestaoUsuarios gestaoU = GestaoUsuarios.getInstancia();
        gestaoU.cadastrar("itim123", "Pedrim1234", "pedrim", "28391293921", "38998070792", data1, TipoUsuario.BARBEIRO);
        gestaoU.cadastrar("itao123", "Pedrim1234", "pedrim", "28391293921", "38998070792", data1, TipoUsuario.ATENDENTE);
        gestaoU.cadastrar("pedrim123", "Pedrim1234", "pedrim", "28391293921", "38998070792", data1, TipoUsuario.GERENTE, "9090");
    
       //Usuario pedrim = gestaoU.buscarUsername("pedrim123");
       //Usuario itim = gestaoU.buscarUsername("itao123");
       //Usuario itao = gestaoU.buscarUsername("itim123");
        
       dados.listaClientes.add(italo);
       //dados.listaGerentes.add((Gerente) pedrim);
       //dados.listaGerentes.add((Gerente) itim);
       //dados.listaGerentes.add((Gerente)itao);

        // 🔹 Salva as mudanças
        GerenciadorDeArquivos.salvar(dados);
    }
}