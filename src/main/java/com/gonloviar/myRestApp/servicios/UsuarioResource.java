
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
        System.out.println("Creando los usuarios");
        ingresarUsuariosPredefinidos();
    }
    

//    probar lo del asyncronous, haciendo un "stop" y haciendo otra peticion
//    @GET
//    @Path("{id}")
//    @Produces("text/html")
//    public String getUsuario(@PathParam("id") int id){
//        Usuario user = usuarioDB.get(id);
//        if(user == null){
//            System.out.println("--------paila mijo, no hay ese usuario-------");
//            throw new WebApplicationException(Response.Status.NO_CONTENT);
//        }
//        
//        return
//                user.getId() + "__" +user.getNombre()+ "__"+user.getApellido();
//    }
    
    @GET
    @Path("{id}")
    @Produces("text/html")
    public String getUsuario(@PathParam("id") int id){
        
        System.out.println("ESTO POR DONDE SALE? que mierda ");
       return
               "<h3> funcion&aacute; pues hijueputa!! </h3>" +
               "en la url he puesto:&nbsp; "+id;
    }


    private void ingresarUsuariosPredefinidos() {  
        //user 1
        Usuario user1 = new Usuario();
        user1.setNombre("alejo");
        user1.setApellido("gonzalez");
        user1.setId(idCounter.incrementAndGet());
        usuarioDB.put(user1.getId(), user1);
        System.out.println("Usuario 1 creado");
        
        //user 2
        Usuario user2 = new Usuario();
        user1.setNombre("johanna");
        user1.setApellido("monroy");
        user1.setId(idCounter.incrementAndGet());
        usuarioDB.put(user1.getId(), user1);
        System.out.println("Usuario 2 creado");
    }
    
    
    
}
