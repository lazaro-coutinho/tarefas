package br.com.lazaro.tarefas.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lazaro.tarefas.service.TarefaForm;
import br.com.lazaro.tarefas.service.TarefaService;
import br.com.lazaro.tarefas.service.TarefaView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/tarefas", produces = "application/json")
@AllArgsConstructor
public class TarefasResource {
	
	private final TarefaService tarefaService;
	
	@ApiOperation("Salva uma tarefa")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Tarefa criada"),
			@ApiResponse(code = 400, message = "Tarefa inválida"),
			@ApiResponse(code = 500, message = "Ocorreu um erro na api ao criar uma tarefa")
	})
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid TarefaForm tarefaForm) {
		TarefaView tarefaView = tarefaService.save(tarefaForm);
		URI uri = URI.create("/tarefas/").resolve(tarefaView.getId().toString());
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Atualiza uma tarefa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 204, message = "Tarefa atualizada"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Ocorreu um erro na api ao atualizar uma tarefa")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid TarefaForm tarefaForm) {
		tarefaService.update(id, tarefaForm);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Remove uma tarefa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Tarefa removida"),
			@ApiResponse(code = 404, message = "Not found"),
	})
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		tarefaService.remove(id);
	}
	
	@ApiOperation("Busca uma tarefa pelo Id")
	@GetMapping("/{id}")
	public ResponseEntity<TarefaView> findById(@PathVariable Long id) {
		TarefaView tarefaView = tarefaService.findById(id);
		return ResponseEntity.ok(tarefaView);
	}
	
	@ApiOperation("Pesquisa uma ou mais tarefas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 500, message = "Ocorreu um erro na api ao pesquisar uma ou mais tarefas")
	})
	@GetMapping
	public ResponseEntity<List<TarefaView>> find(@ApiParam(value = "Nome da tarefa") @RequestParam(value = "nome", required = false) String nome) {
		List<TarefaView> tarefasView = tarefaService.find(nome);
		return ResponseEntity.ok(tarefasView);
	}
	
	@ApiOperation("Finaliza uma tarefa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 204, message = "Tarefa finalizada"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Ocorreu um erro na api ao finalizar uma tarefa")
	})
	@PutMapping("/{id}/finalizar")
	public ResponseEntity<Void> finalizar(@PathVariable Long id) {
		tarefaService.finalizar(id);
		return ResponseEntity.noContent().build();
	}

}
