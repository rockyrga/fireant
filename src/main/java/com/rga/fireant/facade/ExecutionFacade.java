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

import com.rga.fireant.model.Execution;
import com.rga.fireant.service.AddExecution;
import com.rga.fireant.service.DeleteExecution;
import com.rga.fireant.service.ListExecution;
import com.rga.fireant.service.UpdateExecution;

@Component
@Path("/test-cases/{case-id}/executions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExecutionFacade {

    private final AddExecution addExecution;
    private final UpdateExecution updateExecuteion;
    private final DeleteExecution deleteExectuion;
    private final ListExecution listExecution;

    @Autowired
    public ExecutionFacade(AddExecution addExecution, UpdateExecution updateExecuteion, DeleteExecution deleteExectuion,
            ListExecution listExecution) {

        this.addExecution = addExecution;
        this.updateExecuteion = updateExecuteion;
        this.deleteExectuion = deleteExectuion;
        this.listExecution = listExecution;
    }

    @GET
    public Response list(@PathParam("case-id") long caseId) {
        return Response.ok(listExecution.list(caseId)).build();
    }

    @POST
    public Response save(@PathParam("case-id") long caseId, Execution execution) {
        return Response.ok(addExecution.add(caseId, execution)).build();
    }

    @PUT
    @Path("/{execution-id}")
    public Response update(@PathParam("case-id") long caseId, @PathParam("execution-id") long executionId, Execution execution) {

        execution.setId(executionId);
        return Response.ok(updateExecuteion.update(caseId, execution)).build();
    }

    @DELETE
    @Path("/{execution-id}")
    public Response delete(@PathParam("case-id") long caseId, @PathParam("execution-id") long executionId) {

        deleteExectuion.delete(caseId, executionId);
        return Response.ok().build();
    }
}
