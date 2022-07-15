
package Modelo;


public class Propietario {
    private int codigo;
    private String nombre;
    private int edad;
    private int cedula;
    private int celular;

    public Propietario(int codigo, String nombre, int edad, int cedula) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
    }

    public Propietario(int codigo, String nombre, int edad, int cedula, int celular) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
        this.celular = celular;
    }


    public Propietario() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Propietario{" + "codigo=" + codigo + ", nombre=" + nombre + ", edad=" + edad + ", cedula=" + cedula + ", celular=" + celular + '}';
    }
    
    
}
