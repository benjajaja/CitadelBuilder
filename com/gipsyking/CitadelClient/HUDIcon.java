package com.gipsyking.CitadelClient;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class HUDIcon extends Gui {

	public static RenderItem itemRenderer = new RenderItem();
	
	private Minecraft mc;
	private ResourceLocation resource;
	private int x;
	private int y;
	public int width;
	public int height;
	public boolean enabled;
	private ItemStack itemStack = null;
	
	public HUDIcon(Minecraft minecraft, String resource, int x, int y, int width, int height) {
		super();
		
		this.mc = minecraft;
		this.resource = new ResourceLocation(resource);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.enabled = false;
	}
	
	public void render(int x, int y) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
    	
        mc.getTextureManager().bindTexture(resource);
    	this.drawTexturedModalRect(x, y, this.x, this.y, this.width, this.height);
    	
    	if (this.itemStack != null) {
    		RenderHelper.enableStandardItemLighting();
    		RenderHelper.enableGUIStandardItemLighting();

    		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
    		
            itemRenderer.zLevel = 200.0F;
            itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), this.itemStack,
    				x + (int)Math.floor((this.width - 16) / 2), y +(int)Math.floor((this.height - 16) / 2));
    		
    		RenderHelper.disableStandardItemLighting();
    		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
    	}
    	
    	GL11.glDisable(GL11.GL_BLEND);
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

	public void setItem(ItemStack itemStack) {
		this.itemStack  = itemStack;
	}
}
