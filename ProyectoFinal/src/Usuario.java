
import java.io.Serializable;

public class Usuario implements Serializable {
    String Usuario;
    String Contraseña;
    String Puesto;
    
    Usuario(String usuario,String contraseña, String puesto){
        Usuario=usuario;
        Contraseña=contraseña;
        Puesto=puesto;
    }
    Usuario(){
        Usuario="Admin";
        Contraseña="root";
        Puesto="Administrador";
    }
    
    public String getUsuario(){
        return Usuario;
    }
    public void setUsuario(String Usuario){
        this.Usuario = Usuario;
    }
    public String getContraseña(){
        return Contraseña;
    }
    public void setContraseña(String Contraseña){
        this.Contraseña = Contraseña;
    }
    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }
}
