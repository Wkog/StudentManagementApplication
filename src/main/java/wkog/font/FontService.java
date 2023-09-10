package wkog.font;

import java.awt.*;
import java.io.File;

public class FontService {

    public Font montserrat() {
        Font montserrat = null;
        try {
            montserrat = Font.createFont(Font.TRUETYPE_FONT,new File("src/main/java/wkog/font/Montserrat.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(montserrat);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return montserrat;
    }
}
