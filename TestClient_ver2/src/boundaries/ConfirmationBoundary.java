package boundaries;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;




/**
 * 出題者のお題確認用
 * @author Kazuki0724
 *
 */
class ConfirmationBoundary extends JPanel{

    
    //各パーツ
    private JLabel messageLabel;
    private JLabel themeLabel;
    private JLabel timerLabel;
    
    
    
    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public ConfirmationBoundary(){

        
        
        LineBorder border = new LineBorder(Color.RED, 2, true);
        
        
        //各パーツのインスタンス生成
        messageLabel = new JLabel("お題を確認してください"); 
        themeLabel = new JLabel("お題：");
        timerLabel = new JLabel("timer");
        
       
        
        
        
        
        
        /*レイアウト***************************************/
        this.setLayout(null);
        
        messageLabel.setBounds(250,200,300,40);
        themeLabel.setBounds(250,250,300,40);
        timerLabel.setBounds(470,10,150,40);
        timerLabel.setBorder(border);
        
        
        /***************************************************/
        
        
        /*パネルに追加***********/
        this.add(messageLabel);
        this.add(themeLabel);
        this.add(timerLabel);
        /************************/
        
        
        
        this.setSize(640, 480);
        

    }
    
    
    
    /**
     * テーマのラベルへの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {
    	
    	System.out.println("お題は"+theme);
    	this.themeLabel.setText(String.format("お題：%s",theme ));
    	
    }
    
    
    /**
     * カウントダウンタイマー
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }
    
    
    
    
}