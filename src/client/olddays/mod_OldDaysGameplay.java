package net.minecraft.src;
import net.minecraft.client.Minecraft;

public class mod_OldDaysGameplay extends mod_OldDays{
    public void load(){
        registerModule(this, 2);
        addProperty(1, "Experience",         1, 0, "EnableXP",        "");
        addProperty(2, "Hunger",             1, 0, "EnableHunger",    "");
        addProperty(3, "Instant food",       0, 1, "InstantFood",     "");
        addProperty(4, "Food stacking",      1, 0, "FoodStacking",    "");
        addProperty(5, "Old loot",           0, 1, "OldDrops",        "");
        addProperty(6, "Rare loot",          1, 0, "RareLoot",        "");
        addProperty(7, "Machine bow",        0, 1, "InstantBow",      "");
        addProperty(8, "Bow durability",     1, 0, "FiniteBow",       "");
        addProperty(9, "Combat system",      3, 2, "CombatSystem",    "", new String[]{"Beta 1.3", "Beta 1.7.3", "Beta 1.8.1", "1.0"});
        addProperty(10,"Old armor",          0, 1, "OldArmor",        "");
        addProperty(11,"Allow debug screen", 1, 1, "AllowDebug",      "");
        addProperty(12,"Allow sprint",       1, 1, "AllowSprint",     "");
        addProperty(13,"Jump delay",         1, 0, "JumpDelay",       "");
        loadModuleProperties();
        ModLoader.setInGameHook(this, true, true);
    }

    public void callback (int i){
        switch (i){
            case 1: setBool(net.minecraft.src.EntityXPOrb.class, "noxp", !EnableXP);
                    setBool(net.minecraft.src.GuiIngame.class, "hidexp", !EnableXP); break;
            case 2: setBool(net.minecraft.src.FoodStats.class, "disabled", !EnableHunger);
                    setBool(net.minecraft.src.ItemFood.class, "heal", !EnableHunger);
                    setBool(net.minecraft.src.BlockCake.class, "heal", !EnableHunger);
                    setBool(net.minecraft.src.GuiIngame.class, "hidehunger", !EnableHunger); break;
            case 3: setBool(net.minecraft.src.ItemFood.class, "instant", InstantFood); break;
            case 4: setBool(net.minecraft.src.ItemFood.class, "stacks", FoodStacking); break;
            case 5: setBool(net.minecraft.src.EntityLiving.class, "oldloot", OldDrops); break;
            case 6: setBool(net.minecraft.src.EntityLiving.class, "rareloot", RareLoot); break;
            case 7: setBool(net.minecraft.src.EntityArrow.class, "olddamage", InstantBow);
                    setBool(net.minecraft.src.ItemBow.class, "nocharging", InstantBow); break;
            case 8: setBool(net.minecraft.src.ItemBow.class, "nodurability", !FiniteBow); break;
            case 9: setInt(net.minecraft.src.EntityPlayer.class, "combat", CombatSystem-1);
                    setBool(net.minecraft.src.EntityZombie.class, "defense", CombatSystem>=4);
                    setSwordDamage(CombatSystem<3); break;
            case 10:setBool(net.minecraft.src.EntityPlayer.class, "oldarmor", OldArmor);
                    setArmorDamage(OldArmor); break;
            case 11:setBool(net.minecraft.src.GuiIngame.class, "nodebug", !AllowDebug); break;
            case 12:setBool(net.minecraft.src.EntityPlayer.class, "sprint", AllowSprint);
                    setInt(net.minecraft.src.FoodStats.class, "disabledLevel", AllowSprint ? 20 : 5); break;
            case 13:setBool(net.minecraft.src.EntityLiving.class, "jumpdelay", JumpDelay); break;
        }
    }

    public static boolean EnableXP;
    public static boolean EnableHunger;
    public static boolean InstantFood = true;
    public static boolean FoodStacking;
    public static boolean OldDrops = true;
    public static boolean RareLoot;
    public static boolean InstantBow = true;
    public static boolean FiniteBow;
    public static int CombatSystem = 1;
    public static boolean OldArmor = true;
    public static boolean AllowDebug = true;
    public static boolean AllowSprint = true;
    public static boolean JumpDelay;

    private void setSwordDamage(boolean b){
        try{
            if (b){
                ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordDiamond, 0, 10);
                ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordSteel, 0, 8);
                ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordStone, 0, 6);
            }else{
                ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordDiamond, 0, 7);
                ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordSteel, 0, 6);
                ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordStone, 0, 5);
            }
            ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordWood, 0, 4);
            ModLoader.setPrivateValue(net.minecraft.src.ItemSword.class, Item.swordGold, 0, 4);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    private void setArmorDamage(boolean b){
        try{
            if (b){
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetLeather, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetChain, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetSteel, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetGold, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateLeather, 2, 8);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateChain, 2, 8);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateSteel, 2, 8);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateGold, 2, 8);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsLeather, 2, 6);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsChain, 2, 6);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsSteel, 2, 6);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsGold, 2, 6);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsLeather, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsChain, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsSteel, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsGold, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetLeather, 145, 33 << 0);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetChain, 145, 33 << 1);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetSteel, 145, 33 << 2);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetGold, 145, 33 << 4);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetDiamond, 145, 33 << 3);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateLeather, 145, 48 << 0);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateChain, 145, 48 << 1);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateSteel, 145, 48 << 2);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateGold, 145, 48 << 4);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateDiamond, 145, 48 << 3);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsLeather, 145, 45 << 0);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsChain, 145, 45 << 1);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsSteel, 145, 45 << 2);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsGold, 145, 45 << 4);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsDiamond, 145, 45 << 3);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsLeather, 145, 39 << 0);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsChain, 145, 39 << 1);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsSteel, 145, 39 << 2);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsGold, 145, 39 << 4);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsDiamond, 145, 39 << 3);
            }else{
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetLeather, 2, 1);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetChain, 2, 2);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetSteel, 2, 2);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetGold, 2, 2);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateLeather, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateChain, 2, 5);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateSteel, 2, 6);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateGold, 2, 5);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsLeather, 2, 2);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsChain, 2, 4);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsSteel, 2, 5);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsGold, 2, 3);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsLeather, 2, 1);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsChain, 2, 1);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsSteel, 2, 2);
                ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsGold, 2, 1);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetLeather, 145, 55);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetChain, 145, 165);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetSteel, 145, 165);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetGold, 145, 77);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.helmetDiamond, 145, 363);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateLeather, 145, 80);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateChain, 145, 240);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateSteel, 145, 240);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateGold, 145, 112);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.plateDiamond, 145, 528);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsLeather, 145, 75);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsChain, 145, 225);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsSteel, 145, 225);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsGold, 145, 105);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.legsDiamond, 145, 495);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsLeather, 145, 65);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsChain, 145, 195);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsSteel, 145, 195);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsGold, 145, 91);
                ModLoader.setPrivateValue(net.minecraft.src.Item.class, Item.bootsDiamond, 145, 429);
            }
            ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.helmetDiamond, 2, 3);
            ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.plateDiamond, 2, 8);
            ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.legsDiamond, 2, 6);
            ModLoader.setPrivateValue(net.minecraft.src.ItemArmor.class, Item.bootsDiamond, 2, 3);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public boolean onTickInGame(float f, Minecraft minecraft){
        tickDebug(minecraft);
        return true;
    }

    public void tickDebug(Minecraft minecraft){
        if (AllowDebug){
            return;
        }
        minecraft.gameSettings.showDebugInfo = false;
    }
}