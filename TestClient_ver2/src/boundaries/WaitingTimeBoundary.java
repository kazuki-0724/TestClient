package boundaries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ClientSystemControl;




/**
 * 回答者の待機用画面
 * @author Kazuki0724
 *
 */
class WaitingTimeBoundary extends JPanel{

    private Boundary boundary;
    private ClientSystemControl control;
    
    
    //各パーツ
    private JLabel messageLabel;
    private JButton nextButton;
    
    
    
    public WaitingTimeBoundary(Boundary boundary, ClientSystemControl control) {
		// TODO 自動生成されたコンストラクター・スタブ
	

        this.boundary = boundary;
        this.control = control;
        
        
        /*各インスタンス生成****************************************************/
        messageLabel = new JLabel("出題者がお題確認中。しばらくお待ちください");     
        
        nextButton = new JButton("次へ");
        nextButton.addActionListener(new ActionListener() {
        	
        	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
        		control.communicate().sendData("game start","");
			}
        });
        
        /***********************************************************************/
        
        
        
        
        
        /*レイアウト***************/
        this.setLayout(null);

        messageLabel.setBounds(250,200,300,40);
        nextButton.setBounds(250,350,200,40);
        
        /*************************************/
        
        
        /*パネルに追加*********/
        this.add(messageLabel);
        this.add(nextButton);
        /**********************/
        
        
        this.setSize(640, 480);
        

    }
    
    
    
    
    
}