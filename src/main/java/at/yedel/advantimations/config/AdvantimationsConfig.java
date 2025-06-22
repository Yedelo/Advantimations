package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class AdvantimationsConfig {
    public static final ConfigClassHandler<AdvantimationsConfig> HANDLER = ConfigClassHandler.createBuilder(AdvantimationsConfig.class)
        .id(Identifier.of("advantimations", "advantimations-config"))
        .serializer(
            config -> GsonConfigSerializerBuilder.create(config)
                .setPath(FabricLoader.getInstance().getConfigDir().resolve("advantimations.json"))
                .setJson5(false)
                .build()
        )
        .build();

    public static AdvantimationsConfig getInstance() {
        return HANDLER.instance();
    }

    public static void init() {
        HANDLER.load();
    }

    @SerialEntry
    public EntityOption cancelSwings = new EntityOption()
        .enabled()
        .enabledInFirstPerson()
        .enabledOnSelf();

    @SerialEntry
    public SimpleFirstPersonOption cancelEatingAnimation = SimpleFirstPersonOption.falseOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelDrinkingAnimation = SimpleFirstPersonOption.falseOption();

    @SerialEntry
    public EntityOption cancelBlockingAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelShieldAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBowAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelCrossbowAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelChargedCrossbowAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelSpyglassAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelSpearAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBrushingAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBundleAnimation = new EntityOption();

    @SerialEntry
    public EntityOption cancelBowArrowModel = new EntityOption();

    @SerialEntry
    public EntityOption cancelCrossbowArrowModel = new EntityOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelAttackCooldownResets = SimpleFirstPersonOption.trueOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelBlockInteractResets = SimpleFirstPersonOption.trueOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelItemInteractResets = SimpleFirstPersonOption.trueOption();

    @SerialEntry
    public SimpleFirstPersonOption cancelSlotSwappingResets = SimpleFirstPersonOption.falseOption();

    @SerialEntry
    public EntityOption cancelLimbMovements = new EntityOption();

    @SerialEntry
    public EntityOption weirderLimbMovements = new EntityOption();

    @SerialEntry
    public EntityOption cancelSneaking = new EntityOption();

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> builder
            .title(Text.literal("Advantimations Config"))
            .category(ConfigCategory.createBuilder()
                .name(Text.literal("Item Model"))
                .tooltip(Text.literal("Options for cancelling item models and animations."))
                .group(EntityOption.createGroup(
                    "Cancel Swings", "Cancel hand and item swing animations.", defaults.cancelSwings, config.cancelSwings,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .option(Option.<Boolean>createBuilder()
                    .name(Text.literal("Cancel Eating Animation"))
                    .description(OptionDescription.of(Text.literal("Cancel the first-person eating animation.")))
                    .binding(
                        defaults.cancelEatingAnimation.isEnabled(),
                        config.cancelEatingAnimation::isEnabled,
                        config.cancelEatingAnimation::setEnabled
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
                )
                .option(Option.<Boolean>createBuilder()
                    .name(Text.literal("Cancel Drinking Animation"))
                    .description(OptionDescription.of(Text.literal("Cancel the first-person drinking animation.")))
                    .binding(
                        defaults.cancelDrinkingAnimation.isEnabled(),
                        config.cancelDrinkingAnimation::isEnabled,
                        config.cancelDrinkingAnimation::setEnabled
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build()
                )
                .group(EntityOption.createGroup(
                    "Cancel Blocking Animation", "Cancel the item blocking animation.", defaults.cancelBlockingAnimation, config.cancelBlockingAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Shield Animation", "Cancel the shield blocking animation.", defaults.cancelShieldAnimation, config.cancelShieldAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Bow Animation", "Cancel the bow drawing animation.", defaults.cancelBowAnimation, config.cancelBowAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Crossbow Animation", "Cancel the crossbow drawing animation.", defaults.cancelCrossbowAnimation, config.cancelCrossbowAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Charged Crossbow Animation", "Cancel the charged crossbow model.", defaults.cancelChargedCrossbowAnimation, config.cancelChargedCrossbowAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Spyglass Animation", "Cancel the spyglass animation.", defaults.cancelSpyglassAnimation, config.cancelSpyglassAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Spear Animation", "Cancel the spear drawing animation.", defaults.cancelSpearAnimation, config.cancelSpearAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Brushing Animation", "Cancel the brushing animation.", defaults.cancelBrushingAnimation, config.cancelBrushingAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Bundle Animation", "Cancel the bundle use swing animation.", defaults.cancelBundleAnimation, config.cancelBundleAnimation,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Bow Arrow Model", "Cancel the arrow in a bow being rendered.", defaults.cancelBowArrowModel, config.cancelBowArrowModel,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Crossbow Arrow Model", "Cancel the arrow in a crossbow being rendered.", defaults.cancelCrossbowArrowModel, config.cancelCrossbowArrowModel,
                    EntityOption.Configuration.PERSPECTIVE_INDEPENDENT_OPTION_CONFIGURATOR
                ))
                .group(OptionGroup.createBuilder()
                    .name(Text.literal("Item Resets"))
                    .description(OptionDescription.of(Text.literal("Options for cancelling the item reset animation.")))
                    .option(Option.<Boolean>createBuilder()
                        .name(Text.literal("Cancel Attack Cooldown Resets"))
                        .description(OptionDescription.of(Text.literal("Cancel the item reset animation when attacking or swapping items with cooldowns.")))
                        .binding(
                            defaults.cancelAttackCooldownResets.isEnabled(),
                            config.cancelAttackCooldownResets::isEnabled,
                            config.cancelAttackCooldownResets::setEnabled
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )
                    .option(Option.<Boolean>createBuilder()
                        .name(Text.literal("Cancel Block Interact Resets"))
                        .description(OptionDescription.of(Text.literal("Cancel the item reset animation when interacting with a block.")))
                        .binding(
                            defaults.cancelBlockInteractResets.isEnabled(),
                            config.cancelBlockInteractResets::isEnabled,
                            config.cancelBlockInteractResets::setEnabled
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )
                    .option(Option.<Boolean>createBuilder()
                        .name(Text.literal("Cancel Item Interact Resets"))
                        .description(OptionDescription.of(Text.literal("Cancel the item reset animation when interacting with an item.")))
                        .binding(
                            defaults.cancelItemInteractResets.isEnabled(),
                            config.cancelItemInteractResets::isEnabled,
                            config.cancelItemInteractResets::setEnabled
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )
                    .option(Option.<Boolean>createBuilder()
                        .name(Text.literal("Cancel Slot Swapping Resets"))
                        .description(OptionDescription.of(Text.literal("Cancel the item reset animation when swapping items.")))
                        .binding(
                            defaults.cancelSlotSwappingResets.isEnabled(),
                            config.cancelSlotSwappingResets::isEnabled,
                            config.cancelSlotSwappingResets::setEnabled
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )
                    .build()
                )
                .build()
            )
            .category(ConfigCategory.createBuilder()
                .name(Text.literal("Entity Model"))
                .tooltip(Text.literal("Options for cancelling entity model animations."))
                .group(EntityOption.createGroup(
                    "Cancel Limb Movements", "Cancel entity limb movements.", defaults.cancelLimbMovements, config.cancelLimbMovements,
                    EntityOption.Configuration.THIRD_PERSON_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Weirder Limb Movements", "Somewhat cancel entity limb movements.", defaults.weirderLimbMovements, config.weirderLimbMovements,
                    EntityOption.Configuration.THIRD_PERSON_OPTION_CONFIGURATOR
                ))
                .group(EntityOption.createGroup(
                    "Cancel Sneaking", "Cancel the sneaking animation.", defaults.cancelSneaking, config.cancelSneaking,
                    (configuration) -> configuration.canBeEnabledInFirstPerson().canBeEnabledOnSelf().canBeEnabledOnOtherPlayers()
                ))
                .build()
            )
        ).generateScreen(parent);
    }
}