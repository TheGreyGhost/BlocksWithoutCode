package radpack;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
  public ItemBlock [] itemBlockCutouts;
  public ItemBlock [] itemBlockSolids;
  public ItemBlock [] itemBlockTranslucents;

  public static CreativeTabs [] customTabs;               // will hold our creative tabs
  public static Item [] itemTabIcons;             // create a dummy item for each creative tab

  public static int blockSolidCount = 0;
  public static int blockCutoutCount = 0;
  public static int blockTranslucentCount = 0;
  public static int creativeTabCount = 0;

  /**
   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
   */
  public void preInit()
  {
    String numberOfBlockSolids = I18n.format("config.blocksolids");
    System.out.println("Number of solid blocks:" + numberOfBlockSolids);
    String numberOfBlockCutouts = I18n.format("config.blockcutouts");
    System.out.println("Number of cutout blocks:" + numberOfBlockCutouts);
    String numberOfBlockTranslucent = I18n.format("config.blocktranslucents");
    System.out.println("Number of translucent blocks:" + numberOfBlockTranslucent);
    String numberOfCreativeTabs = I18n.format("config.creativetabs");
    System.out.println("Number of creative tabs:" + numberOfCreativeTabs);

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
    try {
      creativeTabCount = Integer.valueOf(numberOfCreativeTabs);
    } catch (NumberFormatException e) {
    }
    blockSolids = new BlockSolid[blockSolidCount];
    blockCutouts = new BlockCutout[blockCutoutCount];
    blockTranslucents = new BlockTranslucent[blockTranslucentCount];
    itemBlockSolids = new ItemBlock[blockSolidCount];
    itemBlockCutouts = new ItemBlock[blockCutoutCount];
    itemBlockTranslucents = new ItemBlock[blockTranslucentCount];
    customTabs = new CreativeTabs[creativeTabCount];
    itemTabIcons = new Item[creativeTabCount];

    for (int i = 0; i < creativeTabCount; ++i) {
      String tabName = "radpacktab" + (i + 1);
      String tabIconName = "radpacktabicon" + (i + 1);
      final Item itemTabIcon = new Item().setUnlocalizedName(tabIconName).setRegistryName(tabIconName);
      GameRegistry.register(itemTabIcon);

      CreativeTabs creativeTab = new CreativeTabs(tabName) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
          return itemTabIcon;
        }
      };
      customTabs[i] = creativeTab;
      itemTabIcons[i] = itemTabIcon;
    }

    for (int i = 0; i < blockSolidCount; ++i) {
      String blockName = "blocksolid" + (i + 1);
      CreativeTabs creativeTab = getTab(blockName);
      blockSolids[i] = (BlockSolid)(new BlockSolid().setUnlocalizedName(blockName).setCreativeTab(creativeTab).setRegistryName(blockName));
      GameRegistry.register(blockSolids[i]);

      itemBlockSolids[i] = new ItemBlock(blockSolids[i]);
      itemBlockSolids[i].setRegistryName(blockSolids[i].getRegistryName());
      GameRegistry.register(itemBlockSolids[i]);
    }

    for (int i = 0; i < blockCutoutCount; ++i) {
      String blockName = "blockcutout" + (i + 1);
      CreativeTabs creativeTab = getTab(blockName);
      blockCutouts[i] = (BlockCutout)(new BlockCutout().setUnlocalizedName(blockName).setCreativeTab(creativeTab).setRegistryName(blockName));
      GameRegistry.register(blockCutouts[i]);

      itemBlockCutouts[i] = new ItemBlock(blockCutouts[i]);
      itemBlockCutouts[i].setRegistryName(blockCutouts[i].getRegistryName());
      GameRegistry.register(itemBlockCutouts[i]);
    }

    for (int i = 0; i < blockTranslucentCount; ++i) {
      String blockName = "blocktranslucent" + (i + 1);
      CreativeTabs creativeTab = getTab(blockName);
      blockTranslucents[i] = (BlockTranslucent)(new BlockTranslucent().setUnlocalizedName(blockName).setCreativeTab(creativeTab).setRegistryName(blockName));
      GameRegistry.register(blockTranslucents[i]);

      itemBlockTranslucents[i] = new ItemBlock(blockTranslucents[i]);
      itemBlockTranslucents[i].setRegistryName(blockTranslucents[i].getRegistryName());
      GameRegistry.register(itemBlockTranslucents[i]);
    }
  }

  // get the creative block for a tab.  If error or none, use Blocks tab
  private CreativeTabs getTab(String blockName)
  {
    int tabNumber = getTabNumber(blockName);
    if (tabNumber <= 0 || tabNumber > creativeTabCount) {
      return CreativeTabs.BUILDING_BLOCKS;
    }
    return customTabs[tabNumber - 1];
  }

  // get the number of the tab; 0 means no number allocated
  private int getTabNumber(String blockName) {
    String configKey = "tile." + blockName + ".tab";
    String tabNumberString = I18n.format(configKey);
    int tabNumber = 0;
    try {
      tabNumber = Integer.valueOf(tabNumberString);
    } catch (NumberFormatException e) {
    }
    return tabNumber;
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
