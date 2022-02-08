package br.com.lazaro.tarefas.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lazaro.tarefas.model.Status;
import br.com.lazaro.tarefas.model.Tarefa;
import br.com.lazaro.tarefas.repository.TarefaCriadaEvent;
import br.com.lazaro.tarefas.repository.TarefaRepository;

public class CriarTarefaUseCaseTest {
	
	@Mock
	private TarefaRepository tarefaRepository;
	
	private CriarTarefaUseCase useCase;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		List<TarefaEvent> events = new ArrayList<>();
		events.add(new TarefaCriadaEvent(tarefaRepository));
		useCase = new CriarTarefaUseCase(events);
	}
	
	@Test
	public void deve_ciar_uma_tarefa() {
		
		TarefaDto tarefaDto = TarefaDto.builder()
				.nome("Nome 01")
				.descricao("Descrição 01")
				.build();
		
		Tarefa tarefa = useCase.execute(tarefaDto);
		
		assertEquals("Nome 01", tarefa.getNome());
		assertEquals("Descrição 01", tarefa.getDescricao());
		assertEquals(Status.CRIADA, tarefa.getStatus());
		assertEquals(formatarData(Calendar.getInstance()), formatarData(tarefa.getDataCriacao()));
		assertTrue(tarefa.isAtivo());
		
	}
	
	private String formatarData(Calendar data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
	}

}
