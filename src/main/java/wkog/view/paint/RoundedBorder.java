package wkog.view.paint;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {
    private final Border delegate;
    private final int cornerRadius;

    public RoundedBorder(Border delegate, int cornerRadius) {
        this.delegate = delegate;
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        delegate.paintBorder(c, g2d, x, y, width, height);
        g2d.setColor(new Color(0,0,0,30));
        g2d.drawRoundRect(x, y, width - 1, height - 1, cornerRadius, cornerRadius);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return delegate.getBorderInsets(c);
    }

    @Override
    public boolean isBorderOpaque() {
        return delegate.isBorderOpaque();
    }
}