package net.pixelized.pixelizedmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pixelized.pixelizedmod.PixelizedMod;
import net.pixelized.pixelizedmod.block.custom.CounterBlock;
import net.pixelized.pixelizedmod.block.custom.LightwoodLeaves;
import net.pixelized.pixelizedmod.block.custom.LightwoodLog;
import net.pixelized.pixelizedmod.block.custom.LightwoodSapling;
import net.pixelized.pixelizedmod.item.ModItemGroup;
import net.pixelized.pixelizedmod.world.features.tree.ModSaplingGenerator;

public class ModBlocks {

    public static final Block TOPAZ_ORE = registerBlock("topaz_ore",
            new Block(AbstractBlock.Settings.of(Material.STONE).strength(2,3).requiresTool()));

    public static final Block TOPAZ_BLOCK = registerBlock("topaz_block",
            new Block(AbstractBlock.Settings.of(Material.STONE).strength(2,3).requiresTool()));

    public static final CounterBlock COUNT_BLOCK = (CounterBlock) registerBlock("count_block",
            new CounterBlock(AbstractBlock.Settings.of(Material.DECORATION).strength(1,50).requiresTool()));

    public static final Block LIGHTWOOD_LOG = registerBlock("lightwood_log",
            new LightwoodLog(AbstractBlock.Settings.of(Material.WOOD).strength(2,3).requiresTool()));
    public static final Block LIGHTWOOD_LEAVES = registerBlock("lightwood_leaves",
            new LightwoodLeaves(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block LIGHTWOOD_SAPLING = registerBlock("lightwood_sapling",
            new LightwoodSapling(new ModSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)));



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
