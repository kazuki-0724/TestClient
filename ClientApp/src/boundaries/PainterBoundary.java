package boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



/**
 * 出題者用画面
 * @author Kazuki0724
 *
 */
class PainterBoundary extends JPanel{


    private Boundary boundary;

    //各パーツ
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel messageLabel_3;

    private JLabel themeLabel;
    private JLabel timerLabel;
    private JLabel pointLabel;

    private JButton clearButton;

    private JProgressBar timerBar;

    private GameCanvas gc;




    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane sp;


    /*クラス図にはいらない気がする********/
    final String FONT_NAME = "MS ゴシック";
    Font MONOSPACED = new Font(Font.MONOSPACED,Font.PLAIN,15);
    final String THEME = "お題：〇〇〇";
    final String TIMEKEEP = "※制限時間内に絵を描いてください";
    final String LEFTCLICK = "	・左クリック：線を描く";
    final String RIGHTCLICK = "・右クリック：消しゴム";
    final String PLAYER = "・playerLabel   xxpt";
    final String TIMER = "00";
    final String POINT = "point";
    final String CLEAR = "全消し";
    final String[] COLUMN_NAMES = {"ID","Point"};
    final String[][] member = {
			{"aaaa","0"},
    		{"bbbb","0"},
    		{"cccc","0"},
    		{"dddd","0"}};

    /*************************************/




    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public PainterBoundary(Boundary boundary){


        this.boundary = boundary;

      //各パーツのインスタンス生成
        themeLabel = new JLabel(THEME);

        messageLabel_1 = new JLabel(TIMEKEEP);
        messageLabel_2 = new JLabel(LEFTCLICK);
        messageLabel_3 = new JLabel(RIGHTCLICK);

        timerLabel = new JLabel(TIMER);

        timerBar = new JProgressBar();

        pointLabel = new JLabel(POINT);

        clearButton = new JButton(CLEAR);


        gc = new GameCanvas(boundary.getControl());


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



        /********************************/



        /*レイアウト視覚化のためのもの*/
        LineBorder border = new LineBorder(Color.RED, 2, true);
        Font themeFont = new Font(FONT_NAME, Font.BOLD,30);
        Font timerFont = new Font(FONT_NAME,Font.BOLD,18);
        Font messageFont = new Font(FONT_NAME, Font.BOLD,30);
        Font messageFont2 = new Font(FONT_NAME, Font.BOLD,16);
        Font playerFont = new Font(FONT_NAME, Font.PLAIN,16);
        /*****************************/




        /*レイアウト***********************************/
        this.setLayout(null);

        themeLabel.setBounds(280,10,300,40);
        themeLabel.setFont(themeFont);

        messageLabel_1.setBounds(70,537,500,36);
        messageLabel_1.setFont(messageFont);

        messageLabel_2.setBounds(632,100,200,30);
        messageLabel_2.setFont(messageFont2);
        messageLabel_3.setBounds(632,120,200,30);
        messageLabel_3.setFont(messageFont2);

        timerLabel.setBounds(600,10,30,40);

        timerBar.setBounds(645, 11, 150, 40);
        timerBar.setStringPainted(true);

        clearButton.setBounds(648,170,150,40);


        sp.setBounds(640, 290, 170, 115);


        gc.setBounds(20,70,600,450);

        /*********************************************/


        /*パネルに追加**********/
        this.add(gc);
        this.add(themeLabel);
        this.add(messageLabel_1);
        this.add(messageLabel_2);
        this.add(messageLabel_3);
        this.add(sp);
        //this.add(timerLabel);
        this.add(timerBar);

        /*
        for(int i=0; i<4 ; i++) {
        	this.add(playerLabel[i]);
        }
        */

        this.add(clearButton);
        /***********************/




        clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				gc.clear();

			}


        });





        this.setSize(840, 630);

    }


    /**
     * pointLabelのgetter
     * @return
     */
    public JLabel getPointLabel() {
        return pointLabel;
    }

    /**
     * pointLabelのsetter
     * @param pointLabel
     */
    public void setPointLabel(JLabel pointLabel) {
        this.pointLabel = pointLabel;
    }

    /**
     * timerLabelのgetter
     * @return
     */
    public JLabel getTimerLabel() {
        return timerLabel;
    }

    /**
     * timerLabelのsetter
     * @param timerLabel
     */
    public void setTimerLabel(JLabel timerLabel) {
        this.timerLabel = timerLabel;
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
     * テーマの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {
    	this.themeLabel.setText(String.format("お題 : %s", theme));
    }



    public void setTable(List<PlayerMessage> list ) {

    	for(int i=0;i<4;i++) {

    		PlayerMessage tmp = list.get(i);

    		//setValueAt(セルにセットするデータ,,n行,n列)
    		tableModel.setValueAt(" " + tmp.getPlayerID(),i,0);
    		tableModel.setValueAt(tmp.getTotalPoint(),i,1);
    	}

    }


    //正解しているプレイヤーの表示を変える
    public void setCorrectPlayer(List<PlayerMessage> list ,int correctPlayerNum) {

    	for(int i=0;i<4;i++) {
    		PlayerMessage tmp = list.get(i);

    		if( Integer.parseInt(tmp.getPlayerNum()) == correctPlayerNum ) {

    			tableModel.setValueAt("〇" + tmp.getPlayerID(), correctPlayerNum, i);
    		}
    	}
    }


    public void clear() {
    	clearButton.doClick();
    	System.out.println("[ PainterBoundary ] clear() : Log");
    }




}