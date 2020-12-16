package net.tezla.commands.list;

import net.tezla.Tezla;
import net.tezla.commands.Command;
import net.tezla.utils.ChatHelper;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle", "Toggle a Module");
    }

    @Override
    public void onCommand(String commandLine, String[] args) {
        if (args.length == 0)
            ChatHelper.sendMessage("Use: #toggle <name>");
        else {
            try {
                Tezla.getInstance().getModuleList().getModByName(args[0], true).toggle();
                ChatHelper.sendMessage("Toggle " + args[0]);
            } catch (Exception e) {
                ChatHelper.sendMessage("Module not exists!");
            }
        }
    }
}
