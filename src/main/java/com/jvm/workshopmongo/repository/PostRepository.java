package com.jvm.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{ 'title': { $regex: ?0, $options: 'i' }}")
	List<Post> searchTitle(String text);
	
	/*
	 * Query methods. Retorna uma lista de post procurando pelo titulo. Obs:Nome da
	 * query tem q ser padrao nao pode ser qualquer nome
	 */
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	/*O parametro String texto vai estpa ou no titulo ou no corpo ou nos comentarios "||"    
	  Na data vai ser o operador E, "and"
	  gte é maior ou igual
	  lte é menor ou igual
	 * */
	@Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} }, {$or: [ { 'title': { $regex: ?0, $options: 'i' }}, { 'body': { $regex: ?0, $options: 'i' }}, { 'comments.text': { $regex: ?0, $options: 'i' }} ] }]}")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
}
