package com.gipsyking.CitadelClient;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CitadelGroupCommand extends CommandBase {
	
	private String name;
	private Mode mode;

	public CitadelGroupCommand(Mode mode, String name) {
		this.mode = mode;
		this.name = name;
	}

	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "CitadelBuilder client group lookup";
	}
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
        return true;
    }
	
	@Override
    public int getRequiredPermissionLevel() {
        return 1;
    }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {		
		String command = "/" + this.name;
		for (String arg: args) {
			command += " " + arg;
		}
		
		Minecraft.getMinecraft().thePlayer.sendChatMessage(command);
		
		if (args.length == 2 && args[0].equals("group")) {
			mode.lastGroup = args[1];
		} else {
			mode.lastGroup = null;
		}
	}

}
