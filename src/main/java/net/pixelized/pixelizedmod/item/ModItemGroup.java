package net.pixelized.pixelizedmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.pixelized.pixelizedmod.PixelizedMod;
import net.pixelized.pixelizedmod.block.ModBlocks;

public class ModItemGroup {
    public static final ItemGroup PIXELIZED = FabricItemGroupBuilder.build(new Identifier(PixelizedMod.MOD_ID, "pixelized"),
            () -> new ItemStack(ModBlocks.COUNT_BLOCK));
}
