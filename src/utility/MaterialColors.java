/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

/**
 *
 * @author Eike
 */
public interface MaterialColors 
{
    
    public static PhongMaterial WHITE_MATERIAL()
    {
        PhongMaterial whiteMaterial = new PhongMaterial();    
        whiteMaterial.setDiffuseColor(Color.WHITE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
        return whiteMaterial;
    }
    
    public static PhongMaterial RED_MATERIAL()
    {
        PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        return redMaterial;
    }
    
    public static PhongMaterial GREY_MATERIAL()
    {
        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);
        return greyMaterial;
    }
}
