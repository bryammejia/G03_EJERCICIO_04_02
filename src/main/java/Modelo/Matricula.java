
package Modelo;

/**
 *
 * @author USER
 */
public class Matricula {
    
    private int Codigo;
    private int yearMatricula;
    private int fechaExpira;
    private String placa;
    private Auto auto;
    private Propietario propietario;

    public Matricula(int Codigo, int yearMatricula, int fechaExpira, String placa, Auto auto, Propietario propietario) {
        this.Codigo = Codigo;
        this.yearMatricula = yearMatricula;
        this.fechaExpira = fechaExpira;
        this.placa = placa;
        this.auto = auto;
        this.propietario = propietario;
    }

    public Matricula() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getYearMatricula() {
        return yearMatricula;
    }

    public void setYearMatricula(int yearMatricula) {
        this.yearMatricula = yearMatricula;
    }

    public int getFechaExpira() {
        return fechaExpira;
    }

    public void setFechaExpira(int fechaExpira) {
        this.fechaExpira = fechaExpira;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Matricula{" + "Codigo=" + Codigo + ", yearMatricula=" + 
                yearMatricula + ", fechaExpira=" + fechaExpira + ", placa=" +
                placa + ", auto=" + auto.toString() + ", propietario=" + 
                propietario.toString() + '}';
    }
    
    

    
}
