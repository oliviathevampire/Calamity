package io.github.linkle.valleycraft.items.misc;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class BilletItemBase extends Item {
    public BilletItemBase(Settings settings) { super(settings); }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("item.valley.copper_billet.tooltip").formatted(Formatting.YELLOW) );
        tooltip.add( new TranslatableText("item.valley.copper_billet.tooltip_2").formatted(Formatting.YELLOW) );
    }
}
