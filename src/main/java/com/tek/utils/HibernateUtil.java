package com.tek.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static Logger log = Logger.getLogger(HibernateUtil.class);

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		log.info(">> HibernateUtil buildSessionFactory() invoked >>");
		StandardServiceRegistryBuilder serviceBuilder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry serviceRegistry = serviceBuilder.configure().build();
		MetadataBuilder metaDataBuilder = new MetadataSources(serviceRegistry).getMetadataBuilder();
		Metadata metaData = metaDataBuilder.build();
		SessionFactory factory = metaData.getSessionFactoryBuilder().build();
		log.info(" --- Returning factory object ---   " + factory);
		return factory;
	}

	public static SessionFactory getSessionFactory() {
		log.info(">> HibernateUtil getSessionFactory() invoked >>");
		return sessionFactory;
	}

	public static void shutDown() {
		log.info(">> HibernateUtil releasing Session Factory >>");
		getSessionFactory().close();
	}
}
