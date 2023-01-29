import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
class ImageLoader
{
BufferedImage scaledImage(Image a,int w,int h)
{
BufferedImage b;
Graphics2D g2;
b=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
g2=b.createGraphics();
g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
g2.drawImage(a,0,0,w,h,null);
g2.dispose();
return b;
}
BufferedImage loadImage(String name,int w,int h)
{
BufferedImage icon;
try
{
icon=ImageIO.read(new File(name));
icon=scaledImage(icon,w,h);
}
catch(Exception e)
{
return null;
}
return icon;
}
}