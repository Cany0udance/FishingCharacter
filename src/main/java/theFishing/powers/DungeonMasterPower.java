package theFishing.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.FishingMod;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class DungeonMasterPower extends AbstractAdventurerPower implements OnCompleteDungeonPower {
    public static String ID = makeID(DungeonMasterPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public DungeonMasterPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onDungeonComplete() {
        flash();
        for (int i = 0; i < DungeonMasterPower.this.amount; i++) {
            FishingMod.activeBoard.effects.get(FishingMod.activeBoard.effects.size() - 1).run();
        }
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? powerStrings.DESCRIPTIONS[2] : (powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1]);
    }
}