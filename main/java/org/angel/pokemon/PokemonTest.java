package org.angel.pokemon;

import org.angel.pokemon.dao.PokemonDAO;
import org.angel.pokemon.dao.implementation.PokemonDAOImp;
import org.angel.pokemon.model.Pokemon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonTest {
    public static void main(String[] args) {
        PokemonDAO prueba = new PokemonDAOImp();
        List<Pokemon> list = new ArrayList<>();

        try {
            list = prueba.readAll();
            for(Pokemon p: list){
                System.out.println(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
