package com.Acrobot.ChestShop.Signs;

import com.Acrobot.Breeze.Utils.StringUtil;

public class ChestShopTradeSign extends ChestShopSign{
    public static final byte QUANTITY_LINE = 1;
    public static final byte PRICE_LINE = 1;
    public static final byte ITEM1_LINE = 2;
    public static final byte ITEM2_LINE = 3;
    public static String getQuantityLine(String[] lines) throws IllegalArgumentException {
        return lines.length > QUANTITY_LINE ? StringUtil.strip(StringUtil.stripColourCodes(lines[QUANTITY_LINE])) : "";
    }
}
