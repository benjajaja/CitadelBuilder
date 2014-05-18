package com.gipsyking.CitadelClient;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {
	
	public static KeyBinding fortification;
	public static KeyBinding reinforcement;
	public static KeyBinding bypass;
	public static KeyBinding info;
	
	public static void init() {
		fortification = new KeyBinding("key.fortification", Keyboard.KEY_F, "key.categories.citadel");
		ClientRegistry.registerKeyBinding(fortification);
		
		reinforcement = new KeyBinding("key.reinforcement", Keyboard.KEY_R, "key.categories.citadel");
		ClientRegistry.registerKeyBinding(reinforcement);
		
		bypass = new KeyBinding("key.bypass", Keyboard.KEY_B, "key.categories.citadel");
		ClientRegistry.registerKeyBinding(bypass);
		
		info = new KeyBinding("key.info", Keyboard.KEY_I, "key.categories.citadel");
		ClientRegistry.registerKeyBinding(info);
	}
}
