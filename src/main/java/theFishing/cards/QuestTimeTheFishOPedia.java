package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.TheFishOPedia;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class QuestTimeTheFishOPedia extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheFishOPedia");
    // intellij stuff skill, self, uncommon, , , , , , 

    public QuestTimeTheFishOPedia() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AcceptQuestAction(new TheFishOPedia()));
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = QuestHelper.hasQuest(TheFishOPedia.ID) ? QuestHelper.QUEST_DUPE_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(3);
    }
}