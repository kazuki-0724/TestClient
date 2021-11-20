package listener;

import java.awt.event.ActionEvent;

import boundaries.AccountAuthentificationBoundary;
import boundaries.AccountRegistrationBoundary;
import boundaries.Boundary;
import boundaries.FinalResultBoundary;
import control.ClientSystemControl;
import entity.Boundaries;


/**
 * ロビーに遷移する系のクラス
 * @author Kazuki0724
 * 新規登録に関しては帰ってくる指示が[ログイン画面に遷移しろ]だから若干趣旨とずれてる
 */
public class ToLobbyActionListener extends BoundaryActionListener{
    


    private AccountAuthentificationBoundary aab;
    private AccountRegistrationBoundary arb;
    private FinalResultBoundary frb;
    private ClientSystemControl control;
    private Boundary boundary;


    public ToLobbyActionListener(AccountAuthentificationBoundary aab, Boundary boundary ,ClientSystemControl control){
        this.aab = aab;
        this.control = control;
    }

    
    public ToLobbyActionListener(AccountRegistrationBoundary arb, Boundary boundary ,ClientSystemControl control){
        this.arb = arb;
        this.control = control;
    }
    
    
    public ToLobbyActionListener(FinalResultBoundary frb, Boundary boundary ,ClientSystemControl control){
        this.frb = frb;
        this.control = control;
    }
    
    


    public void actionPerformed(ActionEvent e){
        
        String str = e.getActionCommand();
        
        
        String userId = "";
        String userPass = "";
        
        
        //どっちのバウンダリーのボタン検知か区別
        if(str.equals("LOGIN")) {
        	
        	System.out.println("[Log] Login Button Clicked");
            
            userId = this.aab.getIdField().getText();
            userPass = new String( this.aab.getPassFiled().getPassword() );

            System.out.println(String.format("[Log] Login id = %s / pass = %s",userId,userPass));
            
            //init Field
            this.aab.getIdField().setText("");
            this.aab.getPassFiled().setText("");
            
            
            //
            if(userId.equals("") || userPass.equals("")) {
            	
            	boundary.updatePanel(Boundaries.AccountAuthentificationBoudary, "error");
            	System.out.println("[Error] not enterted");
            	
            }else{
              	
            	control.communicate().sendData("login",String.format("%s&%s",userId,userPass));
        	
            }
            
            

        	
        }else if(str.equals("REGISTRATION")) {
        	
        	System.out.println("[Log] Login Button Clicked");
            
            userId = this.arb.getIdField().getText();
            userPass = new String( this.arb.getPassFiled().getPassword() );

            System.out.println(String.format("[Log] Resist id = %s / pass = %s",userId,userPass));

            
            //init Field
            this.arb.getIdField().setText("");
            this.arb.getPassFiled().setText("");
            
           
            if(userId.equals("") || userPass.equals("")) {
            	
            	boundary.updatePanel(Boundaries.AccountRegistrationBoundary, "error");
            	System.out.println("[Error] not enterted");
            	
            }else{
              	
            	control.communicate().sendData("regist",String.format("%s&%s",userId,userPass));
        	
            }
            
        	
         
        }else if(str.equals("BackToLobby")) {
        	
        	System.out.println("[Log] BackToLobby Button Clicked");
                
         	
            control.communicate().sendData("back to lobby", "blank");
        	
            
            
        	    
        }else {
        	System.out.println("[Error] aaListener cannot resolve action command");
        }

        
    
    }
    
}
