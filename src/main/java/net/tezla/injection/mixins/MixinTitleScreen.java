package net.tezla.injection.mixins;

import net.tezla.gui.AccountLoginScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends Screen {

    public MixinTitleScreen(Text title) {
        super(title);
    }

    @Shadow
    protected abstract void switchToRealms();

    /**
     * @author Mojang, PrestigeCode, Flori2007
     */
    @Overwrite
    private void initWidgetsNormal(int y, int spacingY) {
        this.addButton(new ButtonWidget(this.width / 2 - 100, y, 200, 20, new
                TranslatableText("menu.singleplayer"), (buttonWidget) -> {
            this.client.openScreen(new SelectWorldScreen(this));
        }));
        boolean bl = this.client.isMultiplayerEnabled();
        ButtonWidget.TooltipSupplier tooltipSupplier = bl ? ButtonWidget.EMPTY : (buttonWidget, matrixStack, i, j) -> {
            if (!buttonWidget.active) {
                this.renderOrderedTooltip(matrixStack, this.client.textRenderer.wrapLines(new TranslatableText(
                        "title.multiplayer.disabled"), Math.max(this.width / 2 - 43, 170)), i, j);
            }
        };
        this.addButton(new ButtonWidget(this.width / 2 - 100, y + spacingY * 1, 200,
                20, new TranslatableText("menu.multiplayer"), (buttonWidget) -> {
            Screen screen = this.client.options.skipMultiplayerWarning ? new MultiplayerScreen(this) :
                    new MultiplayerWarningScreen(this);
            this.client.openScreen(screen);
        }, tooltipSupplier)).active = bl;

        this.addButton(new ButtonWidget(this.width / 2 - 100, y + spacingY * 2, 98,
                20, new TranslatableText("menu.online"), (buttonWidget) -> {
            this.switchToRealms();
        }, tooltipSupplier)).active = bl;

        this.addButton(new ButtonWidget(this.width / 2 + 2, y + spacingY * 2, 98,
                20, new LiteralText("Account Login"), (buttonWidget) ->
                this.client.openScreen(new AccountLoginScreen(this))
        ));
    }
}
