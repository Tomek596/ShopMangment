package models.parser;

import entity.enums.SkinType;
import entity.parser.SkinParser;
import org.junit.Assert;
import org.junit.Test;

public class SkinParserTest {

    @Test
    public void testParseNatural(){
        String strSkin = "NatuRAL";

        SkinType skinType = SkinParser.parseStrToSkinType(strSkin);

        Assert.assertEquals(SkinType.NATURAL, skinType);
    }
    @Test
    public void testPraseArtificial(){
        String strSkin = "ARTIFIcial";

        SkinType skinType = SkinParser.parseStrToSkinType(strSkin);

        Assert.assertEquals(SkinType.ARTIFICIAL, skinType);
    }
    @Test
    public void testParseWrongType(){
        String strSkin = "any-no-exist";

        SkinType skinType = SkinParser.parseStrToSkinType(strSkin);

        Assert.assertEquals(SkinType.ARTIFICIAL, skinType);
    }
}
