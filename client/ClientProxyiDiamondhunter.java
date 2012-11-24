package iDiamondhunter.client;

import iDiamondhunter.common.CommonProxyiDiamondhunter;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxyiDiamondhunter extends CommonProxyiDiamondhunter
{
	@Override
	public void registerRenderThings()
	{
		
		MinecraftForgeClient.preloadTexture("/MoreBows/Bows.png");
		
	}
}