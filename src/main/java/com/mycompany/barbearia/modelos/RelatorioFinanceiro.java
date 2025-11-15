/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barbearia.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.Utilidades.TipoRelatorio;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author italo
 */
public class RelatorioFinanceiro {

    private TipoRelatorio tipo; 
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate periodo; // data do relatorio
    private String conteudo; // O texto formatado do relatório
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataGeracao; // quando foi gerado

    public RelatorioFinanceiro() {}

    public RelatorioFinanceiro(TipoRelatorio tipo, LocalDate periodo, String conteudo) {
        this.tipo = tipo;
        this.periodo = periodo;
        this.conteudo = conteudo;
        this.dataGeracao = LocalDateTime.now();
    }

    public TipoRelatorio getTipo() { 
        return tipo; 
    }
    
    public LocalDate getPeriodo() { 
        return periodo; 
    }
    
    public String getConteudo() { 
        return conteudo; 
    }
    
    public LocalDateTime getDataGeracao() { 
        return dataGeracao; 
    }

    public void setTipo(TipoRelatorio tipo) { 
        this.tipo = tipo; 
    }
    
    public void setPeriodo(LocalDate periodo) { 
        this.periodo = periodo; 
    }
    
    public void setConteudo(String conteudo) { 
        this.conteudo = conteudo; 
    }
    
    public void setDataGeracao(LocalDateTime dataGeracao) { 
        this.dataGeracao = dataGeracao; }
}
