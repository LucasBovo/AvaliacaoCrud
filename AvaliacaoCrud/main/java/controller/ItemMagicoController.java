package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@Tag(name = "Itens Mágicos", description = "Caracteristicas do Item Magico")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService service;

    @PostMapping
    public ItemMagico criar(@RequestBody ItemMagico item) {
        return service.salvar(item);
    }

    @GetMapping
    public List<ItemMagico> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ItemMagico buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}