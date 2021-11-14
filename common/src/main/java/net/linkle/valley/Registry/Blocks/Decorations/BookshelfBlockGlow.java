package net.linkle.valley.Registry.Blocks.Decorations;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BookshelfBlockGlow extends Block {

    public BookshelfBlockGlow() {
        super(Block.Settings.of(Material.WOOD)
                .sounds(BlockSoundGroup.WOOD)
                .strength(1, 2f).luminance(state -> 15));
    }
}
