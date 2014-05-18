package com.gipsyking.CitadelClient;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {
	private Mode mode;

	public KeyInputHandler(Mode mode) {
		this.mode = mode;
	}

	@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
		String command = null;
		
		if (KeyBindings.fortification.isPressed()) {
			command = "/ctf";
        	if (mode.lastGroup != null) {
        		command += " group " + mode.lastGroup;
        	}
        	
        } else if (KeyBindings.reinforcement.isPressed()) {
        	command = "/ctr";
        	if (mode.lastGroup != null) {
        		command += " group " + mode.lastGroup;
        	}
        	
        } else if (KeyBindings.bypass.isPressed()) {
        	command = "/ctb";
        	
        } else if (KeyBindings.info.isPressed()) {
        	command = "/cti";
        	
        }
		
		
		if (command != null) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage(command);
		}
            
    }
}
