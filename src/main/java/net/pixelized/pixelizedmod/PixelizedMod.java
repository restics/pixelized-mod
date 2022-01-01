package net.pixelized.pixelizedmod;

import net.fabricmc.api.ModInitializer;
import net.pixelized.pixelizedmod.block.ModBlocks;
import net.pixelized.pixelizedmod.item.ModItems;
import net.pixelized.pixelizedmod.sounds.ModSounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PixelizedMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("pixelizedmod");

	public static final String MOD_ID = "pixelizedmod";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
		LOGGER.info("Pixelized Mod is online!");
	}
}
