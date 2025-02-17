package io.github.linkle.valleycraft;

import io.github.linkle.valleycraft.blocks.decorations.Furnaces.Furnaces;
import io.github.linkle.valleycraft.criterions.VCriteria;
import io.github.linkle.valleycraft.init.*;
import io.github.linkle.valleycraft.init.features.*;
import io.github.linkle.valleycraft.utils.SimpleConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValleyMain implements ModInitializer {

    public static final String MOD_ID = "valley";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    @SuppressWarnings("unused")
    public void onInitialize() {
        // This should stay first before items and blocks.
        Object initializer1 = VItemTags.KNIVES; // will force the class to load (any static member will do)
        Object initializer2 = VCriteria.BROKE_BLOCK;
        
        // Simple config system.
        var config = new SimpleConfig("valleycraft", 1);

        //Item Initializers
        FoodAndCooking.initialize();
        Fishing.initialize();
        ItemGroups.initialize();
        WeaponsAndTools.initialize();
        MiscItems.initialize();
        Armors.initialize();

        //Block Initializers
        Furniture.initialize();
        FurnitureCont.initialize();
        Plants.initialize();
        Aquatic.initialize();
        Crops.initialize();
        StoneBlocks.initialize();
        Furnaces.ints();
        PotBlock.initialize();
        
        // Misc Initializers (Recommended put it after the blocks and items initializers)
        VLootTables.initialize(config);
        Sounds.initialize();
        Compostables.initialize();
        Entities.initialize();
        WanderingTraderOffers.initialize(config);
        
        //Configured Feature Initializers
        OreFeatures.initialize(config);
        PlantFeatures.initialize(config);
        CaveFeatures.initialize(config);
        OceanFeatures.initialize(config);
        Trees.initialize(config);

        //Future Updates ;)
        //NetherPlantConfiguredFeatures.initialize();
        //CavePlantConfiguredFeatures.initialize();
        //WaterPlantConfiguredFeatures.initialize();

        //Tells you if this shit actually worked
        System.out.println("The main mod initialization sections loaded fine somehow.");
        config.flush();
    }
}