package boundaries;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.ClientControl;


/**
 * ゲーム開始前画面
 * @author Kazuki0724
 *
 */
public class GameStartBoundary extends JPanel{
	
	
	//リスナーに渡すだけのため
		private Boundary boundary;
	    private ClientControl control;
	    
	    
	    //各パーツ
	    private JLabel messageLabel;
	    private JLabel timerLabel;
	    
	    
	    /**
	     * コンストラクタ
	     * @param boundary
	     * @param control
	     */
	    public GameStartBoundary(Boundary boundary, ClientControl control){

	        this.boundary = boundary;
	        this.control = control;
	        
	        
	        LineBorder border = new LineBorder(Color.RED, 2, true);
	        
	        
	        //各パーツのインスタンス生成
	        messageLabel = new JLabel("ゲーム参加者"); 
	        timerLabel = new JLabel("timer");
	        
	        
	        
	        /*レイアウト***************************************/
	        this.setLayout(null);
	        
	        messageLabel.setBounds(250,200,300,40);
	        timerLabel.setBounds(470,10,150,40);
	        timerLabel.setBorder(border);
	        
	        /***************************************************/
	        
	        
	        /*パネルに追加***********/
	        this.add(messageLabel);
	        this.add(timerLabel);
	        /************************/
	        
	        
	        
	        this.setSize(640, 480);
	        

	    }
	    
	    
	    
	    
	    /**
	     * タイマーカウントダウン
	     * @param time
	     */
	    public void updateTimer(String time) {
	    	this.timerLabel.setText(time + "");
	    }
	    
	    
	    

}