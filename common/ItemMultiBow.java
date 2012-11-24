package iDiamondhunter.common;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBow;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemMultiBow extends ItemBow
{
	private Random rand = new Random();
	
    public ItemMultiBow(int par1, int i)
    {
        super(par1);
        maxStackSize = 1;
        setMaxDamage(550);
        bFull3D = true;
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }
    public String getTextureFile()
    {
    	return "/MoreBows/Bows.png";
    }
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            ItemStack itemstack1 = entityplayer.inventory.getCurrentItem();

    }
    public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
            if(player.isUsingItem() && usingItem.itemID == iDiamondhunter.MultiBow.shiftedIndex)
            {
                if (usingItem != null && usingItem.getItem().shiftedIndex == iDiamondhunter.MultiBow.shiftedIndex)
                {
                    int k = usingItem.getMaxItemUseDuration() - useRemaining;
                    if (k >= 12) return iDiamondhunter.MultiBow_4;
                    if (k > 7) return iDiamondhunter.MultiBow_3;
                    if (k > 0) return iDiamondhunter.MultiBow_2;
                }
            }
            else 
            {
            iconIndex = iDiamondhunter.MultiBow_1;
            }
            return iconIndex;
    }


    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            int i = getMaxItemUseDuration(par1ItemStack) - par4;
            float f = (float)i / 13;
            f = (f * f + f * 2.0F) / 3F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
            EntityArrow var9 = new EntityArrow(par2World, par3EntityPlayer, f * 1.65F);
            EntityArrow var10 = new EntityArrow(par2World, par3EntityPlayer, f * 1.275F);
            
            if (f == 1.0F)
            {
            	var8.setIsCritical(true);
            	var9.setIsCritical(true);
            	var10.setIsCritical(true);
            }

            int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (j > 0)
            {
            	var8.setDamage(var8.getDamage() + (double)j * 0.5D + 0.5D);
            	var9.setDamage(var8.getDamage() + (double)j * 0.5D + 0.5D);
            	var10.setDamage(var8.getDamage() + (double)j * 0.5D + 0.5D);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (k > 0)
            {
            	var8.setKnockbackStrength(k);
            	var9.setKnockbackStrength(k);
            	var10.setKnockbackStrength(k);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
            	var8.setFire(100);
            	var9.setFire(100);
            	var10.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            
            if (!flag)
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
            }
            else
            {
            	var8.canBePickedUp = 2;
            	var9.canBePickedUp = 0;
            	var10.canBePickedUp = 0;
            }

            if (!par2World.isRemote)
            {
            	par2World.spawnEntityInWorld(var8);
            	par2World.spawnEntityInWorld(var9);
            	if(par3EntityPlayer.rotationYaw > 180)
            	{
            		var9.posX = var9.posX + par3EntityPlayer.rotationYaw / 180;
            	}
            	else
            	{
            		var9.posX = var9.posX + par3EntityPlayer.rotationYaw / 180;
            	}
            	var8.setDamage(var8.getDamage() * 1.5D);
            	var9.setDamage(var9.getDamage() * 1.3D);
            	var10.setDamage(var9.getDamage() * 1.15D);
                var9.canBePickedUp = 0;
                iconIndex = iDiamondhunter.MultiBow_1;
                if(rand.nextInt(4) == 0)
                {
                	par2World.spawnEntityInWorld(var10);
                	if(par3EntityPlayer.rotationYaw > 180)
                	{
                		var10.posX = var10.posX - par3EntityPlayer.rotationYaw / 180;
                	}
                	else
                	{
                		var10.posX = var10.posX - par3EntityPlayer.rotationYaw / 180;
                	}
                }
            }
        }
        
        ItemStack cInv = par3EntityPlayer.inventory.getCurrentItem();
        
        if(cInv.itemID == iDiamondhunter.MultiBow.shiftedIndex)
        {
        	return;
        }
        else
        {
        	iconIndex = iDiamondhunter.MultiBow_1;
        	return;
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