package com.example;

import com.example.dictionary.Controller;
import com.example.dictionary.GenericParams;
import com.example.dictionary.Params;
import com.example.dictionary.audit.AuditConfiguration;
import com.example.dictionary.translation.TranslationConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import javax.validation.Validator;

public class App {

	public static void main(String... args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				AppConfiguration.class);

		Controller c = ctx.getBean(Controller.class);
		c.run();

		ctx.close();
	}

	@Configuration
	@EnableAsync
	@EnableAspectJAutoProxy
	@ComponentScan("com.example.dictionary")
	@Import({TranslationConfiguration.class, AuditConfiguration.class})
	public static class AppConfiguration {

		@Bean
		@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
		public Params paramsFactory(String line) {
			return GenericParams.ofString(line);
		}

		@Bean
		public Validator validator() {
			return new LocalValidatorFactoryBean();
		}

		@Bean
		public DataSource productionDatasource() {
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName(org.hsqldb.jdbc.JDBCDriver.class.getName());
			ds.setUrl("jdbc:hsqldb:file:/tmp/testdb");
			ds.setUsername("SA");
			ds.setPassword("");

			return ds;
		}

		@Bean
		public DataSourceInitializer initdb(DataSource ds, @Value("classpath:import.sql") Resource file) {
			ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			populator.addScript(file);

			final DataSourceInitializer initializer = new DataSourceInitializer();
			initializer.setDataSource(ds);
			initializer.setDatabasePopulator(populator);
			return initializer;
		}
	}

}
