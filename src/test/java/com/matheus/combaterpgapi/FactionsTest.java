package com.matheus.combaterpgapi;

import com.matheus.combaterpgapi.model.BaseCharacterOriginal;
import com.matheus.combaterpgapi.model.Factions;
import com.matheus.combaterpgapi.model.MeleeCharacterOriginal;
import com.matheus.combaterpgapi.model.RangedCharacterOriginal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class FactionsTest {
    Factions factions;
    BaseCharacterOriginal meleeChar;
    BaseCharacterOriginal rangedChar;

    @BeforeEach
    void setUp() {
        factions = new Factions();
        meleeChar = new MeleeCharacterOriginal("MeeleCharacter", 1000, 1,5);
        rangedChar = new RangedCharacterOriginal("RangedCharacter", 1000, 1, 5);
    }

    @Test
    @DisplayName("joinFaction Test")
    void joinFaction() {
        factions.joinFaction(meleeChar,"TestFaction");
        factions.joinFaction(meleeChar,"TestFaction2");
        factions.joinFaction(rangedChar,"TestFaction");
        factions.joinFaction(rangedChar,"TestFaction2");

        assertThat(factions.getFactionList(),hasItems("TestFaction","TestFaction2"));
        assertThat(factions.getFactionMembers(),hasKey(meleeChar));
        assertThat(factions.getFactionMembers(),hasKey(rangedChar));

    }

    @Test
    @DisplayName("leaveFaction Test")
    void leaveFaction() {
        factions.joinFaction(meleeChar,"TestFaction");
        factions.joinFaction(rangedChar,"TestFaction");
        factions.leaveFaction(meleeChar,"TestFaction");
        factions.leaveFaction(rangedChar,"TestFaction");

        assertThat(factions.getFactionList(),not(hasItem("TestFaction")));
        assertThat(factions.getFactionMembers(),not(hasKey(meleeChar)));
        assertThat(factions.getFactionMembers(),not(hasKey(rangedChar)));

    }
}