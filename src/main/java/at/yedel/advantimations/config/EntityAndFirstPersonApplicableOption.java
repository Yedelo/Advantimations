package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.text.Text;

import java.util.ArrayList;



public class EntityAndFirstPersonApplicableOption extends EntityApplicableOption {
    private boolean enabledInFirstPerson;

    public static OptionGroup createGroup(String groupName, String groupDescription, EntityAndFirstPersonApplicableOption defaultValue, EntityAndFirstPersonApplicableOption configValue) {
        ArrayList<Option<Boolean>> options = EntityApplicableOption.createOptions(defaultValue, configValue);
        options.add(
            1,
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
        return OptionGroup.createBuilder()
            .name(Text.literal(groupName))
            .description(OptionDescription.of(Text.literal(groupDescription)))
            .options(options)
            .build();
    }

    public EntityAndFirstPersonApplicableOption enabled() {
        this.enabled = true;
        return this;
    }

    public EntityAndFirstPersonApplicableOption enabledInFirstPerson() {
        this.enabledInFirstPerson = true;
        return this;
    }

    public EntityAndFirstPersonApplicableOption enabledOnSelf() {
        this.enabledOnSelf = true;
        return this;
    }

    public EntityAndFirstPersonApplicableOption enabledOnOtherPlayers() {
        this.enabledOnOtherPlayers = true;
        return this;
    }

    public EntityAndFirstPersonApplicableOption enabledOnOtherEntities() {
        this.enabledOnOtherEntities = true;
        return this;
    }

    public boolean isEnabledInFirstPerson() {
        return enabledInFirstPerson;
    }

    public void setEnabledInFirstPerson(boolean enabledInFirstPerson) {
        this.enabledInFirstPerson = enabledInFirstPerson;
    }
}
