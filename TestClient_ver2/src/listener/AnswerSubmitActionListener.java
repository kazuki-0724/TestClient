package listener;

import java.awt.event.ActionEvent;

import boundaries.Boundary;
import boundaries.RespondentBoundary;
import entity.ProcessID;

/**
 * 回答送信用のリスナークラス
 * @author Kazuki0724
 *
 */
public class AnswerSubmitActionListener extends BoundaryActionListener{
    

	private RespondentBoundary rb;
    private Boundary boundary;
	
    
    /**
     * コンストラクタ
     * @param rb
     * @param control
     * @param boundary
     */
    public AnswerSubmitActionListener(RespondentBoundary rb, Boundary boundary) {
    	
    	this.rb = rb;
    	this.boundary = boundary;
    }
    
    
	/**
	 * イベント処理
	 */
	public void actionPerformed(ActionEvent e){
        
		System.out.println("[Log] Answer Submit Button Clicked");
		
		String answer = this.rb.getAnswerField().getText();
		System.out.println("[Log] answer is "+answer);
		
		
		if( checkAnswer(answer) ) {
			//正しい場合
			
			//答えが一致していた場合、実際に正解時刻を送信する
			System.out.println("[Log] your answer is correct");
			//時刻じゃなくて正解した旨を送信
			boundary.getControl().communicate().sendData(ProcessID.ANSWER, "correct");
			
		}else {
			//間違っている場合
			//実際に一致していなかった場合
			System.out.println("[Log] your answer is incorrect");
		}
		 
    
    }
	
	
	
	/**
	 * 正誤判定
	 * @param answer
	 */
	public boolean checkAnswer(String answer) {
	
		if(boundary.getControl().getGameInfo().getTheme().equals(answer)) {
			return true;	
		}
		
		return false;
	}
    
	
	
}