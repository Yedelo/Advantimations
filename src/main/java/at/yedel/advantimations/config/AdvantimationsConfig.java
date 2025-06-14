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

    public boolean cancelFirstPersonSwings = true;

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public EntityApplicableOption cancelSwings = new EntityApplicableOption()
        .enabled()
        .enabledOnSelf();

    public static class EntityApplicableOption {
        public boolean enabled;
        public boolean enabledOnSelf;
        public boolean enabledOnOtherPlayers;
        public boolean enabledOnOtherEntities;

        public EntityApplicableOption enabled() {
            enabled = true;
            return this;
        }

        public EntityApplicableOption enabledOnSelf() {
            enabledOnSelf = true;
            return this;
        }

        public EntityApplicableOption enabledOnOtherPlayers() {
            enabledOnOtherPlayers = true;
            return this;
        }

        public EntityApplicableOption enabledOnOtherEntities() {
            enabledOnOtherEntities = true;
            return this;
        }
    }
}
