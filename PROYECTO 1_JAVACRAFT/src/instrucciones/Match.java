/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

/**
 *
 * @author Alex
 */
import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

public class Match extends Instruccion {

    private Instruccion expresion;
    private LinkedList<Caso> casos;
    private LinkedList<Instruccion> defaultCaso;

    public Match(Instruccion expresion, LinkedList<Caso> casos, LinkedList<Instruccion> defaultCaso, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresion = expresion;
        this.casos = casos;
        this.defaultCaso = defaultCaso;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valorExpresion = this.expresion.interpretar(arbol, tabla);
        if (valorExpresion instanceof Errores) {
            return valorExpresion;
        }

        var newTabla = new tablaSimbolos(tabla);
        for (var caso : this.casos) {
            var valorCaso = caso.getValor().interpretar(arbol, tabla);
            if (valorCaso instanceof Errores) {
                return valorCaso;
            }

            if (valorExpresion.equals(valorCaso)) {
                for (var instruccion : caso.getInstrucciones()) {
                    var resultado = instruccion.interpretar(arbol, newTabla);
                    if (resultado instanceof Break) {
                        return resultado;
                    }
                }
                return null;
            }
        }

        // Si no se encontró ningún caso coincidente, ejecutar las instrucciones del caso por defecto
        if (this.defaultCaso != null) {
            for (var instruccion : this.defaultCaso) {
                var resultado = instruccion.interpretar(arbol, newTabla);
                if (resultado instanceof Break) {
                    return resultado;
                }
            }
        }

        return null;
    }

    public static class Caso {
        private Instruccion valor;
        private LinkedList<Instruccion> instrucciones;

        public Caso(Instruccion valor, LinkedList<Instruccion> instrucciones) {
            this.valor = valor;
            this.instrucciones = instrucciones;
        }

        public Instruccion getValor() {
            return valor;
        }

        public LinkedList<Instruccion> getInstrucciones() {
            return instrucciones;
        }
    }
}

