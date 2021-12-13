package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;



/**
 * 出題者用画面
 * @author Kazuki0724
 *
 */
class PainterBoundary extends JPanel{


    private Boundary boundary;
    //private ClientControl control;

    //各パーツ
    private JLabel messageLabel_1;
    private JLabel messageLabel_2;
    private JLabel messageLabel_3;

    private JLabel themeLabel;
    private JLabel timerLabel;
    private JLabel pointLabel;


    private JList<String> list;
    private JScrollPane sp;

    private GameCanvas gc;
    
  //参加プレイヤーラベル
    private JLabel[] playerLabel = new JLabel[4];
    

    /*クラス図にはいらない気がする********/
    final String FONT_NAME = "MS ゴシック";
    final String THEME = "お題：";
    final String TIMEKEEP = "制限時間内に絵を描いてください";


    private String[] sampleData = {"1st:aaaa","2nd:bbbb","3rd:cccc","4th:dddd","5th:eeee","6th:ffff"};
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
        
        timerLabel = new JLabel("timer");
        
        pointLabel = new JLabel("point");

        list = new JList<>(sampleData);
        sp = new JScrollPane();
                
        gc = new GameCanvas(this.boundary.getControl());
        
        
        for(int i=0; i<4; i++) {
        	playerLabel[i] = new JLabel("playerLabel");
        }
        
        /********************************/

        

        /*レイアウト視覚化のためのもの*/
        LineBorder border = new LineBorder(Color.RED, 2, true);
        Font themeFont = new Font(FONT_NAME, Font.BOLD,30);
        /*****************************/

        
        
        
        /*レイアウト***********************************/
        this.setLayout(null);
        
        themeLabel.setBounds(150,10,300,40);
        themeLabel.setBorder(border);
        themeLabel.setFont(themeFont);
        
        messageLabel_1.setBounds(200,415,150,20);
        messageLabel_1.setBorder(border);
        
        timerLabel.setBounds(470,10,150,40);
        timerLabel.setBorder(border);
        
        sp.getViewport().setView(list);
        sp.setBounds(540,200,80,200);
        
        gc.setBounds(20,50,500,350);
        
        /*********************************************/
        

        /*パネルに追加**********/
        this.add(gc);
        this.add(themeLabel);
        this.add(sp);
        this.add(timerLabel);
        /***********************/
        
        

        this.setSize(640, 480);
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
    }
    
    /**
     * テーマの埋め込み
     * @param theme
     */
    public void setTheme(String theme) {
    	this.themeLabel.setText(String.format("お題 : %s", theme));
    }
    
    
    
    public void setPlayersLabel(String[] data ) {
    	
    	//(name_rate )* 4
    	/**
    	 * split(_)
    	 */
    	
    	
    	for(int i=0; i<4;i++) {
    		playerLabel[i].setText(String.format("%s : %s", "player"+i,"rate")); 
    	}
    	
    }

}