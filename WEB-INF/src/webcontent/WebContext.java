package webContent;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WebContext {
    private String color;
    private String font;


    public WebContext(String color, String font) {
        this.color = color;
        this.font = font;
    }

    public WebContext() {

    }

    @JsonGetter("color")
    public String getColor() {
        return color;
    }

    @JsonSetter("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonGetter("font")
    public String getFont() {
        return font;
    }

    @JsonSetter("font")
    public void setFont(String font) {
        this.font = font;
    }
}
