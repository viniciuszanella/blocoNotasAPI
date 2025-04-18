package com.vinizanella.bloconoatasapi.controller;

import com.vinizanella.bloconoatasapi.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/notes") // Todas as URLs aqui começarão com /api/notes
public class NoteController {

    // --- Nosso "Banco de Dados" em Memória ---
    // Usamos um ConcurrentHashMap para ser mais seguro se várias requisições chegarem ao mesmo tempo.
    // A chave (Long) será o ID da nota, e o valor (Note) será o objeto da nota em si.
    private Map<Long, Note> notes = new ConcurrentHashMap<>();
    // Usamos AtomicLong para gerar IDs únicos de forma segura. Começa em 0.
    private AtomicLong idCounter = new AtomicLong();

    // --- Endpoints da API ---

    // Endpoint para CRIAR uma nova nota (POST /api/notes)
    // @PostMapping: Mapeia requisições HTTP POST para este método.
    // @RequestBody String content: Pega o texto enviado no corpo da requisição.
    //                             Esperamos receber apenas o texto simples da nota.
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody String content) {
        if (content == null || content.trim().isEmpty()) {
            // Retorna um erro 400 Bad Request se o conteúdo estiver vazio
            return ResponseEntity.badRequest().build();
        }

        // 1. Gera um novo ID único (incrementa o contador e pega o valor)
        Long newId = idCounter.incrementAndGet();
        // 2. Cria um novo objeto Note
        Note newNote = new Note(newId, content.trim());
        // 3. "Salva" a nota no nosso Map em memória
        notes.put(newId, newNote);
        // 4. Retorna a nota criada com status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(newNote);
    }

    // Endpoint para LISTAR TODAS as notas (GET /api/notes)
    // @GetMapping: Mapeia requisições HTTP GET para este método.
    @GetMapping
    public List<Note> getAllNotes() {
        // Retorna uma lista com todos os valores (objetos Note) do nosso Map.
        return new ArrayList<>(notes.values());
    }

    // Endpoint para BUSCAR UMA nota por ID (GET /api/notes/{id})
    // @GetMapping("/{id}"): Mapeia GET para /api/notes/ALGUM_NUMERO
    // @PathVariable Long id: Pega o número da URL e coloca na variável 'id'.
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        // Busca a nota no Map usando o ID fornecido
        Note note = notes.get(id);

        if (note != null) {
            // Se encontrou, retorna a nota com status 200 OK
            return ResponseEntity.ok(note);
        } else {
            // Se não encontrou (ID não existe no Map), retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para DELETAR uma nota por ID (DELETE /api/notes/{id})
    // @DeleteMapping("/{id}"): Mapeia requisições HTTP DELETE.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        // Tenta remover a nota do Map usando o ID
        Note removedNote = notes.remove(id);

        if (removedNote != null) {
            // Se removeu com sucesso, retorna status 204 No Content (padrão para delete bem-sucedido)
            return ResponseEntity.noContent().build();
        } else {
            // Se a nota com esse ID não existia, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}