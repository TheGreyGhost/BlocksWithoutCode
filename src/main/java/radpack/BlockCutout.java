package radpack;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * BlockVariants uses a model which
 * - doesn't occupy the entire 1x1x1m space,
 * - is made up of two pieces,
 * - uses a CUTOUT texture (with seethrough holes)
 * - has variants (can face in four directions, and can be four different colours)
 * We can walk over it without colliding.
 * For background information on blocks see here http://greyminecraftcoder.blogspot.com.au/2014/12/blocks-18.html
 */
public class BlockCutout extends Block
{
  public BlockCutout()
  {
    super(Material.rock);
    this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative by default
  }

  // the block will render in the CUTOUT layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
  @SideOnly(Side.CLIENT)
  public EnumWorldBlockLayer getBlockLayer()
  {
    return EnumWorldBlockLayer.CUTOUT;
  }

  // used by the renderer to control lighting and visibility of other blocks.
  // set to false because this block doesn't fill the entire 1x1x1 space
  @Override
  public boolean isOpaqueCube() {
    return false;
  }

  // used by the renderer to control lighting and visibility of other blocks, also by
  // (eg) wall or fence to control whether the fence joins itself to this block
  // set to false because this block doesn't fill the entire 1x1x1 space
  @Override
  public boolean isFullCube() {
    return false;
  }

  // render using a BakedModel (mbe01_block_simple.json --> mbe01_block_simple_model.json)
  // not strictly required because the default (super method) is 3.
  @Override
  public int getRenderType() {
    return 3;
  }

  // Our block has two properties:
  // 1) PROPERTYFACING for which way the sign points (east, west, north, south).  EnumFacing is as standard used by vanilla for a number of blocks.
  public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

//  // this function returns the correct item type corresponding to the colour of our block;
//  // i.e. when a sign is broken, it will drop the correct item.  Ignores Facing, because we get the same item
//  //   no matter which way the block is facing
//  @Override
//  public int damageDropped(IBlockState state)
//  {
//    EnumColour enumColour = (EnumColour)state.getValue(PROPERTYCOLOUR);
//    return enumColour.getMetadata();
//  }

//  // create a list of the subBlocks available for this block, i.e. one for each colour
//  // ignores facings, because the facing is calculated when we place the item.
//  //  - used to populate items for the creative inventory
//  // - the "metadata" value of the block is set to the colours metadata
//  @Override
//  @SideOnly(Side.CLIENT)
//  public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
//  {
//    EnumColour[] allColours = EnumColour.values();
//    for (EnumColour colour : allColours) {
//      list.add(new ItemStack(itemIn, 1, colour.getMetadata()));
//    }
//  }

  // getStateFromMeta, getMetaFromState are used to interconvert between the block's property values and
  //   the stored metadata (which must be an integer in the range 0 - 15 inclusive)
  // The property is encoded as:
  // - lower two bits = facing direction (i.e. 0, 1, 2, 3)
  @Override
  public IBlockState getStateFromMeta(int meta)
  {
    EnumFacing facing = EnumFacing.getHorizontal(meta);
    return this.getDefaultState().withProperty(PROPERTYFACING, facing);
  }

  @Override
  public int getMetaFromState(IBlockState state)
  {
    EnumFacing facing = (EnumFacing)state.getValue(PROPERTYFACING);

    int facingbits = facing.getHorizontalIndex();
    return facingbits;
  }

  // this method isn't required if your properties only depend on the stored metadata.
  // it is required if:
  // 1) you are making a multiblock which stores information in other blocks eg BlockBed, BlockDoor
  // 2) your block's state depends on other neighbours (eg BlockFence)
  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
  {
    return state;
  }

  // necessary to define which properties your blocks use
  // will also affect the variants listed in the blockstates model file
  @Override
  protected BlockState createBlockState()
  {
    return new BlockState(this, PROPERTYFACING);
  }

  // when the block is placed, set the appropriate facing direction based on which way the player is looking
  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
  {
    // find the quadrant the player is facing
    EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw).getOpposite();

    return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
  }


}
