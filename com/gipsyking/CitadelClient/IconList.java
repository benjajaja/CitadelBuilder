package com.gipsyking.CitadelClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class IconList {

	private Minecraft mc;
	public HashMap<Icon, HUDIcon> map = new HashMap<Icon, HUDIcon>();
	private ScaledResolution scaledResolution;
	private List<Icon> order;

	public IconList() {
		this.mc = Minecraft.getMinecraft();
		
		this.map.put(Icon.INFO, new HUDIcon(mc, "textures/gui/container/inventory.png", 72, 216, 18, 18));
		this.map.put(Icon.BYPASS, new HUDIcon(mc, "textures/gui/container/inventory.png", 36, 198, 18, 18));
		
		this.map.put(Icon.FORTIFICATION_GROUP, new HUDIcon(mc, "textures/gui/achievement/achievement_background.png", 26, 202, 26, 26));
		this.map.put(Icon.FORTIFICATION_PRIVATE, new HUDIcon(mc, "textures/gui/achievement/achievement_background.png", 0, 202, 26, 26));
		
		this.map.put(Icon.REINFORCEMENT_GROUP, new HUDIcon(mc, "textures/gui/achievement/achievement_background.png", 26, 202, 26, 26));
		this.map.put(Icon.REINFORCEMENT_PRIVATE, new HUDIcon(mc, "textures/gui/achievement/achievement_background.png", 0, 202, 26, 26));
		
		ItemStack privateItem = Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(Enchantment.unbreaking, Enchantment.unbreaking.getMinLevel()));
		this.map.get(Icon.REINFORCEMENT_GROUP).setItem(privateItem);
		this.map.get(Icon.REINFORCEMENT_PRIVATE).setItem(privateItem);
		
		this.order = Arrays.asList(new Icon[]{
				Icon.FORTIFICATION_GROUP, Icon.FORTIFICATION_PRIVATE,
				Icon.REINFORCEMENT_GROUP, Icon.REINFORCEMENT_PRIVATE,
				Icon.INFO, Icon.BYPASS
		});
	}

	@SubscribeEvent(priority=EventPriority.NORMAL)
    public void onTick(RenderGameOverlayEvent.Post  event) {
		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
//    	if (!mc.inGameHasFocus || mc.currentScreen == null || mc.currentScreen instanceof GuiChat) {
//			return;
//		}
		
		scaledResolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		
		int x = 0;
		HUDIcon icon;
		for (Icon name: order) {
			icon = map.get(name);
			if (icon.enabled) {
				icon.render(scaledResolution.getScaledWidth() - icon.width - x, scaledResolution.getScaledHeight() - icon.height);
				x += icon.width + 2;
			}
		}
	}
}
