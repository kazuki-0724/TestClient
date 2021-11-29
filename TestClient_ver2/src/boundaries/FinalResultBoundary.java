package boundaries;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.ClientSystemControl;
import listener.ToLobbyActionListener;


/**
 * 最終結果画面
 * @author Kazuki0724
 *
 */
public class FinalResultBoundary extends JPanel{


    private Boundary boundary;
    private ClientSystemControl control;
    //各パーツ
    private JLabel messageLabel;
    private JLabel themeLabel;
    private JLabel timerLabel;
    private JButton nextButton;

    
    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public FinalResultBoundary(Boundary boundary, ClientSystemControl control){

        this.boundary = boundary;
        this.control = control;
        
        
        LineBorder border = new LineBorder(Color.RED, 2, true);
        
        
        //各パーツのインスタンス生成
        messageLabel = new JLabel("最終結果"); 
        themeLabel = new JLabel("");
        timerLabel = new JLabel("");
        
        nextButton = new JButton("ロビーに戻る");
        nextButton.setActionCommand("BackToLobby");
        
        
        
        
        ToLobbyActionListener listener = new ToLobbyActionListener(this, boundary, control);
        nextButton.addActionListener(listener);
        
        
        
        /*レイアウト***************************************/
        this.setLayout(null);
        
        messageLabel.setBounds(250,200,300,40);
        themeLabel.setBounds(250,250,300,40);
        timerLabel.setBounds(470,10,150,40);
        timerLabel.setBorder(border);
        nextButton.setBounds(250,350,200,40);
        
        /***************************************************/
        
        
        /*パネルに追加***********/
        this.add(messageLabel);
        this.add(themeLabel);
        this.add(timerLabel);
        this.add(nextButton);
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
