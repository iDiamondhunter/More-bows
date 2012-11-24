package iDiamondhunter.common;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBow;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemFlameBow extends ItemBow
{
	private Random rand = new Random();

	
    public ItemFlameBow(int par1, int i)
    {
        super(par1);
        maxStackSize = 1;
        setMaxDamage(576);
        bFull3D = true;
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
    public String getTextureFile()
    {
    	return "/MoreBows/Bows.png";
    }
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.uncommon;
    }
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            ItemStack itemstack1 = entityplayer.inventory.getCurrentItem();
    }
    public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
            if(player.isUsingItem() && usingItem.itemID == iDiamondhunter.FlameBow.shiftedIndex)
            {
                if (usingItem != null && usingItem.getItem().shiftedIndex == iDiamondhunter.FlameBow.shiftedIndex)
                {
                    int k = usingItem.getMaxItemUseDuration() - useRemaining;
                    if (k >= 14) return iDiamondhunter.FlameBow_4;
                    if (k > 9) return iDiamondhunter.FlameBow_3;
                    if (k > 0) return iDiamondhunter.FlameBow_2;
                }
            }
            else 
            {
            iconIndex = iDiamondhunter.FlameBow_1;
            }
            return getIconIndex(stack);
    }


    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            int i = getMaxItemUseDuration(par1ItemStack) - par4;
            float f = (float)i / 15;
            f = (f * f + f * 2.0F) / 3F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrows var8 = new EntityArrows(par2World, par3EntityPlayer, f * 2.0F);


  

            int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (j > 0)
            {
            	var8.setDamage(var8.getDamage() + (double)j * 0.5D + 0.5D);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (k > 0)
            {
            	var8.setKnockbackStrength(k);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
            	var8.setFire(100);
            	var8.setDamage(var8.getDamage() * 1.25D);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (!flag)
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
            }
            else
            {
            	var8.canBePickedUp = 0;
            }

            if (!par2World.isRemote)
            {
            	par2World.spawnEntityInWorld(var8);
            	var8.setDamage(var8.getDamage() * 2.0D);
            	var8.setFire(50);

            	
                iconIndex = iDiamondhunter.FlameBow_1;
            }
        }
        
        ItemStack cInv = par3EntityPlayer.inventory.getCurrentItem();
        
        if(cInv.itemID != iDiamondhunter.FlameBow.shiftedIndex)
        {
        	this.iconIndex = iDiamondhunter.FlameBow_1;
        }
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }


    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }


    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }


    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }


    public int getItemEnchantability()
    {
        return 1;
    }
}