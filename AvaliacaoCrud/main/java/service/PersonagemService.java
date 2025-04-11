package service;

import jakarta.transaction.Transactional;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.*;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemMagicoRepository itemRepository;

    public Personagem salvar(Personagem personagem) {
        if (personagem.getForcaTotal() + personagem.getDefesaTotal() > 10)
            throw new RuntimeException("Força + Defesa deve ser no máximo 10.");

        return personagemRepository.save(personagem);
    }

    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Personagem buscarPorId(Long id) {
        return personagemRepository.findById(id).orElseThrow();
    }

    public void remover(Long id) {
        personagemRepository.deleteById(id);
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem p = buscarPorId(id);
        p.setNomeAventureiro(novoNome);
        return personagemRepository.save(p);
    }

    public Personagem adicionarItem(Long personagemId, Long itemId) {
        Personagem p = buscarPorId(personagemId);
        ItemMagico item = itemRepository.findById(itemId).orElseThrow();

        if (item.getTipo() == TipoItem.AMULETO &&
                p.getItensMagicos().stream().anyMatch(i -> i.getTipo() == TipoItem.AMULETO)) {
            throw new RuntimeException("Personagem já possui um amuleto.");
        }

        item.setPersonagem(p);
        itemRepository.save(item);
        return personagemRepository.save(p);
    }

    public void removerItem(Long personagemId, Long itemId) {
        ItemMagico item = itemRepository.findById(itemId).orElseThrow();
        if (!item.getPersonagem().getId().equals(personagemId)) {
            throw new RuntimeException("Item não pertence ao personagem.");
        }
        item.setPersonagem(null);
        itemRepository.save(item);
    }

    public ItemMagico buscarAmuleto(Long personagemId) {
        return buscarPorId(personagemId).getItensMagicos().stream()
                .filter(i -> i.getTipo() == TipoItem.AMULETO)
                .findFirst().orElse(null);
    }
}