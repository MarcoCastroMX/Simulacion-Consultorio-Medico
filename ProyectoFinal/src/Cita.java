
import java.io.Serializable;

public class Cita implements Serializable {

    String NombreCompletoPaciente;
    String Horario;
    int Dia;
    int Mes;
    int Estado;
    
    public Cita(String NombreCompletoPaciente, String Horario, int Dia, int Mes) {
        this.NombreCompletoPaciente = NombreCompletoPaciente;
        this.Horario = Horario;
        this.Dia = Dia;
        this.Mes = Mes;
        this.Estado = 1;
    }
     public Cita(String NombreCompletoPaciente, String Horario, int Dia, int Mes,int Estado) {
        this.NombreCompletoPaciente = NombreCompletoPaciente;
        this.Horario = Horario;
        this.Dia = Dia;
        this.Mes = Mes;
        this.Estado = Estado;
    }
    
    Cita(){
        
    }
    public String getNombreCompletoPaciente() {
        return NombreCompletoPaciente;
    }

    public void setNombreCompletoPaciente(String NombreCompletoPaciente) {
        this.NombreCompletoPaciente = NombreCompletoPaciente;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public int getDia() {
        return Dia;
    }

    public void setDia(int Dia) {
        this.Dia = Dia;
    }

    public int getMes() {
        return Mes;
    }

    public void setMes(int Mes) {
        this.Mes = Mes;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
}
