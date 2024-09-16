package br.edu.univille.poo.jpa.servico;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import br.edu.univille.poo.jpa.repositorio.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServico {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> obterTodas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> obterPeloId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa incluir(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().length() < 5) {
            throw new RuntimeException("Título deve conter pelo menos 5 caracteres.");
        }
        if (tarefa.getDataPrevistaFinalizacao() == null) {
            throw new RuntimeException("Data prevista de finalização é obrigatória.");
        }
        tarefa.setId(null);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Tarefa tarefa) {
        var existente = tarefaRepository.findById(tarefa.getId())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        if (existente.getFinalizado()) {
            throw new RuntimeException("Tarefas finalizadas não podem ser modificadas.");
        }
        existente.setTitulo(tarefa.getTitulo());
        existente.setDescricao(tarefa.getDescricao());
        existente.setDataPrevistaFinalizacao(tarefa.getDataPrevistaFinalizacao());
        return tarefaRepository.save(existente);
    }

    public void excluir(Long id) {
        var existente = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        if (existente.getFinalizado()) {
            throw new RuntimeException("Tarefas finalizadas não podem ser excluídas.");
        }
        tarefaRepository.delete(existente);
    }
}
