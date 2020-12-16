package net.tezla.commands.list;

import net.tezla.commands.Command;
import net.tezla.utils.ChatHelper;

public class NameCommand extends Command {

    public NameCommand() {
        super("name", "Print the Name of the Current Session");
    }

    @Override
    public void onCommand(String commandLine, String[] args) {
        ChatHelper.sendMessage(MC.getSession().getUsername());
    }
}
