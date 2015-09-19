package com.song.dao.basic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
@Component
public abstract class BaseDao {

	@PersistenceContext
	protected EntityManager entityManager;
}
