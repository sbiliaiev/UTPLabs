import java.awt.*;

public enum Status {

    Running(Color.BLUE, Color.WHITE), Accomplished(Color.GREEN, Color.BLACK), Pending(Color.YELLOW, Color.BLACK),
    Failed(Color.RED, Color.WHITE);

    private final Color backgroundColor;
    private final Color fontColor;

    Status(Color backgroundColor, Color fontColor) {
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

}