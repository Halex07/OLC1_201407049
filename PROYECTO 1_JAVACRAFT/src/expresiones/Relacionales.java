/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;


/**
 *
 * @author Alex
 */
public class Relacionales extends Instruccion {

    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;

    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if (condIzq instanceof Errores) {
            return condIzq;
        }

        var condDer = this.cond2.interpretar(arbol, tabla);
        if (condDer instanceof Errores) {
            return condDer;
        }

        return switch (relacional) {
            case EQUALS ->
                this.equals(condIzq, condDer);
            case NOTEQUALS ->
                this.noequals(condIzq, condDer);  
            case MENOR ->
                this.menor(condIzq, condDer);
            case MAYOR ->
                this.mayor(condIzq, condDer); 
            case MENOREQ ->
                this.menoreq(condIzq, condDer);
            case MAYOREQ ->
                this.mayoreq(condIzq, condDer);  
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
//EQUALS
    public Object equals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->
                        (int) comp1 == (int) comp2;
                    case DECIMAL ->
                        (int) comp1 == (double) comp2;
                    case CADENA ->
                        comp1.toString().equals(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->
                        (double) comp1 == (int) comp2;
                    case DECIMAL ->
                        (double) comp1 == (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
           case CARACTER ->
            switch (comparando2) {
                case ENTERO -> {
                    int ascii1 = (int) ((char) comp1);
                    int entero2 = (int) comp2;
                    yield ascii1 == entero2;
                }
                case DECIMAL -> {
                    int ascii1 = (int) ((char) comp1);
                    double decimal2 = (double) comp2;
                    yield ascii1 == decimal2;
                }
                case CADENA -> {
                    String cadena2 = comp2.toString();
                    yield comp1.toString().equalsIgnoreCase(cadena2);
                }
                case CARACTER -> {
                    int ascii1 = (int) ((char) comp1);
                    int ascii2 = (int) ((char) comp2);
                    yield ascii1 == ascii2;
                }
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            case BOOLEANO ->
                switch (comparando2) {
                case ENTERO ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y ENTERO", this.linea, this.col);
                case DECIMAL ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y DECIMAL", this.linea, this.col);
                case CADENA ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y CADENA", this.linea, this.col);
                case BOOLEANO ->
                    (boolean) comp1 == (boolean) comp2;
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    //NOEQUALS
    public Object noequals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->
                        (int) comp1 != (int) comp2;
                    case DECIMAL ->
                        (int) comp1 != (double) comp2;
                    case CADENA ->
                        comp1.toString().equals(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->
                        (double) comp1 != (int) comp2;
                    case DECIMAL ->
                        (double) comp1 != (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
           case CARACTER ->
            switch (comparando2) {
                case ENTERO -> {
                    int ascii1 = (int) ((char) comp1);
                    int entero2 = (int) comp2;
                    yield ascii1 != entero2;
                }
                case DECIMAL -> {
                    int ascii1 = (int) ((char) comp1);
                    double decimal2 = (double) comp2;
                    yield ascii1 != decimal2;
                }
                case CADENA -> {
                    String cadena2 = comp2.toString();
                    yield comp1.toString().equalsIgnoreCase(cadena2);
                }
                case CARACTER -> {
                    int ascii1 = (int) ((char) comp1);
                    int ascii2 = (int) ((char) comp2);
                    yield ascii1 != ascii2;
                }
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            case BOOLEANO ->
                switch (comparando2) {
                case ENTERO ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y ENTERO", this.linea, this.col);
                case DECIMAL ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y DECIMAL", this.linea, this.col);
                case CADENA ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y CADENA", this.linea, this.col);
                case BOOLEANO ->
                    (boolean) comp1 != (boolean) comp2;
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    //MAYORQ
    public Object mayor(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->
                        (int) comp1 > (int) comp2;
                    case DECIMAL ->
                        (int) comp1 > (double) comp2;
                    case CADENA ->
                        comp1.toString().equals(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->
                        (double) comp1 > (int) comp2;
                    case DECIMAL ->
                        (double) comp1 > (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
           case CARACTER ->
            switch (comparando2) {
                case ENTERO -> {
                    int ascii1 = (int) ((char) comp1);
                    int entero2 = (int) comp2;
                    yield ascii1 > entero2;
                }
                case DECIMAL -> {
                    int ascii1 = (int) ((char) comp1);
                    double decimal2 = (double) comp2;
                    yield ascii1 > decimal2;
                }
                case CADENA -> {
                    String cadena2 = comp2.toString();
                    yield comp1.toString().equalsIgnoreCase(cadena2);
                }
                case CARACTER -> {
                    int ascii1 = (int) ((char) comp1);
                    int ascii2 = (int) ((char) comp2);
                    yield ascii1 > ascii2;
                }
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            case BOOLEANO ->
                switch (comparando2) {
                case ENTERO ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y ENTERO", this.linea, this.col);
                case DECIMAL ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y DECIMAL", this.linea, this.col);
                case CADENA ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y CADENA", this.linea, this.col);
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    //MENORQ
    public Object menor(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->
                        (int) comp1 < (int) comp2;
                    case DECIMAL ->
                        (int) comp1 < (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional invaldo",
                        this.linea, this.col);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->
                        (double) comp1 < (int) comp2;
                    case DECIMAL ->
                        (double) comp1 < (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional invaldo",
                        this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional invaldo",
                this.linea, this.col);
        };
    }
    //MAYOREQ
    public Object mayoreq(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->
                        (int) comp1 >= (int) comp2;
                    case DECIMAL ->
                        (int) comp1 >= (double) comp2;
                    case CADENA ->
                        comp1.toString().equals(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->
                        (double) comp1 >= (int) comp2;
                    case DECIMAL ->
                        (double) comp1 >= (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
           case CARACTER ->
            switch (comparando2) {
                case ENTERO -> {
                    int ascii1 = (int) ((char) comp1);
                    int entero2 = (int) comp2;
                    yield ascii1 != entero2;
                }
                case DECIMAL -> {
                    int ascii1 = (int) ((char) comp1);
                    double decimal2 = (double) comp2;
                    yield ascii1 >= decimal2;
                }
                case CADENA -> {
                    String cadena2 = comp2.toString();
                    yield comp1.toString().equalsIgnoreCase(cadena2);
                }
                case CARACTER -> {
                    int ascii1 = (int) ((char) comp1);
                    int ascii2 = (int) ((char) comp2);
                    yield ascii1 >= ascii2;
                }
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            case BOOLEANO ->
                switch (comparando2) {
                case ENTERO ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y ENTERO", this.linea, this.col);
                case DECIMAL ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y DECIMAL", this.linea, this.col);
                case CADENA ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y CADENA", this.linea, this.col);
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    //MENOREQ
    public Object menoreq(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->
                        (int) comp1 <= (int) comp2;
                    case DECIMAL ->
                        (int) comp1 <= (double) comp2;
                    case CADENA ->
                        comp1.toString().equals(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->
                        (double) comp1 <= (int) comp2;
                    case DECIMAL ->
                        (double) comp1 <= (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
           case CARACTER ->
            switch (comparando2) {
                case ENTERO -> {
                    int ascii1 = (int) ((char) comp1);
                    int entero2 = (int) comp2;
                    yield ascii1 <= entero2;
                }
                case DECIMAL -> {
                    int ascii1 = (int) ((char) comp1);
                    double decimal2 = (double) comp2;
                    yield ascii1 <= decimal2;
                }
                case CADENA -> {
                    String cadena2 = comp2.toString();
                    yield comp1.toString().equalsIgnoreCase(cadena2);
                }
                case CARACTER -> {
                    int ascii1 = (int) ((char) comp1);
                    int ascii2 = (int) ((char) comp2);
                    yield ascii1 <= ascii2;
                }
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            case BOOLEANO ->
                switch (comparando2) {
                case ENTERO ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y ENTERO", this.linea, this.col);
                case DECIMAL ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y DECIMAL", this.linea, this.col);
                case CADENA ->
                    new Errores("SEMANTICO", "Relacional Invalido: Comparación entre BOOLEANO y CADENA", this.linea, this.col);
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            };
            
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
}