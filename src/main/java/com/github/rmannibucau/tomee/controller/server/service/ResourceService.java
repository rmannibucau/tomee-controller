package com.github.rmannibucau.tomee.controller.server.service;

import org.apache.openejb.assembler.classic.FacilitiesInfo;
import org.apache.openejb.assembler.classic.ResourceInfo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Map;

// TODO: this is not yet used by the app
@Path("resource")
@ApplicationScoped
public class ResourceService {
    @Inject
    private FacilitiesInfo facilitiesInfo;

    @Inject
    private JsonBuilderFactory builderFactory;

    private JsonObjectBuilder buildResourceObject(final int idx, final ResourceInfo resourceInfo) {
        return builderFactory.createObjectBuilder().add("id", idx).add("resourceId", resourceInfo.id);
    }

    @GET
    @Path("all.json")
    public JsonArray resources() {
        final JsonArrayBuilder builder = builderFactory.createArrayBuilder();
        int idx = 0;
        for (final ResourceInfo resourceInfo : facilitiesInfo.resources) {
            builder.add(buildResourceObject(idx++, resourceInfo));
        }
        return builder.build();
    }

    @GET
    @Path("{index : \\d+}.json")
    public JsonObject resource(final @PathParam("index") int index) {
        if (facilitiesInfo.resources.size() > index) {
            return buildResourceObject(index, facilitiesInfo.resources.get(index)).build();
        }
        return null;
    }
}
