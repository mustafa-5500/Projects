package com.Acrobot.ChestShop.Listeners.PreShopCreation;

import com.Acrobot.Breeze.Utils.MaterialUtil;
import com.Acrobot.Breeze.Utils.StringUtil;
import com.Acrobot.ChestShop.Configuration.Properties;
import com.Acrobot.ChestShop.Events.ItemParseEvent;
import com.Acrobot.ChestShop.Events.PreShopCreationEvent;
import com.Acrobot.ChestShop.Signs.ChestShopSign;
import com.Acrobot.ChestShop.Signs.TraderShopSign;
import com.Acrobot.ChestShop.Utils.ItemUtil;
import com.Acrobot.ChestShop.Utils.uBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static com.Acrobot.Breeze.Utils.MaterialUtil.*;
import static com.Acrobot.ChestShop.Events.PreShopCreationEvent.CreationOutcome.INVALID_ITEM;
import static com.Acrobot.ChestShop.Events.PreShopCreationEvent.CreationOutcome.ITEM_AUTOFILL;
import static com.Acrobot.ChestShop.Signs.ChestShopSign.ITEM_LINE;
import static com.Acrobot.ChestShop.Signs.ChestShopSign.AUTOFILL_CODE;

/**
 * @author Acrobot
 * Originall checks the last line for an item, and fills in the name of the item
 * will also make it check the second last line, this will only be an item for tradershops
 */
public class ItemChecker implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public static void onPreShopCreation(PreShopCreationEvent event) {
        String itemCode2 = ChestShopSign.getItem(event.getSignLines());
        String itemCode1 = TraderShopSign.getItem(event.getSignLines());

        //the last line, every shop chest has an item here
        ItemParseEvent parseEvent = new ItemParseEvent(itemCode2);
        Bukkit.getPluginManager().callEvent(parseEvent);

        //second last line only trader chest have a line here
        ItemParseEvent parseEvent1 = new ItemParseEvent(itemCode1);
        Bukkit.getPluginManager().callEvent(parseEvent1);

        //first do the last item that every shop chest has, then the second last item.
        ArrayList<String> itemCodes = new ArrayList<String>();
        itemCodes.add(itemCode2);
        itemCodes.add(itemCode1);
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(parseEvent.getItem());
        items.add(parseEvent1.getItem());

        byte j = 0;
        byte i = 0;
        for (byte x = 0; x < items.size(); x ++) {
            ItemStack item = items.get(x);
            String itemCode = itemCodes.get(x);

            if (item == null) {
                if (Properties.ALLOW_AUTO_ITEM_FILL && itemCode.equals(AUTOFILL_CODE)) {
                    Container container = uBlock.findConnectedContainer(event.getSign());
                    if (container != null) {
                        for (ItemStack stack : container.getInventory().getContents()) {
                            if (!MaterialUtil.isEmpty(stack)) {
                                item = stack;
                                if (i == j) {
                                    j ++;
                                    break;
                                }
                            }
                        }
                    }

                    if (item == null && i == 0) {
                        event.setSignLine((byte) (ITEM_LINE - i), ChatColor.BOLD + ChestShopSign.AUTOFILL_CODE);
                        event.setOutcome(ITEM_AUTOFILL);
                        return;
                    }
                } else {
                    if (i == 0) {
                        event.setOutcome(INVALID_ITEM);
                        return;
                    }
                }
            }

            itemCode = ItemUtil.getSignName(item);

            if (StringUtil.getMinecraftStringWidth(itemCode) > MAXIMUM_SIGN_WIDTH) {
                event.setOutcome(INVALID_ITEM);
                return;
            }

            event.setSignLine((byte) (ITEM_LINE - i), itemCode);
            i++;
        }
    }

    private static boolean isSameItem(String newCode, ItemStack item) {
        ItemParseEvent parseEvent = new ItemParseEvent(newCode);
        Bukkit.getPluginManager().callEvent(parseEvent);
        ItemStack newItem = parseEvent.getItem();

        return newItem != null && MaterialUtil.equals(newItem, item);
    }

    private static String getMetadata(String itemCode) {
        Matcher m = METADATA.matcher(itemCode);

        if (!m.find()) {
            return "";
        }

        return m.group();
    }
}
