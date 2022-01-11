package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

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




/**
 * ターン結果用画面
 * @author Kazuki0724
 *
 */
class ResultBoundary extends JPanel{


	//各パーツ
    private JLabel titleLabel;
    private JProgressBar timerBar;
    private JLabel themeLabel;
    private JLabel timerLabel;
    private JLabel messageLabel;
    private JLabel messageLabel_2;

    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane sp;


    //文字列
    final String TITLE = "ターン結果発表";
    final String THEME = "このターンのお題：〇〇〇";
    final String MESSAGE = "現在の獲得ポイント";
    final String MESSAGE2 = "次のターンまで";
    final String TIMER = "00";
    //final String PLAYER = "・playerLabel   xxpt";
    final String FONT_NAME = "MS ゴシック";
    final String[] COLUMN_NAMES = {"ID","正誤","獲得ポイント","	総ポイント"};
    final Object[][] member = {
			{"player","○","25","70"},
    		{"player","×","0","60"},
    		{"player","○","10","55"},
    		{"player","×","5","20"}};
    Font MONOSPACED = new Font(Font.MONOSPACED,Font.PLAIN,24);

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

        //本来なら秒数依存で決める
        timerBar = new JProgressBar();


        tableModel = new DefaultTableModel(COLUMN_NAMES,0);
        for(int i=0;i<4;i++) {
        	tableModel.addRow(member[i]);
        }

        table = new JTable(tableModel);
        table.setFont(MONOSPACED);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel=table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(420);
        colModel.getColumn(1).setPreferredWidth(60);
        colModel.getColumn(2).setPreferredWidth(160);
        colModel.getColumn(3).setPreferredWidth(160);

        JTableHeader jheader = table.getTableHeader();
        jheader.setReorderingAllowed(false);

        table.setEnabled(false);

        table.setRowHeight(32);

        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setCellRenderer(tableCellRenderer);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        col2.setCellRenderer(tableCellRenderer);
        TableColumn col3 = table.getColumnModel().getColumn(2);
        col3.setCellRenderer(tableCellRenderer);
        TableColumn col4 = table.getColumnModel().getColumn(3);
        col4.setCellRenderer(tableCellRenderer);


        sp = new JScrollPane(table);



        //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
        Font playerFont = new Font(FONT_NAME, Font.BOLD ,36);
        Font themeFont = new Font(FONT_NAME, Font.BOLD,30);
        Font timerFont = new Font(FONT_NAME, Font.BOLD,18);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,20);
        Font messageFont2 = new Font(FONT_NAME, Font.BOLD,16);


        /*レイアウト***************************************/
        this.setLayout(null);

        titleLabel.setBounds(240,80,360,45);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(titleFont);

        themeLabel.setBounds(210,170,420,40);
        themeLabel.setHorizontalAlignment(JLabel.CENTER);
        themeLabel.setFont(themeFont);

        messageLabel.setBounds(310,270,220,40);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(messageFont);

        messageLabel_2.setBounds(640,25,150,30);
        messageLabel_2.setFont(messageFont2);

        timerLabel.setBounds(605,60,30,30);
        timerLabel.setFont(timerFont);

        timerBar.setBounds(630, 59, 150, 30);
        timerBar.setStringPainted(true);


        sp.setBounds(170, 320, 500, 151);

        /***************************************************/


        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(messageLabel);
        this.add(themeLabel);
        this.add(messageLabel_2);
        //this.add(timerLabel);
        this.add(timerBar);
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



    public void setTable(List<PlayerMessage> list , String painterNum) {

    	int painterPlayerNum = Integer.parseInt(painterNum);

    	for(int i=0;i<4;i++) {

    		PlayerMessage tmp = list.get(i);

    		//System.out.println(tmp.getPlayerID());

    		if(painterPlayerNum == Integer.parseInt(tmp.getPlayerNum())) {
    			//出題者は
    			//setValueAt(セルにセットするデータ,,n行,n列)
        		tableModel.setValueAt("[出題者] " + tmp.getPlayerID(),i,0);
        		tableModel.setValueAt("-",i,1);

    		}else {
    			//setValueAt(セルにセットするデータ,,n行,n列)
        		tableModel.setValueAt("[解答者] " + tmp.getPlayerID(),i,0);

        		if(tmp.getTurnPoint() > 0 ) {
        			tableModel.setValueAt("〇",i,1);
        		}else {
        			tableModel.setValueAt("×",i,1);
        		}

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

}