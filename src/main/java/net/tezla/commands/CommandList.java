package net.tezla.commands;

import com.darkmagician6.eventapi.EventTarget;
import net.tezla.Tezla;
import net.tezla.commands.list.NameCommand;
import net.tezla.commands.list.ToggleCommand;
import net.tezla.events.ChatMessageEvent;
import net.tezla.utils.ChatHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandList {

    private final List<Command> commands = new ArrayList<>();

    public void init() {
        add(
                new NameCommand(),
                new ToggleCommand()
        );
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void add(Command... commands) {
        for (Command c : commands) {
            this.commands.add(c);
            if (Tezla.getInstance().debug)
                Tezla.getInstance().LOGGER.info("Loading Command: " + c.getName());
        }
    }

    public Command getCommandByName(String name, boolean ignoreCase) {
        return this.getCommands().stream().filter(t -> t.getName().equals(name) || ignoreCase && t.getName()
                .equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @EventTarget
    public void onCommand(ChatMessageEvent e) {
        if (e.getMessage().startsWith("#")) {
            e.setCancelled(true);

            String[] input = e.getMessage().split(" ");
            String command = input[0].substring(1);
            String[] args = Arrays.copyOfRange(input, 1, input.length);

            boolean exists = false;

            for (Command c : getCommands()) {
                if (c.getName().equalsIgnoreCase(command)) {
                    c.onCommand(e.getMessage(), args);
                    exists = true;
                }
            }

            if (!exists) ChatHelper.sendMessage("Command not exists!");
        }
    }
}
