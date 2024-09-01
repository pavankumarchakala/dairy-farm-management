package com.dairyfarm.config;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.dairyfarm.util.RandomUtils;

/**
 * @author Pavankumar - created date : Oct 1, 2021
 * 
 */
public class CustomIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return RandomUtils.getGeneratedTimeStamp();
	}

}
