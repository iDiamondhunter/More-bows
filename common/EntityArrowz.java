package iDiamondhunter.common;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;

public class EntityArrowz extends EntityArrow
{
    public EntityArrowz(World var1, EntityLiving var2, EntityLiving var3, float var4, float var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    public EntityArrowz(World var1, EntityLiving var2, float var3)
    {
        super(var1, var2, var3);
    }

    public EntityArrowz(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
    }

    public EntityArrowz(World var1)
    {
        super(var1);
    }


    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    public void hitGround(int var1, int var2, int var3, int var4, int var5)
    {
        if (var4 == 0)
        {
            --var2;
        }

        if (var4 == 1)
        {
            ++var2;
        }

        if (var4 == 2)
        {
            --var3;
        }

        if (var4 == 3)
        {
            ++var3;
        }

        if (var4 == 4)
        {
            --var1;
        }

        if (var4 == 5)
        {
            ++var1;
            this.worldObj.setBlockAndMetadata(var1, var2, var3, Block.fire.blockID, 0);
        }

        if (this.worldObj.canPlaceEntityOnSide(Block.fire.blockID, var1, var2, var3, true, 0, (Entity)null))
        {
            this.worldObj.setBlockAndMetadata(var1, var2, var3, Block.fire.blockID, 0);
            this.setDead();
        }
    }



    public boolean hitTarget(Entity var1)
    {
        var1.setInWeb();
        return true;
    }
}
