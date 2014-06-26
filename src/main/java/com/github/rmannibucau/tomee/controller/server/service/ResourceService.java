package com.github.rmannibucau.tomee.controller.server.service;

import org.apache.openejb.OpenEJBException;
import org.apache.openejb.assembler.classic.Assembler;
import org.apache.openejb.assembler.classic.FacilitiesInfo;
import org.apache.openejb.assembler.classic.ResourceInfo;
import org.apache.openejb.config.ConfigurationFactory;
import org.apache.openejb.config.sys.Resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Locale;
import java.util.Map;

@Path("resource")
@ApplicationScoped
public class ResourceService {
    private final ConfigurationFactory configurationFactory;
    private final Assembler assembler;
    private final FacilitiesInfo facilitiesInfo;
    private final JsonBuilderFactory builderFactory;

    public ResourceService() { // CDI needs it to be proxiable
        this(null, null, null, null);
    }

    @Inject
    public ResourceService(final ConfigurationFactory configurationFactory,
                           final Assembler assembler,
                           final FacilitiesInfo facilitiesInfo,
                           final JsonBuilderFactory builderFactory) {
        this.configurationFactory = configurationFactory;
        this.assembler = assembler;
        this.facilitiesInfo = facilitiesInfo;
        this.builderFactory = builderFactory;
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

    @POST
    @Path("create")
    public JsonObject createResource(final JsonObject jsonToResource) {
        final Resource resource = jsonToResource(jsonToResource);
        try {
            final ResourceInfo serviceInfo = configurationFactory.configureService(resource, ResourceInfo.class);
            assembler.createResource(serviceInfo);
        } catch (final OpenEJBException e) {
            throw new IllegalArgumentException(e);
        }
        return builderFactory.createObjectBuilder().add("id", resource.getId()).build();
    }

    private Resource jsonToResource(final JsonObject resource) {
        final Resource newResource = new Resource();
        newResource.setId(resource.getString("resourceId"));
        if (newResource.getId() == null) {
            throw new IllegalArgumentException("no resourceId");
        }
        newResource.setType(resource.getString("type"));
        final JsonValue value = resource.get("properties");
        if (JsonArray.class.isInstance(value)) {
            for (final JsonValue property : JsonArray.class.cast(value)) {
                if (JsonObject.class.isInstance(property)) {
                    final JsonObject prop = JsonObject.class.cast(property);
                    newResource.getProperties().put(prop.getString("key"), prop.get("value").toString());
                }
            }
        }
        return newResource;
    }

    private JsonObjectBuilder buildResourceObject(final int idx, final ResourceInfo resourceInfo) {
        JsonArrayBuilder properties = builderFactory.createArrayBuilder();
        for (final Map.Entry<Object, Object> entry : resourceInfo.properties.entrySet()) {
            if (String.class.isInstance(entry.getValue()) && !entry.getValue().toString().isEmpty()) {
                final String key = entry.getKey().toString();
                final String lowerCase = key.toLowerCase(Locale.ENGLISH);
                if (lowerCase.contains("password") || lowerCase.contains("pwd")) {
                    continue; // skip
                }
                properties = properties.add(
                        builderFactory.createObjectBuilder()
                                .add("key", key)
                                .add("value", entry.getValue().toString()));
            }
        }
        return builderFactory
                .createObjectBuilder()
                .add("id", idx)
                .add("resourceId", resourceInfo.id)
                .add("type", !resourceInfo.types.isEmpty()? resourceInfo.types.iterator().next() : resourceInfo.className)
                .add("properties", properties);
    }
}
