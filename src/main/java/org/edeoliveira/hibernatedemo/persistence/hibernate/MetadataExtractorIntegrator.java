package org.edeoliveira.hibernatedemo.persistence.hibernate;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class MetadataExtractorIntegrator implements org.hibernate.integrator.spi.Integrator {

	public static final MetadataExtractorIntegrator INSTANCE = new MetadataExtractorIntegrator();

	private Database database;

	private Metadata metadata;

	private SessionFactoryServiceRegistry serviceRegistry;

	public Database getDatabase() {
		return database;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public SessionFactoryServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	@Override
	public void integrate(
			Metadata metadata,
			SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		this.metadata = metadata;
		this.database = metadata.getDatabase();
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public void disintegrate(
			SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		this.metadata = null;
		this.database = null;
		this.serviceRegistry = null;
	}
}