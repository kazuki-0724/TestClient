package boundaries;



import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import listener.SwitchActionListener;
import listener.ToLobbyActionListener;




/**
 * 新規登録用画面
 * @author Kazuki0724
 *
 */
/**
 * @author Kazuki0724
 *
 */
public class AccountRegistrationBoundary extends JPanel{

	//リスナーに渡すだけのために
    //private Boundary boundary;
    //private ClientControl control;

    //各パーツ
    private JLabel titleLabel;
    private JLabel messageLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JLabel passLabel;
    private JPasswordField passFiled;
    private JButton registButton;
    
    //ログインと新規登録の切り替え
    private JButton switchButton;

    /*クラス図にはいらない気がする********/
    final String REGISTRATION = "REGISTRATION";
    final String ID = "ID";
    final String PASSWORD = "PASSWORD";
    /*************************************/


    public AccountRegistrationBoundary(){


        //this.boundary = boundary;
        //this.control = control;

        //各パーツのインスタンス生成
        titleLabel = new JLabel(REGISTRATION);
        messageLabel = new JLabel("message");

        idLabel = new JLabel(ID);
        idField = new JTextField(10);
        passLabel = new JLabel(PASSWORD);
        passFiled = new JPasswordField(10);
        registButton = new JButton(REGISTRATION);
        registButton.setActionCommand(REGISTRATION);
        switchButton = new JButton("ログイン");
        switchButton.setActionCommand("goLogin");

        
        //新規登録ボタンのリスナー
        //ToLobbyActionListener listener = new ToLobbyActionListener(this, boundary, control);
        //registButton.addActionListener(listener);
        
        //ログインと新規登録の切り替えリスナー
        //SwitchActionListener sListener = new SwitchActionListener(this, boundary, control); 
        //switchButton.addActionListener(sListener);
        

        /*レイアウト視覚化のためのもの************************/
        LineBorder border = new LineBorder(Color.RED, 2, true);
        Font titleFont = new Font("MS ゴシック", Font.BOLD,40);
        Font textFont = new Font("MS ゴシック", Font.BOLD,25);
        /*****************************************************/


        /*レイアウト***************************************/
        this.setLayout(null);


        titleLabel.setBounds(100, 10, 500, 60);
        titleLabel.setBorder(border);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(titleFont);


        messageLabel.setBounds(250,100,200,30);
        messageLabel.setBorder(border);
        messageLabel.setFont(textFont);


        idLabel.setBounds(200, 150, 80, 40);
        idLabel.setFont(textFont);
        idField.setBounds(300, 150, 160, 40);
        idField.setFont(textFont);
        passLabel.setBounds(200, 200, 80, 40);
        passLabel.setFont(textFont);
        passFiled.setBounds(300, 200, 160, 40);
        passFiled.setFont(textFont);
        
        registButton.setBounds(300, 250, 80, 20);
        switchButton.setBounds(520,350,80,20);

        /***************************************************/
        
        
        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(messageLabel);
        this.add(idLabel);
        this.add(idField);
        this.add(passLabel);
        this.add(passFiled);
        this.add(registButton);
        this.add(switchButton);
        /************************/

        this.setSize(640, 480);
    }


    
    /**
     * 
     * @param toLobbyActionListener
     */
    public void addLoginButtonListener(ToLobbyActionListener toLobbyActionListener) {
    	this.registButton.addActionListener(toLobbyActionListener);
    }
    
    /**
     * 
     * @param switchActionListener
     */
    public void addSwitchButtonListener(SwitchActionListener switchActionListener) {
    	this.switchButton.addActionListener(switchActionListener);
    }
    

    
    
    /**
     * idFieldのgetter
     * @return
     */
    public JTextField getIdField() {
        return idField;
    }

    
    /**
     * idFieldのsetter
     * @param idField
     */
    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    
    /**
     * passFieldのgetter
     * @return
     */
    public JPasswordField getPassFiled() {
        return passFiled;
    }

    
    /**
     * passFieldのsetter
     * @param passFiled
     */
    public void setPassFiled(JPasswordField passFiled) {
        this.passFiled = passFiled;
    }
    
    
    /**
     * エラーメッセージの表示
     * @param text エラーメッセージなどの表示用
     */
    public void updateMessageLabel(String text) {
		this.messageLabel.setText(text);
	}



}