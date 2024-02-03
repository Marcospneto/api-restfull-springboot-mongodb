package com.jvm.workshopmongo.repository;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jvm.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	/*
	 * Query methods. Retorna uma lista de post procurando pelo titulo. Obs:Nome da
	 * query tem q ser padrao nao pode ser qualquer nome
	 */
	List<Post> findByTitleContainingIgnoreCase(String text);
}
