package br.com.lazaro.tarefas.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.lazaro.tarefas.model.Tarefa;
import br.com.lazaro.tarefas.model.Status;

public class CriarTarefaActionTest {
	
	private CriarTarefaAction action;
	
	@BeforeEach
	public void init() {
		action = new CriarTarefaAction();
	}
	
	@Test
	public void deve_criar_uma_tarefa_se_a_tarefa_nao_tiver_criada() {
		Status status = action.execute(new Tarefa());
		assertEquals(Status.CRIADA, status);
	}
	
	@Test
	public void deve_lancar_uma_exception_se_a_tarefa_ja_tiver_criada() {
		Tarefa tarefa = Mockito.mock(Tarefa.class);
		Mockito.when(tarefa.getStatus()).thenReturn(Status.CRIADA);
		assertThrows(RuntimeException.class, () -> action.execute(tarefa));
		Mockito.verify(tarefa).getStatus();
	}
	
	@Test
	public void deve_lancar_uma_exception_se_a_tarefa_tiver_finalizada() {
		Tarefa tarefa = Mockito.mock(Tarefa.class);
		Mockito.when(tarefa.getStatus()).thenReturn(Status.FINALIZADA);
		assertThrows(RuntimeException.class, () -> action.execute(tarefa));
		Mockito.verify(tarefa).getStatus();
	}
	
	@Test
	public void deve_lancar_uma_exception_se_a_tarefa_tiver_arquivada() {
		Tarefa tarefa = Mockito.mock(Tarefa.class);
		Mockito.when(tarefa.getStatus()).thenReturn(Status.ARQUIVADA);
		assertThrows(RuntimeException.class, () -> action.execute(tarefa));
		Mockito.verify(tarefa).getStatus();
	}

}
