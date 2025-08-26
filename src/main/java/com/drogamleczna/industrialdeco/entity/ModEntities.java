package com.drogamleczna.industrialdeco.entity;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, IndustrialDeco.MODID);

    public static final RegistryObject<EntityType<Seat>> SEAT =
            ENTITY_TYPES.register("seat", () -> EntityType.Builder.of(Seat::new, MobCategory.MISC)
                    .sized(0,0).build("seat"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
