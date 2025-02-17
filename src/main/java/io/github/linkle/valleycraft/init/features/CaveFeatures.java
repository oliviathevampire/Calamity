package io.github.linkle.valleycraft.init.features;

import io.github.linkle.valleycraft.ValleyMain;
import io.github.linkle.valleycraft.init.Plants;
import io.github.linkle.valleycraft.utils.SimpleConfig;
import io.github.linkle.valleycraft.utils.Util;
import io.github.linkle.valleycraft.world.gen.features.CavePatchConfig;
import io.github.linkle.valleycraft.world.gen.features.CavePatchFeature;
import io.github.linkle.valleycraft.world.gen.features.GlowKelpFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.ArrayList;

import static io.github.linkle.valleycraft.utils.Util.register;

public class CaveFeatures {
    /** Custom gen feature to spawn stuffs in caves. */
    private static final CavePatchFeature CAVE_PATCH = new CavePatchFeature();
    private static final GlowKelpFeature GLOW_KELP = new GlowKelpFeature();

    private static final ConfiguredFeature<?, ?> REDSTONE_CRYSTAL_PATCH_CONFIG = CAVE_PATCH
            .configure(new CavePatchConfig(BlockStateProvider.of(Plants.REDSTONE_CRYSTAL.getDefaultState()), ConstantIntProvider.create(20),
                    ConstantIntProvider.create(4), ConstantIntProvider.create(6)));
    
    private static final ConfiguredFeature<?, ?> SPIDER_EGG_PATCH_CONFIG = CAVE_PATCH
            .configure(new CavePatchConfig(BlockStateProvider.of(Plants.SPIDER_EGG_BLOCK.getDefaultState()), ConstantIntProvider.create(20),
                    ConstantIntProvider.create(4), ConstantIntProvider.create(6)));

    public static void initialize(SimpleConfig config) {
        if (config.get("disable-features-gen", false)) {
            return;
        }
        
        Registry.register(Registry.FEATURE, new Identifier(ValleyMain.MOD_ID, "cave_patch"), CAVE_PATCH);
        Registry.register(Registry.FEATURE, new Identifier(ValleyMain.MOD_ID, "glow_kelp"), GLOW_KELP);
        var underground = GenerationStep.Feature.UNDERGROUND_DECORATION;
        ArrayList<PlacementModifier> list;
        RegistryKey<PlacedFeature> place;
        
        list = new ArrayList<>();
        list.add(RarityFilterPlacementModifier.of(3));
        list.add(SquarePlacementModifier.of());
        list.add(HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0)));
        place = Util.register("redstone_crystal_patch_cave", REDSTONE_CRYSTAL_PATCH_CONFIG, list);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), underground, place);
        
        list = new ArrayList<>();
        list.add(RarityFilterPlacementModifier.of(4));
        list.add(SquarePlacementModifier.of());
        list.add(HeightRangePlacementModifier.uniform(YOffset.aboveBottom(24), YOffset.fixed(24)));
        place = Util.register("spider_egg_patch_cave", SPIDER_EGG_PATCH_CONFIG, list);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), underground, place);
        
        list = new ArrayList<>();
        list.add(CountPlacementModifier.of(10));
        list.add(SquarePlacementModifier.of());
        list.add(HeightRangePlacementModifier.uniform(YOffset.aboveBottom(10), YOffset.fixed(32)));
        place = register("glow_kelp_patch_cave", GLOW_KELP.configure(FeatureConfig.DEFAULT), list);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), underground, place);
    }
}
