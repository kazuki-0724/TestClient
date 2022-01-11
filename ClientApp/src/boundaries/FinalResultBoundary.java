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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
    Font MONOSPACED = new Font(Font.MONOSPACED,Font.PLAIN,36);
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
        table.setFont(MONOSPACED);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel=table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);
        colModel.getColumn(1).setPreferredWidth(220);
        colModel.getColumn(2).setPreferredWidth(150);

        JTableHeader jheader = table.getTableHeader();
        jheader.setReorderingAllowed(false);

        table.setEnabled(false);

        table.setRowHeight(48);

        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setCellRenderer(tableCellRenderer);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        col2.setCellRenderer(tableCellRenderer);
        TableColumn col3 = table.getColumnModel().getColumn(2);
        col3.setCellRenderer(tableCellRenderer);

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

        titleLabel.setBounds(245,80,350,52);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(titleFont);

        winnerLabel.setBounds(240,180,360,40);
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        winnerLabel.setFont(winnerFont);

        messageLabel.setBounds(360,250,120,50);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(messageFont);

        sp.setBounds(120,300,600,212);

        toLobbyButton.setBounds(675,535,120,35);

        /***************************************************/


        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(winnerLabel);
        this.add(messageLabel);
        this.add(toLobbyButton);
        this.add(sp);


        /************************/



        this.setSize(840, 630);


    }


    /**
     *時間遷移オンリーならいらない
     * @param toLobbyActionListener
     *
     */
    public void addToLobbyButtonActionListener(ToLobbyActionListener toLobbyActionListener) {
    	this.toLobbyButton.addActionListener(toLobbyActionListener);
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
