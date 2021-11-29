package listener;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

import boundaries.Boundary;
import boundaries.RespondentBoundary;
import control.ClientControl;

/**
 * 回答送信用のリスナークラス
 * @author Kazuki0724
 *
 */
public class AnswerSubmitActionListener extends BoundaryActionListener{
    

	private RespondentBoundary rb;
    private ClientControl control;
    private Boundary boundary;
	
    
    /**
     * コンストラクタ
     * @param rb
     * @param control
     * @param boundary
     */
    public AnswerSubmitActionListener(RespondentBoundary rb, ClientControl control, Boundary boundary) {
    	
    	this.rb = rb;
    	this.control = control;
    	this.boundary = boundary;
    }
    
    
	/**
	 * イベント処理
	 */
	public void actionPerformed(ActionEvent e){
        
		System.out.println("[Log] Answer Submit Button Clicked");
		
		String answer = this.rb.getAnswerField().getText();
		System.out.println("[Log] answer is "+answer);
		
		
		checkAnswer(answer);
		 
    
    }
	
	
	
	/**
	 * 正誤判定
	 * @param answer
	 */
	public void checkAnswer(String answer) {
	
		if(control.getGameInfo().getTheme().equals(answer)) {
			
			//答えが一致していた場合、実際に正解時刻を送信する
			System.out.println("[Log] your answer is correct");
			control.communicate().sendData("answer", LocalDateTime.now().toString());
			
			
			
		}else {
			
			//実際に一致していなかった場合
			System.out.println("[Log] your answer is incorrect");
			
			
		}
		
	}
    
	
	
}