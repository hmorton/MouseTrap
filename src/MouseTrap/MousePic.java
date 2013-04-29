package MouseTrap;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MousePic extends JPanel{
	private ImageIcon icon;
	private Image pic;
	private int x = 10-1, y = 10-1;
	
	//private Image iconish;
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public MousePic(){
		icon = createImageIcon("/MouseTrap/image/mouse.gif");
		pic = icon.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		revalidate();
		repaint();
	}
	
	void draw(Graphics g){
		g.drawImage(pic, x*30-10, y*30-10,  this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imageURL = MousePic.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
	}
	
	
	
//	public static void main(String[] args) {
//
//	    JFrame frame = new JFrame("Test Switch");
//	     
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    MousePic app = new MousePic();
//	    frame.add(app);
//	    frame.setVisible(true); 
//	    
//	    frame.setSize(300, 200);
//	  
//	
//	   
//				
//
//	}

}
