package net.pixelized.pixelizedmod.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pixelized.pixelizedmod.PixelizedMod;

public class ModSounds {

    public static SoundEvent SUS_MUSIC = registerSoundEvent("sus_music");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(PixelizedMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds(){
        System.out.println("Registering ModSounds for " + PixelizedMod.MOD_ID);
    }
}
