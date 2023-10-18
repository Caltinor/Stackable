package dicemc.stackable;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod(value=Stackable.MODID)
public class Stackable {
    public static final String MODID = "stackable";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);
    public static final RegistryObject<Stack> STACK = ITEMS.register("stack", Stack::new);

    public Stackable() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::creativeTabs);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        ItemProperties.register(STACK.get(), new ResourceLocation("filled"), (stack, level, entity, i) -> Stack.getContents(stack).toList().size());
    }

    public void creativeTabs(BuildCreativeModeTabContentsEvent event) {
    if (event.getTab().equals(BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.TOOLS_AND_UTILITIES)))
            event.accept(STACK.get());
    }
}
