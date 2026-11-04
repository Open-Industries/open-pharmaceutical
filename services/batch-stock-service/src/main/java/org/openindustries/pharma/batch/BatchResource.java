package org.openindustries.pharma.batch;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/batch-stock")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Batch Stock", description = "Guarded batch stock accounts and the releases against them.")
public class BatchResource {

    @Inject
    BatchService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Open a batchstock")
    public Response open(@Valid OpenBatchStockRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.open(request)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public BatchStock get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/releases")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Draw from the account (rejects if it would overdraw, 422)")
    public BatchRelease consume(@PathParam("id") UUID id, @Valid CreateBatchReleaseRequest request) {
        return service.consume(id, request);
    }

    @GET
    @Path("/{id}/releases")
    @Operation(summary = "List consumption records of an account")
    public List<BatchRelease> records(@PathParam("id") UUID id) {
        return service.recordsOf(id);
    }
}
