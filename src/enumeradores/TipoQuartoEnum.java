/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumeradores;

/**
 *
 * @author Silas
 */
public enum TipoQuartoEnum {
    SOLTEIRO("Solteiro"), DUPLO_SOLTEIRO("Solteiro 2 camas"), CASAL("Casal");
    
    private final String descricao;
    
    TipoQuartoEnum(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }    
    
}
