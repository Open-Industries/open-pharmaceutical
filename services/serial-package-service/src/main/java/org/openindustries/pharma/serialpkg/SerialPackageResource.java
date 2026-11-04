package org.openindustries.pharma.serialpkg;

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

@Path("/api/v1/serial-packages")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Serial Packages", description = "Serialized package format catalogue.")
public class SerialPackageResource {

    @Inject
    SerialPackageService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a serialpackage")
    public Response create(@Valid CreateSerialPackageRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<SerialPackage> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public SerialPackage get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/verify")
    @Operation(summary = "Verify (lifecycle transition, no body)")
    public SerialPackage verify(@PathParam("id") UUID id) {
        return service.verify(id);
    }
}
