
package Servicio;

import Modelo.Propietario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class PropietarioServicio implements IPropietarioServicio{
    
    private static List<Propietario> propietarioList= new ArrayList<>();

    @Override
    public Propietario crear(Propietario propietario) {
      this.propietarioList.add(propietario);
      return propietario;
    }

    @Override
    public Propietario modificar(int codigoNuevo, Propietario propietarioNuevo) {
     var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
     this.listar().get(posicion).setNombre(propietarioNuevo.getNombre());
     this.listar().get(posicion).setEdad(propietarioNuevo.getEdad());
     this.listar().get(posicion).setCedula(propietarioNuevo.getCedula());
     this.listar().get(posicion).setCelular(propietarioNuevo.getCelular());
     return propietarioNuevo;
             
    }

    @Override
    public Propietario eliminar(int codigo) {
        Propietario propietario = this.buscarPorCodigo(codigo);
        var posicion= this.buscarPosicion(propietario);
        this.listar().remove(posicion);
        return propietario;
        
    }

    @Override
    public Propietario buscarPorCodigo(int codigo) {
        Propietario propietario= null;
        for(var p:this.listar()){
            if(codigo==p.getCodigo()){
                propietario = p;
                break;
            }
        }
        return propietario;        
    }

    @Override
    public int buscarPosicion(Propietario propietario) {
        
        int posicion =-1;
        for(var p:this.propietarioList){
            posicion++;
            if(p.getCodigo()==propietario.getCodigo()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Propietario> listar() {
        return this.propietarioList;
    }
    
}
