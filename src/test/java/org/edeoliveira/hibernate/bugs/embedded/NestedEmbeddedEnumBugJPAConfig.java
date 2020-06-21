package org.edeoliveira.hibernate.bugs.embedded;

import org.edeoliveira.hibernate.AbstractJPAConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-h2.properties" })
@ComponentScan({ "org.edeoliveira.hibernate.bugs.embedded" })
@EnableJpaRepositories(basePackages = "org.edeoliveira.hibernate.bugs.embedded")
public class NestedEmbeddedEnumBugJPAConfig  extends AbstractJPAConfig {

    public NestedEmbeddedEnumBugJPAConfig(Environment env) {
        super(env);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("org.edeoliveira.hibernate.bugs.embedded");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }
}