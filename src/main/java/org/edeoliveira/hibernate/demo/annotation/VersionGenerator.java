package org.edeoliveira.hibernate.demo.annotation;

import org.edeoliveira.hibernate.demo.MetadataExtractorIntegrator;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.tuple.ValueGenerator;
import org.hibernate.type.LongType;

import java.util.Properties;

public class VersionGenerator implements ValueGenerator<Long> {

    private Properties properties = new Properties();
    private SequenceStyleGenerator generator;

    public VersionGenerator() {
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private SequenceStyleGenerator getGenerator() {
        if (generator == null) {
            MetadataExtractorIntegrator metadataExtractorIntegrator = MetadataExtractorIntegrator.INSTANCE;
            generator = new SequenceStyleGenerator();
            generator.configure(LongType.INSTANCE, properties, metadataExtractorIntegrator.getServiceRegistry());
            generator.registerExportables(metadataExtractorIntegrator.getDatabase());
        }

        return generator;
    }

    public Long generateValue(Session session, Object owner) {
        if (IVersionnable.class.isAssignableFrom(owner.getClass())) {
            IVersionnable versionnable = (IVersionnable) owner;
            if (versionnable.getVersion() != null) {
                return versionnable.getVersion();
            } else {
                return (Long) getGenerator().generate((SharedSessionContractImplementor) session, owner);
            }
        } else {
            throw new UnsupportedOperationException(
                    "GenerateVersion annotation is only intended to be used on a class that implements IVersionable interface");
        }
    }
}
