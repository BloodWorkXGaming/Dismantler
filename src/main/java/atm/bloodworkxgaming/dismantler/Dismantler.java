package atm.bloodworkxgaming.dismantler;

import atm.bloodworkxgaming.dismantler.integration.DismantleHelper;
import atm.bloodworkxgaming.dismantler.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
@Mod(modid = Dismantler.MODID, version = Dismantler.VERSION, acceptedMinecraftVersions = "{1.12,1.13)")
public class Dismantler
{
    public static final String MODID = "dismantler";
    public static final String VERSION = "0.1";

    @SidedProxy(serverSide = "atm.bloodworkxgaming.dismantler.proxy.ServerProxy", clientSide = "atm.bloodworkxgaming.dismantler.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Dismantler instance;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        DismantleHelper.registerDefaults();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        proxy.registerModels(event);
    }
}
