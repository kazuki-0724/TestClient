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
 * ログイン用画面
 * @author Kazuki0724
 *
 */
public class AccountAuthentificationBoundary extends JPanel{


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
    private JButton loginButton;

    //ログインと新規登録の切り替え
    private JButton switchButton;



    /*クラス図にはいらない気がする********/
    final String LOGIN = "LOGIN";
    final String ID = "ID";
    final String PASSWORD = "PASSWORD";
    /*************************************/



    /**
     * コンストラクタ
     * @param boundary
     * @param control
     */
    public AccountAuthentificationBoundary(){


        //各パーツのインスタンス生成
        titleLabel = new JLabel(LOGIN);
        messageLabel = new JLabel("message");

        idLabel = new JLabel(ID);
        idField = new JTextField(10);
        passLabel = new JLabel(PASSWORD);
        passFiled = new JPasswordField(10);
        loginButton = new JButton(LOGIN);
        loginButton.setActionCommand(LOGIN);
        switchButton = new JButton("新規登録");
        switchButton.setActionCommand("goResist");


        /*レイアウト視覚化のためのもの************************/
        LineBorder border = new LineBorder(Color.RED, 2, true);
        Font titleFont = new Font("MS ゴシック", Font.BOLD,50);
        Font textFont = new Font("MS ゴシック", Font.BOLD,25);
        /*****************************************************/


        /*レイアウト***************************************/

        this.setLayout(null);


        titleLabel.setBounds(180, 70, 480, 60);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(titleFont);

        messageLabel.setBounds(250,190,340,36);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(textFont);

        idLabel.setBounds(290, 285, 80, 40);
        idLabel.setFont(textFont);
        idField.setBounds(410, 285, 180, 40);
        idField.setFont(textFont);

        passLabel.setBounds(230, 340, 160, 40);
        passLabel.setFont(textFont);
        passFiled.setBounds(410, 340, 180, 40);
        passFiled.setFont(textFont);

        loginButton.setBounds(360, 420, 100, 30);
        switchButton.setBounds(690,540,100,30);

        /***************************************************/


        /*パネルに追加***********/
        this.add(titleLabel);
        this.add(messageLabel);
        this.add(idLabel);
        this.add(idField);
        this.add(passLabel);
        this.add(passFiled);
        this.add(loginButton);
        this.add(switchButton);
        /************************/



        this.setSize(840, 630);
    }


    /**
     *
     * @param toLobbyActionListener
     */
    public void addLoginButtonListener(ToLobbyActionListener toLobbyActionListener) {
    	this.loginButton.addActionListener(toLobbyActionListener);
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
     *
     * @param text エラーメッセージなどの表示用
     */
    public void updateMessageLabel(String text) {
		this.messageLabel.setText(text);
	}





}