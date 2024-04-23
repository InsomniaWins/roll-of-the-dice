package wins.insomnia.rollofthedice.register;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import wins.insomnia.rollofthedice.RollOfTheDice;
import wins.insomnia.rollofthedice.block.BlockIronDie;
import wins.insomnia.rollofthedice.block.BlockWoodenDie;

@Mod.EventBusSubscriber(modid = RollOfTheDice.MODID)
public class RegisterBlocks {

    public static final Block BLOCK_WOODEN_DIE = new BlockWoodenDie(Material.WOOD)
            .setRegistryName(RollOfTheDice.MODID, "wooden_die")
            .setTranslationKey(RollOfTheDice.MODID + ".wooden_die")
            .setCreativeTab(CreativeTabs.DECORATIONS);

    public static final Block BLOCK_IRON_DIE = new BlockIronDie(Material.IRON)
            .setRegistryName(RollOfTheDice.MODID, "iron_die")
            .setTranslationKey(RollOfTheDice.MODID + ".iron_die")
            .setCreativeTab(CreativeTabs.REDSTONE);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event) {

        final Block[] blocks = {
                BLOCK_WOODEN_DIE,
                BLOCK_IRON_DIE
        };

        for (Block block : blocks) {
            event.getRegistry().register(block);

            Item itemBlock = new ItemBlock(block).
                    setRegistryName(block.getRegistryName());

            ForgeRegistries.ITEMS.register(
                    itemBlock
            );

            if (RollOfTheDice.isClient()) {
                RegisterItems.registerItemModel(itemBlock, 0);
            }
        }

    }
}
