package com.matheus.combaterpgapi.service;

import com.matheus.combaterpgapi.dto.FactionDTO;
import com.matheus.combaterpgapi.exception.NotFoundException;
import com.matheus.combaterpgapi.model.Character;
import com.matheus.combaterpgapi.model.Faction;
import com.matheus.combaterpgapi.repository.CharacterRepository;
import com.matheus.combaterpgapi.repository.FactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FactionService {

    private final FactionRepository factionRepository;
    private final CharacterService characterService;
    private final CharacterRepository characterRepository;


    public FactionService(FactionRepository factionRepository, CharacterService characterService, CharacterRepository characterRepository) {
        this.factionRepository = factionRepository;
        this.characterService = characterService;
        this.characterRepository = characterRepository;
    }

    public List<Faction> findAll(){
        return factionRepository.findAll();
    }

    public Faction findById(Integer id){
        return factionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID não localizado!"));
    }

    public Faction findOneByName(String name) {
        return factionRepository.findOneByFactionName(name)
                .orElseThrow(() -> new NotFoundException("Facção não localizada por nome."));
    }

    public Faction create(Faction faction) {
        return factionRepository.save(faction);
    }

    public Faction update(Faction faction){
        Faction entity = this.findById(faction.getId());
        entity.setFactionName(faction.getFactionName());
        return factionRepository.save(entity);
    }

    public void deleteById(Integer id) {
        factionRepository.deleteById(this.findById(id).getId());
    }

    public Faction joinFaction(Faction faction, Integer id){
        Character character = characterService.findById(id);
        character.getFactions().add(faction);
        characterRepository.save(character);
        faction.getCharacterList().add(character);
        return factionRepository.save(faction);
    }
}
