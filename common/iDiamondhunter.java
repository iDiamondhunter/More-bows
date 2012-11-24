package iDiamondhunter.common;

import iDiamondhunter.client.ClientProxyiDiamondhunter;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;




@Mod(modid = "More Bows mod by iDiamondhunter", name = "More Bows mod", version = "1.4.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class iDiamondhunter
{

    @SidedProxy(clientSide = "iDiamondhunter.client.ClientProxyiDiamondhunter", serverSide= "iDiamondhunter.common.CommonProxyiDiamondhunter")
    public static CommonProxyiDiamondhunter proxy;
    public static ClientProxyiDiamondhunter proxy1;

	
    public static Item DiamondBow;
	public static int DiamondBow_1;
	public static int DiamondBow_2;
	public static int DiamondBow_3;
	public static int DiamondBow_4;
	public static Item GoldBow;
	public static int GoldBow_1;
	public static int GoldBow_2;
	public static int GoldBow_3;
	public static int GoldBow_4;
	public static Item EnderBow;
	public static int EnderBow_1;
	public static int EnderBow_2;
	public static int EnderBow_3;
	public static int EnderBow_4;
	public static Item StoneBow;
	public static int StoneBow_1;
	public static int StoneBow_2;
	public static int StoneBow_3;
	public static int StoneBow_4;
	public static Item IronBow;
	public static int IronBow_1;
	public static int IronBow_2;
	public static int IronBow_3;
	public static int IronBow_4;
	public static Item MultiBow;
	public static int MultiBow_1;
	public static int MultiBow_2;
	public static int MultiBow_3;
	public static int MultiBow_4;
	public static Item FlameBow;
	public static int FlameBow_1;
	public static int FlameBow_2;
	public static int FlameBow_3;
	public static int FlameBow_4;
	/*public static Item IceBow;
	public static int IceBow_1;
	public static int IceBow_2;
	public static int IceBow_3;
	public static int IceBow_4;*/
	
	
	public void load(FMLPreInitializationEvent event)
	{
        MinecraftForgeClient.preloadTexture("/MoreBows/Bows.png");


	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{

		

		
		
		
		DiamondBow = new ItemDiamondBow(3906, 0).setItemName("DiamondBow").setIconIndex(0);
		GoldBow = new ItemGoldBow(3907, 12).setItemName("GoldBow").setIconIndex(12);
		EnderBow = new ItemBowEnder(3908, 4).setItemName("EnderBow").setIconIndex(4);
		StoneBow = new ItemStoneBow(3909, 24).setItemName("StoneBow").setIconIndex(24);
		IronBow = new ItemIronBow(3910, 16).setItemName("IronBow").setIconIndex(16);
		MultiBow = new ItemMultiBow(3911, 20).setItemName("MultiBow").setIconIndex(20);
		FlameBow = new ItemFlameBow(3912, 8).setItemName("FlameBow").setIconIndex(8);
		//IceBow = new ItemIceBow(3913, 28).setItemName("IceBow").setIconIndex(28);

		DiamondBow_1 = 0;
		GoldBow_1 = 12;
		EnderBow_1 = 4;
		StoneBow_1 = 24;
		IronBow_1 = 16;
		FlameBow_1 = 8;
		MultiBow_1 = 20;
		//IceBow_1 = 28;
		GoldBow_2 = 13;
		GoldBow_3 = 14;
		GoldBow_4 = 15;
		EnderBow_2 = 5;
		EnderBow_3 = 6;
		EnderBow_4 = 7;
		StoneBow_2 = 25;
		StoneBow_3 = 26;
		StoneBow_4 = 27;
		IronBow_2 = 17;
		IronBow_3 = 18;
		IronBow_4 = 19;
		MultiBow_2 = 21;
		MultiBow_3 = 22;
		MultiBow_4 = 23;
		FlameBow_2 = 9;
		FlameBow_3 = 10;
		FlameBow_4 = 11;
		/*IceBow_1 = 29;
		IceBow_1 = 30;
		IceBow_1 = 31;*/

		LanguageRegistry.addName(DiamondBow, "Crystal Bow");
		LanguageRegistry.addName(GoldBow, "Golden Bow");
		LanguageRegistry.addName(MultiBow, "Legia Bow");
		LanguageRegistry.addName(FlameBow, "Blazing Bow");
		LanguageRegistry.addName(IronBow, "Iron Bow");
		LanguageRegistry.addName(StoneBow, "Reinforced Bow");
		LanguageRegistry.addName(EnderBow, "Ender Bow");
		//LanguageRegistry.addName(IceBow, "Frostbite Bow");
		
		
		GameRegistry.addRecipe(new ItemStack(StoneBow, 1), new Object[]
                {
                    " $*", "#(*", " $*", '#', Item.stick, '*', Item.silk, '$', Block.stone, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(StoneBow, 1), new Object[]
                {
                    "*$ ", "*(#", "*$ ", '#', Item.stick, '*', Item.silk, '$', Block.stone, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(IronBow, 1), new Object[]
                {
                    " $*", "$(*", " $*", '*', Item.silk, '$', Item.ingotIron, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(IronBow, 1), new Object[]
                {
                    "*$ ", "*($", "*$ ", '*', Item.silk, '$', Item.ingotIron, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(GoldBow, 1), new Object[]
                {
                    " $*", "$(*", " $*", '*', Item.silk, '$', Item.ingotGold, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(GoldBow, 1), new Object[]
                {
                    "*$ ", "*($", "*$ ", '*', Item.silk, '$', Item.ingotGold, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(DiamondBow, 1), new Object[]
                {
                    " $*", "I(*", " $*", '*', Item.silk, '$', Item.diamond, 'I', Item.ingotIron, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(DiamondBow, 1), new Object[]
                {
                    "*$ ", "*(I", "*$ ", '*', Item.silk, '$', Item.diamond, 'I', Item.ingotIron, '(', Item.bow
                });
        GameRegistry.addRecipe(new ItemStack(MultiBow, 1), new Object[]
                {
                    " $*", "#(*", " $*", '*', Item.silk, '#', Item.ingotIron, '$', iDiamondhunter.IronBow
                });
        GameRegistry.addRecipe(new ItemStack(MultiBow, 1), new Object[]
                {
                    "*$ ", "* #", "*$ ", '*', Item.silk, '#', Item.ingotIron, '$', iDiamondhunter.IronBow
                });
        GameRegistry.addRecipe(new ItemStack(FlameBow, 1), new Object[]
                {"NB ", "GI ", "NB ",   'G', Item.ingotGold, 'B', Item.blazeRod,  'I', iDiamondhunter.IronBow, 'N',Block.netherrack
        	    });
        GameRegistry.addRecipe(new ItemStack(FlameBow, 1), new Object[]
                {" NB", " GI", " NB",   'G', Item.ingotGold, 'B', Item.blazeRod, 'I', iDiamondhunter.IronBow, 'N', Block.netherrack
        	    });
        GameRegistry.addRecipe(new ItemStack(EnderBow, 1), new Object[]
                {
                    "GP ", "EI ", "GP ",   'G', Item.ingotGold, 'P', Item.enderPearl,  'I', iDiamondhunter.IronBow, 'E',Item.eyeOfEnder
                });
        GameRegistry.addRecipe(new ItemStack(EnderBow, 1), new Object[]
                {
                    " GP", " EI", " GP",  'G', Item.ingotGold, 'P', Item.enderPearl,  'I', iDiamondhunter.IronBow, 'E', Item.eyeOfEnder
                });
		
	}
}
