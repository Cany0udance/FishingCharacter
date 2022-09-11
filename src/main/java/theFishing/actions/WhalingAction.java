package theFishing.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.att;

public class WhalingAction extends AbstractGameAction {
    public WhalingAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        AbstractDungeon.player.loseGold(amount);
        applyToSelfTop(new StrengthPower(AbstractDungeon.player, 1));
        att(new VFXAction(new InflameEffect(AbstractDungeon.player)));
    }
}
