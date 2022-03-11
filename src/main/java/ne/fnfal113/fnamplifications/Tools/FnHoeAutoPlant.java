package ne.fnfal113.fnamplifications.tools;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import ne.fnfal113.fnamplifications.FNAmplifications;
import ne.fnfal113.fnamplifications.items.FNAmpItems;
import ne.fnfal113.fnamplifications.multiblocks.FnAssemblyStation;
import ne.fnfal113.fnamplifications.tools.abstracts.AbstractHoe;
import ne.fnfal113.fnamplifications.tools.implementation.HoeTask;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FnHoeAutoPlant extends AbstractHoe implements NotPlaceable {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();

    private final HoeTask hoeTask = new HoeTask();

    public FnHoeAutoPlant(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void onRightClick(Player player, Block clickedBlock){
        hoeTask.tillLand(player, clickedBlock);
    }

    @Override
    public void onLeftClick(Player player, Block clickedBlock, ItemStack itemStack){
        hoeTask.harvest(player, clickedBlock, itemStack, true);
    }

    @Override
    public boolean useVanillaBlockBreaking(){
        return true;
    }

    public static void setup(){
        new FnHoeAutoPlant(FNAmpItems.FN_MISC, FNAmpItems.FN_HOE_5X5_AUTO_PLANT, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                SlimefunItems.COMMON_TALISMAN, new SlimefunItemStack(SlimefunItems.CARBON, 6), SlimefunItems.COMMON_TALISMAN,
                new SlimefunItemStack(SlimefunItems.EARTH_RUNE, 7), new ItemStack(Material.DIAMOND_HOE), new SlimefunItemStack(SlimefunItems.EARTH_RUNE, 7),
                new SlimefunItemStack(SlimefunItems.REINFORCED_ALLOY_INGOT, 14), new SlimefunItemStack(SlimefunItems.CARBON, 6), new SlimefunItemStack(SlimefunItems.REINFORCED_ALLOY_INGOT, 14)})
                .register(plugin);
    }
}