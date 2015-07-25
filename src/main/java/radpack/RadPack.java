package radpack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;    

@Mod(modid = RadPack.MODID, name = RadPack.MODNAME, version = RadPack.VERSION)
public class RadPack
{
  public static final String MODID = "radpack";
  public static final String MODNAME = "RadPack";
  public static final String VERSION = "1.1";

  // The instance of your mod that Forge uses.  Optional.
  @Mod.Instance(RadPack.MODID)
  public static RadPack instance;

  // Says where the client and server 'proxy' code is loaded.
  @SidedProxy(clientSide="radpack.ClientOnlyProxy", serverSide="radpack.DedicatedServerProxy")
  public static CommonProxy proxy;

  @EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    proxy.preInit();
  }

  @EventHandler
  public void init(FMLInitializationEvent event)
  {
    proxy.init();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    proxy.postInit();
  }
}
