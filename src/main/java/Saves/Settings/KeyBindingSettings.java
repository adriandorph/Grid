package Saves.Settings;

import Model.Snake.KeyBinding;
import Saves.FilePath;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KeyBindingSettings {
    private static final List<KeyBinding> keyBindings = generateDefaultKeyBindings();
    private static List<KeyBinding> customKeyBindings = readKeyBindings();
    private static KeyBinding activeKeyBinding = readActiveKeyBinding();

    public static void setActiveKeyBinding(KeyBinding keyBinding) {
        KeyBindingSettings.activeKeyBinding = keyBinding;
        saveActiveKeyBinding();
    }

    public static KeyBinding getActiveKeyBinding(){
        return KeyBindingSettings.activeKeyBinding;
    }

    public static void saveActiveKeyBinding(){
        try {
            Path path = FilePath.getFilePath("activeKeyBinding");
            FileOutputStream fos = new FileOutputStream(String.valueOf(path));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(activeKeyBinding);
            oos.close();
            bos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static KeyBinding readActiveKeyBinding(){
        try{
            Path path = FilePath.getFilePath("activeKeyBinding");
            FileInputStream fis = new FileInputStream(String.valueOf(path));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream objis = new ObjectInputStream(bis);
            KeyBinding activeKeyBinding = ((KeyBinding) objis.readObject());
            if (activeKeyBinding == null) throw new IOException("No active KeyBinding");
            objis.close();
            bis.close();
            fis.close();
            return activeKeyBinding;

        } catch (IOException | ClassNotFoundException e){
            var activeKeyBinding = getKeyBindings().get(0);
           KeyBindingSettings.setActiveKeyBinding(activeKeyBinding);
            return activeKeyBinding;
        }
    }

    public static List<KeyBinding> generateDefaultKeyBindings(){
        return new ArrayList<>(List.of(new KeyBinding[]{
                //north
                //east
                //south
                //west
                //left
                //right
                //pause
                //restart

        }));
    }

    /**
     * Adds keyBinding to customKeyBindings and saves the changes
     * @param keyBinding - Color scheme to be added
     */
    public static void addKeyBinding(KeyBinding keyBinding){
        customKeyBindings.add(keyBinding);
        saveKeyBindings();
    }

    public static void setKeyBindings(List<KeyBinding> keyBindings){
        KeyBindingSettings.customKeyBindings = keyBindings;
        saveKeyBindings();
    }

    public static void updateActiveKeyBinding(KeyBinding keyBinding, int index){
        customKeyBindings.set(index - keyBindings.size(), keyBinding);
       KeyBindingSettings.saveKeyBindings();
       KeyBindingSettings.setActiveKeyBinding(keyBinding);
    }

    public static void deleteKeyBinding(int index){
        customKeyBindings.remove(index - keyBindings.size());
        setKeyBindings(customKeyBindings);
    }

    public static List<KeyBinding> getKeyBindings(){
        List<KeyBinding> allKeyBindings = new LinkedList<>();
        allKeyBindings.addAll(keyBindings);
        allKeyBindings.addAll(customKeyBindings);
        return allKeyBindings;
    }

    public static KeyBinding getKeyBinding(int index){
        if (index < keyBindings.size()) return keyBindings.get(index);
        else return customKeyBindings.get(index - keyBindings.size());
    }

    public static void saveKeyBindings(){
        try {
            Path path = FilePath.getFilePath("keyBindings");
            FileOutputStream fos = new FileOutputStream(String.valueOf(path));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            List<KeyBinding> keyBindings = new LinkedList<>(customKeyBindings);

            oos.writeObject(keyBindings);
            oos.close();
            bos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<KeyBinding> readKeyBindings(){
        try{
            Path path = FilePath.getFilePath("keyBindings");
            FileInputStream fis = new FileInputStream(String.valueOf(path));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream objis = new ObjectInputStream(bis);
            List<KeyBinding> customKeyBindings = new LinkedList<>(((List<KeyBinding>) objis.readObject()));

            if (customKeyBindings.isEmpty()) throw new IOException("No KeyBindings");
            objis.close();
            bis.close();
            fis.close();
            return customKeyBindings;

        } catch (IOException | ClassNotFoundException e){
            return new LinkedList<>();
        }
    }

    public static String getNewKeyBindingName(){
        String prefix = "key binding";
        String newKeyBindingName = prefix + " " + 1;
        boolean available = false;
        int count = 1;
        while (!available){
            available = true;
            for (KeyBinding cs: customKeyBindings){
                newKeyBindingName = prefix + " " + count;
                if (cs.getName().equals(newKeyBindingName)) {
                    available = false;
                    break;
                }
            }
            count++;
        }
        return newKeyBindingName;

    }
}
