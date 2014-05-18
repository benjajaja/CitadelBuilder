package com.gipsyking.CitadelClient;

import java.util.logging.Logger;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid="citadelbuilder", name="CitadelBuilder", version="1.3")
public class CitadelBuilder {

	public static Logger logger = Logger.getLogger("CitadelClient");
	
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	    	
    	IconList iconList = new IconList();
    	Mode mode = new Mode(iconList);
    	
    	MinecraftForge.EVENT_BUS.register(new ChatParser(mode));
    	
    	MinecraftForge.EVENT_BUS.register(iconList);
    	
    	KeyBindings.init();
    	FMLCommonHandler.instance().bus().register(new KeyInputHandler(mode));
    	
    	ClientCommandHandler.instance.registerCommand(new CitadelGroupCommand(mode, "ctf"));
    	ClientCommandHandler.instance.registerCommand(new CitadelGroupCommand(mode, "ctr"));
   	
    }
}
