
package Servicio;

import Modelo.Matricula;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class MatriculaServicio implements IMatriculaServicio {
    
    private static List<Matricula> matriculaList = new ArrayList<>();

    @Override
    public Matricula crear(Matricula matricula) {
        this.matriculaList.add(matricula);
        return matricula;
    }

    @Override
    public Matricula modificar(int codigoNuevo, Matricula matriculaNuevo) {
       var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
       this.listar().get(posicion).setYearMatricula(matriculaNuevo.getYearMatricula());
       this.listar().get(posicion).setFechaExpira(matriculaNuevo.getFechaExpira());
       this.listar().get(posicion).setPlaca(matriculaNuevo.getPlaca());
       this.listar().get(posicion).setAuto(matriculaNuevo.getAuto());
       this.listar().get(posicion).setPropietario(matriculaNuevo.getPropietario());
       return matriculaNuevo;
    }

    @Override
    public Matricula eliminar(int codigo) {
       Matricula matricula = this.buscarPorCodigo(codigo);
       var posicion = this.buscarPosicion(matricula);
       this.listar().remove(posicion);
       return matricula;
       
    }

    @Override
    public Matricula buscarPorCodigo(int codigo) {
        Matricula matricula = null;
        for(var m:this.listar()){
            if(codigo==m.getCodigo()){
                matricula = m;
                break;
            }
        }
       return matricula;
    }

    @Override
    public int buscarPosicion(Matricula matricula) {
        
        int posicion = -1;
        for(var m:this.matriculaList){
        posicion++;
        if(m.getCodigo()==matricula.getCodigo()){
            break;
        }
    }
        return posicion;
    }

    @Override
    public List<Matricula> listar() {
         return this.matriculaList;
    }


}
