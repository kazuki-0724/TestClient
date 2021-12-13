package listener;

import java.awt.event.ActionEvent;

import boundaries.AccountAuthentificationBoundary;
import boundaries.AccountRegistrationBoundary;
import boundaries.Boundary;
import boundaries.FinalResultBoundary;
import entity.BoundaryID;
import entity.ProcessID;


/**
 * ロビーに遷移する系のクラス
 * @author Kazuki0724
 * 新規登録に関しては帰ってくる指示が[ログイン画面に遷移しろ]だから若干趣旨とずれてる
 */
public class ToLobbyActionListener extends BoundaryActionListener{
    


    private AccountAuthentificationBoundary aab;
    private AccountRegistrationBoundary arb;
    private FinalResultBoundary frb;
    //private ClientControl control;
    private Boundary boundary;
    private final String[] FORBIDDEN_CHARACTERS = {"_","#"}; 
    
    
    /**
     * ログイン画面用のコンストラクタ
     * @param aab
     * @param boundary
     * @param control
     */
    public ToLobbyActionListener(AccountAuthentificationBoundary aab, AccountRegistrationBoundary arb, FinalResultBoundary frb,Boundary boundary){
        this.aab = aab;
        this.arb = arb;
        this.frb = frb;
        this.boundary = boundary;
        //this.control = control;
        
    }

    
  
    
    

    /**
     * イベント処理
     */
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
            
            
            //空入力と禁止文字の排除
            if(userId.equals("") || userPass.equals("")) {
            	
            	boundary.updatePanel(BoundaryID.AccountAuthentificationBoudary, "error");
            	System.out.println("[Error] not enterted");
            	
            }else{
              	
            	if( !checkCharacters(userId) || !checkCharacters(userPass)) {
            		boundary.updatePanel(BoundaryID.AccountAuthentificationBoudary, "forbidden character");
                	System.out.println("[Error] forbidden character");
                	return;
            	}
            	
            	boundary.getControl().communicate().sendData(ProcessID.LOGIN,String.format("%s_%s",userId,userPass));
        	
            }
            
            

        	
        }else if(str.equals("REGISTRATION")) {
        	
        	System.out.println("[Log] Login Button Clicked");
            
            userId = this.arb.getIdField().getText();
            userPass = new String( this.arb.getPassFiled().getPassword() );

            System.out.println(String.format("[Log] Resist id = %s / pass = %s",userId,userPass));

            
            //init Field
            this.arb.getIdField().setText("");
            this.arb.getPassFiled().setText("");
            
            //空入力と禁止文字の排除
            if(userId.equals("") || userPass.equals("")) {
            	
            	
            	boundary.updatePanel(BoundaryID.AccountRegistrationBoundary, "blank input");
            	System.out.println("[Error] not enterted");
            	
            
            }else{
            	
            	if( !checkCharacters(userId) || !checkCharacters(userPass)) {
            		boundary.updatePanel(BoundaryID.AccountRegistrationBoundary, "forbidden character");
                	System.out.println("[Error] forbidden character");
                	return;
            	}
              	
            	boundary.getControl().communicate().sendData(ProcessID.REGIST,String.format("%s_%s",userId,userPass));
        	
            }
            
        	
         
        }else if(str.equals("BackToLobby")) {
        	
        	System.out.println("[Log] BackToLobby Button Clicked");
                
         	//明示的に呼ばないとロビーに関する情報がもらえない(MAKELOBBYとかかも)
            boundary.getControl().communicate().sendData(ProcessID.BACKTOLOBBY, "blank");
        	
            
            
        	    
        }else {
        	System.out.println("[Error] aaListener cannot resolve action command");
        }

        
    
    }
    
    
    
    private boolean checkCharacters(String text) {
    	
    	for(int i=0;i<FORBIDDEN_CHARACTERS.length;i++) {
    		if(text.indexOf(FORBIDDEN_CHARACTERS[i]) != -1 ) {
    			System.out.println("Forbidden characters include");
    			return false;
    		}
    	}
    	return true;
    }
    
}
