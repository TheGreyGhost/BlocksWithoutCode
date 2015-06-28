package radpack;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * CommonProxy is used to set up the mod and start it running.  It contains all the code that should run on both the
 *   Standalone client and the dedicated server.
 *   For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public abstract class CommonProxy
{
  public BlockCutout [] blockCutouts;
  public BlockSolid [] blockSolids;
  public BlockTranslucent [] blockTranslucents;
  public static CreativeTabs customTab;               // will hold our first custom creative tab
  public static Item itemTabIcon;             // create a dummy item for our creative tab

  public static int blockSolidCount = 0;
  public static int blockCutoutCount = 0;
  public static int blockTranslucentCount = 0;

  /**
   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
   */
  public void preInit()
  {
    String numberOfBlockSolids = LanguageRegistry.instance().getStringLocalization("config.blocksolids");
    System.out.println("Number of solid blocks:" + numberOfBlockSolids);
    String numberOfBlockCutouts = LanguageRegistry.instance().getStringLocalization("config.blockcutouts");
    System.out.println("Number of cutout blocks:" + numberOfBlockCutouts);
    String numberOfBlockTranslucent = LanguageRegistry.instance().getStringLocalization("config.blocktranslucents");
    System.out.println("Number of translucent blocks:" + numberOfBlockTranslucent);

    try {
      blockSolidCount = Integer.valueOf(numberOfBlockSolids);
    } catch (NumberFormatException e) {
    }
    try {
      blockCutoutCount = Integer.valueOf(numberOfBlockCutouts);
    } catch (NumberFormatException e) {
    }
    try {
      blockTranslucentCount = Integer.valueOf(numberOfBlockTranslucent);
    } catch (NumberFormatException e) {
    }
    blockSolids = new BlockSolid[blockSolidCount];
    blockCutouts = new BlockCutout[blockCutoutCount];
    blockTranslucents = new BlockTranslucent[blockTranslucentCount];

    // each instance of your item should have a name that is unique within your mod.  use lower case.
    itemTabIcon = new Item().setUnlocalizedName("radpacktabicon");
    GameRegistry.registerItem(itemTabIcon, "radpacktabicon");

    customTab = new CreativeTabs("radpacktab") {
      @Override
      @SideOnly(Side.CLIENT)
      public Item getTabIconItem() {
        return itemTabIcon;
      }
    };

    for (int i = 0; i < blockSolidCount; ++i) {
      String blockName = "blocksolid" + (i + 1);
      blockSolids[i] = (BlockSolid)(new BlockSolid().setUnlocalizedName(blockName).setCreativeTab(customTab));
      GameRegistry.registerBlock(blockSolids[i], blockName);
    }

    for (int i = 0; i < blockCutoutCount; ++i) {
      String blockName = "blockcutout" + (i + 1);
      blockCutouts[i] = (BlockCutout)(new BlockCutout().setUnlocalizedName(blockName).setCreativeTab(customTab));
      GameRegistry.registerBlock(blockCutouts[i], blockName);
    }

    for (int i = 0; i < blockTranslucentCount; ++i) {
      String blockName = "blocktranslucent" + (i + 1);
      blockTranslucents[i] = (BlockTranslucent)(new BlockTranslucent().setUnlocalizedName(blockName).setCreativeTab(customTab));
      GameRegistry.registerBlock(blockTranslucents[i], blockName);
    }

  }

  /**
   * Do your mod setup. Build whatever data structures you care about. Register recipes,
   * send FMLInterModComms messages to other mods.
   */
  public void init()
  {
  }

  /**
   * Handle interaction with other mods, complete your setup based on this.
   */
  public void postInit()
  {
  }
}
