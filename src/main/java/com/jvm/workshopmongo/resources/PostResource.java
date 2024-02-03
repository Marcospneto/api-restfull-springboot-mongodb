package com.jvm.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.workshopmongo.domain.Post;
import com.jvm.workshopmongo.resources.util.URL;
import com.jvm.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	

	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	                                        /*Aqui meu criterio de busca vai ser passado como um parametro '?'
	                                        e não como variavel de URL, com '/', e o value=text é pra o end point 
											identificar o nome do parametro  */
	@RequestMapping(value= "/titlesearch", method=RequestMethod.GET)             
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		/*Primeiro pego o parametro text e decodificar*/
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		/*Retornando uma resposta cujo o corpo vai ser a lista*/
		return ResponseEntity.ok().body(list);
	}

	
}
