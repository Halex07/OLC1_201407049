package patroninterprete;

import excepciones.Errores;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Alex
 */

public class ReporteErrores extends javax.swing.JFrame {

    public LinkedList<Errores> errores;
    private JTextArea textArea;

    public ReporteErrores(LinkedList<Errores> e) {
        this.errores = e;
        initComponents();
    }

    private void initComponents() {
        setTitle("Reporte de Errores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        StringBuilder sb = new StringBuilder();
        for (Errores error : errores) {
            sb.append(error.toString()).append("\n");
        }
        textArea.setText(sb.toString());

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
