package com.github.rmannibucau.tomee.controller.server.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import java.util.Collections;

@ApplicationScoped
public class AppProducers {
    @Produces
    @ApplicationScoped
    public JsonBuilderFactory jsonBuilderFactory() {
        return Json.createBuilderFactory(Collections.<String, Object>emptyMap());
    }
}
