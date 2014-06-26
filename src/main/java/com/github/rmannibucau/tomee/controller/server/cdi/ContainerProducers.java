package com.github.rmannibucau.tomee.controller.server.cdi;

import org.apache.openejb.assembler.classic.Assembler;
import org.apache.openejb.assembler.classic.FacilitiesInfo;
import org.apache.openejb.assembler.classic.OpenEjbConfiguration;
import org.apache.openejb.assembler.classic.OpenEjbConfigurationFactory;
import org.apache.openejb.config.ConfigurationFactory;
import org.apache.openejb.core.ParentClassLoaderFinder;
import org.apache.openejb.loader.SystemInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ContainerProducers {
    @Produces
    public ClassLoader containerLoader() {
        return ParentClassLoaderFinder.Helper.get();
    }

    @Produces
    public Assembler assembler() {
        return component(Assembler.class);
    }

    @Produces
    public OpenEjbConfiguration configuration() {
        return component(OpenEjbConfiguration.class);
    }

    @Produces
    public ConfigurationFactory configurationFactory() {
        return ConfigurationFactory.class.cast(component(OpenEjbConfigurationFactory.class));
    }

    @Produces
    public FacilitiesInfo facilitiesInfo() {
        return configuration().facilities;
    }

    private static <T> T component(final Class<T> clazz) {
        return SystemInstance.get().getComponent(clazz);
    }
}
