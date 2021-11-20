package boundaries;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.ClientSystemControl;




/**
 * 出題者のお題確認用
 * @author Kazuki0724
 *
 */
class ConfirmationBoundary extends JPanel{

    //リスナーに渡すだけのため
	private Boundary boundary;
    private ClientSystemControl control;
    
    
    //各パーツ
    private JLabel messageLabel;
    private JLabel themeLabel;
    private JLabel timerLabel;
    private JButton nextButton;
    
    

    public ConfirmationBoundary(Boundary boundary, ClientSystemControl control){

        this.boundary = boundary;
        this.control = control;
        
        
        LineBorder border = new LineBorder(Color.RED, 2, true);
        
        
        //各パーツのインスタンス生成
        messageLabel = new JLabel("お題を確認してください"); 
        themeLabel = new JLabel("お題：");
        timerLabel = new JLabel("timer");
        
        nextButton = new JButton("次へ");
        nextButton.addActionListener(new ActionListener() {
        	
        	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
        		control.communicate().sendData("game start","");
        		control.stopTimer();
			}
        });
        
        
        
        
        
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
    
    
    
    
    public void setTheme(String theme) {
    	
    	System.out.println("お題は"+theme);
    	this.themeLabel.setText(String.format("お題：%s",theme ));
    	
    }
    
    
    
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }
    
    
    
    
}