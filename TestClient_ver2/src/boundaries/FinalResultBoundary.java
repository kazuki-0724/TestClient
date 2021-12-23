package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entity.PlayerMessage;
import listener.ToLobbyActionListener;


/**
 * 最終結果画面
 * @author Kazuki0724
 *
 */
public class FinalResultBoundary extends JPanel{


	//各パーツ
    private JLabel titleLabel;
    private JLabel winnerLabel;
    private JLabel messageLabel;
    private JLabel timerLabel;
    private JProgressBar timerBar;

    private JButton toLobbyButton;

    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane sp;

    //文字列
    final String TITLE = "最終結果発表";
    final String MESSAGE = "勝者  〇〇  xxpt";
    final String MESSAGE2 = "結果";
    final String PLAYER1 = "1st playerLabel   xxpt";
    final String PLAYER2 = "2nd playerLabel   xxpt";
    final String PLAYER3 = "3rd playerLabel   xxpt";
    final String PLAYER4 = "4th playerLabel   xxpt";
    final String TOLOBBY = "ロビーへ戻る";
    final String FONT_NAME = "MS ゴシック";
    final String[] COLUMN_NAMES = {"Rank","ID","Total Point"};
    final String[][] member = {
			{"1st","blank","blank"},
			{"2nd","blank","blank"},
			{"3th","blank","blank"},
			{"4th","blank","blank"}
    		};


    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public FinalResultBoundary(){

        //this.boundary = boundary;
        //this.control = control;


        LineBorder border = new LineBorder(Color.RED, 2, true);


        //各パーツのインスタンス生成
        titleLabel = new JLabel(TITLE);
        winnerLabel = new JLabel(MESSAGE);
        messageLabel = new JLabel(MESSAGE2);
        timerLabel = new JLabel("");

        //本来なら秒数依存で幅を決めるはず
        timerBar = new JProgressBar();



        tableModel = new DefaultTableModel(COLUMN_NAMES,0);
        for(int i=0;i<4;i++) {
        	tableModel.addRow(member[i]);
        }

        table = new JTable(tableModel);

        sp = new JScrollPane(table);


        toLobbyButton = new JButton(TOLOBBY);
        toLobbyButton.setActionCommand("BackToLobby");

      //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,52);
        Font playerFont = new Font(FONT_NAME, Font.BOLD ,36);
        Font winnerFont = new Font(FONT_NAME, Font.BOLD,36);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,45);
        Font messageFont2 = new Font(FONT_NAME, Font.BOLD,16);


        /*レイアウト***************************************/
        this.setLayout(null);

        titleLabel.setBounds(250,80,330,52);
        titleLabel.setBorder(border);
        titleLabel.setFont(titleFont);

        winnerLabel.setBounds(275,190,275,40);
        winnerLabel.setBorder(border);
        winnerLabel.setFont(winnerFont);

        messageLabel.setBounds(360,260,100,50);
        messageLabel.setBorder(border);
        messageLabel.setFont(messageFont);


        sp.setBounds(220,320,390,176);

        timerLabel.setBounds(640,10,150,40);
        timerLabel.setBorder(border);

        timerBar.setBounds(640, 55, 150, 40);

        toLobbyButton.setBounds(675,535,120,35);

        /***************************************************/


        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(winnerLabel);
        this.add(messageLabel);
        //this.add(timerLabel);
        this.add(toLobbyButton);


        /************************/



        this.setSize(840, 630);


    }


    /**
     *時間遷移オンリーならいらない
     * @param toLobbyActionListener
     *
     */
    public void addNextButtonActionListener(ToLobbyActionListener toLobbyActionListener) {
    //	this.nextButton.addActionListener(toLobbyActionListener);
    }





    /**
     * カウントダウンタイマー
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");

    	int count;

    	try {
    		count = Integer.parseInt(time);
    	}catch(Exception e) {
    		return;
    	}


    	this.timerBar.setValue(count);
    	this.timerBar.setString(time);
    }



    public void setTimerBar(int num) {
    	timerBar.setMaximum(num);
    	timerBar.setMinimum(0);
    }


    public void setTable(List<PlayerMessage> list) {


    	//昇順
    	PlayerMessage[] sorted = bubbleSort(list);

    	for(int i=0;i<4;i++) {

    		tableModel.setValueAt(sorted[3-i].getPlayerID(), i, 1);
    		tableModel.setValueAt(sorted[3-i].getTotalPoint(), i, 2);

    	}


    	winnerLabel.setText( String.format("勝者 %s %dpt", sorted[3].getPlayerID(),sorted[3].getTotalPoint()) );


    }


    /**
     * 昇順ソート
     * @param list
     * @return
     */
    public PlayerMessage[] bubbleSort(List<PlayerMessage> list){

    	PlayerMessage[] players = new PlayerMessage[4];

    	for(int i=0;i<4;i++) {
    		players[i] = list.get(i) ;
    	}

    	boolean flag = true;

    	while(flag) {

	    	for(int i=1;i<4;i++) {

	    		flag = false;

	    		if(players[i-1].getTotalPoint() > players[i].getTotalPoint()) {

	    			PlayerMessage tmp = players[i];
	    			players[i] = players[i-1];
	    			players[i-1] = tmp;
	    			flag = true;

	    		}
	    	}
    	}


    	return players;

    }


}
