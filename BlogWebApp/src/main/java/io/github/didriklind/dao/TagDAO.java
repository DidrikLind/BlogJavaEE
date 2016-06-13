package io.github.didriklind.dao;

import javax.ejb.Stateless;

import io.github.didriklind.entity.Tag;

@Stateless
public class TagDAO extends GenericDAO<Tag>{
	public TagDAO() {super(Tag.class);}
}
