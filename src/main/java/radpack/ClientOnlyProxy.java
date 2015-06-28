package radpack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
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
  }

  /**
   * Do your mod setup. Build whatever data structures you care about. Register recipes,
   * send FMLInterModComms messages to other mods.
   */
  public void init()
  {
    super.init();

    Item itemTabIcon = GameRegistry.findItem("radpack", "radpacktabicon");
    ModelResourceLocation itemTabIconRL = new ModelResourceLocation("radpack:radpacktabicon", "inventory");
    final int DEFAULT_ITEM_SUBTYPE = 0;
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemTabIcon, DEFAULT_ITEM_SUBTYPE, itemTabIconRL);

    for (int i = 0; i < blockSolidCount; ++i) {
      String name = "blocksolid" + (i+1);
      Item itemBlock = GameRegistry.findItem("radpack", name);
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("radpack:" + name, "inventory");
      Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    for (int i = 0; i < blockCutoutCount; ++i) {
      String name = "blockcutout" + (i+1);
      Item itemBlock = GameRegistry.findItem("radpack", name);
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("radpack:" + name, "inventory");
      Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    for (int i = 0; i < blockTranslucentCount; ++i) {
      String name = "blocktranslucent" + (i+1);
      Item itemBlock = GameRegistry.findItem("radpack", name);
      ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("radpack:" + name, "inventory");
      Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

  }

  /**
   * Handle interaction with other mods, complete your setup based on this.
   */
  public void postInit()
  {
    super.postInit();
  }

}
