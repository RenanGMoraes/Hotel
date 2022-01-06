/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidade;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Silas
 */
public class Utilidade {

    public void aplicaMascaraTelefone(JFormattedTextField txt) throws ParseException {

        MaskFormatter mascara = new MaskFormatter("(##)#########");
        mascara.install(txt);

    }

    public void aplicaMascaraCpf(JFormattedTextField txt) throws ParseException {

        MaskFormatter mascara = new MaskFormatter("###.###.###-##");
        mascara.install(txt);

    }

}
