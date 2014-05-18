package com.gipsyking.CitadelClient;

import net.minecraft.item.ItemStack;


public class Mode {
	
	public String lastGroup = null;
	private IconList iconList;

	public Mode(IconList iconList) {
		this.iconList = iconList;
	}

	public void setReinf(boolean isGroup) {
		hideFort();
		setInfo(false);
		iconList.map.get(Icon.REINFORCEMENT_GROUP).enabled = isGroup;
		iconList.map.get(Icon.REINFORCEMENT_PRIVATE).enabled = !isGroup;
		
	}

	private void hideFort() {
		iconList.map.get(Icon.FORTIFICATION_GROUP).enabled = false;
		iconList.map.get(Icon.FORTIFICATION_PRIVATE).enabled = false;
	}

	public void setOff() {
		iconList.map.get(Icon.FORTIFICATION_PRIVATE).enabled = false;
		iconList.map.get(Icon.FORTIFICATION_GROUP).enabled = false;
		setInfo(false);
	}

	public void setFort(ItemStack itemStack, boolean isGroup) {
		hideReinf();
		setInfo(false);
		
		HUDIcon icon;
		if (isGroup) {
			iconList.map.get(Icon.FORTIFICATION_PRIVATE).enabled = false;
			icon = iconList.map.get(Icon.FORTIFICATION_GROUP);
			
		} else {
			iconList.map.get(Icon.FORTIFICATION_GROUP).enabled = false;
			icon = iconList.map.get(Icon.FORTIFICATION_PRIVATE);
		}
		
		icon.enabled = true;
		icon.setItem(itemStack);
		
	}

	private void hideReinf() {
		iconList.map.get(Icon.REINFORCEMENT_GROUP).enabled = false;
		iconList.map.get(Icon.REINFORCEMENT_PRIVATE).enabled = false;
	}

	public void setInfo(boolean enabled) {
		iconList.map.get(Icon.INFO).enabled = enabled;
		hideReinf();
		hideFort();
		
	}

	public void setBypass(boolean enabled) {
		iconList.map.get(Icon.BYPASS).enabled = enabled;
		
	}

}
