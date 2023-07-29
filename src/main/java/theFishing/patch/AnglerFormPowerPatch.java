package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theFishing.powers.PreEnemyAttackPower;

public class AnglerFormPowerPatch {
    @SpirePatch(
            clz = DamageAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCreature.class,
                    DamageInfo.class,
                    AbstractGameAction.AttackEffect.class
            }
    )
    public static class BeforeEnemyAttack {
        public static void Postfix(DamageAction __instance, AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
            if (target != null && target == AbstractDungeon.player && info.owner != null && info.owner instanceof AbstractMonster && info.type == DamageInfo.DamageType.NORMAL) {
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    if (p instanceof PreEnemyAttackPower) {
                        ((PreEnemyAttackPower) p).preEnemyAttack(info);
                    }
                }
            }
        }
    }
}
