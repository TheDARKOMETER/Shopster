import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

class MyScrollbarUI extends BasicScrollBarUI {

	    @Override
	    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
	        g.setColor(new Color(243, 249, 255));
	        
	        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
	    }
	    @Override
	    public Dimension getPreferredSize(JComponent c) {
	        return new Dimension(8,50);
	    }
	    @Override
	    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
	        g.setColor(new Color(73,183,63));
	        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
	    }
	    @Override
	    protected JButton createDecreaseButton(int orientation) {
	        JButton button = new JButton();
	        button.setPreferredSize(new Dimension(0,0));
	        return button;
	    }

	    @Override
	    protected JButton createIncreaseButton(int orientation) {
	        JButton button = new JButton();
	        button.setPreferredSize(new Dimension(0,0));
	        return button;
	    }
	}