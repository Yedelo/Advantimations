package at.yedel.advantimations;



import at.yedel.advantimations.config.AdvantimationsConfig;
import net.fabricmc.api.ClientModInitializer;



// Maud
public class Advantimations implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AdvantimationsConfig.init();
	}
}