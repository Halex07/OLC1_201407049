/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

/**
 *
 * @author Alex
 */
public class simbolo {
    private Tipo tipo;
    private String id;
    private Object  valor;

    public simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    public simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }

    public Object getValor() {
        return valor;
    }
    
    
    
}
