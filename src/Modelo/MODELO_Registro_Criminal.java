/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author alexa
 */
public class MODELO_Registro_Criminal {
    private void createEmail() throws MessagingException{
        String correo = "procesamientodatoscriminales@gmail.com";
        String contra = "oczjlmjzjddrkuhz";
        String correoDestino = "adn.nto@gmail.com";
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.put("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", correo);
            p.put("mail.smtp.ssl.protocols", "TLSv1.2");
            p.setProperty("mail.smtp.auth", "true");
            
            Session s = Session.getDefaultInstance(p);
            BodyPart texto = new MimeBodyPart();
            texto.setText("Espero se encuentre bien, enviamos este correo desde la oficina de procesamiento de datos criminales"
                        + " con la finalidad de que usted lleve a cabo la revisión de los datos del delincuente, cualquier duda"
                        + " no dude en responder este mensaje y nosotros la aclararemos a la brevedad.\n"
                        + "Sin nada más por el momento le mando un cordial saludo.");
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\alexa\\OneDrive\\Escritorio\\REPORTES RECLUSORIO\\ingreso.pdf")));
            adjunto.setFileName("ingreso.pdf");
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            m.addBodyPart(adjunto);
            
            
            
            MimeMessage mensaje = new MimeMessage(s);
            
            mensaje.setFrom(new InternetAddress(correo));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mensaje.setSubject("INGRESO DE REO A PRISIÓN");
            mensaje.setContent(m);
            
            Transport t = s.getTransport("smtp");
            t.connect(correo, contra);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            JOptionPane.showMessageDialog(null, "Mensaje enviado");
    }
    
    
    private void nuevoIngreso(){
        Document documento = new Document();

    try {
        PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\alexa\\OneDrive\\Escritorio\\REPORTES RECLUSORIO\\ingreso.pdf"));
        
        documento.open();
        
        // Añadir los logos en las esquinas superiores
        try {
            com.itextpdf.text.Image logoIzquierdo = com.itextpdf.text.Image.getInstance("src/imagenes/gobierno.jpg");
            logoIzquierdo.scaleToFit(300, 120);
            logoIzquierdo.setAbsolutePosition(50, 750);
            documento.add(logoIzquierdo);
            
            com.itextpdf.text.Image logoDerecho = com.itextpdf.text.Image.getInstance("src/imagenes/prision.png");
            logoDerecho.scaleToFit(90, 80);
            logoDerecho.setAbsolutePosition(450, 750);
            documento.add(logoDerecho);
        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR LOS LOGOS: " + e);
        }
        
        // Añadir título
        Paragraph title = new Paragraph("\n\nNuevo Delincuente Ingresado al Penal\n\n", 
                FontFactory.getFont("Tahoma", 22, Font.BOLD, BaseColor.DARK_GRAY));
        title.setAlignment(Element.ALIGN_CENTER);
        documento.add(title);
        
        
        // Primera página: Mensaje de media cuartilla
        Paragraph mensaje2 = new Paragraph(
            "Se informa que ha habido un nuevo ingreso de un reo al penal. " +
            "El sistema penitenciario se encarga de la seguridad y custodia de los internos, " +
            "proporcionando un ambiente seguro tanto para los reclusos como para el personal. " +
            "Este reporte contiene los datos personales y las imágenes del nuevo recluso, " +
            "así como la información relevante para su seguimiento y control. " +
            "El objetivo es asegurar la correcta identificación y registro de cada interno, " +
            "manteniendo un control riguroso y preciso dentro del penal. " +
            "Es importante que todos los datos sean revisados y actualizados conforme a los protocolos establecidos.\n\n" +
            "Para más información, consulte las siguientes páginas del documento donde se detallan " +
            "los datos del nuevo recluso y se proporcionan imágenes para su correcta identificación.\n" +
            "Agradecemos su atención y colaboración en este proceso."
          + "\n\n\n\n\n\n\n\n\n\n\n\n Atentamente\n Oficial de Procesamiento de Datos de Criminales."
            , FontFactory.getFont("Tahoma", 14, Font.NORMAL, BaseColor.BLACK));
        mensaje2.setAlignment(Element.ALIGN_JUSTIFIED);
        documento.add(mensaje2);

        // Añadir un salto de página
        documento.newPage();
        
        
        
        // Añadir datos del delincuente en una tabla
        PdfPTable tablaDatos = new PdfPTable(2);
        tablaDatos.setWidthPercentage(100);
        tablaDatos.setSpacingBefore(20f);
        tablaDatos.setSpacingAfter(20f);

        // Definir fuentes para encabezados y datos
        Font fontHeader = FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.WHITE);
        Font fontData = FontFactory.getFont("Arial", 14, Font.NORMAL, BaseColor.BLACK);
        
        // Encabezados de los datos
        String[] headers = {"NUC", "Nombre(s)", "Apellidos", "Edad", "Delito", "Lugar de Nacimiento", "Tipo de Sangre"};
        String[] data = {
        };
        
        for (int i = 0; i < headers.length; i++) {
            PdfPCell headerCell = new PdfPCell(new Phrase(headers[i], fontHeader));
            headerCell.setBackgroundColor(BaseColor.ORANGE);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setPadding(10f);
            tablaDatos.addCell(headerCell);

            PdfPCell dataCell = new PdfPCell(new Phrase(data[i], fontData));
            dataCell.setPadding(10f);
            tablaDatos.addCell(dataCell);
        }
        
        documento.add(tablaDatos);
        
        // Añadir las imágenes de las labels en una tabla de 4x2 con espaciado y bordes
        PdfPTable tablaImagenes = new PdfPTable(4);
        tablaImagenes.setWidthPercentage(100);
        tablaImagenes.setSpacingBefore(10f);
        tablaImagenes.setSpacingAfter(10f);

        try {
            ImageIcon[] imageIcons = new ImageIcon[] {
            };

            for (ImageIcon icon : imageIcons) {
                com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(((ImageIcon) icon).getImage(), null);
                img.scaleToFit(120, 120);
                PdfPCell cell = new PdfPCell(img, true);
                cell.setPadding(10f); // Añadir espacio dentro de la celda
                cell.setBorderWidth(1); // Añadir borde a la celda
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tablaImagenes.addCell(cell);
            }

            // Añadir tabla de imágenes al documento
            documento.add(tablaImagenes);

        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR LAS IMÁGENES: " + e);
        }
        
        documento.close();
        
        String mensaje = "REPORTE CREADO EXITOSAMENTE";
        System.out.println(mensaje);
        // MailSender.sendEmailWithAttachment("destinatario@example.com", "Nuevo Delincuente Ingresado", "Adjunto encontrará el reporte del nuevo delincuente ingresado.", "C:\\Users\\alexa\\OneDrive\\Documentos\\NetBeansProjects\\PROYECTO RECLUSORIO\\RECLUSORIO2\\PDF's REPORTES\\nuevoDelincuente.pdf");
        
    } catch (Exception e) {
        System.out.println("ERROR EN PDF " + e);
    }
    }
    
}
