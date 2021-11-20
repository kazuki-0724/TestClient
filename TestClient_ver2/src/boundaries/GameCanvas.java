package boundaries;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import control.ClientSystemControl;




/**
 * 描画用キャンバス
 * @author Kazuki0724
 *
 */
public class GameCanvas extends Canvas implements MouseListener,MouseMotionListener {


	private ClientSystemControl csc;
	
    static int w = 800, h = 600;
    public String position;


    private BufferedImage cImage = null;
    
    private Graphics2D g2d;

    private int x, y, xx, yy;
    
    private int type;
    
    public int width = 6;
    
    public Color black = Color.black;
    public Color white = Color.white;

    

    public GameCanvas(ClientSystemControl csc) {
        
    	this.csc = csc;
    	
        x = -1;
        y = -1;
        xx = -1;
        yy = -1;
        type = 0;

        addMouseListener(this);
        addMouseMotionListener(this);

        setBackground(white);
        
        cImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) cImage.getGraphics();
        g2d.setColor(white);
        g2d.fillRect(0, 0, w, h);

       
    }



    public void paint(Graphics g) {
    	
        if (type == 1) {
            if (x >= 0 && y >= 0 && xx >= 0 && yy >= 0) {
                BasicStroke stroke = new BasicStroke(width,
                        BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                g2d.setStroke(stroke);
                g2d.setColor(black);
                g2d.drawLine(xx, yy, x, y);
            }
            
        } else if (type == 2) {
            if (x >= 0 && y >= 0 && xx >= 0 && yy >= 0) {
            	
                BasicStroke stroke = new BasicStroke(25.0f,
                        BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                g2d.setStroke(stroke);
                g2d.setColor(white);
                g2d.drawLine(xx, yy, x, y);
            }
        }

        
        g.drawImage(cImage, 0, 0, null);
    }


    public void update(Graphics g){
        paint(g);
    }



    @Override
    public void mouseDragged(MouseEvent e) {
    	
        if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
        	
            type = 1;
        }
        if (e.getModifiersEx() == MouseEvent.BUTTON2_DOWN_MASK) {
        	
        }
        if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK) {
            type = 2;
        }

        xx = x;
        yy = y;

        
        Point point = e.getPoint();
        x = point.x;
        y = point.y;

    
        String position = String.format("%d %d %d %d %d",type,xx,yy,point.x,point.y);
        this.csc.communicate().sendData("stroke",position);
        
    
       
        repaint();
    }



    @Override
    public void mouseMoved(MouseEvent e) {

        x = -1;
        y = -1;
        xx = -1;
        yy = -1;
        type = 0;

    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        x = point.x;
        y = point.y;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }

   


}