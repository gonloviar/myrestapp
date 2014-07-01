
package com.gonloviar.myRestApp.servicios;

import com.gonloviar.myRestApp.dominio.Usuario;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author yotas
 */
@Path("/usuarios")
public class UsuarioResource {
    
    private Map<Integer, Usuario> usuarioDB = 
            new ConcurrentHashMap<Integer, Usuario>();
    
    private AtomicInteger idCounter = new AtomicInteger();

    public UsuarioResource() {        
//        usuarios de prueba
        System.out.println("Creando los usuarios");        
        addUsuarioPredefinido("Alejandro", "Gonzalez");
        addUsuarioPredefinido("Johanna", "Monroy");
    }
    

//    probar lo del asyncronous, haciendo un "stop" y haciendo otra peticion
    @GET
    @Path("{id}")
    @Produces("text/html")
    public String getUsuario(@PathParam("id") int id){
        Usuario user = usuarioDB.get(id);        
        if(user == null){
            System.err.println("--------Paila mijo, no hay ese usuario------");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return
                user.getId() + "__" +user.getNombre()+ "__"+user.getApellido();
    }

    private void addUsuarioPredefinido(String nombre, String apellido) {
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setId(idCounter.incrementAndGet());
        usuarioDB.put(user.getId(), user);
        System.err.println("Usuario " + user.getId() + " creado.");
    }
    
    
    
}
