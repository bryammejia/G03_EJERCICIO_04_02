
package Servicio;

import Modelo.Auto;
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

/**
 *
 * @author USER
 */
public class AutoServicio implements IAutoServicio {
    
    private static List<Auto> autoList = new ArrayList<>();

    @Override
    public Auto crear(Auto auto) {
        if(this.existeCodigo(auto.getCodigo())){
            throw new RuntimeException("El código de la profesión ya existe"); 
        }
        this.autoList.add(auto);
        try {
            this.almacenarEnArchivo(auto, "C:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            throw new RuntimeException("No se puede almacenar en archivo"+ex.getMessage());
        }
        return auto;
    }
    

    @Override
    public Auto modificar(int codigoNuevo, Auto autoNuevo) {
        var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
        this.listar().get(posicion).setMarca(autoNuevo.getMarca());
        this.listar().get(posicion).setColor(autoNuevo.getColor());
        this.listar().get(posicion).setYear(autoNuevo.getYear());
        this.listar().get(posicion).setPrecio(autoNuevo.getPrecio());
        return autoNuevo;
    }

    @Override
    public Auto eliminar(int posicion) {
      try{
            try {
                this.autoList=this.recuperarArchivo("C:/Progra/ArchivoAuto.obj");
            } catch (Exception ex) {
                System.out.println("");
            }
            
            Auto auto1=autoList.get(posicion);
            autoList.remove(posicion);
            try{
                this.eliminarArchivo("C:/Progra/ArchivoAuto.obj");
                for(int i=0;i<autoList.size();i++)
                {
                    Auto auto2=autoList.get(i);
                    this.almacenarEnArchivo(auto2,"C:/Progra/ArchivoAuto.obj");
                }
            }catch(Exception e1)
            {
                System.out.println("Error general");
            }
            
            return auto1; 
        }catch(NullPointerException ex)
        {
            throw new NullPointerException("El codigo de profesor ya existe");
        }catch(NumberFormatException ex)
        {
            throw new NumberFormatException("Error en el formato");
        }catch(RuntimeException ex)
        {
            throw new RuntimeException("La asignatura no existe");
        }
      
    }

    @Override
    public Auto buscarPorCodigo(int codigo) {
        for(var auto:this.autoList){
            if(auto.getCodigo()==codigo)
                return auto;
        }
        throw new RuntimeException("No se encontró el código de la profesión");
    }

    @Override
    public int buscarPosicion(Auto auto) {
        
        int posicion = -1;
        for(var a:this.autoList){
            posicion++;
            if(a.getCodigo()==auto.getCodigo()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Auto> listar() {
        try {
            this.autoList=this.recuperarArchivo("c:/Progra/archivoAuto.obj");
        } catch (Exception ex) {
            throw new RuntimeException("No se puede recuperar datos del archivo"+ex.getMessage());
        }
        return this.autoList;
    }

    @Override
    public boolean almacenarEnArchivo(Auto auto, String rutaArchivo) throws Exception {
        var retorno = false;
        ObjectOutputStream salida=null;
        try{
            salida = new ObjectOutputStream( new FileOutputStream(rutaArchivo,true) );
            salida.writeObject(auto);
            salida.close();
            retorno=true;
        }catch(IOException e)
        {
            salida.close();
        }
        return retorno;
    }

    @Override
    public List<Auto> recuperarArchivo(String rutaArchivo) throws Exception {
        /*var autoList = new ArrayList<Auto>();
        ObjectInputStream entrada=null;
        try{
            entrada = new ObjectInputStream(new FileInputStream(rutaArchivo));
            while(true){
                var codigo=entrada.readInt();
                var marca=entrada.readUTF();
                var color=entrada.readUTF();
                var year = entrada.readInt();
                var precio = entrada.readInt();
                var auto = new Auto(codigo, marca, color, year, precio);
                autoList.add(auto);
            }
        }catch(IOException e){
            entrada.close();
        }
        
        return autoList;*/
        var autoList = new ArrayList<Auto>();
        var fis = new FileInputStream(new File(rutaArchivo));
        ObjectInputStream entrada = null;
        try{        
            while(fis.available()>0){
                entrada = new ObjectInputStream(fis);
                Auto auto= (Auto) entrada.readObject();
                autoList.add(auto);
            }
            entrada.close();
        }catch(Exception ex){
            entrada.close();
        }
        return autoList;
        
    }
    
    public boolean eliminarArchivo(String rutaArchivo) throws Exception {
        boolean llave=false;
        File archivo = new File(rutaArchivo);
        archivo.delete();
        return llave;
    }
    
    private boolean existeCodigo(int codigo)
    {
        var retorno=false;
        for(var auto:this.autoList){
            if(auto.getCodigo()==codigo){
                retorno=true;
                break;
            }
        }
        return retorno;
    }

}
