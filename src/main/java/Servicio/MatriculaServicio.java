
package Servicio;

import Modelo.Auto;
import Modelo.Matricula;
import Modelo.Propietario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class MatriculaServicio implements IMatriculaServicio {
    
    private static List<Matricula> matriculaList = new ArrayList<>();

    @Override
    public Matricula crear(Matricula matricula) {
         if(this.existeCodigo(matricula.getCodigo())){
            throw new RuntimeException("El código de la profesión ya existe"); 
        }
        this.matriculaList.add(matricula);
        try {
            this.almacenarEnArchivo(matricula, "C:/Progra/archivoMat.obj");
        } catch (Exception ex) {
            throw new RuntimeException("No se puede almacenar en archivo"+ex.getMessage());
        }
        return matricula;
    }

    @Override
    public Matricula modificar(int codigoNuevo, Matricula matriculaNuevo) {
       var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
       try {
            this.matriculaList=this.recuperarArchivo("c:/Progra/archivoMat.obj");
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        matriculaList.remove(posicion);
        matriculaList.add(posicion,matriculaNuevo);
        try {
            this.eliminarArchivo("c:/Progra/archivoMat.obj");
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<matriculaList.size();i++){
            Matricula matricula=matriculaList.get(i);
         try {
             this.almacenarEnArchivo(matricula,"c:/Progra/archivoMat.obj");
         } catch (Exception ex) {
             Logger.getLogger(MatriculaServicio.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        
     
     return matriculaNuevo;
    }

    @Override
    public Matricula eliminar(int codigo) {
       Matricula matricula = this.buscarPorCodigo(codigo);
       var posicion= this.buscarPosicion(matricula);
        try {
            System.out.println("Si entro al recuperar");
            this.matriculaList=this.recuperarArchivo("c:/Progra/archivoMat.obj");
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        matriculaList.remove(posicion);
       System.out.println(matriculaList);
        try {
            System.out.println("Si se elimino el archivo");
            this.eliminarArchivo("c:/Progra/archivoMat.obj");
        } catch (Exception ex) {
            System.out.println("No se elimino el archivo");
        }
        for(int i=0;i<matriculaList.size();i++){
            Matricula matricula2=matriculaList.get(i);
         try {
             this.almacenarEnArchivo(matricula2,"c:/Progra/archivoMat.obj");
         } catch (Exception ex) {
             System.out.println("El error esta en el for");
         }
        }
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
         try {
            this.matriculaList=this.recuperarArchivo("c:/Progra/archivoMat.obj");
        } catch (Exception ex) {
            throw new RuntimeException("No se puede recuperar datos del archivo"+ex.getMessage());
        }
        return this.matriculaList;
    }
    
     private boolean existeCodigo(int codigo)
    {
        var retorno=false;
        for(var auto:this.matriculaList){
            if(auto.getCodigo()==codigo){
                retorno=true;
                break;
            }
        }
        return retorno;
    }
     
      public boolean eliminarArchivo(String rutaArchivo) throws Exception {
        boolean llave=false;
        File archivo = new File(rutaArchivo);
        archivo.delete();
        return llave;
    }

      public List<Matricula> recuperarArchivo(String rutaArchivo) throws Exception {
        var matriculaList = new ArrayList<Matricula>();
        /*
        DataInputStream entrada=null;
        try{
            entrada = new DataInputStream(new FileInputStream(rutaArchivo));
            while(true){
                var codigo=entrada.readInt();
                var yearMatricula=entrada.readInt();
                var fechaExpira=entrada.readInt();
                var placa = entrada.readUTF();
                var auto = entrada.readInt();
                var propietario = entrada.readInt();
                var matricula = new Matricula(codigo, yearMatricula, fechaExpira, placa, auto, propietario);
                matriculaList.add(matricula);
            }
        }catch(IOException e){
            entrada.close();
        }
        */
        //var autoList = new ArrayList<Auto>();
        var fis = new FileInputStream(new File(rutaArchivo));
        ObjectInputStream entrada = null;
        try{        
            while(fis.available()>0){
                entrada = new ObjectInputStream(fis);
                Matricula matricula= (Matricula) entrada.readObject();
                matriculaList.add(matricula);
            }
            entrada.close();
        }catch(Exception ex){
            entrada.close();
        }
        return matriculaList;
    }
      
      public boolean almacenarEnArchivo(Matricula matricula, String rutaArchivo) throws Exception {
        var retorno = false;
        ObjectOutputStream salida=null;
        try{
            salida = new ObjectOutputStream( new FileOutputStream(rutaArchivo,true) );
            /*salida.writeInt(matricula.getCodigo());
            salida.writeInt(matricula.getYearMatricula());
            salida.writeInt(matricula.getFechaExpira());
            salida.write(matricula.getPlaca());
            salida.writeInt(matricula.getPropietario());*/
            salida.writeObject(matricula);
            salida.close();
            retorno=true;
        }catch(IOException e)
        {
            salida.close();
        }
        return retorno;
    }
}
