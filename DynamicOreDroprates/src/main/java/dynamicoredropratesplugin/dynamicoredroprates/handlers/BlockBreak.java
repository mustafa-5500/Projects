package dynamicoredropratesplugin.dynamicoredroprates.handlers;

import dynamicoredropratesplugin.dynamicoredroprates.Randomizer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Chunk chunk = block.getChunk();
        ItemStack helditem = event.getPlayer().getItemInUse();
        if(silkTouch(helditem)){
            Bukkit.getServer().broadcastMessage("silk");
            block.setMetadata("Silk", new MetadataValue() {
                @Override
                public Object value() {
                    return null;
                }

                @Override
                public int asInt() {
                    return 0;
                }

                @Override
                public float asFloat() {
                    return 0;
                }

                @Override
                public double asDouble() {
                    return 0;
                }

                @Override
                public long asLong() {
                    return 0;
                }

                @Override
                public short asShort() {
                    return 0;
                }

                @Override
                public byte asByte() {
                    return 0;
                }

                @Override
                public boolean asBoolean() {
                    return true;
                }

                @Override
                public String asString() {
                    return null;
                }

                @Override
                public Plugin getOwningPlugin() {
                    return null;
                }

                @Override
                public void invalidate() {

                }
            });

        } else {
            int dropRate = getDropRate(chunk);

            if (isOre(block.getType())) {
                dropModifiedOreRate(block, dropRate);
            }
        }

    }
    private boolean silkTouch(ItemStack item){
        if(item == null || !(item.hasItemMeta())){
            return false;
        }
        return Objects.requireNonNull(item.getItemMeta()).hasEnchant(Enchantment.SILK_TOUCH);


    }
    private void dropModifiedOreRate(Block block, int dropRate){

        if(!block.getMetadata("Silk").isEmpty()){
            return;
        }
        dropRate = Randomizer.generate(dropRate);

        ArrayList<ItemStack> original = (ArrayList<ItemStack>) block.getDrops();
        Material mat = original.get(0).getType();
        int count = 0;
        for (ItemStack drop: original){
            count += drop.getAmount();
        }
        ItemStack multiplied = new ItemStack(mat, count*dropRate);

        block.getWorld().dropItemNaturally(block.getLocation(), multiplied);
    }
    private boolean isOre(Material material){
        return  material == Material.IRON_ORE ||
                material == Material.GOLD_ORE ||
                material == Material.COAL_ORE ||
                material == Material.COPPER_ORE ||
                material == Material.REDSTONE_ORE ||
                material == Material.LAPIS_ORE ||
                material == Material.DIAMOND_ORE ||
                material == Material.EMERALD_ORE ||
                material == Material.DEEPSLATE_COPPER_ORE ||
                material == Material.DEEPSLATE_COAL_ORE ||
                material == Material.DEEPSLATE_IRON_ORE ||
                material == Material.DEEPSLATE_GOLD_ORE ||
                material == Material.DEEPSLATE_REDSTONE_ORE ||
                material == Material.DEEPSLATE_LAPIS_ORE ||
                material == Material.DEEPSLATE_DIAMOND_ORE ||
                material == Material.DEEPSLATE_EMERALD_ORE;
    }
    private int getDropRate(Chunk chunk){
        return 4;
        //I dont know how the ore drop rates are implemented yet.
    }
}
