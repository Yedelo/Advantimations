package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;



public class EntityApplicableOption {
    private boolean enabled;
    private boolean enabledOnSelf;
    private boolean enabledOnOtherPlayers;
    private boolean enabledOnOtherEntities;

    public EntityApplicableOption enabled() {
        this.enabled = true;
        return this;
    }

    public EntityApplicableOption enabledOnSelf() {
        this.enabledOnSelf = true;
        return this;
    }

    public EntityApplicableOption enabledOnOtherPlayers() {
        this.enabledOnOtherPlayers = true;
        return this;
    }

    public EntityApplicableOption enabledOnOtherEntities() {
        this.enabledOnOtherEntities = true;
        return this;
    }

    public <T> T getResult(Entity entity, T originalValue, T newValue) {
        if (enabled) {
            if (enabledOnSelf && entity instanceof ClientPlayerEntity) {
                return newValue;
            }
            if (enabledOnOtherPlayers && entity instanceof PlayerEntity player && !player.isMainPlayer()) {
                return newValue;
            }
            if (enabledOnOtherEntities && !(entity instanceof PlayerEntity)) {
                return newValue;
            }
        }
        return originalValue;
    }

    public static OptionGroup createGroup(String groupName, String groupDescription, EntityApplicableOption defaultValue, EntityApplicableOption configValue) {
        return OptionGroup.createBuilder()
            .name(Text.literal(groupName))
            .description(OptionDescription.of(Text.literal(groupDescription)))
            .option(
                Option.<Boolean>createBuilder()
                    .name(Text.literal("Enabled"))
                    .binding(
                        defaultValue.isEnabled(),
                        configValue::isEnabled,
                        configValue::setEnabled
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
            )
            .option(
                Option.<Boolean>createBuilder()
                    .name(Text.literal("Enabled on Self"))
                    .description(OptionDescription.of(Text.literal("Enable this option for yourself.")))
                    .binding(
                        defaultValue.isEnabledOnSelf(),
                        configValue::isEnabledOnSelf,
                        configValue::setEnabledOnSelf
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build()
            )
            .option(
                Option.<Boolean>createBuilder()
                    .name(Text.literal("Enabled on Other Players"))
                    .description(OptionDescription.of(Text.literal("Enable this option for other players.")))
                    .binding(
                        defaultValue.isEnabledOnOtherPlayers(),
                        configValue::isEnabledOnOtherPlayers,
                        configValue::setEnabledOnOtherPlayers
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build()
            )
            .option(
                Option.<Boolean>createBuilder()
                    .name(Text.literal("Enabled on Other Entities"))
                    .description(OptionDescription.of(Text.literal("Enable this option for other non-player entities, such as zombies.")))
                    .binding(
                        defaultValue.isEnabledOnOtherEntities(),
                        configValue::isEnabledOnOtherEntities,
                        configValue::setEnabledOnOtherEntities
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build()
            )
            .build();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabledOnSelf() {
        return enabledOnSelf;
    }

    public void setEnabledOnSelf(boolean enabledOnSelf) {
        this.enabledOnSelf = enabledOnSelf;
    }

    public boolean isEnabledOnOtherPlayers() {
        return enabledOnOtherPlayers;
    }

    public void setEnabledOnOtherPlayers(boolean enabledOnOtherPlayers) {
        this.enabledOnOtherPlayers = enabledOnOtherPlayers;
    }

    public boolean isEnabledOnOtherEntities() {
        return enabledOnOtherEntities;
    }

    public void setEnabledOnOtherEntities(boolean enabledOnOtherEntities) {
        this.enabledOnOtherEntities = enabledOnOtherEntities;
    }
}
