/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class Arbol {
    
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private tablaSimbolos tablaGlobal;
    private LinkedList<Errores> errores;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new tablaSimbolos();
        this.errores = new LinkedList<>();
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public tablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public void setTablaGlobal(tablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }
    
    
    public void Print(String valor){
    this.consola += valor + "\n";
    }
    
    
    
}
