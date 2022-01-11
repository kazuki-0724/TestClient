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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import entity.PlayerMessage;
import listener.AnswerSubmitActionListener;



/**
 * 回答者用画面
 * @author Kazuki0724
 *
 */
public class RespondentBoundary extends JPanel{

	 //各パーツ
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel messageLabel_3;

    private JLabel timerLabel;
    private JLabel pointLabel;
    private JProgressBar timerBar;


    private AnswerCanvas ac;

    private JTextField answerField;
    private JButton answerButton;

    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane sp;



    /**********************************/


    /*クラス図にはいらない気がする********/
    final String FONT_NAME = "MS ゴシック";
    Font MONOSPACED = new Font(Font.MONOSPACED,Font.PLAIN,15);
    final String THEME = "お題 : ";
    final String TIMEKEEP = "※制限時間以内に解答を送信してください";
    final String PLAYER = "・playerLabel   xxpt";
    final String TIMER = "timer";
    final String POINT = "point";
    final String[] COLUMN_NAMES = {"ID","Point"};
    final String[][] member = {
			{"aaaa","0"},
    		{"bbbb","0"},
    		{"cccc","0"},
    		{"dddd","0"}};



    //String[] sampleData = {"1st:aaaa","2nd:bbbb","3rd:cccc","4th:dddd","5th:eeee","6th:ffff"};
    /*******************************************/

    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public RespondentBoundary(){

    	 //各パーツのインスタンス生成
        messageLabel_1 = new JLabel(TIMEKEEP);

        timerLabel = new JLabel(TIMER);
        timerBar = new JProgressBar();

        // answer input field
        answerField = new JTextField(10);

        // submit button
        answerButton = new JButton("送信");
        answerButton.setActionCommand("answer");

        ac = new AnswerCanvas();



        tableModel = new DefaultTableModel(COLUMN_NAMES,0);
        for(int i=0;i<4;i++) {
        	tableModel.addRow(member[i]);
        }

        table = new JTable(tableModel);
        table.setFont(MONOSPACED);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel=table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(125);
        colModel.getColumn(1).setPreferredWidth(40);

        JTableHeader jheader = table.getTableHeader();
        jheader.setReorderingAllowed(false);

        table.setEnabled(false);

        table.setRowHeight(23);

        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setCellRenderer(tableCellRenderer);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        col2.setCellRenderer(tableCellRenderer);

        sp = new JScrollPane(table);


        /**************************************************/





        //レイアウト可視化のため
        LineBorder border = new LineBorder(Color.RED, 2, true);
        Font messageFont = new Font(FONT_NAME, Font.BOLD,29);
        Font playerFont = new Font(FONT_NAME, Font.PLAIN,16);
        Font timerFont = new Font(FONT_NAME,Font.BOLD,18);
        this.setLayout(null);
        /****************************************************/



        /*レイアウト*******************************/
        this.setLayout(null);

        messageLabel_1.setBounds(20,20,580,36);
        messageLabel_1.setFont(messageFont);

        timerLabel.setBounds(600,20,30,40);

        timerBar.setBounds(625, 21, 150, 40);
        timerBar.setStringPainted(true);

        sp.setBounds(640, 230, 170, 115);

        answerField.setBounds(540,540,200,30);

        answerButton.setBounds(740,540,80,30);

        ac.setBounds(20,70,600,450);

        /******************************************/




        /*パネルに追加*****************/
        this.add(ac);
        this.add(messageLabel_1);
        //this.add(timerLabel);
        this.add(sp);
        this.add(answerField);
        this.add(answerButton);
        /******************************/



        this.setSize(840, 630);

    }


    /**
     *
     * @param answerSubmitActionListener
     */
    public void addAnswerButtonListener(AnswerSubmitActionListener answerSubmitActionListener) {
    	this.answerButton.addActionListener(answerSubmitActionListener);
    }



    /**
     * タイマーカウントダウン
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




    /**
     * 送られてきた座標データによって描画
     * @param stroke
     */
    public void drawStroke(String stroke) {

    	System.out.println("[ RespondentBoundary ] drawStroke() : Log stroke = " + stroke);

    	if(stroke.equals("CLEAR")) {

    		System.out.println("[ RespondentBoundary ] drawStroke() : Log");
    		ac.clear();

    	}else {

    		int type,s_x,s_y,e_x,e_y;

        	String[] tmpString = stroke.split("_");

        	type = Integer.parseInt(tmpString[0]);
        	s_x = Integer.parseInt(tmpString[1]);
        	s_y = Integer.parseInt(tmpString[2]);
        	e_x = Integer.parseInt(tmpString[3]);
        	e_y = Integer.parseInt(tmpString[4]);


        	ac.setLine(type,s_x,s_y,e_x,e_y);
    	}



    }

    /**
     * answerFieldのgetter
     * @return
     */
    public JTextField getAnswerField() {
		return answerField;
	}


    public JButton getAnswerButton() {
    	return answerButton;
    }


    public void setTable(List<PlayerMessage> list ) {

    	for(int i=0;i<4;i++) {

    		PlayerMessage tmp = list.get(i);

    		//setValueAt(セルにセットするデータ,,n行,n列)
    		tableModel.setValueAt(" " + tmp.getPlayerID(),i,0);
    		tableModel.setValueAt(tmp.getTotalPoint(),i,1);
    	}

    }




    /**
     * 正解しているプレイヤーの
     * @param list
     * @param correctPlayerNum
     */
    public void setCorrectPlayer(List<PlayerMessage> list ,int correctPlayerNum) {

    	for(int i=0;i<4;i++) {

    		PlayerMessage tmp = list.get(i);


    		if( Integer.parseInt(tmp.getPlayerNum()) == correctPlayerNum ) {

    			tableModel.setValueAt("〇 " + tmp.getPlayerID(), i, 0);
    		}

    	}
    }


}