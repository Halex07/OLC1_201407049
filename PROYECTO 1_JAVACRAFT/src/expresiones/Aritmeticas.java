/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import static expresiones.OperadoresAritmeticos.DIVISION;
import static expresiones.OperadoresAritmeticos.MULTIPLICACION;
import static expresiones.OperadoresAritmeticos.SUMA;
import static expresiones.OperadoresAritmeticos.*;
import simbolo.*;
import static simbolo.tipoDato.CADENA;
import static simbolo.tipoDato.CARACTER;
import static simbolo.tipoDato.DECIMAL;
import static simbolo.tipoDato.ENTERO;

/**
 *
 * @author Alex
 */

public class Aritmeticas extends Instruccion {

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operandoUnico;

    //negacion 
    public Aritmeticas(Instruccion operandoUnico, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    //cualquier operacion menos negacion
    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
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
            case SUMA ->
                this.suma(opIzq, opDer);
             case RESTA ->
                this.resta(opIzq, opDer);
            case MULTIPLICACION ->
                this.mult(opIzq, opDer);
            case DIVISION ->
                this.div(opIzq, opDer); 
            case MODULO ->
                this.modulo(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer); 
            case NEGACION ->
                this.negacion(Unico);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.col);
        };
    }
//suma
    public Object suma(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        int sumaAscii = ascii1 + ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
            }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (double) op1 + (int) op1;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        int sumaAscii = ascii1 + ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                    
                    
                }
            }
            case CADENA -> {
                this.tipo.setTipo(tipoDato.CADENA);
                return op1.toString() + op2.toString();
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }
    
    //Resta 
    public Object resta(Object op1, Object op2) {
    var tipo1 = this.operando1.tipo.getTipo();
    var tipo2 = this.operando2.tipo.getTipo();

    switch (tipo1) {
        case ENTERO -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) op1 - (int) op2;
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (int) op1 - (double) op2;
                }
                case CADENA -> {
                    return new Errores("SEMANTICO", "No se puede restar una cadena de un entero", this.linea, this.col);
                }
                case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        int sumaAscii = ascii1 - ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
                    }
                default -> {
                    return new Errores("SEMANTICO", "Resta erronea", this.linea, this.col);
                }
            }
        }
        case DECIMAL -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 - (int) op2;
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 - (double) op2;
                }
                case CADENA -> {
                    return new Errores("SEMANTICO", "No se puede restar una cadena de un decimal", this.linea, this.col);
                }
                case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        int sumaAscii = ascii1 - ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
                    }
                default -> {
                    return new Errores("SEMANTICO", "Resta erronea", this.linea, this.col);
                }
            }
        }
        case CADENA -> {
            switch (tipo2) {
                case ENTERO, DECIMAL -> {
                    return new Errores("SEMANTICO", "No se puede restar un entero o decimal de una cadena", this.linea, this.col);
                }
                case CADENA -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return op1.toString().length() - op2.toString().length();
                }
                case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        int sumaAscii = ascii1 - ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
                    }
                default -> {
                    return new Errores("SEMANTICO", "Resta erronea", this.linea, this.col);
                }
            }
        }
        default -> {
            return new Errores("SEMANTICO", "Tipo de dato no soportado para la resta", this.linea, this.col);
        }
    }
}

