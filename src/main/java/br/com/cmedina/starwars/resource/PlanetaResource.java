package br.com.cmedina.starwars.resource;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.bson.types.ObjectId;

import br.com.cmedina.starwars.dao.PlanetaDAO;
import br.com.cmedina.starwars.dto.PlanetDTO;
import br.com.cmedina.starwars.dto.SWModelListDTO;
import br.com.cmedina.starwars.entities.Planeta;


@Path("/")
public class PlanetaResource {

	PlanetaDAO planetaDAO = new PlanetaDAO();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Path("/planetas")
    public Response getPlanetas() {
        return Response.ok(planetaDAO.find().asList()).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Path("/planetas/{id}")
    public Response getPlanetaById(@PathParam("id") String id) {
        return Response.ok(planetaDAO.get(new ObjectId(id))).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/planetas")
    public Response incluirPlaneta(Planeta planeta, @Context UriInfo uriInfo) throws KeyManagementException, NoSuchAlgorithmException {
    	
    	String uri = "https://swapi.co/api/planets/";
        
    	Client client = ClientBuilder.newBuilder().build();
 
        WebTarget webTarget = client.target(uri).queryParam("search", planeta.getNome());
    	 
    	Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();
    	
    	SWModelListDTO<PlanetDTO> res = response.readEntity(new GenericType<SWModelListDTO<PlanetDTO>>(){});
    	PlanetDTO planet = res.results.get(0);  

    	if (planet != null) 
    		planeta.setQuantAparicoes(planet.filmsUrls.size());
    	
    	planetaDAO.save(planeta);
    	
    	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(planeta.getId());
        return Response.created(builder.build()).build();
        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Path("/planetas/{id}")
    public Response alterarPlaneta(@PathParam("id") String id, Planeta planeta) {
    	planetaDAO.save(planeta);
        return Response.ok(planeta).build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Path("/planetas/{id}")
    public Response excluirPlaneta(@PathParam("id") String id) {
    	planetaDAO.deleteById(id);
        return Response.noContent().build();
    }
    
}
