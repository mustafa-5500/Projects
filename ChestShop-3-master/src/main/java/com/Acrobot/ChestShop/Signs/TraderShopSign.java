package com.Acrobot.ChestShop.Signs;

import com.Acrobot.Breeze.Utils.StringUtil;
import com.griefcraft.util.Tuple;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;

public class TraderShopSign {
    public static final byte QUANTITY_LINE = 1;
    public static final byte ITEM1_LINE = 2;
    public static String getQuantityLine(String[] lines) throws IllegalArgumentException {
        return lines.length > QUANTITY_LINE ? StringUtil.strip(StringUtil.stripColourCodes(lines[QUANTITY_LINE])) : "";
    }
    /*
     returns the Item1 since ChestShopSign already returns item2,
     */
    public static String getItem(String[] lines) {
        return lines.length > ITEM1_LINE ? StringUtil.strip(StringUtil.stripColourCodes(lines[ITEM1_LINE])) : "";
    }
}
