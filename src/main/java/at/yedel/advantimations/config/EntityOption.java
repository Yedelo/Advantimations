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

import java.util.ArrayList;



public class EntityOption {
    protected boolean enabled;
    protected boolean enabledInFirstPerson;
    protected boolean enabledOnSelf;
    protected boolean enabledOnOtherPlayers;
    protected boolean enabledOnOtherEntities;

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

    public static OptionGroup createGroup(String groupName, String groupDescription, EntityOption defaultValue, EntityOption configValue) {
        return OptionGroup.createBuilder()
            .name(Text.literal(groupName))
            .description(OptionDescription.of(Text.literal(groupDescription)))
            .options(createOptions(defaultValue, configValue))
            .build();
    }

    protected static ArrayList<Option<Boolean>> createOptions(EntityOption defaultValue, EntityOption configValue) {
        ArrayList<Option<Boolean>> options = new ArrayList<>();
        options.add(
            Option.<Boolean>createBuilder()
                .name(Text.literal("Enabled"))
                .binding(
                    defaultValue.isEnabled(),
                    configValue::isEnabled,
                    configValue::setEnabled
                )
                .controller(BooleanControllerBuilder::create)
                .build()
        );
        options.add(
            Option.<Boolean>createBuilder()
                .name(Text.literal("Enabled in First Person"))
                .description(OptionDescription.of(Text.literal("Enable this option for yourself in first person.")))
                .binding(
                    defaultValue.enabledInFirstPerson,
                    configValue::isEnabledInFirstPerson,
                    configValue::setEnabledInFirstPerson
                )
                .controller(TickBoxControllerBuilder::create)
                .build()
        );
        options.add(
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
        );
        options.add(
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
        );
        options.add(
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
        );
        return options;
    }

    public EntityOption enabled() {
        this.enabled = true;
        return this;
    }

    public EntityOption enabledInFirstPerson() {
        this.enabledInFirstPerson = true;
        return this;
    }

    public EntityOption enabledOnSelf() {
        this.enabledOnSelf = true;
        return this;
    }

    public EntityOption enabledOnOtherPlayers() {
        this.enabledOnOtherPlayers = true;
        return this;
    }

    public EntityOption enabledOnOtherEntities() {
        this.enabledOnOtherEntities = true;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabledInFirstPerson() {
        return enabledInFirstPerson;
    }

    public void setEnabledInFirstPerson(boolean enabledInFirstPerson) {
        this.enabledInFirstPerson = enabledInFirstPerson;
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
