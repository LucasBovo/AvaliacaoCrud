package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
@Tag(name = "Personagens", description = "Caracteristicas do Personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService service;

    @PostMapping
    @Operation(summary = "Cadastrar um novo personagem")
    public Personagem criar(@RequestBody Personagem p) {
        return service.salvar(p);
    }

    @GetMapping
    public List<Personagem> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Personagem buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PatchMapping("/{id}/nome_aventureiro")
    public Personagem atualizarNome(@PathVariable Long id, @RequestBody String novoNome) {
        return service.atualizarNomeAventureiro(id, novoNome);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.remover(id);
    }

    @PostMapping("/{id}/itens/{itemId}")
    public Personagem adicionarItem(@PathVariable Long id, @PathVariable Long itemId) {
        return service.adicionarItem(id, itemId);
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    public void removerItem(@PathVariable Long id, @PathVariable Long itemId) {
        service.removerItem(id, itemId);
    }

    @GetMapping("/{id}/amuleto")
    public ItemMagico buscarAmuleto(@PathVariable Long id) {
        return service.buscarAmuleto(id);
    }
}