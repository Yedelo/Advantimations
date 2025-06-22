package at.yedel.advantimations.config;



public class SimpleFirstPersonOption implements FirstPersonOption {
    private boolean enabled;

    private SimpleFirstPersonOption(boolean enabled) {
        this.enabled = enabled;
    }

    public static SimpleFirstPersonOption trueOption() {
        return new SimpleFirstPersonOption(true);
    }

    public static SimpleFirstPersonOption falseOption() {
        return new SimpleFirstPersonOption(false);
    }

    @Override
    public boolean shouldApplyInFirstPerson() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
