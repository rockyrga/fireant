package com.rga.fireant.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.rga.fireant.model.TestCase;

@Component
@Path("/test-cases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestCaseFacade {

    @GET
    @Path("/{case-id}")
    public Response get(@PathParam("case-id") long caseId) {
        return null;
    }

    @GET
    public Response list() {
        return null;
    }

    @POST
    public Response save(TestCase testCase) {
        return null;
    }

    @PUT
    public Response update(TestCase testCase) {
        return null;
    }

    @DELETE
    @Path("/{case-id}")
    public Response delete(@PathParam("case-id") long caseId) {
        return null;
    }
}
