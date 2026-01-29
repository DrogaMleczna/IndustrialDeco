package com.drogamleczna.industrialdeco.entity;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, IndustrialDeco.MODID);

    public static final Supplier<EntityType<Seat>> SEAT =
            ENTITY_TYPES.register("seat", () -> EntityType.Builder.of(Seat::new, MobCategory.MISC)
                    .sized(0,0).build("seat"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
