/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumeradores.TipoQuartoEnum;

/**
 *
 * @author Silas
 */
public class Quarto {
    
    private int id;
    private TipoQuartoEnum tipo;
    private boolean disponivel;
    private float valor;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoQuartoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuartoEnum tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Quarto{" + "id=" + id + ", tipo=" + tipo + ", disponivel=" + disponivel + ", valor=" + valor + '}';
    }
    
    
}
