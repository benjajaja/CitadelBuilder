package com.gipsyking.CitadelClient;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChatParser {
	
	private static String MODE_NORMAL = "§r§aAll Citadel modes set to normal§r";
	private static String MODE_FORTIFICATION = "§r§aFORTIFICATION mode ";
	private static String MODE_REINFORCEMENT = "§r§aREINFORCEMENT mode ";
	private static String OFF = "off";
	
	private static String WARNING_START = "§r§e";
	private static String DEPLETED = "depleted, left";
	
	private static String INFO_ENABLED = "§r§aINFO mode on§r";
	private static String INFO_DISABLED = "§r§aINFO mode off§r";
	
	private static String BYPASS_ENABLED = "§r§aBypass mode enabled§r";
	private static String BYPASS_DISABLED = "§r§aBypass mode disabled§r";
	private Mode mode;
	
	
	
	public ChatParser(Mode mode) {
		this.mode = mode;
	}



	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent e) {
		if (e.message == null) {
			return;
		}
		
		String text = e.message.getFormattedText();
		
		if (text.startsWith(MODE_FORTIFICATION)) {
			if (text.contains(OFF)) {
				mode.setOff();
				return;
			}
			
			boolean isGroup = text.contains("GROUP");
			
			ItemStack itemStack;
			if (text.contains("STONE")) {
				itemStack = new ItemStack(Blocks.stone);
			} else if (text.contains("IRON_INGOT")) {
				itemStack = new ItemStack(Items.iron_ingot);
			} else if (text.contains("DIAMOND")) {
				itemStack = new ItemStack(Items.diamond);
			} else {
				CitadelBuilder.logger.warning("Unknown material: " + text);
				return;
			}
			
			mode.setFort(itemStack, isGroup);
			
		} else if (text.startsWith(MODE_REINFORCEMENT)) {
			if (text.contains(OFF)) {
				mode.setOff();
				return;
			}
			
			mode.setReinf(text.contains("GROUP"));
			
		} else if (text.equals(INFO_ENABLED)) {
			mode.setInfo(true);
			
		} else if (text.equals(INFO_DISABLED)) {
			mode.setInfo(false);
			
		} else if (text.equals(BYPASS_ENABLED)) {
			mode.setBypass(true);
			
		} else if (text.equals(BYPASS_DISABLED)) {
			mode.setBypass(false);
			
		} else if (text.equals(MODE_NORMAL)) {
			mode.setOff();
			mode.setBypass(false);
			
		} else if (text.startsWith(WARNING_START)) {
			if (text.contains(DEPLETED) || text.contains(OFF)) {
				mode.setOff();
			}
		}
	
	}
}

