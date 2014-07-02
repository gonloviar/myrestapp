
package com.gonloviar.myRestApp.servicios;

import com.gonloviar.myRestApp.dominio.Usuario;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author yotas
 */
@Path("/usuarios")
public class UsuarioResource {
    
    private Map<Integer, Usuario> usuarioDB = 
            new ConcurrentHashMap<>();
    
    private AtomicInteger idCounter = new AtomicInteger();

    public UsuarioResource() {        
//        usuarios de prueba
        System.out.println("Creando los usuarios");        
        addUsuarioPredefinido("Alejandro", "Gonzalez");
        addUsuarioPredefinido("Johanna", "Monroy");
    }    

//    probar lo del asyncronous, haciendo un "stop" y haciendo otra peticion
//    Comprobando que JAX-RS funcione [OK]
    @GET
    @Path("/string/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String getUsuarioPorId(@PathParam("id") int id, 
                                @HeaderParam("x-my-header") String header){
        Usuario user = usuarioDB.get(id);        
        if(user == null){
            System.err.println("--------Paila mijo, no hay ese usuario------");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }        
        return
                user.getId() + "__" +user.getNombre()+ "__"+user.getApellido() +
                "<br>" + "Mi header (x-my-header): " + header;
    }
    
    
//    Compruevo que la integracion de Jackson funcione bien y me devuelva 
//    el objeto JSON [OK]
    @GET
    @Path("/json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioJson(@PathParam("id") int id){
        Usuario user = usuarioDB.get(id);
        if(user == null){
            System.err.println("------Paila mijo, no hay ese usuario------");
            throw new NotFoundException();
        }        
        return user;
    }
    
//   Usando un Response "Manofacturado" por mi, añandiendo algunos headers [ok]
    @GET
    @Path("/json-custom-response/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioJsonCustomResponse(@PathParam("id") int id){
        Usuario user = usuarioDB.get(id);
        if(user == null){
            System.err.println("------Paila mijo, no hay ese usuario------");
            throw new NotFoundException();
        }        
        return Response
                .ok(user)
                .language("es")
                .header("x-my-header", "valor de mi header")
                .build();
    }
    
//    Funciona espectacularmente. Envia un array json con los objetos [OK]
    @GET
    @Path("/jsonlist/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> UsuariosArray = new ArrayList<>();
        for (Entry<Integer, Usuario> e: usuarioDB.entrySet()){
            System.err.println("Usuario leido:  " + e.getValue().getNombre());
            UsuariosArray.add(e.getValue());
        }
        return UsuariosArray; 
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
