package theFishing.patch.foil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import theFishing.FishingMod;
import theFishing.util.ImageHelper;

import java.nio.charset.StandardCharsets;

public class FoilShiny {
    @SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = SpriteBatch.class)
    public static class FoilCardsShine {
        private static final ShaderProgram FOIL_SHINE = new ShaderProgram(SpriteBatch.createDefaultShader().getVertexShaderSource(), Gdx.files.internal("fishingResources/shaders/foil_shine.frag").readString(String.valueOf(StandardCharsets.UTF_8)));

        private static final FrameBuffer fbo = ImageHelper.createBuffer();

        private static int RUNNING_ON_STEAM_DECK = -1;

        private static final String OS = System.getProperty("os.name").toLowerCase();
        public static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);

        public static boolean isOnSteamDeck() {
            if (RUNNING_ON_STEAM_DECK == -1) {
                RUNNING_ON_STEAM_DECK = CardCrawlGame.clientUtils.isSteamRunningOnSteamDeck() ? 1 : 0;
            }
            return RUNNING_ON_STEAM_DECK == 1;
        }

        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractCard __instance, SpriteBatch spriteBatch) {
            if (!Settings.hideCards) {
                if (FoilPatches.isFoil(__instance) && IS_WINDOWS && !isOnSteamDeck()) {
                    TextureRegion t = cardToTextureRegion(__instance, spriteBatch);
                    spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
                    ShaderProgram oldShader = spriteBatch.getShader();
                    spriteBatch.setShader(FOIL_SHINE);
                    FOIL_SHINE.setUniformf("x_time", FishingMod.time);
                    spriteBatch.draw(t, -Settings.VERT_LETTERBOX_AMT, -Settings.HORIZ_LETTERBOX_AMT);
                    spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                    spriteBatch.setShader(oldShader);
                    return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }

        public static TextureRegion cardToTextureRegion(AbstractCard card, SpriteBatch sb) {
            sb.end();
            ImageHelper.beginBuffer(fbo);
            sb.begin();
            card.render(sb, false);
            sb.end();
            fbo.end();
            sb.begin();
            return ImageHelper.getBufferTexture(fbo);
        }
    }
}
