package samart.itthikorn;

import io.quarkus.security.Authenticated;
import samart.itthikorn.security.ResponseDTO;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/api/candidates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class CandidateResource {

    @Inject
    CandidateDAO candidateDAO;

    @GET
    @RolesAllowed("user")
   public Response listAll() {
        return Response.ok(candidateDAO.listAll().stream().map(c -> CandidateResponse.of(c)).collect(Collectors.toList())).build();
    }

    @GET
    @RolesAllowed("user")
    @Path("/{candidateId}")
    public Response findCandidateDetail(@PathParam("candidateId") String candidateId) {
        Optional<CandidateEntity> result = candidateDAO.findById(Long.parseLong(candidateId));
        if (result.isPresent())
            return Response.ok(CandidateResponse.of(result.get())).build();
        else
             return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @RolesAllowed("user")
    @Transactional
    public Response createCandidate(Candidate candidate) {
        return Response.ok(CandidateResponse.of(candidateDAO.create(candidate))).build();
    }

    @PUT
    @RolesAllowed("user")
    @Transactional
    @Path("/{candidateId}")
    public Response updateCandidate(@PathParam("candidateId") String candidateId,Candidate candidate) {
        return Response.ok(CandidateResponse.of(candidateDAO.update(candidate, Long.parseLong(candidateId)))).build();
    }

    @DELETE
    @RolesAllowed("user")
    @Transactional
    @Path("/{candidateId}")
    public Response deleteCandidate(@PathParam("candidateId") String candidateId) {
        long num = candidateDAO.delete(Long.parseLong(candidateId));

        if (num == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseDTO("error", "Candidate not found")).build();
        }
        return Response.ok(new ResponseDTO("ok")).build();
    }
}