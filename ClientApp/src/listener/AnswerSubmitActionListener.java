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



		String answer = this.rb.getAnswerField().getText();



		if( checkAnswer(answer) ) {
			//正しい場合

			System.out.println("[ AnswerSubmitActionListener ] actionPerFormed() : Log your answer is correct (" + answer + ")");
			//時刻じゃなくて正解した旨を送信
			boundary.getControl().communicate().sendData(ProcessID.ANSWER, "correct");
			this.rb.getAnswerField().setText("正解");
			this.rb.getAnswerField().setEditable(false);
			this.rb.getAnswerButton().setEnabled(false);

		}else {
			//間違っている場合
			//実際に一致していなかった場合

			System.out.println("[ AnswerSubmitActionListener ] actionPerFormed() : Log your answer is incorrect (" + answer + ")");
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