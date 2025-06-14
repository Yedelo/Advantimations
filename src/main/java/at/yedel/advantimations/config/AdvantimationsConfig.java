package at.yedel.advantimations.config;



import at.yedel.advantimations.Advantimations;
import at.yedel.advantimations.utils.AdvantimationsConstants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;




@Config(name = AdvantimationsConstants.MOD_ID)

public class AdvantimationsConfig implements ConfigData {
    public static AdvantimationsConfig getInstance() {
        return Advantimations.getInstance().getConfig();
    }

    @ConfigEntry.Gui.Tooltip
    public boolean cancelFirstPersonSwings = true;
}
