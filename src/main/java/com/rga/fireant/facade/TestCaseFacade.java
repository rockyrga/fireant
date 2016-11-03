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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rga.fireant.model.TestCase;
import com.rga.fireant.service.CreateTestCase;
import com.rga.fireant.service.DeleteTestCase;
import com.rga.fireant.service.GetTestCase;
import com.rga.fireant.service.ListTestCase;
import com.rga.fireant.service.UpdateTestCase;

@Component
@Path("/test-cases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestCaseFacade {

    private final GetTestCase getTestCase;
    private final ListTestCase listTestCase;
    private final CreateTestCase createTestCase;
    private final UpdateTestCase updateTestCase;
    private final DeleteTestCase deleteTestCase;

    @Autowired
    public TestCaseFacade(GetTestCase getTestCase, ListTestCase listTestCase, CreateTestCase createTestCase,
            UpdateTestCase updateTestCase, DeleteTestCase deleteTestCase) {

        this.getTestCase = getTestCase;
        this.listTestCase = listTestCase;
        this.createTestCase = createTestCase;
        this.updateTestCase = updateTestCase;
        this.deleteTestCase = deleteTestCase;
    }

    @GET
    @Path("/{case-id}")
    public Response get(@PathParam("case-id") long caseId) {
        return Response.ok(getTestCase.get(caseId)).build();
    }

    @GET
    public Response list() {
        return Response.ok(listTestCase.list()).build();
    }

    @POST
    public Response save(TestCase testCase) {
        return Response.ok(createTestCase.create(testCase)).build();
    }

    @PUT
    @Path("/{case-id}")
    public Response update(@PathParam("case-id") long caseId, TestCase testCase) {

        testCase.setId(caseId);

        return Response.ok(updateTestCase.update(testCase)).build();
    }

    @DELETE
    @Path("/{case-id}")
    public Response delete(@PathParam("case-id") long caseId) {

        deleteTestCase.delete(caseId);

        return Response.ok().build();
    }
}
