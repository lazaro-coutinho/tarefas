package br.com.lazaro.tarefas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.lazaro.tarefas.domain.usecase.CriarTarefaUseCase;
import br.com.lazaro.tarefas.domain.usecase.TarefaDto;
import br.com.lazaro.tarefas.domain.usecase.TarefaEvent;
import br.com.lazaro.tarefas.exceptions.TarefaNotFoundException;
import br.com.lazaro.tarefas.model.Status;
import br.com.lazaro.tarefas.model.Tarefa;
import br.com.lazaro.tarefas.repository.TarefaCriadaEvent;
import br.com.lazaro.tarefas.repository.TarefaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TarefaService {
	
	private final TarefaFormMapper tarefaFormMapper;
	
	private final TarefaViewMapper tarefaViewMapper;
	
	private final TarefaRepository tarefaRepository;
	
	private final TarefaCriadaEvent tarefaCriadaEvent;
	
	private CriarTarefaUseCase criarTarefaUseCase;
	
	public TarefaView save(TarefaForm tarefaForm) {
		
		List<TarefaEvent> events = new ArrayList<>();
		events.add(tarefaCriadaEvent);
		criarTarefaUseCase = new CriarTarefaUseCase(events);
		
		Tarefa tarefa = tarefaFormMapper.map(tarefaForm);
		
		TarefaDto tarefaDto = TarefaDto.builder()
				.nome(tarefaForm.getNome())
				.descricao(tarefaForm.getDescricao())
				.build();
		
		tarefa = criarTarefaUseCase.execute(tarefaDto);
		
		return new TarefaView(tarefa);
		
	}
	
	public void update(Long id, TarefaForm tarefaForm) {
		Tarefa tarefa = tarefaRepository.getById(id);
		exists(tarefa);
		tarefa.setNome(tarefaForm.getNome());
		tarefa.setDescricao(tarefaForm.getDescricao());
		tarefaRepository.save(tarefa);
	}
	
	public void remove(Long id) {
		Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
		if (!tarefaOptional.isPresent()) {
			throw new TarefaNotFoundException();
		}
		Tarefa tarefa = tarefaOptional.get();
		tarefa.setAtivo(false);
		tarefaRepository.save(tarefa);
	}
	
	public TarefaView findById(Long id) {
		Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
		if (!tarefaOptional.isPresent()) {
			throw new TarefaNotFoundException();
		}
		return tarefaViewMapper.map(tarefaOptional.get());
	}
	
	public List<TarefaView> findAll() {
		return tarefaRepository.findAll()
				.stream().map(t -> tarefaViewMapper.map(t))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public List<TarefaView> find(String nome) {
		if (nome == null) {
			nome = "";
		}
		return tarefaRepository.find(nome.toUpperCase(), Status.ARQUIVADA)
				.stream().map(t -> tarefaViewMapper.map(t))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public void finalizar(Long tarefaId) {
		Tarefa tarefa = tarefaRepository.getById(tarefaId);
		exists(tarefa);
		tarefa.finalizar();
		tarefaRepository.save(tarefa);
	}
	
	public void arquivar(Long tarefaId) {
		Tarefa tarefa = tarefaRepository.getById(tarefaId);
		exists(tarefa);
		tarefa.arquivar();
		tarefaRepository.save(tarefa);
	}
	
	private void exists(Tarefa tarefa) {
		findById(tarefa.getId());
	}

}
