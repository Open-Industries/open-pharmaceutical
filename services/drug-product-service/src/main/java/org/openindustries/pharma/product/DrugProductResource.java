package org.openindustries.pharma.product;

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

@Path("/api/v1/drug-products")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Drug Products", description = "Drug product registry (ATC-coded).")
public class DrugProductResource {

    @Inject
    DrugProductService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a drugproduct")
    public Response create(@Valid CreateDrugProductRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<DrugProduct> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public DrugProduct get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/approve")
    @Operation(summary = "Approve this drugproduct (lifecycle transition, no body)")
    public DrugProduct approve(@PathParam("id") UUID id) {
        return service.approve(id);
    }

    @POST
    @Path("/{id}/withdraw")
    @Operation(summary = "Withdraw this drugproduct (lifecycle transition, no body)")
    public DrugProduct withdraw(@PathParam("id") UUID id) {
        return service.withdraw(id);
    }
}
