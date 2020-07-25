
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class Menu extends javax.swing.JFrame {
    ObjectOutputStream output=null;
    ObjectInputStream input=null;
    String Us,Pues;
    /**
     * Creates new form Menu
     */
    public Menu(String Usuario,String Puesto) throws IOException, FileNotFoundException, ClassNotFoundException {
        Us=Usuario;
        Pues=Puesto;
        initComponents();
        setIcon();
        JtxtUsuarioActual.setText("Bienvenido: "+Usuario+" con el Puesto: "+Puesto);
        
        File Pacientes = new File("Pacientes.dat");
        if(!Pacientes.exists()){
            Paciente P;
            P=new Paciente("1","1","1","1","1","1");
            EscribirPaciente(P,"Pacientes.dat");
        }
        File Citas = new File("Citas.dat");
        if(!Citas.exists()){
            Cita C;
            C=new Cita("1","1",1,1,3);
            EscribirCita(C,"Citas.dat");
        }
    }
    public boolean UsuarioRepetido(Usuario obj) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            input = new ObjectInputStream(new FileInputStream("Usuarios.dat"));
            
                try{
                    while(true){
                    Usuario Leido=(Usuario)input.readObject();  
                    if(obj.getUsuario().equals(Leido.getUsuario())){
                            input.close();
                            JOptionPane.showMessageDialog(null,"Usuario Existente");
                            return true; 
                    }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                    return false;
                }
            }
        
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
        return false;
    }
    
    public Usuario BuscarUsuario(String Usuario) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            input = new ObjectInputStream(new FileInputStream("Usuarios.dat"));
            
                try{
                    while(true){
                    Usuario Leido=(Usuario)input.readObject();  
                    if(Usuario.equals(Leido.getUsuario())){
                            input.close();
                            return Leido; 
                    }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                    return null;
                }
            }
        
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
        return null;
    }
    
    public void ActualizarUsuario(Usuario Nuevo,Usuario Anterior) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            File Archivo = new File("Usuarios.dat");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Usuario Leido=(Usuario)input.readObject();  
                        if(Anterior.getUsuario().equals(Leido.getUsuario())){
                                EscribirUsuario(Nuevo, "UsuarioAux.dat");
                        }
                        else{
                            EscribirUsuario(Leido, "UsuarioAux.dat");
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
    
    public void EliminarUsuario(Usuario Eliminar) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            File Archivo = new File("Usuarios.dat");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Usuario Leido=(Usuario)input.readObject();  
                        if(Eliminar.getUsuario().equals(Leido.getUsuario())){
                                
                        }
                        else{
                            EscribirUsuario(Leido, "UsuarioAux.dat");
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
     
    public void EscribirUsuario(Usuario obj, String Nombre) throws FileNotFoundException, IOException{
        try{ 
            File Archivo = new File(Nombre);
            if(Archivo.exists()){
                
                output = new AppendObject(new FileOutputStream(Archivo,true));
            }
            else{
                output = new ObjectOutputStream(new FileOutputStream(Archivo,true));
            }
            output.writeObject(obj);
            if(output!=null)output.close();
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
    
    public boolean PacienteRepetido(Paciente obj) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            input = new ObjectInputStream(new FileInputStream("Pacientes.dat"));
                try{
                    while(true){
                        Paciente Leido=(Paciente)input.readObject();  
                        if(obj.getNombre().equals(Leido.getNombre())){
                            if(obj.getApellidoPaterno().equals(Leido.getApellidoPaterno())){
                                if(obj.getApellidoMaterno().equals(Leido.getApellidoMaterno())){
                                    input.close();
                                    JOptionPane.showMessageDialog(null,"Paciente Existente");
                                    return true; 
                                }  
                            }  
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                    return false;
                }
            }
        
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
        return false;
    }
    
    public void EscribirPaciente(Paciente obj, String Nombre) throws FileNotFoundException, IOException{
        try{ 
            File Archivo = new File(Nombre);
            if(Archivo.exists()){
                
                output = new AppendObject(new FileOutputStream(Archivo,true));
            }
            else{
                output = new ObjectOutputStream(new FileOutputStream(Archivo,true));
            }
            output.writeObject(obj);
            if(output!=null)output.close();
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
    
    public Paciente BuscarPaciente(String NombreCompleto) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            input = new ObjectInputStream(new FileInputStream("Pacientes.dat"));
            
                try{
                    while(true){
                    Paciente Leido=(Paciente)input.readObject(); 
                    String Nombre = Leido.getNombre()+" "+Leido.getApellidoPaterno()+" "+Leido.getApellidoMaterno();
                    if(NombreCompleto.equals(Nombre)){
                            input.close();
                            return Leido; 
                    }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                    return null;
                }
            }
        
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
        return null;
    }
    
    public void ActualizarPaciente(Paciente Nuevo,Paciente Anterior) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            File Archivo = new File("Pacientes.dat");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Paciente Leido=(Paciente)input.readObject();  
                        if(Anterior.getNombre().equals(Leido.getNombre())){
                            if(Anterior.getApellidoPaterno().equals(Leido.getApellidoPaterno())){
                                if(Anterior.getApellidoMaterno().equals(Leido.getApellidoMaterno())){
                                    EscribirPaciente(Nuevo, "PacienteAux.dat");
                                }  
                            }  
                        }
                        else{
                            EscribirPaciente(Leido, "PacienteAux.dat");
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
    
    public void EliminarPaciente(Paciente Eliminar) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            File Archivo = new File("Pacientes.dat");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Paciente Leido=(Paciente)input.readObject();  
                        if(Eliminar.getNombre().equals(Leido.getNombre())){
                            if(Eliminar.getApellidoPaterno().equals(Leido.getApellidoPaterno())){
                                if(Eliminar.getApellidoMaterno().equals(Leido.getApellidoMaterno())){
                                   
                                }  
                            }  
                        }
                        else{
                            EscribirPaciente(Leido, "PacienteAux.dat");
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
    
    public boolean CitaRepetida(Cita obj) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            input = new ObjectInputStream(new FileInputStream("Citas.dat"));
            
                try{
                    while(true){
                    Cita Leido=(Cita)input.readObject();  
                    if(Leido.getNombreCompletoPaciente().equals(obj.getNombreCompletoPaciente()) && Leido.getEstado() == 1){
                        input.close();
                        JOptionPane.showMessageDialog(null,"Paciente con Cita Actual");
                        return true; 
                    }
                    if(obj.getDia()==Leido.getDia() && obj.getMes()==Leido.getMes()){
                        if(obj.getHorario().equals(Leido.getHorario())){
                            input.close();
                            JOptionPane.showMessageDialog(null,"Horario ya Ocupado");
                            return true;  
                        }   
                    }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                    return false;
                }
            }
        
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
        return false;
    }
    
    public void EscribirCita(Cita obj, String Nombre) throws FileNotFoundException, IOException{
        try{ 
            File Archivo = new File(Nombre);
            if(Archivo.exists()){
                
                output = new AppendObject(new FileOutputStream(Archivo,true));
            }
            else{
                output = new ObjectOutputStream(new FileOutputStream(Archivo,true));
            }
            output.writeObject(obj);
            if(output!=null)output.close();
        }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
    
    public Cita BuscarCita(String NombreCompleto) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            input = new ObjectInputStream(new FileInputStream("Citas.dat"));
            
                try{
                    while(true){
                        Cita Leido=(Cita)input.readObject(); 
                        if(NombreCompleto.equals(Leido.getNombreCompletoPaciente()) && Leido.getEstado()== 1){
                                input.close();
                                return Leido; 
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                    return null;
                }
            }
        
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
        return null;
    }
    
    public void ActualizaCita(Cita Nuevo) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            File Archivo = new File("Citas.dat");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Cita Leido=(Cita)input.readObject();  
                        if(Nuevo.getNombreCompletoPaciente().equals(Leido.getNombreCompletoPaciente())){
                            if(Nuevo.getMes()==(Leido.getMes())){
                                if(Nuevo.getDia()==(Leido.getDia())){
                                    if(Nuevo.getHorario().equals(Leido.getHorario())){
                                        EscribirCita(Nuevo, "CitasAux.dat");
                                    }
                                    else
                                        EscribirCita(Leido, "CitasAux.dat");
                                }
                                else
                                    EscribirCita(Leido, "CitasAux.dat");
                            }
                            else
                                EscribirCita(Leido, "CitasAux.dat");
                        }
                        else
                            EscribirCita(Leido, "CitasAux.dat");  
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
    }
    
    public Cita BuscarFinalizarCita(String NombreCompleto) throws FileNotFoundException, IOException, ClassNotFoundException{
        try{    
            input = new ObjectInputStream(new FileInputStream("Citas.dat"));
            
                try{
                    while(true){
                        Cita Leido=(Cita)input.readObject(); 
                        if(NombreCompleto.equals(Leido.getNombreCompletoPaciente())){
                            if(Leido.getEstado()==2){
                                input.close();
                                return Leido; 
                            } 
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                    return null;
                }
            }
        
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        }
        return null;
    }
    
    private void scrshtActionPerformed() {                                             
        BufferedImage screenshotImage = new BufferedImage(
                this.getBounds().width, this.getBounds().height,
                BufferedImage.TYPE_INT_RGB);
        this.paint(screenshotImage.getGraphics());
        try {
            ImageIO.write(screenshotImage, "png", new File("Receta.png" ));
        } catch (IOException ex) {
            System.err.println("Error");
        }
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtTitulo = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtusuarioCrear = new javax.swing.JTextField();
        TxtContrasenaCrear = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CbPuestoCrear = new javax.swing.JComboBox<>();
        BtnCrearUsuario = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TxtUsuarioBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtPuestoBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtUsuarioABuscar = new javax.swing.JTextField();
        BtnBuscarUsuario = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TxtContrasenaBuscar = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TxtUsuarioABuscarActualizar = new javax.swing.JTextField();
        BtnActualizarBuscarUsuario = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TxtUsuarioActulizar = new javax.swing.JTextField();
        TxtContrasenaActualizar = new javax.swing.JTextField();
        TxtPuestoActualizar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        BtnActualizarUsuario = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        TxtUsuarioEliminar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        BtnEliminarUsuario = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TxtAreaUsuarios = new javax.swing.JTextArea();
        MostrarUsuarios = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        TxtNombrePacienteCrear = new javax.swing.JTextField();
        TxtApePaternoPacienteCrear = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TxtApeMaternoPacienteCrear = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TxtEdadCrearPaciente = new javax.swing.JTextField();
        TxtSexoPacienteCrear = new javax.swing.JTextField();
        TxtDireccionPacienteCrear = new javax.swing.JTextField();
        BtnCrearPaciente = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TxtApeMaternoPacienteBuscar = new javax.swing.JTextField();
        TxtApePaternoPacienteBuscar = new javax.swing.JTextField();
        TxtNombrePacienteBuscar = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        TxtDireccionPacienteBuscar = new javax.swing.JTextField();
        TxtSexoPacienteBuscar = new javax.swing.JTextField();
        TxtEdadBuscarPaciente = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        TxtNombreCompletoPacienteBuscar = new javax.swing.JTextField();
        BtnBuscarPaciente = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        TxtNombreCompletoPacienteActualizar = new javax.swing.JTextField();
        BtnActualizarBuscarPaciente = new javax.swing.JButton();
        TxtNombrePacienteActualizar = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        TxtApePaternoPacienteActualizar = new javax.swing.JTextField();
        TxtApeMaternoPacientetActualizar = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        TxtDireccionPacienteActualizar = new javax.swing.JTextField();
        TxtSexoPacienteActualizar = new javax.swing.JTextField();
        TxtEdadActualizarPaciente = new javax.swing.JTextField();
        BtnActualizarPaciente = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        TxtNombreCompletoPacienteEliminar = new javax.swing.JTextField();
        BtnEliminarBuscarPaciente = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        MostrarPacientes = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TxtAreaPaciente = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        BtnInsertarPacientes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListCitas = new javax.swing.JList<>();
        TxtDia = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        TxtMes = new javax.swing.JTextField();
        CbHorario = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        BtnHacerCita = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        TxtNombreCompletoPacienteCita = new javax.swing.JTextField();
        BtnCambiarEstado = new javax.swing.JButton();
        CbEstado = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        TxtMesReceta = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        TxtDiaReceta = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        TxtNombreCompletoPacienteReceta = new javax.swing.JTextField();
        BtnBuscarFinalizarCita = new javax.swing.JButton();
        TxtHorarioReceta = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TxtAreaReceta = new javax.swing.JTextArea();
        BtnFinalizarCita = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaBaseDatos = new javax.swing.JTextArea();
        BtnMostrarCitas = new javax.swing.JButton();
        JtxtUsuarioActual = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TxtTitulo.setEditable(false);
        TxtTitulo.setBackground(new java.awt.Color(51, 153, 255));
        TxtTitulo.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        TxtTitulo.setForeground(new java.awt.Color(255, 255, 255));
        TxtTitulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtTitulo.setText("Consultorio Medico");

        jPanel5.setPreferredSize(new java.awt.Dimension(807, 473));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Ingresar Usuario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Ingresar Contrasena:");

        TxtusuarioCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtContrasenaCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Ingresar Puesto:");

        CbPuestoCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CbPuestoCrear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Doctor", "Recepcionista" }));

        BtnCrearUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCrearUsuario.setText("Crear Usuario");
        BtnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCrearUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtusuarioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtContrasenaCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(84, 84, 84)
                                .addComponent(CbPuestoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(BtnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtusuarioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtContrasenaCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CbPuestoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(BtnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Crear", new javax.swing.ImageIcon(getClass().getResource("/Crear.png")), jPanel5); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Usuario:");

        TxtUsuarioBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Puesto:");

        TxtPuestoBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Ingrese Usuario a Buscar:");

        TxtUsuarioABuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnBuscarUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnBuscarUsuario.setText("Buscar Usuario");
        BtnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarUsuarioActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Contrasena:");

        TxtContrasenaBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtContrasenaBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtUsuarioBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtPuestoBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(45, 45, 45)
                        .addComponent(TxtUsuarioABuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
                .addGap(278, 278, 278))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(442, 442, 442)
                .addComponent(BtnBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtUsuarioABuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(BtnBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtUsuarioBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtContrasenaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtPuestoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        jTabbedPane2.addTab("Leer", new javax.swing.ImageIcon(getClass().getResource("/Leer.png")), jPanel6); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Ingrese Usuario a Buscar:");

        TxtUsuarioABuscarActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnActualizarBuscarUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnActualizarBuscarUsuario.setText("Buscar Usuario");
        BtnActualizarBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarBuscarUsuarioActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Usuario:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Contrasena:");

        TxtUsuarioActulizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtContrasenaActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtPuestoActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Puesto:");

        BtnActualizarUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnActualizarUsuario.setText("Actualizar Usuario");
        BtnActualizarUsuario.setEnabled(false);
        BtnActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(64, 64, 64)
                        .addComponent(TxtUsuarioABuscarActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtContrasenaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtUsuarioActulizar, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtPuestoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)))
                .addGap(243, 243, 243))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnActualizarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnActualizarBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TxtUsuarioABuscarActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(BtnActualizarBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtUsuarioActulizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(41, 41, 41)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TxtContrasenaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtPuestoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(28, 28, 28)
                .addComponent(BtnActualizarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane2.addTab("Actualizar", new javax.swing.ImageIcon(getClass().getResource("/Actualizar.png")), jPanel7); // NOI18N

        TxtUsuarioEliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Ingrese Usuario a Eliminar:");

        BtnEliminarUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnEliminarUsuario.setText("Eliminar Usuario");
        BtnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jLabel12)
                .addGap(52, 52, 52)
                .addComponent(TxtUsuarioEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addGap(260, 260, 260))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(BtnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TxtUsuarioEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(BtnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Borrar", new javax.swing.ImageIcon(getClass().getResource("/Eliminar.png")), jPanel8); // NOI18N

        TxtAreaUsuarios.setColumns(20);
        TxtAreaUsuarios.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TxtAreaUsuarios.setRows(5);
        jScrollPane3.setViewportView(TxtAreaUsuarios);

        MostrarUsuarios.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        MostrarUsuarios.setText("Mostrar");
        MostrarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(MostrarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(460, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MostrarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Mostrar Todos", new javax.swing.ImageIcon(getClass().getResource("/Mostrar.png")), jPanel14); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Usuario", new javax.swing.ImageIcon(getClass().getResource("/Usuario.png")), jPanel1); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Ingresar Nombre:");

        TxtNombrePacienteCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtApePaternoPacienteCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Ingresar Apellido Paterno:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Ingresar Apellido Materno:");

        TxtApeMaternoPacienteCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Ingresar Direccion:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Ingresar Sexo:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Ingresar Edad:");

        TxtEdadCrearPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtSexoPacienteCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtDireccionPacienteCrear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnCrearPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCrearPaciente.setText("Crear Paciente");
        BtnCrearPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCrearPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtApeMaternoPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtNombrePacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtApePaternoPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(TxtEdadCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtSexoPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TxtDireccionPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel18)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(BtnCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(112, 112, 112))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel18)
                    .addComponent(TxtNombrePacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtEdadCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(TxtSexoPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtApePaternoPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(TxtDireccionPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtApeMaternoPacienteCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addComponent(BtnCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Crear", new javax.swing.ImageIcon(getClass().getResource("/Crear.png")), jPanel9); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Ingresar Nombre:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Ingresar Apellido Paterno:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Ingresar Apellido Materno:");

        TxtApeMaternoPacienteBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtApePaternoPacienteBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtNombrePacienteBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Ingresar Edad:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Ingresar Sexo:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Ingresar Direccion:");

        TxtDireccionPacienteBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtSexoPacienteBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtEdadBuscarPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Ingresar Nombre Completo:");

        TxtNombreCompletoPacienteBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnBuscarPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnBuscarPaciente.setText("Buscar Paciente");
        BtnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtApeMaternoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNombrePacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtApePaternoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TxtEdadBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtSexoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtDireccionPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel22))
                .addGap(88, 88, 88))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(57, 57, 57)
                        .addComponent(TxtNombreCompletoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(BtnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(446, 446, 446))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNombreCompletoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addComponent(BtnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22)
                    .addComponent(TxtNombrePacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtEdadBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23)
                    .addComponent(TxtSexoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtApePaternoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel24)
                    .addComponent(TxtDireccionPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtApeMaternoPacienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        jTabbedPane3.addTab("Leer", new javax.swing.ImageIcon(getClass().getResource("/Leer.png")), jPanel10); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Ingresar Nombre Completo:");

        TxtNombreCompletoPacienteActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnActualizarBuscarPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnActualizarBuscarPaciente.setText("Buscar Paciente");
        BtnActualizarBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarBuscarPacienteActionPerformed(evt);
            }
        });

        TxtNombrePacienteActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Ingresar Nombre:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Ingresar Apellido Paterno:");

        TxtApePaternoPacienteActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtApeMaternoPacientetActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Ingresar Apellido Materno:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Ingresar Edad:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Ingresar Sexo:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("Ingresar Direccion:");

        TxtDireccionPacienteActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtSexoPacienteActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtEdadActualizarPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnActualizarPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnActualizarPaciente.setText("Actualizar Paciente");
        BtnActualizarPaciente.setEnabled(false);
        BtnActualizarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtApeMaternoPacientetActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNombrePacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtApePaternoPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TxtEdadActualizarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtSexoPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtDireccionPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel30))
                .addGap(101, 101, 101))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jLabel26)
                        .addGap(130, 130, 130)
                        .addComponent(TxtNombreCompletoPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(BtnActualizarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(BtnActualizarBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNombreCompletoPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addComponent(BtnActualizarBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30)
                    .addComponent(TxtNombrePacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtEdadActualizarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31)
                    .addComponent(TxtSexoPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtApePaternoPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel32)
                    .addComponent(TxtDireccionPacienteActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtApeMaternoPacientetActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BtnActualizarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane3.addTab("Actualizar", new javax.swing.ImageIcon(getClass().getResource("/Actualizar.png")), jPanel11); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Ingresar Nombre Completo a Eliminar:");

        TxtNombreCompletoPacienteEliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnEliminarBuscarPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnEliminarBuscarPaciente.setText("Borrar Paciente");
        BtnEliminarBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarBuscarPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(BtnEliminarBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(450, 450, 450))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(TxtNombreCompletoPacienteEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(TxtNombreCompletoPacienteEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(BtnEliminarBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Borrar", new javax.swing.ImageIcon(getClass().getResource("/Eliminar.png")), jPanel12); // NOI18N

        MostrarPacientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        MostrarPacientes.setText("Mostrar");
        MostrarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarPacientesActionPerformed(evt);
            }
        });

        TxtAreaPaciente.setColumns(20);
        TxtAreaPaciente.setRows(5);
        jScrollPane4.setViewportView(TxtAreaPaciente);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(MostrarPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(460, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MostrarPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Mostrar Todos", new javax.swing.ImageIcon(getClass().getResource("/Mostrar.png")), jPanel15); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane1.addTab("Paciente", new javax.swing.ImageIcon(getClass().getResource("/Paciente.png")), jPanel2); // NOI18N

        BtnInsertarPacientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnInsertarPacientes.setText("Insetar Pacientes");
        BtnInsertarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInsertarPacientesActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(ListCitas);

        TxtDia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("Mes:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Dia:");

        TxtMes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        CbHorario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CbHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00-11:00", "11:00-12:00", "12:00-1:00", "3:00-4:00", "4:00-5:00" }));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("Horarios:");

        BtnHacerCita.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnHacerCita.setText("Hacer Cita");
        BtnHacerCita.setEnabled(false);
        BtnHacerCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHacerCitaActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("Ingresar Nombre Completo del Paciente con Cita:");

        TxtNombreCompletoPacienteCita.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnCambiarEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCambiarEstado.setText("Cambiar Estado");
        BtnCambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCambiarEstadoActionPerformed(evt);
            }
        });

        CbEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cancelar", "Reservada", "Atendido" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnInsertarPacientes)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel37)
                                .addGap(18, 18, 18)
                                .addComponent(TxtNombreCompletoPacienteCita, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addComponent(CbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(274, 274, 274)
                                        .addComponent(jLabel35)
                                        .addGap(18, 18, 18)
                                        .addComponent(TxtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel34)
                                        .addGap(18, 18, 18)
                                        .addComponent(TxtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(44, 44, 44)
                                .addComponent(jLabel36)
                                .addGap(18, 18, 18)
                                .addComponent(CbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnHacerCita, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(312, 312, 312)
                                .addComponent(BtnCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(34, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnInsertarPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(TxtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(TxtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(CbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnHacerCita, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(TxtNombreCompletoPacienteCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(CbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(BtnCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Citas", new javax.swing.ImageIcon(getClass().getResource("/Cita.png")), jPanel3); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("Mes:");

        TxtMesReceta.setEditable(false);
        TxtMesReceta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Dia:");

        TxtDiaReceta.setEditable(false);
        TxtDiaReceta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtDiaReceta.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("Horarios:");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("Ingresar Nombre Completo del Paciente con Cita:");

        TxtNombreCompletoPacienteReceta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnBuscarFinalizarCita.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnBuscarFinalizarCita.setText("Buscar Cita");
        BtnBuscarFinalizarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarFinalizarCitaActionPerformed(evt);
            }
        });

        TxtHorarioReceta.setEditable(false);
        TxtHorarioReceta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        TxtAreaReceta.setColumns(20);
        TxtAreaReceta.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        TxtAreaReceta.setRows(5);
        TxtAreaReceta.setEnabled(false);
        jScrollPane5.setViewportView(TxtAreaReceta);

        BtnFinalizarCita.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnFinalizarCita.setText("Finalizar");
        BtnFinalizarCita.setEnabled(false);
        BtnFinalizarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFinalizarCitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(26, 26, 26)
                                .addComponent(TxtNombreCompletoPacienteReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(BtnBuscarFinalizarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(TxtMesReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtDiaReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtHorarioReceta)))))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnFinalizarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(398, 398, 398))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtNombreCompletoPacienteReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(TxtHorarioReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(TxtDiaReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtMesReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(BtnBuscarFinalizarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(BtnFinalizarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Consulta", new javax.swing.ImageIcon(getClass().getResource("/Consulta.png")), jPanel4); // NOI18N

        TextAreaBaseDatos.setColumns(20);
        TextAreaBaseDatos.setRows(5);
        jScrollPane1.setViewportView(TextAreaBaseDatos);

        BtnMostrarCitas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnMostrarCitas.setText("Mostrar Citas");
        BtnMostrarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMostrarCitasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(467, Short.MAX_VALUE)
                .addComponent(BtnMostrarCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(465, 465, 465))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnMostrarCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Base de Datos", new javax.swing.ImageIcon(getClass().getResource("/Database.png")), jPanel13); // NOI18N

        JtxtUsuarioActual.setEditable(false);
        JtxtUsuarioActual.setBackground(new java.awt.Color(51, 102, 255));
        JtxtUsuarioActual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JtxtUsuarioActual.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(TxtTitulo)
            .addComponent(JtxtUsuarioActual)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TxtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(JtxtUsuarioActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCrearUsuarioActionPerformed
        try {
            if(Pues.equals("Recepcionista")){
                JOptionPane.showMessageDialog(null,"Error de Privilegios");
                return;
            }
            String Nombre,Contrasena,Puesto;
            Nombre = TxtusuarioCrear.getText();
            Contrasena = TxtContrasenaCrear.getText();
            Puesto = CbPuestoCrear.getSelectedItem().toString();
            if(Nombre.equals("") && Contrasena.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Usuario U;
            U = new Usuario(Nombre, Contrasena, Puesto);
            if(UsuarioRepetido(U)==false){
                EscribirUsuario(U,"Usuarios.dat");
            }
            TxtusuarioCrear.setText("");
            TxtContrasenaCrear.setText("");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnCrearUsuarioActionPerformed

    private void BtnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarUsuarioActionPerformed
        try {
            String Usuario=TxtUsuarioABuscar.getText();
            if(Usuario.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Usuario Leido=BuscarUsuario(Usuario);
            if(Leido!=null){
                TxtUsuarioBuscar.setText(Leido.getUsuario());
                TxtContrasenaBuscar.setText(Leido.getContraseña());
                TxtPuestoBuscar.setText(Leido.getPuesto());
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnBuscarUsuarioActionPerformed

    private void BtnActualizarBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarBuscarUsuarioActionPerformed
        try {
            if(Pues.equals("Recepcionista")){
                JOptionPane.showMessageDialog(null,"Error de Privilegios");
                return;
            }
            String Usuario=TxtUsuarioABuscarActualizar.getText();
             if(Usuario.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Usuario Leido=BuscarUsuario(Usuario);
            if(Leido!=null){
                TxtUsuarioActulizar.setText(Leido.getUsuario());
                TxtContrasenaActualizar.setText(Leido.getContraseña());
                TxtPuestoActualizar.setText(Leido.getPuesto());
                this.BtnActualizarUsuario.setEnabled(true);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnActualizarBuscarUsuarioActionPerformed

    private void BtnActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarUsuarioActionPerformed
        try {
            String UsuarioActualizar=TxtUsuarioABuscarActualizar.getText();
            Usuario Anterior=BuscarUsuario(UsuarioActualizar);
            String Nombre,Contrasena,Puesto;
            Nombre=TxtUsuarioActulizar.getText();
            
            Contrasena=TxtContrasenaActualizar.getText();
            Puesto=TxtPuestoActualizar.getText();
            if(Nombre.equals("")||Contrasena.equals("")||Puesto.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            if(!Puesto.equals("Administrador") && !Puesto.equals("Doctor") && !Puesto.equals("Recepcionista")){
                JOptionPane.showMessageDialog(null,"Puesto Invalido");
                return;
            }
            Usuario Nuevo = new Usuario(Nombre, Contrasena, Puesto);
            if(Anterior.getUsuario().equals(Nuevo.getUsuario())){
                JOptionPane.showMessageDialog(null,"Necesario Modificar Usuario");
                return;
            }
            
            if(UsuarioRepetido(Nuevo)==true){
                return;
            }
            ActualizarUsuario(Nuevo,Anterior);
            
            File Aux = new File("UsuarioAux.dat");
            File Archivo = new File("Usuarios.dat");
            
            Archivo.delete();
            Aux.renameTo(Archivo);
            this.BtnActualizarUsuario.setEnabled(false);
            TxtUsuarioActulizar.setText("");
            TxtContrasenaActualizar.setText("");
            TxtPuestoActualizar.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnActualizarUsuarioActionPerformed

    private void BtnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarUsuarioActionPerformed
        try {
            if(Pues.equals("Recepcionista")){
                JOptionPane.showMessageDialog(null,"Error de Privilegios");
                return;
            }
            String Nombre;
            Nombre = TxtUsuarioEliminar.getText();
             if(Nombre.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Usuario Eliminar=BuscarUsuario(Nombre);
            if(Eliminar.getPuesto().equals("Administrador") && !Pues.equals("Administrador")){
                JOptionPane.showMessageDialog(null,"Error de Privilegios");
                return;
            }
            if(Eliminar==null){
                JOptionPane.showMessageDialog(null,"Usuario Inexistente");
                return;
            }
            EliminarUsuario(Eliminar);
            File Aux = new File("UsuarioAux.dat");
            File Archivo = new File("Usuarios.dat");
            
            Archivo.delete();
            Aux.renameTo(Archivo);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnEliminarUsuarioActionPerformed

    private void BtnCrearPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCrearPacienteActionPerformed
        try {
            String Nombre,ApePaterno,ApeMaterno,Edad,Sexo,Direccion;
            Nombre = TxtNombrePacienteCrear.getText();
            ApePaterno=TxtApePaternoPacienteCrear.getText();
            ApeMaterno=TxtApeMaternoPacienteCrear.getText();
            Edad=TxtEdadCrearPaciente.getText();
            Sexo=TxtSexoPacienteCrear.getText();
            Direccion=TxtDireccionPacienteCrear.getText();
            if(Nombre.equals("") || ApePaterno.equals("") || ApeMaterno.equals("") || Edad.equals("") || Sexo.equals("") || Direccion.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Paciente P;
            P = new Paciente(Nombre, ApePaterno, ApeMaterno,Edad,Sexo,Direccion);
            if(PacienteRepetido(P)==false){
                EscribirPaciente(P,"Pacientes.dat");
            }
            TxtNombrePacienteCrear.setText("");
            TxtApePaternoPacienteCrear.setText("");
            TxtApeMaternoPacienteCrear.setText("");
            TxtEdadCrearPaciente.setText("");
            TxtSexoPacienteCrear.setText("");
            TxtDireccionPacienteCrear.setText("");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnCrearPacienteActionPerformed

    private void BtnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarPacienteActionPerformed
        try {
            String NombreCompleto=TxtNombreCompletoPacienteBuscar.getText();
             if(NombreCompleto.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Paciente Leido=BuscarPaciente(NombreCompleto);
            if(Leido!=null){
                TxtNombrePacienteBuscar.setText(Leido.getNombre());
                TxtApePaternoPacienteBuscar.setText(Leido.getApellidoPaterno());
                TxtApeMaternoPacienteBuscar.setText(Leido.getApellidoMaterno());
                TxtEdadBuscarPaciente.setText(Leido.getEdad());
                TxtSexoPacienteBuscar.setText(Leido.getSexo());
                TxtDireccionPacienteBuscar.setText(Leido.getDireccion());
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnBuscarPacienteActionPerformed

    private void BtnActualizarBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarBuscarPacienteActionPerformed
        try {
            String NombreCompleto=TxtNombreCompletoPacienteActualizar.getText();
             if(NombreCompleto.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Paciente Leido=BuscarPaciente(NombreCompleto);
            if(Leido!=null){
                TxtNombrePacienteActualizar.setText(Leido.getNombre());
                TxtApePaternoPacienteActualizar.setText(Leido.getApellidoPaterno());
                TxtApeMaternoPacientetActualizar.setText(Leido.getApellidoMaterno());
                TxtEdadActualizarPaciente.setText(Leido.getEdad());
                TxtSexoPacienteActualizar.setText(Leido.getSexo());
                TxtDireccionPacienteActualizar.setText(Leido.getDireccion());
                this.BtnActualizarPaciente.setEnabled(true);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnActualizarBuscarPacienteActionPerformed

    private void BtnActualizarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarPacienteActionPerformed
        try {
            String NombreCompleto=TxtNombreCompletoPacienteActualizar.getText();
            Paciente Anterior=BuscarPaciente(NombreCompleto);
            
            String Nombre,ApePaterno,ApeMaterno,Edad,Sexo,Direccion;
            Nombre = TxtNombrePacienteActualizar.getText();
            ApePaterno=TxtApePaternoPacienteActualizar.getText();
            ApeMaterno=TxtApeMaternoPacientetActualizar.getText();
            Edad=TxtEdadActualizarPaciente.getText();
            Sexo=TxtSexoPacienteActualizar.getText();
            Direccion=TxtDireccionPacienteActualizar.getText();
            
            if(Nombre.equals("")||ApePaterno.equals("")||ApeMaterno.equals("")){
                JOptionPane.showMessageDialog(null,"Campos Minmos Vacios");
                return;
            }
            Paciente Nuevo = new Paciente(Nombre, ApePaterno, ApeMaterno,Edad,Sexo,Direccion);
            if(PacienteRepetido(Nuevo)==true){
                return;
            }
            ActualizarPaciente(Nuevo, Anterior);
            
            File Aux = new File("PacienteAux.dat");
            File Archivo = new File("Pacientes.dat");
            
            Archivo.delete();
            Aux.renameTo(Archivo);
            TxtNombrePacienteActualizar.setText("");
            TxtApePaternoPacienteActualizar.setText("");
            TxtApeMaternoPacientetActualizar.setText("");
            TxtEdadActualizarPaciente.setText("");
            TxtSexoPacienteActualizar.setText("");
            TxtDireccionPacienteActualizar.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnActualizarPacienteActionPerformed

    private void BtnEliminarBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarBuscarPacienteActionPerformed
        try {
            String NombreCompleto;
            NombreCompleto = TxtNombreCompletoPacienteEliminar.getText();
            Paciente Eliminar=BuscarPaciente(NombreCompleto);
            if(Eliminar==null){
                JOptionPane.showMessageDialog(null,"Paciente Inexistente");
                return;
            }
            EliminarPaciente(Eliminar);
            File Aux = new File("PacienteAux.dat");
            File Archivo = new File("Pacientes.dat");
            
            Archivo.delete();
            Aux.renameTo(Archivo);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnEliminarBuscarPacienteActionPerformed

    private void BtnInsertarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInsertarPacientesActionPerformed
        try{    
            input = new ObjectInputStream(new FileInputStream("Pacientes.dat"));
            DefaultListModel list = new DefaultListModel();
            String Nombre="NULL";
                try{
                    while(true){
                        Paciente Leido=(Paciente)input.readObject(); 
                        Nombre = Leido.getNombre()+" "+Leido.getApellidoPaterno()+" "+Leido.getApellidoMaterno();
                        if(Leido.getNombre()!=null && !Nombre.equals("1 1 1")){
                            list.addElement(Nombre+System.lineSeparator());
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(Nombre.equals("1 1 1")){
                       JOptionPane.showMessageDialog(null,"No Existen Pacientes"); 
                       return;
                }
                if(input!=null){
                    input.close();
                    ListCitas.setModel(list);
                    this.BtnHacerCita.setEnabled(true);
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnInsertarPacientesActionPerformed

    private void BtnHacerCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHacerCitaActionPerformed
        try {
            String Paciente,Horario;
            int Mes,Dia;
            Paciente=ListCitas.getSelectedValue();
            if(ListCitas.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un Paciente");
                return;
            }
            if(TxtMes.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Ingrese un Mes");
                return;
            }
            if(TxtDia.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Ingrese un Dia");
                return;
            }
            Horario=CbHorario.getSelectedItem().toString();

            Mes=Integer.parseInt(TxtMes.getText());
            Dia=Integer.parseInt(TxtDia.getText());
            
            if(Mes>12 || Dia>31){
                JOptionPane.showMessageDialog(null,"Fecha de Cita Invalida");
                return;
            }
            if(Mes==2 && Dia>28){
                JOptionPane.showMessageDialog(null,"Fecha de Cita Invalida");
                return;
            }
            
            Cita C=new Cita(Paciente,Horario,Dia,Mes);
            if(CitaRepetida(C)==true){
                return;
            }
            EscribirCita(C, "Citas.dat");
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnHacerCitaActionPerformed

    private void BtnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCambiarEstadoActionPerformed
        try {
            String NombreCompleto;
            int Estado = CbEstado.getSelectedIndex();
            NombreCompleto=TxtNombreCompletoPacienteCita.getText()+System.lineSeparator();
            Cita Leido=BuscarCita(NombreCompleto);
            if(Leido!=null){
                Leido.setEstado(Estado);
                ActualizaCita(Leido);
                File Aux = new File("CitasAux.dat");
                File Archivo = new File("Citas.dat");

                Archivo.delete();
                Aux.renameTo(Archivo);
            }
            if(Leido==null)
                JOptionPane.showMessageDialog(null,"Cita Inexistente");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnCambiarEstadoActionPerformed

    private void BtnBuscarFinalizarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarFinalizarCitaActionPerformed
         try {
            if(Pues.equals("Recepcionista")){
                JOptionPane.showMessageDialog(null,"Error de Privilegios");
                return;
            }
            String NombreCompleto;
            NombreCompleto=TxtNombreCompletoPacienteReceta.getText()+System.lineSeparator();
            if(NombreCompleto.equals(""+System.lineSeparator())){
                JOptionPane.showMessageDialog(null,"Campos Vacios");
                return;
            }
            Cita Leido=BuscarFinalizarCita(NombreCompleto);
            if(Leido!=null){
                Leido.setEstado(3);
                ActualizaCita(Leido);
                File Aux = new File("CitasAux.dat");
                File Archivo = new File("Citas.dat");

                Archivo.delete();
                Aux.renameTo(Archivo);
            }
            if(Leido==null){
                JOptionPane.showMessageDialog(null,"Cita Inexistente y/o Sin Estado de Atendido");
                return;
            }
            TxtHorarioReceta.setText(Leido.getHorario());
            TxtDiaReceta.setText(String.valueOf(Leido.getDia()));
            TxtMesReceta.setText(String.valueOf(Leido.getMes()));
            BtnFinalizarCita.setEnabled(true);
            TxtAreaReceta.setEnabled(true);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnBuscarFinalizarCitaActionPerformed

    private void BtnMostrarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMostrarCitasActionPerformed
        try{    
            File Archivo = new File("Citas.dat");
            TextAreaBaseDatos.setText("");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Cita Leido=(Cita)input.readObject(); 
                        if(Leido.getNombreCompletoPaciente()!=null && !Leido.getNombreCompletoPaciente().equals("1")){
                            String C="Nombre: "+Leido.getNombreCompletoPaciente()+" Horario: "+Leido.getHorario()+" Mes: "+String.valueOf(Leido.getMes())+" Dia: "+String.valueOf(Leido.getDia())+" Estado: "+String.valueOf(Leido.getEstado());
                            TextAreaBaseDatos.append(C+System.lineSeparator());
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnMostrarCitasActionPerformed

    private void MostrarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarUsuariosActionPerformed
        try{    
            File Archivo = new File("Usuarios.dat");
            TxtAreaUsuarios.setText("");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Usuario Leido=(Usuario)input.readObject(); 
                        String C="Usuario: "+Leido.getUsuario()+" Puesto: "+Leido.getPuesto();
                        TxtAreaUsuarios.append(C+System.lineSeparator());
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MostrarUsuariosActionPerformed

    private void MostrarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarPacientesActionPerformed
         try{    
            File Archivo = new File("Pacientes.dat");
            TxtAreaPaciente.setText("");
            input = new ObjectInputStream(new FileInputStream(Archivo));
                try{
                    while(true){
                        Paciente Leido=(Paciente)input.readObject(); 
                        if(!Leido.getNombre().equals("1")){
                            String C="Nombre: "+Leido.getNombre()+" Apellido Paterno: "+Leido.getApellidoPaterno()+" Apellido Materno: "+Leido.getApellidoMaterno()+" Edad: "+Leido.getEdad()+" Sexo: "+Leido.getSexo()+" Direccion: "+Leido.getDireccion();
                            TxtAreaPaciente.append(C+System.lineSeparator());
                        }
                    }
                }catch(IOException | ClassNotFoundException ex){
                }
                if(input!=null){
                    input.close();
                }
            }
        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Archivo Inexistente");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MostrarPacientesActionPerformed

    private void BtnFinalizarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFinalizarCitaActionPerformed
        scrshtActionPerformed();  
        BtnFinalizarCita.setEnabled(false);
        TxtAreaReceta.setEnabled(false);
        TxtNombreCompletoPacienteReceta.setText("");
        TxtHorarioReceta.setText("");
        TxtDiaReceta.setText("");
        TxtMesReceta.setText("");
        TxtAreaReceta.setText("");       
    }//GEN-LAST:event_BtnFinalizarCitaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizarBuscarPaciente;
    private javax.swing.JButton BtnActualizarBuscarUsuario;
    private javax.swing.JButton BtnActualizarPaciente;
    private javax.swing.JButton BtnActualizarUsuario;
    private javax.swing.JButton BtnBuscarFinalizarCita;
    private javax.swing.JButton BtnBuscarPaciente;
    private javax.swing.JButton BtnBuscarUsuario;
    private javax.swing.JButton BtnCambiarEstado;
    private javax.swing.JButton BtnCrearPaciente;
    private javax.swing.JButton BtnCrearUsuario;
    private javax.swing.JButton BtnEliminarBuscarPaciente;
    private javax.swing.JButton BtnEliminarUsuario;
    private javax.swing.JButton BtnFinalizarCita;
    private javax.swing.JButton BtnHacerCita;
    private javax.swing.JButton BtnInsertarPacientes;
    private javax.swing.JButton BtnMostrarCitas;
    private javax.swing.JComboBox<String> CbEstado;
    private javax.swing.JComboBox<String> CbHorario;
    private javax.swing.JComboBox<String> CbPuestoCrear;
    private javax.swing.JTextField JtxtUsuarioActual;
    private javax.swing.JList<String> ListCitas;
    private javax.swing.JButton MostrarPacientes;
    private javax.swing.JButton MostrarUsuarios;
    private javax.swing.JTextArea TextAreaBaseDatos;
    private javax.swing.JTextField TxtApeMaternoPacienteBuscar;
    private javax.swing.JTextField TxtApeMaternoPacienteCrear;
    private javax.swing.JTextField TxtApeMaternoPacientetActualizar;
    private javax.swing.JTextField TxtApePaternoPacienteActualizar;
    private javax.swing.JTextField TxtApePaternoPacienteBuscar;
    private javax.swing.JTextField TxtApePaternoPacienteCrear;
    private javax.swing.JTextArea TxtAreaPaciente;
    private javax.swing.JTextArea TxtAreaReceta;
    private javax.swing.JTextArea TxtAreaUsuarios;
    private javax.swing.JTextField TxtContrasenaActualizar;
    private javax.swing.JTextField TxtContrasenaBuscar;
    private javax.swing.JTextField TxtContrasenaCrear;
    private javax.swing.JTextField TxtDia;
    private javax.swing.JTextField TxtDiaReceta;
    private javax.swing.JTextField TxtDireccionPacienteActualizar;
    private javax.swing.JTextField TxtDireccionPacienteBuscar;
    private javax.swing.JTextField TxtDireccionPacienteCrear;
    private javax.swing.JTextField TxtEdadActualizarPaciente;
    private javax.swing.JTextField TxtEdadBuscarPaciente;
    private javax.swing.JTextField TxtEdadCrearPaciente;
    private javax.swing.JTextField TxtHorarioReceta;
    private javax.swing.JTextField TxtMes;
    private javax.swing.JTextField TxtMesReceta;
    private javax.swing.JTextField TxtNombreCompletoPacienteActualizar;
    private javax.swing.JTextField TxtNombreCompletoPacienteBuscar;
    private javax.swing.JTextField TxtNombreCompletoPacienteCita;
    private javax.swing.JTextField TxtNombreCompletoPacienteEliminar;
    private javax.swing.JTextField TxtNombreCompletoPacienteReceta;
    private javax.swing.JTextField TxtNombrePacienteActualizar;
    private javax.swing.JTextField TxtNombrePacienteBuscar;
    private javax.swing.JTextField TxtNombrePacienteCrear;
    private javax.swing.JTextField TxtPuestoActualizar;
    private javax.swing.JTextField TxtPuestoBuscar;
    private javax.swing.JTextField TxtSexoPacienteActualizar;
    private javax.swing.JTextField TxtSexoPacienteBuscar;
    private javax.swing.JTextField TxtSexoPacienteCrear;
    private javax.swing.JTextField TxtTitulo;
    private javax.swing.JTextField TxtUsuarioABuscar;
    private javax.swing.JTextField TxtUsuarioABuscarActualizar;
    private javax.swing.JTextField TxtUsuarioActulizar;
    private javax.swing.JTextField TxtUsuarioBuscar;
    private javax.swing.JTextField TxtUsuarioEliminar;
    private javax.swing.JTextField TxtusuarioCrear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("titulo.png")));
    }
}
