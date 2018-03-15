package atm.bloodworkxgaming.dismantler;

import atm.bloodworkxgaming.dismantler.items.ItemDismantler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static final ItemDismantler ITEM_DISMANTLER = new ItemDismantler();

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(ITEM_DISMANTLER);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(ITEM_DISMANTLER, 0, new ModelResourceLocation(ITEM_DISMANTLER.getRegistryName(), "inventory"));
    }
}
