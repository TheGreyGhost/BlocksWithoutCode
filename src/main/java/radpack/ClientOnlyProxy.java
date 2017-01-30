package radpack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * ClientProxy is used to set up the mod and start it running on normal minecraft.  It contains all the code that should run on the
 *   client side only.
 *   For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public class ClientOnlyProxy extends CommonProxy
{
  /**
   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
   */
  public void preInit()
  {
    super.preInit();

    final int DEFAULT_ITEM_SUBTYPE = 0;

    for (int i = 0; i < creativeTabCount; ++i) {
      String name = "radpacktabicon" + (i+1);
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("radpack:" + name, "inventory");
      ModelLoader.setCustomModelResourceLocation(RadPack.proxy.itemTabIcons[i], DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    for (int i = 0; i < blockSolidCount; ++i) {
      String name = "blocksolid" + (i+1);
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("radpack:" + name, "inventory");
      ModelLoader.setCustomModelResourceLocation(RadPack.proxy.itemBlockSolids[i], DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    for (int i = 0; i < blockCutoutCount; ++i) {
      String name = "blockcutout" + (i+1);
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("radpack:" + name, "inventory");
      ModelLoader.setCustomModelResourceLocation(RadPack.proxy.itemBlockCutouts[i], DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    for (int i = 0; i < blockTranslucentCount; ++i) {
      String name = "blocktranslucent" + (i+1);
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("radpack:" + name, "inventory");
      ModelLoader.setCustomModelResourceLocation(RadPack.proxy.itemBlockTranslucents[i], DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

  }

  /**
   * Do your mod setup. Build whatever data structures you care about. Register recipes,
   * send FMLInterModComms messages to other mods.
   */
  public void init()
  {
    super.init();
  }

  /**
   * Handle interaction with other mods, complete your setup based on this.
   */
  public void postInit()
  {
    super.postInit();
  }

}
