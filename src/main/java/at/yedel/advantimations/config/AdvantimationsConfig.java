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
    public PerspectiveIndependentOption cancelSwings = new PerspectiveIndependentOption()
        .enabled()
        .enabledInFirstPerson()
        .enabledOnSelf();

    @SerialEntry
    public boolean cancelEatingAnimation = false;

    @SerialEntry
    public boolean cancelDrinkingAnimation = false;

    @SerialEntry
    public PerspectiveIndependentOption cancelBlockingAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelShieldAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelBowingAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelBowArrowModel = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelSpearAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelBrushingAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelBundleAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelCrossbowingAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelChargedCrossbowAnimation = new PerspectiveIndependentOption();

    @SerialEntry
    public PerspectiveIndependentOption cancelCrossbowArrowModel = new PerspectiveIndependentOption();

    @SerialEntry
    public boolean cancelBlockInteractResets = true;

    @SerialEntry
    public boolean cancelItemInteractResets = true;

    @SerialEntry
    public boolean cancelAllItemResets = false;

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
                .category(ConfigCategory.createBuilder()
                    .name(Text.literal("Item Model"))
                    .tooltip(Text.literal("Options for cancelling item models and animations."))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Swings", "Cancel hand and item swing animations.", defaults.cancelSwings, config.cancelSwings
                    ))
                    .option(Option.<Boolean>createBuilder()
                        .name(Text.literal("Cancel Eating Animation"))
                        .description(OptionDescription.of(Text.literal("Cancel the first-person eating animation.")))
                        .binding(
                            defaults.cancelEatingAnimation,
                            () -> config.cancelEatingAnimation,
                            (cancelEatingAnimation) -> config.cancelEatingAnimation = cancelEatingAnimation
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )
                    .option(Option.<Boolean>createBuilder()
                        .name(Text.literal("Cancel Drinking Animation"))
                        .description(OptionDescription.of(Text.literal("Cancel the first-person drinking animation.")))
                        .binding(
                            defaults.cancelDrinkingAnimation,
                            () -> config.cancelDrinkingAnimation,
                            (cancelDrinkingAnimation) -> config.cancelDrinkingAnimation = cancelDrinkingAnimation
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Blocking Animation", "Cancel the item blocking animation.", defaults.cancelBlockingAnimation, config.cancelBlockingAnimation
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Shield Animation", "Cancel the shield blocking animation.", defaults.cancelShieldAnimation, config.cancelShieldAnimation
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Bowing Animation", "Cancel the bow drawing animation.", defaults.cancelBowingAnimation, config.cancelBowingAnimation
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Bow Arrow Model", "Cancel the arrow in a bow being rendered.", defaults.cancelBowArrowModel, config.cancelBowArrowModel
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Crossbowing Animation", "Cancel the crossbow drawing animation.", defaults.cancelCrossbowingAnimation, config.cancelCrossbowingAnimation
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Charged Crossbow Animation", "Cancel the charged crossbow model.", defaults.cancelChargedCrossbowAnimation, config.cancelChargedCrossbowAnimation
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Crossbow Arrow Model", "Cancel the arrow in a crossbow being rendered", defaults.cancelCrossbowArrowModel, config.cancelCrossbowArrowModel
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Spear Animation", "Cancel the spear drawing animation.", defaults.cancelSpearAnimation, config.cancelSpearAnimation
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Brushing Animation", "Cancel the brushing animation.", defaults.cancelBrushingAnimation, config.cancelBrushingAnimation
                    ))
                    .group(PerspectiveIndependentOption.createGroup(
                        "Cancel Bundle Animation", "Cancel the bundle use swing animation.", defaults.cancelBundleAnimation, config.cancelBundleAnimation
                    ))
                    .group(OptionGroup.createBuilder()
                        .name(Text.literal("Item Resets"))
                        .description(OptionDescription.of(Text.literal("Options for cancelling the item reset animation.")))
                        .option(Option.<Boolean>createBuilder()
                            .name(Text.literal("Cancel Block Interact Resets"))
                            .description(OptionDescription.of(Text.literal("Cancel the item reset animation when interacting with a block.")))
                            .binding(
                                defaults.cancelBlockInteractResets,
                                () -> config.cancelBlockInteractResets,
                                (cancelBlockInteractResets) -> config.cancelBlockInteractResets = cancelBlockInteractResets
                            )
                            .controller(BooleanControllerBuilder::create)
                            .build()
                        )
                        .option(Option.<Boolean>createBuilder()
                            .name(Text.literal("Cancel Item Interact Resets"))
                            .description(OptionDescription.of(Text.literal("Cancel the item reset animation when interacting with an item.")))
                            .binding(
                                defaults.cancelItemInteractResets,
                                () -> config.cancelItemInteractResets,
                                (cancelItemInteractResets) -> config.cancelItemInteractResets = cancelItemInteractResets
                            )
                            .controller(BooleanControllerBuilder::create)
                            .build()
                        )
                        .option(Option.<Boolean>createBuilder()
                            .name(Text.literal("Cancel All Item Resets"))
                            .description(OptionDescription.of(Text.literal("Always cancel the item reset animation when swapping items.")))
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
                .category(ConfigCategory.createBuilder()
                    .name(Text.literal("Entity Model"))
                    .tooltip(Text.literal("Options for cancelling entity model animations."))
                    .group(EntityApplicableOption.createGroup(
                        "Cancel Limb Movements", "Cancel entity limb movements.", defaults.cancelLimbMovements, config.cancelLimbMovements
                    ))
                    .group(EntityApplicableOption.createGroup(
                        "Weirder Limb Movements", "Somewhat cancel entity limb movements.", defaults.weirderLimbMovements, config.weirderLimbMovements
                    ))
                    .group(EntityApplicableOption.createGroup(
                        "Cancel Sneaking", "Cancel the sneaking animation.", defaults.cancelSneaking, config.cancelSneaking
                    ))
                    .build()
                )).generateScreen(parent);
    }
}