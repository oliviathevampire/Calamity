package io.github.linkle.valleycraft;

import io.github.linkle.valleycraft.config.VConfig;
import io.github.linkle.valleycraft.init.*;
import io.github.linkle.valleycraft.init.features.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValleyMain implements ModInitializer {

    public static final String MOD_ID = "valley";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static VConfig CONFIG;

    @Override
    public void onInitialize() {
        AutoConfig.register(VConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(VConfig.class).getConfig();

        // This should stay first before items and blocks.
        VItemTags.initialize();

        //Item Initializers
        FoodAndCooking.initialize();
        Fishing.initialize();
        ItemGroups.initialize();
        WeaponsAndTools.initialize();
        MiscItems.initialize();
        VArmorMaterials.initialize();
        Armors.initialize();

        //Block Initializers
        Furniture.initialize();
        FurnitureCont.initialize();
        Plants.initialize();
        Aquatic.initialize();
        Crops.initialize();
        StoneBlocks.initialize();
        Furnaces.initialize();
        PotBlock.initialize();
        
        // Misc Initializers (Recommended put it after the blocks and items initializers)
        VLootTables.initialize();
        Sounds.initialize();
        Compostables.initialize();
        Entities.initialize();
        WanderingTraderOffers.initialize();
        
        //Configured Feature Initializers
        OreFeatures.initialize();
        PlantFeatures.initialize();
        CaveFeatures.initialize();
        OceanFeatures.initialize();
        Trees.initialize();

        //Future Updates ;)
        //NetherPlantConfiguredFeatures.initialize();
        //CavePlantConfiguredFeatures.initialize();
        //WaterPlantConfiguredFeatures.initialize();

        //Tells you if this actually worked
        LOGGER.info("The main mod initialization sections loaded fine somehow.");
    }
}