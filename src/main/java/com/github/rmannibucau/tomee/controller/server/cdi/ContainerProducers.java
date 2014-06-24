package com.github.rmannibucau.tomee.controller.server.cdi;

import org.apache.openejb.OpenEJBException;
import org.apache.openejb.assembler.classic.FacilitiesInfo;
import org.apache.openejb.assembler.classic.OpenEjbConfiguration;
import org.apache.openejb.assembler.classic.OpenEjbConfigurationFactory;
import org.apache.openejb.loader.SystemInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ContainerProducers {
    @Produces
    public OpenEjbConfiguration configuration() {
        try {
            return component(OpenEjbConfigurationFactory.class).getOpenEjbConfiguration();
        } catch (final OpenEJBException e) {
            throw new IllegalStateException(e);
        }
    }

    @Produces
    public FacilitiesInfo facilitiesInfo() {
        return configuration().facilities;
    }

    private static <T> T component(final Class<T> clazz) {
        return SystemInstance.get().getComponent(clazz);
    }
}
