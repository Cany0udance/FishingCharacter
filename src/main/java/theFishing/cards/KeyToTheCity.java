package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.FishingMod;
import theFishing.actions.AbandonQuestAction;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class KeyToTheCity extends AbstractFishingCard {
    public final static String ID = makeID("KeyToTheCity");
    // intellij stuff attack, all_enemy, rare, 10, 4, , , , 

    public KeyToTheCity() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 9;
        isMultiDamage = true;
        tags.add(FishingMod.DELVES);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SMASH);
        atb(new EnterTheDungeonAction());
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractQuest q : QuestHelper.quests) {
                    q.grantReward();
                    atb(new AbandonQuestAction(q));
                }
            }
        });
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
        uDesc();
    }
}