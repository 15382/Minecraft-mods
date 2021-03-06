package net.minecraft.src;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.*;
import net.minecraft.client.Minecraft;
import java.lang.reflect.Field;

public class mod_OldDays extends BaseModMp{
    public String getVersion(){
        return "1.2.5";
    }

    public mod_OldDays(){
        texman = new TextureManager();
        saveman = new SavingManager(this);
        smpman = new SMPManager(this);
        modules = new ArrayList();
        lang = new OldDaysEasyLocalization("olddays");
    }

    public void load(){
        ModLoader.registerKey(this, this.keySettings, false);
        ModLoader.addLocalization("key_settings", "Old Days Settings");
        ModLoader.setInGameHook(this, true, true);
        loadModules(this);
        saveman.loadAll();
    }

    public void keyboardEvent(KeyBinding keybinding){
        if (keybinding==keySettings && getMinecraftInstance().currentScreen==null){
            ModLoader.openGUI(getMinecraftInstance().thePlayer, new GuiOldDaysModules(null));
        }
        for (int i = 0; i < modules.size(); i++){
            getModuleById(i).keyboardEvent(keybinding);
        }
    }

    public boolean renderWorldBlock(RenderBlocks r, IBlockAccess i, int x, int y, int z, Block b, int id){
        for (int j = 0; j < modules.size(); j++){
            if (getModuleById(j).renderWorldBlock(r, i, x, y, z, b, id)){
                return true;
            }
        }
        return false;
    }

    public boolean onTickInGame(float f, Minecraft minecraft){
        smpman.onTick();
        texman.onTick();
        return true;
    }

    public static void loadModules(mod_OldDays core){
        Class c = net.minecraft.src.mod_OldDays.class;
        String p = "";
        try{
            p = c.getPackage().getName()+".";
        }catch(Exception ex){}
        File file = new File(c.getProtectionDomain().getCodeSource().getLocation().getPath()+p.replace(".", "/"));
        List classes = new ArrayList();
        if (file.getName().endsWith(".zip") || file.getName().endsWith(".jar")){
            try{
                ZipFile jar = new ZipFile(file);
                Enumeration entries = jar.entries();
                while (entries.hasMoreElements()){
                    String str = ((ZipEntry)entries.nextElement()).getName();
                    if (str.startsWith("OD") && str.endsWith(".class")){
                        classes.add(str.replace(".class", ""));
                    }
                }
            }catch(Exception ex){
                System.out.println(ex);
            }
        }else{
            String[] str = file.list();
            for (int i = 0; i < str.length; i++){
                if (str[i].startsWith("OD") && str[i].endsWith(".class")){
                    classes.add(str[i].replace(".class", ""));
                }
            }
        }
        for (int i = 0; i < classes.size(); i++){
            String name = ((String)classes.get(i));
            Class c2 = null;
            try{
                c2 = c.getClassLoader().loadClass(p+name);
            }catch(Exception ex){
                System.out.println("OldDays: Failed to load module: "+ex);
                continue;
            }
            if (!((net.minecraft.src.OldDaysModule.class).isAssignableFrom(c2))){
                continue;
            }
            OldDaysModule module = null;
            try{
                module = ((OldDaysModule)c2.getDeclaredConstructor(c).newInstance(core));
            }catch(Exception ex){
                System.out.println("OldDays: Failed to load module: "+ex);
                continue;
            }
            modules.add(module);
            System.out.println("OldDays: Loaded "+module.name+" module");
        }
    }

    public void handlePacket(Packet230ModLoader packet){}

    public static void loadModuleProperties(){}
    
    public static int getPropertyType(int id, int id2){
        return getModuleById(id).getPropertyById(id2).type;
    }
    
    public static int getPropertyGuiType(int id, int id2){
        return getModuleById(id).getPropertyById(id2).guitype;
    }

    public static String getPropertyButtonText(int id, int id2){
        return getModuleById(id).getPropertyById(id2).getButtonText();
    }

    public static OldDaysModule getModuleById(int id){
        for (int i = 0; i < modules.size(); i++){
            OldDaysModule module = ((OldDaysModule)modules.get(i));
            if (module.id == id){
                return module;
            }
        }
        return null;
    }

    public static void saveModuleProperties(int id){}

    public static String getStringPropValue(int id, int id2){
        return getModuleById(id).getPropertyById(id2).saveToString();
    }

    public static void setStringPropValue(int id, int id2, String str){
        getModuleById(id).getPropertyById(id2).loadFromString(str);
    }

    public static void sendCallback(int id, int id2){
        getModuleById(id).last = id2;
        getModuleById(id).getPropertyById(id2).onChange();
        texman.onTick();
    }

    public static void sendCallbackAndSave(int id, int id2){
        sendCallback(id, id2);
        saveman.saveAll();
    }

    public static Minecraft getMinecraftInstance(){
        return ModLoader.getMinecraftInstance();
    }

    public static int getDescriptionNumber(String s){
        boolean end = false;
        int i = 0;
        while (!end){
            i++;
            end = (s+i).startsWith(lang.get(s+i));
        }
        return i - 1;
    }

    public KeyBinding keySettings = new KeyBinding("key_settings", 35);
    public static TextureManager texman;
    public static SavingManager saveman;
    public static SMPManager smpman;
    public static List modules;
    public static OldDaysEasyLocalization lang;
}
