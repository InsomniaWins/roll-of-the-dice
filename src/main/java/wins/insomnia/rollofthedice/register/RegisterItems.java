package wins.insomnia.rollofthedice.register;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wins.insomnia.rollofthedice.RollOfTheDice;

@Mod.EventBusSubscriber(modid = RollOfTheDice.MODID)
public class RegisterItems {

    private static void registerItemModels() {

        //registerItemModel(ITEM_PLACEHOLDER_ITEM, 0);

    }

    public static void registerItemModel(Item item, int meta) {

        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));

    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {

        final Item[] items = {

        };
        event.getRegistry().registerAll(items);

        if (RollOfTheDice.isClient()) {
            registerItemModels();
        }
    }

}
