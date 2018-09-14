package com.goal.tracking.utils;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.goal.tracking.beans.Goal;
import com.goal.tracking.beans.Task;
import com.goal.tracking.beans.TaskCategory;
import com.goal.tracking.beans.UserRole;
import com.goal.tracking.beans.Users;

public final class HibernateUtils {

	private static HibernateUtils hibernateUtilsInstance = new HibernateUtils();
	private SessionFactory sessionFactory;
	private StandardServiceRegistry serviceRegistry;
	
	private HibernateUtils() {}
	
	public static HibernateUtils getInstance() {
		return hibernateUtilsInstance;
	}
	
	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			createSessionFactory();
		}
		
		return sessionFactory;
	}
	
	private void createSessionFactory () {
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistry = registryBuilder.applySettings(getEnvironment()).build();
		MetadataSources sources = new MetadataSources(serviceRegistry);
		
		Configuration config = new Configuration(sources);
		addAnnotatedClasses(config);
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	
	private Map<String, Object> getEnvironment() {
		Map<String, Object> env = new HashMap<>();
		
		env.put(Environment.DRIVER, "org.postgresql.Driver");
		env.put(Environment.URL, "jdbc:postgresql://localhost:5432/myapp");
		env.put(Environment.USER, "postgres");
		env.put(Environment.PASS, "postgres");
		env.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
		env.put(Environment.HBM2DDL_AUTO, "create");
		
		return env;
	}
	
	private void addAnnotatedClasses(Configuration config) {
		config.addAnnotatedClass(Goal.class);
		config.addAnnotatedClass(Task.class);
		config.addAnnotatedClass(TaskCategory.class);
		config.addAnnotatedClass(Users.class);
		config.addAnnotatedClass(UserRole.class);
	}
}
