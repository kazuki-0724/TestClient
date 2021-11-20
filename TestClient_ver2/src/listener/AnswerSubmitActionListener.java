package listener;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

import boundaries.Boundary;
import boundaries.RespondentBoundary;
import control.ClientSystemControl;

/**
 * 回答送信用のリスナークラス
 * @author Kazuki0724
 *
 */
public class AnswerSubmitActionListener extends BoundaryActionListener{
    

	private RespondentBoundary rb;
    private ClientSystemControl control;
    private Boundary boundary;
	
    
    
    public AnswerSubmitActionListener(RespondentBoundary rb, ClientSystemControl control, Boundary boundary) {
    	
    	this.rb = rb;
    	this.control = control;
    	this.boundary = boundary;
    }
    
    
	
	public void actionPerformed(ActionEvent e){
        
		System.out.println("[Log] Answer Submit Button Clicked");
		
		String answer = this.rb.getAnswerField().getText();
		System.out.println("[Log] answer is "+answer);
		
		
		checkAnswer(answer);
		 
    
    }
	
	
	
	//回答の確認
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