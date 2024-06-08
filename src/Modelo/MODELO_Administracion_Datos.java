package Modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MODELO_Administracion_Datos {
    public Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/datosdelincuentes", "root", "");
    }

    public void cargarTabla(DefaultTableModel tableModel, String filtro) {
        String sql = filtro.isEmpty() ? "SELECT * FROM presos" : "SELECT * FROM presos WHERE nombre LIKE '%" + filtro + "%'";
        try (Connection cn = getConnection(); PreparedStatement pst = (PreparedStatement) cn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los datos: " + e);
        }
    }
    
    public void generarReporte() {
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\alexa\\OneDrive\\Escritorio\\REPORTES RECLUSORIO\\reporte.pdf"));
            documento.open();

            // Añadir la imagen del header
            try {
                com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/imagenes/header.png");
                header.scaleToFit(650, 1000);
                header.setAlignment(Chunk.ALIGN_CENTER);
                documento.add(header);
            } catch (Exception e) {
                System.out.println("ERROR AL CARGAR LA IMAGEN DEL HEADER: " + e);
            }
            // Añadir la imagen del logo
            try {
                com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src/imagenes/prision.png");
                logo.scaleToFit(100, 100);
                logo.setAlignment(Chunk.ALIGN_CENTER);
                documento.add(logo);
            } catch (Exception e) {
                System.out.println("ERROR AL CARGAR LA IMAGEN DEL LOGO: " + e);
            }

            // Añadir título y subtítulo con diferentes estilos
            Paragraph title = new Paragraph("REPORTE DE DELINCUENTES ACTUALIZADO\n\n", 
                    FontFactory.getFont("Tahoma", 22, Font.BOLD, BaseColor.DARK_GRAY));
            title.setAlignment(Element.ALIGN_CENTER);
            documento.add(title);

            Paragraph subtitle = new Paragraph("Delincuentes Registrados\n\n", 
                    FontFactory.getFont("Arial", 18, Font.ITALIC, BaseColor.GRAY));
            subtitle.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitle);

            // Añadir un párrafo con detalles
            Paragraph details = new Paragraph("Este reporte contiene información detallada sobre los delincuentes ingresados en el reclusorio.\n"
                                            + " A continuación, se presentan los datos más relevantes recopilados hasta la fecha:\n\n",
                    FontFactory.getFont("Times New Roman", 14, Font.NORMAL, BaseColor.BLACK));
            details.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(details);

            // Añadir la tabla con datos de los presos
            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);

            PdfPCell[] celdas = new PdfPCell[] {
                new PdfPCell(new Phrase("NUC", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE))),
                new PdfPCell(new Phrase("Nombre(s)", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE))),
                new PdfPCell(new Phrase("Apellidos", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE))),
                new PdfPCell(new Phrase("Edad", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE))),
                new PdfPCell(new Phrase("Delito", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE))),
                new PdfPCell(new Phrase("Lugar de nacimiento", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE))),
                new PdfPCell(new Phrase("Tipo de sangre", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE)))
            };

            for (PdfPCell celda : celdas) {
                celda.setBackgroundColor(BaseColor.ORANGE);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celda);
            }

            try (Connection cn = getConnection(); PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * FROM presos"); ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                    tabla.addCell(rs.getString(6));
                    tabla.addCell(rs.getString(7));
                }
            } catch (Exception e) {
                System.out.println("ERROR AL OBTENER DATOS DE LA BASE DE DATOS: " + e);
            }

            documento.add(tabla);

            documento.close();

            String mensaje = "REPORTE CREADO EXITOSAMENTE";
            JOptionPane.showMessageDialog(null, mensaje);

            // Abrir el PDF automáticamente después de la creación
            if (Desktop.isDesktopSupported()) {
                File pdfFile = new File("C:\\Users\\alexa\\OneDrive\\Escritorio\\REPORTES RECLUSORIO\\reporte.pdf");
                if (pdfFile.exists()) {
                    Desktop.getDesktop().open(pdfFile);
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR EN PDF " + e);
        }
    }
}

