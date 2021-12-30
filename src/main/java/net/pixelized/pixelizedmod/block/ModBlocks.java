package net.pixelized.pixelizedmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pixelized.pixelizedmod.PixelizedMod;
import net.pixelized.pixelizedmod.block.custom.CounterBlock;
import net.pixelized.pixelizedmod.item.ModItemGroup;

public class ModBlocks {

    public static final Block TOPAZ_ORE = registerBlock("topaz_ore",
            new Block(AbstractBlock.Settings.of(Material.STONE).strength(2,3).requiresTool()));

    public static final Block TOPAZ_BLOCK = registerBlock("topaz_block",
            new Block(AbstractBlock.Settings.of(Material.STONE).strength(2,3).requiresTool()));

    public static final CounterBlock COUNT_BLOCK = (CounterBlock) registerBlock("count_block",
            new CounterBlock(AbstractBlock.Settings.of(Material.STONE).strength(2,3).requiresTool()));



    private static void register(String id, Block block) {
        Identifier identifier = new Identifier(PixelizedMod.MOD_ID, id);
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(ModItemGroup.PIXELIZED)));
    }

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(PixelizedMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registry.ITEM, new Identifier(PixelizedMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ModItemGroup.PIXELIZED)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + PixelizedMod.MOD_ID);
    }
}
