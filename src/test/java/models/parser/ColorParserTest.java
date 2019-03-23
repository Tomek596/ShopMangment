package models.parser;

import entity.enums.Color;
import entity.parser.ColorParser;
import org.junit.Assert;
import org.junit.Test;

public class ColorParserTest {

    @Test
    public void testParseColorRed(){
        String strColor = "rEd";

        Color color = ColorParser.parseStrToColor(strColor);

        Assert.assertEquals(Color.RED, color);
    }

    @Test
    public void testParseColorBlue(){
        String strColor = "BluE";

        Color color = ColorParser.parseStrToColor(strColor);

        Assert.assertEquals(Color.BLUE, color);
    }
    @Test
    public void testParseGreen(){
        String strColor = "GreeN";

        Color color = ColorParser.parseStrToColor(strColor);

        Assert.assertEquals(Color.GREEN, color);
    }
    @Test
    public void testParseYellow(){
        String strColor = "YELLOw";

        Color color = ColorParser.parseStrToColor(strColor);

        Assert.assertEquals(Color.YELLOW, color);
    }
    @Test
    public void testParseBlack(){
        String strColor = "BLACK";

        Color color = ColorParser.parseStrToColor(strColor);

        Assert.assertEquals(Color.BLACK, color);
    }
    @Test
    public void testParseWhite(){
        String strColor = "WHite";

        Color color = ColorParser.parseStrToColor(strColor);

        Assert.assertEquals(Color.WHITE, color);
    }
    @Test
    public void testParseWrongColor(){
        String strColor = "guq";

        Color color = ColorParser.parseStrToColor(strColor);

        Assert.assertEquals(Color.WHITE, color);
    }
}
