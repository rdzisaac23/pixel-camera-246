package com.example.pixelcamera;

import javax.imageio.ImageIO;


public class ViewPixels {
    final int PIX_SIZE = 10;

    // Read file
    img =ImageIO.read(new

    File("saitama_test.jpg"));

    //Raster data
    Raster src = img.getData();

    // Create an identically
    WritableRaster dest = src.createCompatibleWritableRaster();

    // Loop through every PIX_SIZE
    for(
    int y = 0; y<src.getHeight();y +=PIX_SIZE)

    {
        for (int x = 0; x < src.getWidth(); x += PIX_SIZE) {

            //Copy the pixel
            double[] pixel = new double[3];
            pixel = src.getPixel(x, y, pixel);

            // "Paste" the pixel
            // Never go outside
            for (int yd = y; (yd < y + PIX_SIZE) && (yd < dest.getHeight()); yd++) {
                for (int xd = x; (xd < x + PIX_SIZE) && (xd < dest.getWidth()); xd++) {
                    dest.setPixel(xd, yd, pixel);
                }
            }
        }
    }

    // Save the raster
    img.setData(dest);

    //Write new image
    ImageIO.write(img, "jpg", new File('image-pixelated.jpg'));
}