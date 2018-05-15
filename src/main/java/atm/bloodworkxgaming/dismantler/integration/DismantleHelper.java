package atm.bloodworkxgaming.dismantler.integration;

import atm.bloodworkxgaming.dismantler.Config.ModConfig;
import atm.bloodworkxgaming.dismantler.integration.envoTech.EnvoTechStructures;
import atm.bloodworkxgaming.dismantler.integration.woot.WootStructure;
import com.valkyrieofnight.et.m_multiblocks.m_lightningrod.features.LRBlocks;
import com.valkyrieofnight.et.m_multiblocks.m_lightningrod.tile.cont.*;
import com.valkyrieofnight.et.m_multiblocks.m_nanobot.m_personal.features.PBlocks;
import com.valkyrieofnight.et.m_multiblocks.m_nanobot.structure.NBStructures;
import com.valkyrieofnight.et.m_multiblocks.m_solar.features.SABlocks;
import com.valkyrieofnight.et.m_multiblocks.m_solar.tile.cont.*;
import com.valkyrieofnight.et.m_multiblocks.m_voidminer.m_botanic.features.BBlocks;
import com.valkyrieofnight.et.m_multiblocks.m_voidminer.m_ore.features.OBlocks;
import com.valkyrieofnight.et.m_multiblocks.m_voidminer.m_res.features.RBlocks;
import com.valkyrieofnight.et.m_multiblocks.m_voidminer.structure.VMStructures;
import com.valkyrieofnight.vlib.api.multiblock.structure.IMultiBlockStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DismantleHelper {
    public static final List<DismantleType> DISMANTLE_TYPES = new ArrayList<>();

    public static void registerDefaults() {
        if (ModConfig.environmental_integration && Loader.isModLoaded("environmentaltech")) {

            DISMANTLE_TYPES.add(new EnvoTechStructures(VMStructures.TIER_1, BBlocks.VOID_BOTANIC_MINER_1, OBlocks.VOID_ORE_MINER_1, RBlocks.VOID_RES_MINER_1));
            DISMANTLE_TYPES.add(new EnvoTechStructures(VMStructures.TIER_2, BBlocks.VOID_BOTANIC_MINER_2, OBlocks.VOID_ORE_MINER_2, RBlocks.VOID_RES_MINER_2));
            DISMANTLE_TYPES.add(new EnvoTechStructures(VMStructures.TIER_3, BBlocks.VOID_BOTANIC_MINER_3, OBlocks.VOID_ORE_MINER_3, RBlocks.VOID_RES_MINER_3));
            DISMANTLE_TYPES.add(new EnvoTechStructures(VMStructures.TIER_4, BBlocks.VOID_BOTANIC_MINER_4, OBlocks.VOID_ORE_MINER_4, RBlocks.VOID_RES_MINER_4));
            DISMANTLE_TYPES.add(new EnvoTechStructures(VMStructures.TIER_5, BBlocks.VOID_BOTANIC_MINER_5, OBlocks.VOID_ORE_MINER_5, RBlocks.VOID_RES_MINER_5));
            DISMANTLE_TYPES.add(new EnvoTechStructures(VMStructures.TIER_6, BBlocks.VOID_BOTANIC_MINER_6, OBlocks.VOID_ORE_MINER_6, RBlocks.VOID_RES_MINER_6));

            DISMANTLE_TYPES.add(new EnvoTechStructures(TileContLightningT1.STRUCTURE, LRBlocks.LIGHTNING_CONT_1));
            DISMANTLE_TYPES.add(new EnvoTechStructures(TileContLightningT2.STRUCTURE, LRBlocks.LIGHTNING_CONT_2));
            DISMANTLE_TYPES.add(new EnvoTechStructures(TileContLightningT3.STRUCTURE, LRBlocks.LIGHTNING_CONT_3));
            DISMANTLE_TYPES.add(new EnvoTechStructures(TileContLightningT4.STRUCTURE, LRBlocks.LIGHTNING_CONT_4));
            DISMANTLE_TYPES.add(new EnvoTechStructures(TileContLightningT5.STRUCTURE, LRBlocks.LIGHTNING_CONT_5));
            DISMANTLE_TYPES.add(new EnvoTechStructures(TileContLightningT6.STRUCTURE, LRBlocks.LIGHTNING_CONT_6));

            DISMANTLE_TYPES.add(new EnvoTechStructures(NBStructures.T1, PBlocks.NANO_PERSONAL_1));
            DISMANTLE_TYPES.add(new EnvoTechStructures(NBStructures.T2, PBlocks.NANO_PERSONAL_2));
            DISMANTLE_TYPES.add(new EnvoTechStructures(NBStructures.T3, PBlocks.NANO_PERSONAL_3));
            DISMANTLE_TYPES.add(new EnvoTechStructures(NBStructures.T4, PBlocks.NANO_PERSONAL_4));
            DISMANTLE_TYPES.add(new EnvoTechStructures(NBStructures.T5, PBlocks.NANO_PERSONAL_5));
            DISMANTLE_TYPES.add(new EnvoTechStructures(NBStructures.T6, PBlocks.NANO_PERSONAL_6));

            try {
                DISMANTLE_TYPES.add(new EnvoTechStructures((IMultiBlockStructure) FieldUtils.readStaticField(TileContSolarT1.class, "STRUCTURE", true), SABlocks.SOLAR_CONT_1));
                DISMANTLE_TYPES.add(new EnvoTechStructures((IMultiBlockStructure) FieldUtils.readStaticField(TileContSolarT2.class, "STRUCTURE", true), SABlocks.SOLAR_CONT_2));
                DISMANTLE_TYPES.add(new EnvoTechStructures((IMultiBlockStructure) FieldUtils.readStaticField(TileContSolarT3.class, "STRUCTURE", true), SABlocks.SOLAR_CONT_3));
                DISMANTLE_TYPES.add(new EnvoTechStructures((IMultiBlockStructure) FieldUtils.readStaticField(TileContSolarT4.class, "STRUCTURE", true), SABlocks.SOLAR_CONT_4));
                DISMANTLE_TYPES.add(new EnvoTechStructures((IMultiBlockStructure) FieldUtils.readStaticField(TileContSolarT5.class, "STRUCTURE", true), SABlocks.SOLAR_CONT_5));
                DISMANTLE_TYPES.add(new EnvoTechStructures((IMultiBlockStructure) FieldUtils.readStaticField(TileContSolarT6.class, "STRUCTURE", true), SABlocks.SOLAR_CONT_6));
            } catch (IllegalAccessException e) {
                System.out.println("Couldn't access solars from environmental Tech");
                e.printStackTrace();
            }
        }

        if (ModConfig.environmental_integration && Loader.isModLoaded("woot")) {
            DISMANTLE_TYPES.add(new WootStructure());
        }

    }

    public static boolean dismantle(EntityPlayer player, World worldIn, BlockPos pos, IBlockState state) {
        Optional<DismantleType> dismantleTypeOptional = DISMANTLE_TYPES.stream().filter(dismantleType -> dismantleType.shouldTrigger(state)).findFirst();

        if (dismantleTypeOptional.isPresent()) {
            DismantleType disType = dismantleTypeOptional.get();

            NonNullList<ItemStack> drops = NonNullList.create();
            disType.startMining(player, worldIn, pos, state, drops);

            // adds items to the player or in the world
            if (!worldIn.isRemote) {
                drops.removeIf(drop -> player.inventory.addItemStackToInventory(drop));

                for (ItemStack drop : drops) {
                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(),pos.getY(),pos.getZ(), drop));
                }
            }

            return true;
        }

        return false;
    }

}
