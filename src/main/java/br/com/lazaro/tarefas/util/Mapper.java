package br.com.lazaro.tarefas.util;

public interface Mapper<K, S> {
	
	S map(K k);

}
