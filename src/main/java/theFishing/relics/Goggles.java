package theFishing.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Goggles extends AbstractEasyRelic {
    public static final String ID = makeID("Goggles");

    public Goggles() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hand.size() == 0) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            applyToSelf(new StrengthPower(AbstractDungeon.player, 1));
        }
    }
}
