package net.tezla.gui;

import net.tezla.utils.AccountHelper;
import net.tezla.utils.RenderHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class AccountLoginScreen extends Screen {

    private final Screen parent;

    private TextFieldWidget nameField;
    private TextFieldWidget passwordField;

    private String status = "";

    public AccountLoginScreen(Screen parent) {
        super(new LiteralText("Account Login"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        addButton(nameField = new TextFieldWidget(client.textRenderer, width / 2 - 100, 60, 200, 20,
                new LiteralText("")));

        addButton(passwordField = new TextFieldWidget(client.textRenderer, width / 2 - 100, 100, 200,
                20, new LiteralText("")));

        addButton(new ButtonWidget(width / 2 - 100, height / 4 + 120 + 12, 98, 20,
                new LiteralText("Back"), (button -> {
            client.openScreen(getParent());
        })));

        addButton(new ButtonWidget(width / 2 + 2, height / 4 + 120 + 12, 98, 20,
                new LiteralText("Login"), (button -> {
            if (nameField.getText().isEmpty()) {
                status = "§cError";
            } else {
                if (!nameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
                    AccountHelper.login(nameField.getText());
                    status = "§aSuccess!";
                    client.openScreen(getParent());
                } else {
                    AccountHelper.login(nameField.getText(), passwordField.getText());
                    status = "§aSuccess!";
                    client.openScreen(getParent());
                }
            }
        })));
    }

    @Override
    public void tick() {
        super.tick();
        nameField.tick();
        passwordField.tick();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        RenderHelper.drawCenteredString("Account Login", width / 2, 1, -1);
        RenderHelper.drawCenteredString(status, width / 2, 10, -1);

        RenderHelper.drawString("Name or E-Mail", nameField.x, nameField.y - 10, -1);
        RenderHelper.drawString("Password", passwordField.x, passwordField.y - 10, -1);

        super.render(matrices, mouseX, mouseY, delta);
    }

    public Screen getParent() {
        return parent;
    }
}
