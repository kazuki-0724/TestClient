package boundaries;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ClientControl;




/**
 * 回答者の待機用画面
 * @author Kazuki0724
 *
 */
class WaitingTimeBoundary extends JPanel{

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
    public WaitingTimeBoundary(Boundary boundary, ClientControl control) {
		// TODO 自動生成されたコンストラクター・スタブ
	

        this.boundary = boundary;
        this.control = control;
        
        
        /*各インスタンス生成****************************************************/
        messageLabel = new JLabel("出題者がお題確認中。しばらくお待ちください");     
        timerLabel = new JLabel("");
       
        
        /***********************************************************************/
        
        
        
        
        
        /*レイアウト***************/
        this.setLayout(null);

        messageLabel.setBounds(250,200,300,40);
        
        /*************************************/
        
        
        /*パネルに追加*********/
        this.add(messageLabel);
        /**********************/
        
        
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