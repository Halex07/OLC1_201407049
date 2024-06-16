/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patroninterprete;
import excepciones.Errores;
import java.util.LinkedList;
/**
 *
 * @author Alex
 */
public class ReporteErrores extends javax.swing.JFrame{
    
    public LinkedList<Errores> errores;
    
    
    public ReporteErrores(LinkedList<Errores> e){
        this.errores =e;
        initComponents();
    
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
