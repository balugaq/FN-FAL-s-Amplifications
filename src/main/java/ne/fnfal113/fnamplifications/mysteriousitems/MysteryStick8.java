package ne.fnfal113.fnamplifications.mysteriousitems;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import ne.fnfal113.fnamplifications.FNAmplifications;
import ne.fnfal113.fnamplifications.items.FNAmpItems;
import ne.fnfal113.fnamplifications.multiblocks.FnMysteryStickAltar;
import ne.fnfal113.fnamplifications.mysteriousitems.abstracts.AbstractStick;
import ne.fnfal113.fnamplifications.mysteriousitems.implementation.MainStick;
import ne.fnfal113.fnamplifications.utils.Keys;
import ne.fnfal113.fnamplifications.utils.Utils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class MysteryStick8 extends AbstractStick {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();

    public final MainStick mainStick;

    @ParametersAreNonnullByDefault
    public MysteryStick8(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.mainStick = new MainStick(Keys.STICK_8_EXP_LEVELS, Keys.STICK_8_DAMAGE, enchantments(), weaponLore(), stickLore(), 3, 20);
    }

    @Override
    public Map<Enchantment, Integer> enchantments(){
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.DAMAGE_ARTHROPODS, 7);
        enchantments.put(Enchantment.DAMAGE_ALL, 5);
        enchantments.put(Enchantment.DAMAGE_UNDEAD, 6);
        enchantments.put(Enchantment.KNOCKBACK, 2);

        return enchantments;
    }

    @Override
    public String weaponLore(){
        return ChatColor.GOLD + "I'm out of words using this";
    }

    @Override
    public String stickLore(){
        return ChatColor.WHITE + "This stick is kinda heavy";
    }

    @Override
    public void interact(PlayerInteractEvent e) {
        mainStick.onInteract(e, Material.DIAMOND_AXE);
    }

    @Override
    public void onSwing(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getType() != Material.DIAMOND_AXE){
            return;
        }

        if(mainStick.onSwing(item, FNAmpItems.FN_STICK_8, player, event.getDamage(), 18, 3))  {
            LivingEntity victim = (LivingEntity) event.getEntity();
            victim.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 120, 1, false, false, false));
            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1, false, false, false));
            victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0, false, false, false));
            player.sendMessage(Utils.colorTranslator("&cMystery effects was applied to your enemy"));
        }

    }

    public static void setup(){
        new MysteryStick8(FNAmpItems.MYSTERY_STICKS, FNAmpItems.FN_STICK_8, FnMysteryStickAltar.RECIPE_TYPE, new ItemStack[]{
                new SlimefunItemStack(SlimefunItems.ENDER_RUNE, 3), new SlimefunItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE, 1), new SlimefunItemStack(SlimefunItems.FIRE_RUNE, 3),
                new SlimefunItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE, 1), FNAmpItems.FN_STICK_5, new SlimefunItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE, 1),
                new SlimefunItemStack(SlimefunItems.EARTH_RUNE, 3), new SlimefunItemStack(SlimefunItems.AIR_RUNE, 2), new SlimefunItemStack(SlimefunItems.AIR_RUNE, 3)})
                .register(plugin);
    }
}