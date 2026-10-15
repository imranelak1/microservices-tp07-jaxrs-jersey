package ma.rest.spring.web;
import ma.rest.spring.entities.Compte;import ma.rest.spring.repositories.CompteRepository;
import org.springframework.stereotype.Component;
import jakarta.ws.rs.*;import jakarta.ws.rs.core.*;import jakarta.validation.Valid;import java.util.List;
/** Ressource JAX-RS, sans annotations de contrôleur Spring MVC. */
@Component @Path("/banque") @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class CompteRestJaxRSAPI {
    private final CompteRepository comptes;
    public CompteRestJaxRSAPI(CompteRepository comptes){this.comptes=comptes;}
    @GET @Path("/comptes") public List<Compte> getComptes(){return comptes.findAll();}
    @GET @Path("/comptes/{id}") public Compte getCompte(@PathParam("id") Long id){return comptes.findById(id).orElseThrow(NotFoundException::new);}
    @POST @Path("/comptes") @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addCompte(@Valid Compte compte,@Context UriInfo uri){compte.setId(null);Compte saved=comptes.save(compte);return Response.created(uri.getAbsolutePathBuilder().path(saved.getId().toString()).build()).entity(saved).build();}
    @PUT @Path("/comptes/{id}") @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Compte updateCompte(@PathParam("id") Long id,@Valid Compte details){Compte existing=getCompte(id);existing.setSolde(details.getSolde());existing.setDateCreation(details.getDateCreation());existing.setType(details.getType());return comptes.save(existing);}
    @DELETE @Path("/comptes/{id}") public Response deleteCompte(@PathParam("id") Long id){comptes.delete(getCompte(id));return Response.noContent().build();}
}
