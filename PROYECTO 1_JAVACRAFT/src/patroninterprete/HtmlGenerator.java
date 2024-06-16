package patroninterprete;

import simbolo.Simbolo;
import simbolo.tablaSimbolos;

import java.util.HashMap;
import java.util.Map;

public class HtmlGenerator {

    public static String generarTablaHtml(tablaSimbolos tabla) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>\n");
        htmlBuilder.append("<html lang=\"es\">\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
        htmlBuilder.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        htmlBuilder.append("    <title>Tabla de Símbolos</title>\n");
        htmlBuilder.append("    <style>\n");
        htmlBuilder.append("        table {\n");
        htmlBuilder.append("            width: 50%;\n");
        htmlBuilder.append("            border-collapse: collapse;\n");
        htmlBuilder.append("            margin-top: 20px;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("        th, td {\n");
        htmlBuilder.append("            border: 1px solid black;\n");
        htmlBuilder.append("            padding: 8px;\n");
        htmlBuilder.append("            text-align: left;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("        th {\n");
        htmlBuilder.append("            background-color: #f2f2f2;\n");
        htmlBuilder.append("        }\n");
        htmlBuilder.append("    </style>\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("    <h2>Tabla de Símbolos</h2>\n");
        htmlBuilder.append("    <table id=\"tablaSimbolos\">\n");
        htmlBuilder.append("        <tr>\n");
        htmlBuilder.append("            <th>ID</th>\n");
        htmlBuilder.append("            <th>Valor</th>\n");
        htmlBuilder.append("        </tr>\n");

        // Generar filas basadas en los datos de la tabla de símbolos
        HashMap<String, Object> tablaActual = tabla.getTablaActual();
        for (Map.Entry<String, Object> entry : tablaActual.entrySet()) {
            String id = entry.getKey();
            Object valor = ((Simbolo) entry.getValue()).getValor();
            htmlBuilder.append("        <tr>\n");
            htmlBuilder.append("            <td>").append(id).append("</td>\n");
            htmlBuilder.append("            <td>").append(valor).append("</td>\n");
            htmlBuilder.append("        </tr>\n");
        }

        htmlBuilder.append("    </table>\n");
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }
}
