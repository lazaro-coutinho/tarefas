package br.com.lazaro.tarefas.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.lazaro.tarefas.exceptions.TarefaNotFoundException;
import br.com.lazaro.tarefas.model.Tarefa;
import br.com.lazaro.tarefas.repository.TarefaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TarefaService {
	
	private final TarefaFormMapper tarefaFormMapper;
	
	private final TarefaViewMapper tarefaViewMapper;
	
	private final TarefaRepository tarefaRepository;
	
	public TarefaView save(TarefaForm tarefaForm) {
		Tarefa tarefa = tarefaFormMapper.map(tarefaForm);
		tarefa.setId(null);
		tarefa.setAtivo(true);
		tarefa.setDataCriacao(Calendar.getInstance());
		tarefa = tarefaRepository.save(tarefa);
		return new TarefaView(tarefa);
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

}
