package model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private int forca;
    private int defesa;

    @ManyToOne
    private Personagem personagem;
}

public enum Classe {
    GUERREIRO, MAGO, ARQUEIRO, LADINO, BARDO
}

public enum TipoItem {
    ARMA, ARMADURA, AMULETO
}