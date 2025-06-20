package at.yedel.advantimations.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
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
                .setJson5(true)
                .build()
        )
        .build();

    public static AdvantimationsConfig getInstance() {
        return HANDLER.instance();
    }

    @SerialEntry
    public boolean cancelFirstPersonSwings = true;

    @SerialEntry
    public boolean cancelEatingAnimation = false;

    @SerialEntry
    public boolean cancelDrinkingAnimation = false;

    @SerialEntry
    public boolean cancelBlockingAnimation = false;

    @SerialEntry
    public boolean cancelShieldAnimation = false;

    @SerialEntry
    public boolean cancelBowingAnimation = false;

    @SerialEntry
    public boolean cancelArrowAnimation = false;

    @SerialEntry
    public boolean cancelSpearAnimation = false;

    @SerialEntry
    public boolean cancelBrushingAnimation = false;

    @SerialEntry
    public boolean cancelBundleAnimation = false;

    @SerialEntry
    public boolean cancelBlockInteractResets = true;

    @SerialEntry
    public boolean cancelItemInteractResets = true;

    @SerialEntry
    public boolean cancelAllItemResets = true;

    @SerialEntry
    public EntityApplicableOption cancelSwings = new EntityApplicableOption()
        .enabled()
        .enabledOnSelf();

    @SerialEntry
    public EntityApplicableOption cancelLimbMovements = new EntityApplicableOption();

    @SerialEntry
    public EntityApplicableOption weirderLimbMovements = new EntityApplicableOption();

    @SerialEntry
    public EntityApplicableOption cancelSneaking = new EntityApplicableOption();

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) ->
            builder
                .title(Text.literal("Advantimations Config"))
                .category(
                    ConfigCategory.createBuilder()
                        .name(Text.literal("First Person"))
                        .tooltip(Text.literal("Options for canceling first person animations."))
                        .option(
                            Option.<Boolean>createBuilder()
                                .name(Text.literal("Cancel First Person Swings"))
                                .description(OptionDescription.of(Text.literal("")))
                                .binding(
                                    defaults.cancelFirstPersonSwings,
                                    () -> config.cancelFirstPersonSwings,
                                    (cancelFirstPersonSwings) -> config.cancelFirstPersonSwings = cancelFirstPersonSwings
                                )
                                .controller(BooleanControllerBuilder::create)
                                .build()
                        )
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Item Use Animations"))
                                .description(OptionDescription.of(Text.literal("Options for cancelling item use animations such as eating and blocking.")))
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Eating Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelEatingAnimation,
                                            () -> config.cancelEatingAnimation,
                                            (cancelEatingAnimation) -> config.cancelEatingAnimation = cancelEatingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Drinking Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelDrinkingAnimation,
                                            () -> config.cancelDrinkingAnimation,
                                            (cancelDrinkingAnimation) -> config.cancelDrinkingAnimation = cancelDrinkingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Blocking Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBlockingAnimation,
                                            () -> config.cancelBlockingAnimation,
                                            (cancelBlockingAnimation) -> config.cancelBlockingAnimation = cancelBlockingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Shield Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelShieldAnimation,
                                            () -> config.cancelShieldAnimation,
                                            (cancelShieldAnimation) -> config.cancelShieldAnimation = cancelShieldAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Bowing Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBowingAnimation,
                                            () -> config.cancelBowingAnimation,
                                            (cancelBowingAnimation) -> config.cancelBowingAnimation = cancelBowingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Arrow Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelArrowAnimation,
                                            () -> config.cancelArrowAnimation,
                                            (cancelArrowAnimation) -> config.cancelArrowAnimation = cancelArrowAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Spear Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelSpearAnimation,
                                            () -> config.cancelSpearAnimation,
                                            (cancelSpearAnimation) -> config.cancelSpearAnimation = cancelSpearAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Brushing Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBrushingAnimation,
                                            () -> config.cancelBrushingAnimation,
                                            (cancelBrushingAnimation) -> config.cancelBrushingAnimation = cancelBrushingAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Bundle Animation"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBundleAnimation,
                                            () -> config.cancelBundleAnimation,
                                            (cancelBundleAnimation) -> config.cancelBundleAnimation = cancelBundleAnimation
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Item Resets"))
                                .description(OptionDescription.of(Text.literal("Options for cancelling item reset animations.")))
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Block Interact Resets"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelBlockInteractResets,
                                            () -> config.cancelBlockInteractResets,
                                            (cancelBlockInteractResets) -> config.cancelBlockInteractResets = cancelBlockInteractResets
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel Item Interact Resets"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelItemInteractResets,
                                            () -> config.cancelItemInteractResets,
                                            (cancelItemInteractResets) -> config.cancelItemInteractResets = cancelItemInteractResets
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Cancel All Item Resets"))
                                        .description(OptionDescription.of(Text.literal("")))
                                        .binding(
                                            defaults.cancelAllItemResets,
                                            () -> config.cancelAllItemResets,
                                            (cancelAllItemResets) -> config.cancelAllItemResets = cancelAllItemResets
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .category(
                    ConfigCategory.createBuilder()
                        .name(Text.literal("Model"))
                        .tooltip(Text.literal("Options for canceling entity model animations."))
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Cancel Swings"))
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                            defaults.cancelSwings.isEnabled(),
                                            () -> config.cancelSwings.isEnabled(),
                                            (enabled) -> config.cancelSwings.setEnabled(enabled)
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Self"))
                                        .binding(
                                            defaults.cancelSwings.isEnabledOnSelf(),
                                            () -> config.cancelSwings.isEnabledOnSelf(),
                                            (enabledOnSelf) -> config.cancelSwings.setEnabledOnSelf(enabledOnSelf)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Players"))
                                        .binding(
                                            defaults.cancelSwings.isEnabledOnOtherPlayers(),
                                            () -> config.cancelSwings.isEnabledOnOtherPlayers(),
                                            (enabledOnOtherPlayers) -> config.cancelSwings.setEnabledOnOtherPlayers(enabledOnOtherPlayers)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Entities"))
                                        .binding(
                                            defaults.cancelSwings.isEnabledOnOtherEntities(),
                                            () -> config.cancelSwings.isEnabledOnOtherEntities(),
                                            (enabledOnOtherEntities) -> config.cancelSwings.setEnabledOnOtherEntities(enabledOnOtherEntities)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Cancel Limb Movements"))
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                            defaults.cancelLimbMovements.isEnabled(),
                                            () -> config.cancelLimbMovements.isEnabled(),
                                            (enabled) -> config.cancelLimbMovements.setEnabled(enabled)
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Self"))
                                        .binding(
                                            defaults.cancelLimbMovements.isEnabledOnSelf(),
                                            () -> config.cancelLimbMovements.isEnabledOnSelf(),
                                            (enabledOnSelf) -> config.cancelLimbMovements.setEnabledOnSelf(enabledOnSelf)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Players"))
                                        .binding(
                                            defaults.cancelLimbMovements.isEnabledOnOtherPlayers(),
                                            () -> config.cancelLimbMovements.isEnabledOnOtherPlayers(),
                                            (enabledOnOtherPlayers) -> config.cancelLimbMovements.setEnabledOnOtherPlayers(enabledOnOtherPlayers)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Entities"))
                                        .binding(
                                            defaults.cancelLimbMovements.isEnabledOnOtherEntities(),
                                            () -> config.cancelLimbMovements.isEnabledOnOtherEntities(),
                                            (enabledOnOtherEntities) -> config.cancelLimbMovements.setEnabledOnOtherEntities(enabledOnOtherEntities)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Weirder Limb Movements"))
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                            defaults.weirderLimbMovements.isEnabled(),
                                            () -> config.weirderLimbMovements.isEnabled(),
                                            (enabled) -> config.weirderLimbMovements.setEnabled(enabled)
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Self"))
                                        .binding(
                                            defaults.weirderLimbMovements.isEnabledOnSelf(),
                                            () -> config.weirderLimbMovements.isEnabledOnSelf(),
                                            (enabledOnSelf) -> config.weirderLimbMovements.setEnabledOnSelf(enabledOnSelf)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Players"))
                                        .binding(
                                            defaults.weirderLimbMovements.isEnabledOnOtherPlayers(),
                                            () -> config.weirderLimbMovements.isEnabledOnOtherPlayers(),
                                            (enabledOnOtherPlayers) -> config.weirderLimbMovements.setEnabledOnOtherPlayers(enabledOnOtherPlayers)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Entities"))
                                        .binding(
                                            defaults.weirderLimbMovements.isEnabledOnOtherEntities(),
                                            () -> config.weirderLimbMovements.isEnabledOnOtherEntities(),
                                            (enabledOnOtherEntities) -> config.weirderLimbMovements.setEnabledOnOtherEntities(enabledOnOtherEntities)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .group(
                            OptionGroup.createBuilder()
                                .name(Text.literal("Cancel Sneaking"))
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                            defaults.cancelSneaking.isEnabled(),
                                            () -> config.cancelSneaking.isEnabled(),
                                            (enabled) -> config.cancelSneaking.setEnabled(enabled)
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Self"))
                                        .binding(
                                            defaults.cancelSneaking.isEnabledOnSelf(),
                                            () -> config.cancelSneaking.isEnabledOnSelf(),
                                            (enabledOnSelf) -> config.cancelSneaking.setEnabledOnSelf(enabledOnSelf)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Players"))
                                        .binding(
                                            defaults.cancelSneaking.isEnabledOnOtherPlayers(),
                                            () -> config.cancelSneaking.isEnabledOnOtherPlayers(),
                                            (enabledOnOtherPlayers) -> config.cancelSneaking.setEnabledOnOtherPlayers(enabledOnOtherPlayers)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .option(
                                    Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled on Other Entities"))
                                        .binding(
                                            defaults.cancelSneaking.isEnabledOnOtherEntities(),
                                            () -> config.cancelSneaking.isEnabledOnOtherEntities(),
                                            (enabledOnOtherEntities) -> config.cancelSneaking.setEnabledOnOtherEntities(enabledOnOtherEntities)
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )).generateScreen(parent);
    }
}