// Multiplicacion
public Object mult(Object op1, Object op2) {
    var tipo1 = this.operando1.tipo.getTipo();
    var tipo2 = this.operando2.tipo.getTipo();

    switch (tipo1) {
        case ENTERO -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) op1 * (int) op2;
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (int) op1 * (double) op2;
                }
                case CADENA -> {
                    this.tipo.setTipo(tipoDato.CADENA);
                    return op1.toString().length() * op2.toString().length();
                }
                case CARACTER -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    int ascii1 = (int) ((char) op1);
                    int ascii2 = (int) ((char) op2);
                    return ascii1 * ascii2;
                }
                default -> {
                    return new Errores("SEMANTICO", "Multiplicación erronea", this.linea, this.col);
                }
            }
        }
        case DECIMAL -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 * (int) op2;
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 * (double) op2;
                }
                case CADENA -> {
                    this.tipo.setTipo(tipoDato.CADENA);
                    return op1.toString().length() * op2.toString().length();
                }
                case CARACTER -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    int ascii1 = (int) ((char) op1);
                    int ascii2 = (int) ((char) op2);
                    return ascii1 * ascii2;
                }
                default -> {
                    return new Errores("SEMANTICO", "Multiplicación erronea", this.linea, this.col);
                }
            }
        }
        case CADENA -> {
            this.tipo.setTipo(tipoDato.CADENA);
            return op1.toString().length() * op2.toString().length();
        }
        default -> {
            return new Errores("SEMANTICO", "Multiplicación erronea", this.linea, this.col);
        }
    }
}

    //Division
    
    public Object div(Object op1, Object op2) {
    var tipo1 = this.operando1.tipo.getTipo();
    var tipo2 = this.operando2.tipo.getTipo();

    switch (tipo1) {
        case ENTERO -> {
            switch (tipo2) {
                case ENTERO -> {
                    if ((int) op2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) op1 / (int) op2;
                }
                case DECIMAL -> {
                    if ((double) op2 == 0.0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (int) op1 / (double) op2;
                }
                case CADENA -> {
                    int length2 = op2.toString().length();
                    if (length2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) op1 / length2;
                }
                case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        if (ascii2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                        int sumaAscii = ascii1 / ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
                    }
                default -> {
                    return new Errores("SEMANTICO", "División erronea", this.linea, this.col);
                }
            }
        }
        case DECIMAL -> {
            switch (tipo2) {
                case ENTERO -> {
                    if ((int) op2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 / (int) op2;
                }
                case DECIMAL -> {
                    if ((double) op2 == 0.0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 / (double) op2;
                }
                case CADENA -> {
                    int length2 = op2.toString().length();
                    if (length2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 / length2;
                }
                case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        if (ascii2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                        int sumaAscii = ascii1 / ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
                    }
                default -> {
                    return new Errores("SEMANTICO", "División erronea", this.linea, this.col);
                }
            }
        }
        case CADENA -> {
            int length1 = op1.toString().length();
            switch (tipo2) {
                case ENTERO -> {
                    if ((int) op2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return length1 / (int) op2;
                }
                case DECIMAL -> {
                    if ((double) op2 == 0.0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return length1 / (double) op2;
                }
              
                case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        int ascii1 = (int) ((char) op1);
                        int ascii2 = (int) ((char) op2);
                        if (ascii2 == 0) {
                        return new Errores("SEMANTICO", "División por cero", this.linea, this.col);
                    }
                        int sumaAscii = ascii1 / ascii2;
                        char resultado = (char) sumaAscii;
                        return String.valueOf(resultado);
                    }
                default -> {
                    return new Errores("SEMANTICO", "División erronea", this.linea, this.col);
                }
            }
        }
        default -> {
            return new Errores("SEMANTICO", "Tipo de dato no soportado para la división", this.linea, this.col);
        }
    }
}

    //Potencia
    public Object potencia(Object op1, Object op2) {
    var tipo1 = this.operando1.tipo.getTipo();
    var tipo2 = this.operando2.tipo.getTipo();

    switch (tipo1) {
        case ENTERO -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) Math.pow((int) op1, (int) op2);
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow((int) op1, (double) op2);
                }
                case CADENA -> {
                    return new Errores("SEMANTICO", "Potencia no definida para entero y cadena", this.linea, this.col);
                }
                case CARACTER -> {
                    int base = (int) op1;
                    int exponente = (int) ((char) op2);
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) Math.pow(base, exponente);
                }
                default -> {
                    return new Errores("SEMANTICO", "Potencia erronea", this.linea, this.col);
                }
            }
        }
        case DECIMAL -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow((double) op1, (int) op2);
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow((double) op1, (double) op2);
                }
                case CADENA -> {
                    return new Errores("SEMANTICO", "Potencia no definida para decimal y cadena", this.linea, this.col);
                }
                case CARACTER -> {
                    double base = (double) op1;
                    int exponente = (int) ((char) op2);
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return Math.pow(base, exponente);
                }
                default -> {
                    return new Errores("SEMANTICO", "Potencia erronea", this.linea, this.col);
                }
            }
        }
        
        default -> {
            return new Errores("SEMANTICO", "Tipo de dato no manejado", this.linea, this.col);
        }
    }
}
    
    //modulo 
    public Object modulo(Object op1, Object op2) {
    var tipo1 = this.operando1.tipo.getTipo();
    var tipo2 = this.operando2.tipo.getTipo();

    switch (tipo1) {
        case ENTERO -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) op1 % (int) op2;
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (int) op1 % (double) op2;
                }
                case CADENA -> {
                    return new Errores("SEMANTICO", "Módulo no definido para entero y cadena", this.linea, this.col);
                }
                case CARACTER -> {
                    int base = (int) op1;
                    int mod = (int) ((char) op2);
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return base % mod;
                }
                default -> {
                    return new Errores("SEMANTICO", "Módulo erróneo", this.linea, this.col);
                }
            }
        }
        case DECIMAL -> {
            switch (tipo2) {
                case ENTERO -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 % (int) op2;
                }
                case DECIMAL -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) op1 % (double) op2;
                }
                case CADENA -> {
                    return new Errores("SEMANTICO", "Módulo no definido para decimal y cadena", this.linea, this.col);
                }
                case CARACTER -> {
                    double base = (double) op1;
                    int mod = (int) ((char) op2);
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return base % mod;
                }
                default -> {
                    return new Errores("SEMANTICO", "Módulo erróneo", this.linea, this.col);
                }
            }
        }
       
     
        default -> {
            return new Errores("SEMANTICO", "Tipo de dato no manejado", this.linea, this.col);
        }
    }
}


    
    //negacion
    public Object negacion(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case ENTERO -> {
                this.tipo.setTipo(tipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case DECIMAL -> {
                this.tipo.setTipo(tipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.col);
            }
        }
    }
}
