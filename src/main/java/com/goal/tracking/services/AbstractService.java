package com.goal.tracking.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.goal.tracking.utils.HibernateUtils;

public class AbstractService {

	private HibernateUtils hibernateUtils = HibernateUtils.getInstance();
	
	protected SessionFactory getSessionFactory() {
		return hibernateUtils.getSessionFactory();
	}
	
	protected Session getSession() {
		return hibernateUtils.getSessionFactory().openSession();
	}
	
}
