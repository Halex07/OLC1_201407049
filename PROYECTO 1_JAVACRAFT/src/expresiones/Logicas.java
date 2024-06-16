/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;
import abstracto.Instruccion;
import excepciones.Errores;
import static expresiones.OperadoresLogicos.*;
import simbolo.*;
import static simbolo.tipoDato.CADENA;
import static simbolo.tipoDato.CARACTER;
import static simbolo.tipoDato.DECIMAL;
import static simbolo.tipoDato.ENTERO;
/**
 *
 * @author Alex
 */
public class Logicas extends Instruccion {

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresLogicos operacion;
    private Instruccion operandoUnico;

    // Negación lógica
    public Logicas(Instruccion operandoUnico, OperadoresLogicos operacion, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    // Operaciones lógicas binarias (OR, AND, XOR)
    public Logicas(Instruccion operando1, Instruccion operando2, OperadoresLogicos operacion, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object opIzq = null, opDer = null, unico = null;
        if (this.operandoUnico != null) {
            unico = this.operandoUnico.interpretar(arbol, tabla);
            if (unico instanceof Errores) {
                return unico;
            }
        } else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }
        }

        return switch (operacion) {
            case OR -> this.or(opIzq, opDer);
            case AND -> this.and(opIzq, opDer);
            case XOR -> this.xor(opIzq, opDer);
            case NOT -> this.not(unico);
            default -> new Errores("SEMANTICO", "Operador inválido", this.linea, this.col);
        };
    }

    public Object or(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            this.tipo.setTipo(tipoDato.BOOLEANO);
            return (Boolean) op1 || (Boolean) op2;
        } else {
            return new Errores("SEMANTICO", "Operación OR inválida", this.linea, this.col);
        }
    }

    public Object and(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            this.tipo.setTipo(tipoDato.BOOLEANO);
            return (Boolean) op1 && (Boolean) op2;
        } else {
            return new Errores("SEMANTICO", "Operación AND inválida", this.linea, this.col);
        }
    }

    public Object xor(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            this.tipo.setTipo(tipoDato.BOOLEANO);
            return (Boolean) op1 ^ (Boolean) op2;
        } else {
            return new Errores("SEMANTICO", "Operación XOR inválida", this.linea, this.col);
        }
    }

    public Object not(Object op) {
        if (op instanceof Boolean) {
            this.tipo.setTipo(tipoDato.BOOLEANO);
            return !(Boolean) op;
        } else {
            return new Errores("SEMANTICO", "Operación NOT inválida", this.linea, this.col);
        }
    }
}

