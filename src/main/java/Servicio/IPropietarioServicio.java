
package Servicio;

import Modelo.Propietario;
import java.util.List;


public interface IPropietarioServicio {
    
    public Propietario crear(Propietario propietario);
    public Propietario modificar(int codigoNuevo, Propietario propietario);
    public Propietario eliminar(int codigo);
    public Propietario buscarPorCodigo(int codigo);
    public int buscarPosicion(Propietario propietario);
    public List<Propietario> listar();
    
}
