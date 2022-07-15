
package Modelo;

/**
 *
 * @author USER
 */
public class Auto {

    private int codigo;
    private String marca;
    private String color;
    private int year;
    private int precio;

    public Auto() {
    }

    public Auto(int codigo, String marca, String color, int year, int precio) {
        this.codigo = codigo;
        this.marca = marca;
        this.color = color;
        this.year = year;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Auto{" + "codigo=" + codigo + ", marca=" + marca + ", color=" + color + ", year=" + year + ", precio=" + precio + '}';
    }
    
    
}
