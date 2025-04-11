package service;

import jakarta.transaction.Transactional;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.*;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemRepository;

    public ItemMagico salvar(ItemMagico item) {
        if (item.getForca() == 0 && item.getDefesa() == 0)
            throw new RuntimeException("Item não pode ter força e defesa zeradas.");
        if (item.getForca() > 10 || item.getDefesa() > 10)
            throw new RuntimeException("Força e Defesa devem ser no máximo 10.");
        if (item.getTipo() == TipoItem.ARMA && item.getDefesa() != 0)
            throw new RuntimeException("Armas devem ter defesa 0.");
        if (item.getTipo() == TipoItem.ARMADURA && item.getForca() != 0)
            throw new RuntimeException("Armaduras devem ter força 0.");

        return itemRepository.save(item);
    }

    public List<ItemMagico> listarTodos() {
        return itemRepository.findAll();
    }

    public ItemMagico buscarPorId(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }
}