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
 * ゲーム開始前画面
 * @author Kazuki0724
 *
 */
public class GameStartBoundary extends JPanel{


	//各パーツ
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel timerLabel;
    private JProgressBar timerBar;


	//参加プレイヤーラベル
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane sp;


    //文字列
    final String message1 = "参加プレイヤー確定";
    final String message2 = "ゲームを開始します";
    final String TIMER = "timer";
    final String PLAYER = "・playerLabel   xx%";
    final String FONT_NAME = "MS ゴシック";
    Font MONOSPACED = new Font(Font.MONOSPACED,Font.PLAIN,38);
    final String[] COLUMN_NAMES = {"ID","RATE"};
    final String[][] member = {
			{"aaaa","100%"},
    		{"bbbb","90%"},
    		{"cccc","80%"},
    		{"dddd","70%"}};

    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public GameStartBoundary(){

    	LineBorder border = new LineBorder(Color.RED, 2, true);


        //各パーツのインスタンス生成
        messageLabel_1 = new JLabel(message1);
        messageLabel_2 = new JLabel(message2);
        timerLabel = new JLabel(TIMER);

        //本来なら秒数依存で幅を決めるはず
        timerBar = new JProgressBar();


        //フォント
        Font titleFont = new Font(FONT_NAME, Font.BOLD,45);
        Font playerFont = new Font(FONT_NAME, Font.BOLD ,30);
        Font messageFont = new Font(FONT_NAME, Font.BOLD ,20);


        /*レイアウト***************************************/
        this.setLayout(null);

        messageLabel_1.setBounds(180,160,480,45);
        messageLabel_1.setHorizontalAlignment(JLabel.CENTER);
        messageLabel_1.setFont(titleFont);

        messageLabel_2.setBounds(610,30,200,30);
        messageLabel_2.setFont(messageFont);

        timerLabel.setBounds(605,60,30,30);

        timerBar.setBounds(630, 60, 150, 30);
        timerBar.setStringPainted(true);


        tableModel = new DefaultTableModel(COLUMN_NAMES,0);
        for(int i=0;i<4;i++) {
        	tableModel.addRow(member[i]);
        }

        table = new JTable(tableModel);
        table.setFont(MONOSPACED);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel=table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(320);
        colModel.getColumn(1).setPreferredWidth(100);

        JTableHeader jheader = table.getTableHeader();
        jheader.setReorderingAllowed(false);// テーブルの列移動を不許可にする。

        table.setEnabled(false);

        table.setRowHeight(42);

        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setCellRenderer(tableCellRenderer);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        col2.setCellRenderer(tableCellRenderer);

        sp = new JScrollPane(table);

        sp.setBounds(210, 280, 420, 191);


        /***************************************************/


        /*パネルに追加***********/
        this.add(messageLabel_1);
        this.add(messageLabel_2);
        //this.add(timerLabel);
        this.add(sp);
        this.add(timerBar);



        /************************/



        this.setSize(840, 630);


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


    public void setTable(List<PlayerMessage> list ) {

    	for(int i=0;i<4;i++) {

    		PlayerMessage tmp = list.get(i);

    		System.out.println(tmp.getPlayerID());

    		//setValueAt(セルにセットするデータ,,n行,n列)
    		tableModel.setValueAt(tmp.getPlayerID(),i,0);
    		tableModel.setValueAt(tmp.getRate(),i,1);


    	}

    }




}
