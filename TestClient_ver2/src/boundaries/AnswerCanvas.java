package boundaries;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;




/**
 * 回答者用キャンバス
 * @author Kazuki0724
 *
 */
/**
 * @author Kazuki0724
 *
 */
public class AnswerCanvas extends Canvas{


    static int w = 800, h = 600;

    private BufferedImage cImage = null;
    //
    private Graphics2D g2d;


    private int x, y, xx, yy;

    private int type;

    public int width = 6;

    public Color black = Color.black;

    public Color white = Color.white;



    /**
     * コンストラクタ
     */
    public AnswerCanvas() {


        x = -1;
        y = -1;
        xx = -1;
        yy = -1;
        type = 0;



        setBackground(Color.white);

        cImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) cImage.getGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, w, h);

    }



    /**
     * 描画処理
     */
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




    /**
     * ちらつき防止の措置
     */
    public void update(Graphics g){
        paint(g);
    }


    public void clear() {
    	g2d.setColor(white);
    	g2d.fillRect(0, 0, w, h);
    	repaint();

    }

    /**
     * 受け取った座標データの描画
     * @param type
     * @param start_x
     * @param start_y
     * @param end_x
     * @param end_y
     */
    public void setLine(int type, int start_x, int start_y, int end_x, int end_y){

        this.type = type;
        this.xx = start_x;
        this.yy = start_y;
        this.x = end_x;
        this.y = end_y;

        repaint();

    }


}