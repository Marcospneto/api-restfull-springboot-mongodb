package com.jvm.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jvm.workshopmongo.domain.User;
import com.jvm.workshopmongo.dto.UserDTO;
import com.jvm.workshopmongo.services.UserService;
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO (x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		//Direcionando para o service e o service direciona para o repositorio para ser salvo no banco
		obj = service.insert(obj);
		/*Boa pratica: Retornar a URL do cabeçalho criado
		  Pega o endereço do novo objeto que inseriu*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//O created retorna o codigo 201 http (codigo de resposta quando cria um novo recurso)
		return ResponseEntity.created(uri).build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		/*Quando faz uma operação que nao tem retorno, vai ser uma resposta com codigo 204, o codigo 204 do
		ResponseEntity é o noContent*/
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		/*Instanciando um obj a partir do objDto que vai vim na requisição*/
		User obj = service.fromDTO(objDto);
		/*Antes de chamar o service update, fazer o setId, 
		  para garantir que o meu objeto vai ter o id da requisição*/
		obj.setId(id);
		obj = service.update(obj);
		/*Quando faz uma operação que nao tem retorno, vai ser uma resposta com codigo 204, o codigo 204 do
		ResponseEntity é o noContent*/
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	
	
	
	

}
