/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;






import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
import conexion.conexionmysql2;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author alexa
 */
public class Registro_Criminal extends javax.swing.JDialog {
    conexion.conexionmysql2 con = new conexionmysql2();
    Connection cn = con.conectar();
    
    String correo = "procesamientodatoscriminales@gmail.com";
    String contra = "oczjlmjzjddrkuhz";
    String correoDestino = "torres.jassiel.305@gmail.com";
 

    /**
     * Creates new form Registro_Criminal
     */
    public Registro_Criminal(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    
    private void createEmail() throws MessagingException{
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
            adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\alexa\\OneDrive\\Documentos\\NetBeansProjects\\PROYECTO RECLUSORIO\\PROYECTO FINAL MVC\\3\\RECLUSORIO2\\REPORTES RECLUSORIO\\ingreso.pdf")));
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
        PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\alexa\\OneDrive\\Documentos\\NetBeansProjects\\PROYECTO RECLUSORIO\\PROYECTO FINAL MVC\\3\\RECLUSORIO2\\REPORTES RECLUSORIO\\ingreso.pdf"));
        
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
            txtNuc.getText(),
            txtNombre.getText(),
            txtApellido.getText(),
            txtEdad.getText(),
            txtDelito.getText(),
            txtNacimiento.getText(),
            cboTipoSangre.getSelectedItem().toString()
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
                (ImageIcon) lbl1.getIcon(),
                (ImageIcon) lbl2.getIcon(),
                (ImageIcon) lbl3.getIcon(),
                (ImageIcon) lbl4.getIcon(),
                (ImageIcon) lbl5.getIcon(),
                (ImageIcon) lbl6.getIcon(),
                (ImageIcon) lbl7.getIcon(),
                (ImageIcon) lbl8.getIcon()
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
    
    void limpiar(){
        txtNuc.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtDelito.setText("");
        txtNacimiento.setText("");
        cboTipoSangre.setSelectedItem("Seleccionar");
        lbl1.setIcon(null);
        txtRuta1.setText("");
        lbl2.setIcon(null);
        txtRuta2.setText("");
        lbl3.setIcon(null);
        txtRuta3.setText("");
        lbl4.setIcon(null);
        txtRuta4.setText("");
        lbl5.setIcon(null);
        txtRuta5.setText("");
        lbl6.setIcon(null);
        txtRuta6.setText("");
        lbl7.setIcon(null);
        txtRuta7.setText("");
        lbl8.setIcon(null);
        txtRuta8.setText("");
    }/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        btnIngresar5 = new javax.swing.JButton();
        btnIngresar1 = new javax.swing.JButton();
        btnIngresar2 = new javax.swing.JButton();
        btnIngresar3 = new javax.swing.JButton();
        btnIngresar4 = new javax.swing.JButton();
        btnIngresar6 = new javax.swing.JButton();
        btnIngresar7 = new javax.swing.JButton();
        btnIngresar8 = new javax.swing.JButton();
        txtRuta1 = new javax.swing.JLabel();
        txtRuta2 = new javax.swing.JLabel();
        txtRuta3 = new javax.swing.JLabel();
        txtRuta4 = new javax.swing.JLabel();
        txtRuta5 = new javax.swing.JLabel();
        txtRuta6 = new javax.swing.JLabel();
        txtRuta7 = new javax.swing.JLabel();
        txtRuta8 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cboTipoSangre = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNuc = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtNacimiento = new javax.swing.JTextField();
        txtDelito = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl1MouseClicked(evt);
            }
        });

        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnIngresar.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnIngresar5.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar5.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar5.setText("Seleccionar imagen");
        btnIngresar5.setOpaque(true);
        btnIngresar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar5ActionPerformed(evt);
            }
        });

        btnIngresar1.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar1.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar1.setText("Seleccionar imagen");
        btnIngresar1.setOpaque(true);
        btnIngresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar1ActionPerformed(evt);
            }
        });

        btnIngresar2.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar2.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar2.setText("Seleccionar imagen");
        btnIngresar2.setOpaque(true);
        btnIngresar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar2ActionPerformed(evt);
            }
        });

        btnIngresar3.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar3.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar3.setText("Seleccionar imagen");
        btnIngresar3.setOpaque(true);
        btnIngresar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar3ActionPerformed(evt);
            }
        });

        btnIngresar4.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar4.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar4.setText("Seleccionar imagen");
        btnIngresar4.setOpaque(true);
        btnIngresar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar4ActionPerformed(evt);
            }
        });

        btnIngresar6.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar6.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar6.setText("Seleccionar imagen");
        btnIngresar6.setOpaque(true);
        btnIngresar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar6ActionPerformed(evt);
            }
        });

        btnIngresar7.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar7.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar7.setText("Seleccionar imagen");
        btnIngresar7.setOpaque(true);
        btnIngresar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar7ActionPerformed(evt);
            }
        });

        btnIngresar8.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnIngresar8.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar8.setText("Seleccionar imagen");
        btnIngresar8.setOpaque(true);
        btnIngresar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresar8ActionPerformed(evt);
            }
        });

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cboTipoSangre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboTipoSangre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "A positivo (A+)", "A negativo (A-)", "B positivo (B+)", "B negativo (B-)", "AB positivo (AB+)", "AB negativo (AB-)", "O positivo (O+)", "O negativo (O-)" }));
        cboTipoSangre.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("NUC");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NOMBRE(S)");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("APELLIDOS");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("EDAD");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("DELITO");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("LUGAR DE NACIMIENTO");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("TIPO DE SANGRE");

        txtNuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNucActionPerformed(evt);
            }
        });
        txtNuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNucKeyTyped(evt);
            }
        });

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        txtEdad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        txtNacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNacimientoActionPerformed(evt);
            }
        });
        txtNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacimientoKeyTyped(evt);
            }
        });

        txtDelito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDelito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDelitoActionPerformed(evt);
            }
        });
        txtDelito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDelitoKeyTyped(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prision2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel8)
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNuc, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdad))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDelito, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(0, 39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDelito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("INGRESE LOS DATOS ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuta5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRuta6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRuta7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl8, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuta8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtRuta1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(txtRuta2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(txtRuta3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(txtRuta4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnIngresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(btnIngresar2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(btnIngresar3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btnIngresar4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnIngresar5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(btnIngresar6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(btnIngresar7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addComponent(btnIngresar8, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(358, 358, 358)
                                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(72, 72, 72))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRuta1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRuta2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRuta3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtRuta4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIngresar1)
                    .addComponent(btnIngresar2)
                    .addComponent(btnIngresar3)
                    .addComponent(btnIngresar4))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRuta5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuta6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuta7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuta8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIngresar5)
                    .addComponent(btnIngresar6)
                    .addComponent(btnIngresar7)
                    .addComponent(btnIngresar8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl1MouseClicked

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
        String nuc = txtNuc.getText();
        String nacimiento = txtNacimiento.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edad = txtEdad.getText();
        String delito = txtDelito.getText();
        String tiposangre = (String) cboTipoSangre.getSelectedItem();
        
        

        if(nuc.isEmpty()||nacimiento.isEmpty()||nombre.isEmpty()||apellido.isEmpty()||edad.isEmpty()||tiposangre.isEmpty()){
            JOptionPane.showMessageDialog(null,"DEBES RELLENAR TODOS LOS CAMPOS");
        }else{
            try{
                String consulta = "INSERT INTO presos (rnd,nombre,apellido,edad,delito,lugarNacimiento,tipoSangre,ruta1,ruta2,ruta3,ruta4,ruta5,ruta6,ruta7,ruta8)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = (PreparedStatement) cn.prepareStatement(consulta);

                ps.setString(1, txtNuc.getText());
                ps.setString(2, txtNombre.getText());
                ps.setString(3, txtApellido.getText());
                ps.setString(4, txtEdad.getText());
                ps.setString(5, txtDelito.getText());
                ps.setString(6, txtNacimiento.getText());
                ps.setString(7, tiposangre);
                ps.setString(8, txtRuta1.getText());
                ps.setString(9, txtRuta2.getText());
                ps.setString(10, txtRuta3.getText());
                ps.setString(11, txtRuta4.getText());
                ps.setString(12, txtRuta5.getText());
                ps.setString(13, txtRuta6.getText());
                ps.setString(14, txtRuta7.getText());
                ps.setString(15, txtRuta8.getText());

                int i = ps.executeUpdate();
                nuevoIngreso();
                createEmail();
                if(i>0){
                    JOptionPane.showMessageDialog(null,"EL DELINCUENTE HA SIDO INGRESADO CORRECTAMENTE");
                    limpiar();
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"NO SE PUDO INGRESAR EL DELINCUENTE"+e);
            }
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIngresar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar5ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta5.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta5.getText());
            foto = foto.getScaledInstance(lbl5.getWidth(), lbl5.getHeight(), Image.SCALE_SMOOTH);
            lbl5.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar5ActionPerformed

    private void btnIngresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar1ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta1.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta1.getText());
            foto = foto.getScaledInstance(lbl1.getWidth(), lbl1.getHeight(), Image.SCALE_SMOOTH);
            lbl1.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar1ActionPerformed

    private void btnIngresar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar2ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta2.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta2.getText());
            foto = foto.getScaledInstance(lbl2.getWidth(), lbl2.getHeight(), Image.SCALE_SMOOTH);
            lbl2.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar2ActionPerformed

    private void btnIngresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar3ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta3.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta3.getText());
            foto = foto.getScaledInstance(lbl3.getWidth(), lbl3.getHeight(), Image.SCALE_SMOOTH);
            lbl3.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar3ActionPerformed

    private void btnIngresar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar4ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta4.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta4.getText());
            foto = foto.getScaledInstance(lbl4.getWidth(), lbl4.getHeight(), Image.SCALE_SMOOTH);
            lbl4.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar4ActionPerformed

    private void btnIngresar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar6ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta6.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta6.getText());
            foto = foto.getScaledInstance(lbl6.getWidth(), lbl6.getHeight(), Image.SCALE_SMOOTH);
            lbl6.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar6ActionPerformed

    private void btnIngresar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar7ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta7.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta7.getText());
            foto = foto.getScaledInstance(lbl7.getWidth(), lbl7.getHeight(), Image.SCALE_SMOOTH);
            lbl7.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar7ActionPerformed

    private void btnIngresar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresar8ActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)","jpg","jpeg","png");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("C:\\Users\\alexa\\OneDrive\\Imágenes\\Imagenes presos");
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            txtRuta8.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtRuta8.getText());
            foto = foto.getScaledInstance(lbl8.getWidth(), lbl8.getHeight(), Image.SCALE_SMOOTH);
            lbl8.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnIngresar8ActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginDialog login = new LoginDialog(new javax.swing.JDialog(), true);
        login.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void txtNucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNucActionPerformed
        // TODO add your handling code here:
        txtNuc.requestFocus();
        txtNuc.transferFocus();
    }//GEN-LAST:event_txtNucActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
        txtNombre.transferFocus();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
        txtApellido.transferFocus();
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
        txtEdad.transferFocus();
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtDelitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDelitoActionPerformed
        // TODO add your handling code here:
        txtDelito.transferFocus();
    }//GEN-LAST:event_txtDelitoActionPerformed

    private void txtNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNacimientoActionPerformed
        // TODO add your handling code here:
        txtNacimiento.transferFocus();
    }//GEN-LAST:event_txtNacimientoActionPerformed

    private void txtNucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNucKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNucKeyTyped

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Edad no valida, ingrese solo números");
        }
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if(Character.isDigit(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Nombre no valido, ingrese solo letras");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if(Character.isDigit(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Apellido no valido, ingrese solo letras");
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtDelitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDelitoKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if(Character.isDigit(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Delito no valido, ingrese solo letras");
        }
    }//GEN-LAST:event_txtDelitoKeyTyped

    private void txtNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacimientoKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if(Character.isDigit(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Lugar no valido, ingrese solo letras");
        }
    }//GEN-LAST:event_txtNacimientoKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro_Criminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_Criminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_Criminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_Criminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registro_Criminal dialog = new Registro_Criminal(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnIngresar1;
    private javax.swing.JButton btnIngresar2;
    private javax.swing.JButton btnIngresar3;
    private javax.swing.JButton btnIngresar4;
    private javax.swing.JButton btnIngresar5;
    private javax.swing.JButton btnIngresar6;
    private javax.swing.JButton btnIngresar7;
    private javax.swing.JButton btnIngresar8;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cboTipoSangre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDelito;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtNuc;
    private javax.swing.JLabel txtRuta1;
    private javax.swing.JLabel txtRuta2;
    private javax.swing.JLabel txtRuta3;
    private javax.swing.JLabel txtRuta4;
    private javax.swing.JLabel txtRuta5;
    private javax.swing.JLabel txtRuta6;
    private javax.swing.JLabel txtRuta7;
    private javax.swing.JLabel txtRuta8;
    // End of variables declaration//GEN-END:variables

class FondoPanel extends JPanel{
        private Image imagen;
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondo2.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
