package br.com.cmedina.starwars.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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


@Path("planetas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
public class PlanetaResource {

	PlanetaDAO planetaDAO = new PlanetaDAO();
	
    @GET
    public Response getPlanetas(@QueryParam("nome") String nome) {
    	if (nome != null)
    		return Response.ok(planetaDAO.createQuery().field("nome").contains(nome).asList()).build();
    	
        return Response.ok(planetaDAO.find().asList()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getPlanetaById(@PathParam("id") String id) {
    	Planeta p = planetaDAO.get(new ObjectId(id));
    	if (p == null)
    		return Response.status(Response.Status.NOT_FOUND).build();	
        return Response.ok(p).build();
    }
    
    @POST
    public Response incluirPlaneta(Planeta planeta, @Context UriInfo uriInfo) {
    	
    	String uri = "https://swapi.co/api/planets/";
        
    	Client client = ClientBuilder.newBuilder().build();
 
        WebTarget webTarget = client.target(uri).queryParam("search", planeta.getNome());
    	 
    	Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();
    	
    	SWModelListDTO<PlanetDTO> res = response.readEntity(new GenericType<SWModelListDTO<PlanetDTO>>(){});
    	PlanetDTO planet = res.results.get(0);  
    	
    	planeta.setQuantAparicoes(planet != null ? planet.filmsUrls.size() : 0);
    	
    	planetaDAO.save(planeta);
    	
    	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(planeta.getId().toString());
        return Response.created(builder.build()).build();
        
    }
    
    @PUT
    @Path("/{id}")
    public Response alterarPlaneta(@PathParam("id") String id, Planeta planeta) {
    	planetaDAO.save(planeta);
        return Response.ok(planeta).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response excluirPlaneta(@PathParam("id") String id) {
    	Planeta p = planetaDAO.get(new ObjectId(id));
    	if (p == null)
    		return Response.status(Response.Status.NOT_FOUND).build();	
    	planetaDAO.delete(p);
        return Response.noContent().build();
    }
    
}
