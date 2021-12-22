package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entity.PlayerMessage;




/**
 * ターン結果用画面
 * @author Kazuki0724
 *
 */
class ResultBoundary extends JPanel{


	//各パーツ
    private JLabel titleLabel;
    private JLabel themeLabel;
    private JLabel timerLabel;
    private JLabel messageLabel;
    private JLabel messageLabel_2;
    //private JLabel[] playerLabel = new JLabel[4];
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane sp;


    //文字列
    final String TITLE = "ターン結果発表";
    final String THEME = "このターンのお題：〇〇〇";
    final String MESSAGE = "現在の獲得ポイント";
    final String MESSAGE2 = "次のターンまで";
    final String TIMER = "timer";
    final String PLAYER = "・playerLabel   xxpt";
    final String FONT_NAME = "MS ゴシック";
    final String[] COLUMN_NAMES = {"ID","Answer","Point","Total Point"};
    final String[][] member = {
			{"blank","blank","blank","blank"},
			{"blank","blank","blank","blank"},
			{"blank","blank","blank","blank"},
			{"blank","blank","blank","blank"}
    		};

    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public ResultBoundary(){

    	LineBorder border = new LineBorder(Color.RED, 2, true);


        //各パーツのインスタンス生成
        titleLabel = new JLabel(TITLE);
        themeLabel = new JLabel(THEME);
        messageLabel = new JLabel(MESSAGE);
        messageLabel_2 = new JLabel(MESSAGE2);
        timerLabel = new JLabel(TIMER);

        /*
        for(int i=0; i<4; i++) {
        	playerLabel[i] = new JLabel(PLAYER);
        }
        */

        tableModel = new DefaultTableModel(COLUMN_NAMES,0);
        for(int i=0;i<4;i++) {
        	tableModel.addRow(member[i]);
        }

        table = new JTable(tableModel);

        sp = new JScrollPane(table);



        //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
        Font playerFont = new Font(FONT_NAME, Font.BOLD ,36);
        Font themeFont = new Font(FONT_NAME, Font.BOLD,30);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,20);
        Font messageFont2 = new Font(FONT_NAME, Font.BOLD,16);


        /*レイアウト***************************************/
        this.setLayout(null);

        titleLabel.setBounds(240,80,340,45);
        titleLabel.setBorder(border);
        titleLabel.setFont(titleFont);

        themeLabel.setBounds(210,170,400,40);
        themeLabel.setBorder(border);
        themeLabel.setFont(themeFont);

        messageLabel.setBounds(310,270,200,40);
        messageLabel.setBorder(border);
        messageLabel.setFont(messageFont);

        messageLabel_2.setBounds(640,10,150,30);
        messageLabel_2.setBorder(border);
        messageLabel_2.setFont(messageFont2);

        timerLabel.setBounds(640,40,150,30);
        timerLabel.setBorder(border);

        /*
        for(int i=0; i<4; i++) {
        	playerLabel[i].setBounds(230,320+50*i,360,44);
        	playerLabel[i].setBorder(border);
        	playerLabel[i].setFont(playerFont);
        }
        */

        sp.setBounds(230, 320, 360, 176);

        /***************************************************/


        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(messageLabel);
        this.add(themeLabel);
        this.add(messageLabel_2);
        this.add(timerLabel);
        /*
        for(int i=0; i<4 ; i++) {
        	this.add(playerLabel[i]);
        }
        */
        this.add(sp);
        /************************/



        this.setSize(840, 630);


    }

    /**
     * テーマの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {

    	this.themeLabel.setText(String.format("正解は %s",theme ));

    }



    public void setTable(List<PlayerMessage> list , int painterNum) {

    	for(int i=0;i<4;i++) {

    		PlayerMessage tmp = list.get(i);

    		//System.out.println(tmp.getPlayerID());

    		if(painterNum == Integer.parseInt(tmp.getPlayerNum())) {
    			//出題者は
    			//setValueAt(セルにセットするデータ,,n行,n列)
        		tableModel.setValueAt("[Painter] " + tmp.getPlayerID(),i,0);

    		}else {
    			//setValueAt(セルにセットするデータ,,n行,n列)
        		tableModel.setValueAt(tmp.getPlayerID(),i,0);
    		}



    		if(tmp.getTurnPoint() > 0 ) {
    			tableModel.setValueAt("〇",i,1);
    		}else {
    			tableModel.setValueAt("×",i,1);
    		}


    		tableModel.setValueAt(tmp.getTurnPoint(),i,2);
    		tableModel.setValueAt(tmp.getTotalPoint(),i,3);


    	}

    }



    /**
     * タイマーカウントダウン
     * @param time
     */
    public void updateTimer(String time) {
    	this.timerLabel.setText(time + "");
    }

}