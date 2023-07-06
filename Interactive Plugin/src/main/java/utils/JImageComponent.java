package utils;

import com.intellij.util.ui.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JImageComponent extends javax.swing.JComponent {

    private BufferedImage bufferedImage = null;


    private Graphics imageGraphics = null;
    public JImageComponent(BufferedImage bufferedImage) {
        this.setBufferedImage(bufferedImage);
    }
    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;

        // Clear the graphics object if null image specified.
        // Clear the component bounds if null image specified.
        if (this.bufferedImage == null) {
            this.imageGraphics = null;
            this.setBounds(0, 0, 0, 0);
        }

        // Set the graphics object.
        // Set the component's bounds.
        else {
            this.imageGraphics = this.bufferedImage.createGraphics();
            this.setBounds(0, 0, this.bufferedImage.getWidth(), this.bufferedImage.getHeight());
        }
    }
    public void loadImage(URL imageLocation) throws IOException {
        this.bufferedImage = ImageIO.read(imageLocation);
        this.setBufferedImage(this.bufferedImage);
    }
    public void loadImage(File imageLocation) throws IOException {
        this.bufferedImage = ImageIO.read(imageLocation);
        this.setBufferedImage(this.bufferedImage);
    }
    /*
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }
        Rectangle rectangle = this.getVisibleRect();
        /*try {
            this.bufferedImage = resizeImage(this.bufferedImage,rectangle.width,rectangle.height);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // Paint the visible region.
        g.translate(rectangle.width / 2, rectangle.height / 2);
        g.translate(-bufferedImage.getWidth(null) / 2, -bufferedImage.getHeight(null) / 2);
        paintImmediately(g, rectangle.x,
                rectangle.y, rectangle.width, rectangle.height);
    };

    /*
     * @see javax.swing.JComponent#paintImmediately(int, int, int, int)
     */
    @Override
    public void paintImmediately(int x, int y, int width, int height) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }

        // Paint the region specified.
        this.paintImmediately(super.getGraphics(), x, y, width, height);
    }

    @Override
    public void paintImmediately(Rectangle rectangle) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }

        // Paint the region specified.
        this.paintImmediately(super.getGraphics(), rectangle.x,
                rectangle.y, rectangle.width, rectangle.height);
    }
    /**
     * Paints the image onto the component.
     *
     * @param g
     *        The Graphics object of the component onto which the
     *        image region will be painted.
     * @param x
     *        The x value of the region to be painted.
     * @param y
     *        The y value of the region to be painted.
     * @param width
     *        The width of the region to be painted.
     * @param height
     *        The height of the region to be painted.
     */
    private void paintImmediately(Graphics g, int x, int y, int width, int height) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }

        int imageWidth = this.bufferedImage.getWidth();
        int imageHeight = this.bufferedImage.getHeight();

        // Exit if the dimension is beyond that of the image.
        if (x >= imageWidth || y >= imageHeight) {
            return;
        }

        // Calculate the rectangle of the image that should be rendered.
        int x1 = x < 0 ? 0 : x;
        int y1 = y < 0 ? 0 : y;
        int x2 = x + width - 1;
        int y2 = y + height - 1;

        if (x2 >= imageWidth) {
            x2 = imageWidth - 1;
        }

        if (y2 >= imageHeight) {
            y2 = imageHeight - 1;
        }

        // Draw the image.
        g.drawImage(this.bufferedImage, x1, y1, x2, y2, x1, y1, x2, y2, null);
    }
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = ImageUtil.createImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
