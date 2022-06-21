package net.pixelized.pixelizedmod.world.features;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.pixelized.pixelizedmod.PixelizedMod;
import net.pixelized.pixelizedmod.block.ModBlocks;

public class ModConfiguredFeatures {

    //one key for every feature
    public static final RegistryKey<ConfiguredFeature<?,?>> LIGHTWOOD_TREE_KEY = registryKey("lightwood_spawn");

    //TODO: use 1.18 stuff or something
//    public static final ConfiguredFeature<?,?> LIGHTWOOD_TREE = register(Feature.TREE.configure (new TreeFeatureConfig.Builder(
//            new SimpleBlockStateProvider(ModBlocks.LIGHTWOOD_LOG.getDefaultState()),
//            new StraightTrunkPlacer(0,4,3),
//            new SimpleBlockStateProvider(ModBlocks.LIGHTWOOD_LEAVES.getDefaultState()),
//            new SimpleBlockStateProvider(ModBlocks.LIGHTWOOD_SAPLING.getDefaultState()),
//            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
//            new TwoLayersFeatureSize(1,0,1)).build()));
//    public static final ConfiguredFeature<?,?> LIGHTWOOD_TREE_SPAWN = register(LIGHTWOOD_TREE)

    private static RegistryKey<ConfiguredFeature<?,?>> registryKey(String name){
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(PixelizedMod.MOD_ID, name));
    }

    private static ConfiguredFeature<?, ?> register(ConfiguredFeature<?,?> configuredFeature, RegistryKey<ConfiguredFeature<?,?>> key){
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key.getValue(), configuredFeature );
    }

    public static void registerConfiguredFeatures(){
        System.out.println("Registering ModConfigFeatures for " + PixelizedMod.MOD_ID);
    }
}
