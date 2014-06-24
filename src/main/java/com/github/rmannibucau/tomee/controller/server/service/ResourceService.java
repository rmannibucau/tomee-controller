package com.github.rmannibucau.tomee.controller.server.service;

import org.apache.openejb.assembler.classic.FacilitiesInfo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

// TODO: this is not yet used by the app
@Path("resource")
@ApplicationScoped
public class ResourceService {
    @Inject
    private FacilitiesInfo facilitiesInfo;

    @Inject
    private JsonBuilderFactory builderFactory;

    @GET
    @Path("ids.json")
    public JsonArray ids() {
        return facilitiesInfo.resources
                .stream()
                .map(ri -> ri.id)
                .collect(builderFactory::createArrayBuilder, (a, s) -> a.add(s), (b1, b2) -> b1.add(b2))
                .build();
    }

    @GET
    @Path("ids.json")
    public JsonArray ids(final String urlHash) {
        return facilitiesInfo.resources
                .stream()
                .map(ri -> ri.id)
                .collect(builderFactory::createArrayBuilder, (a, s) -> a.add(s), (b1, b2) -> b1.add(b2))
                .build();
    }
}
