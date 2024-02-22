package com.jvm.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.workshopmongo.domain.Post;
import com.jvm.workshopmongo.repository.PostRepository;
import com.jvm.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	

	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	/*Query methods. Aqui sim pode escolher o nome do metodo*/
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	/*Na data maxima tem que considerar ate o final daquele dia ou seja, até as 24h
	 *Como estou fazendo que na data maxima tenha uma comparação de menor ou igual, tem que fazer a comparação
	 *menor ou igual até a MEIA NOITE do proximo dia, entao tem que acrescentar mais um dia a nessa data*/
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
		
}
