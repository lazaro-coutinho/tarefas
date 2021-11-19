package br.com.lazaro.tarefas.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lazaro.tarefas.service.TarefaForm;
import br.com.lazaro.tarefas.service.TarefaService;
import br.com.lazaro.tarefas.service.TarefaView;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tarefas")
@AllArgsConstructor
public class TarefasResource {
	
	private final TarefaService tarefaService;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid TarefaForm tarefaForm) {
		TarefaView tarefaView = tarefaService.save(tarefaForm);
		URI uri = URI.create("/tarefas/").resolve(tarefaView.getId().toString());
		return ResponseEntity.created(uri).build();
	}

}
