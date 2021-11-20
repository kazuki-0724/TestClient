package entity;


/**
 * ユーザの情報のクラス
 * @author Kazuki0724
 *
 */
public class Player{

	//通常仕様(クライアント的に必須なもの)
    private String id;
    private String password;
    private int win;
    private int lose;
    private double rate;
    private int games;
    
    
    
    /**************************/
    //CL管理サーバ的
    //private Lobby myLobby; 
    //private int myStatus;
    /**************************/


    /**************************/
    //APサーバ的
    //private int point;
    //private boolean wasPainter;
    //private String adress;
    /**************************/
    
    
    
    
    /**
     * コンストラクタ
     * @param id
     * @param password
     * @param win
     * @param lose
     * @param games
     */
    public Player(String id, String password, int win, int lose, int games){
        this.id = id;
        this.password = password;
        this.win = win;
        this.lose = lose;
        this.games = games;
        this.rate = win / lose;
    }


    //getter/setter
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }
    
    public double getRate() {
		return rate;
	}
    
    public void setRate(double rate) {
		this.rate = rate;
	}
    

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    
    
    /*************************************************/
    //各サーバのプレイヤー反映
    
    /*
    public Lobby getMyLobby() {
  		return this.myLobby;
  	}
      
    public void setMyLobby(Lobby myLobby) {
  		this.myLobby = myLobby;
  	}
    
    
    public int getMyStatus() {
		return myStatus;
	}
    
    public void setMyStatus(int myStatus) {
		this.myStatus = myStatus;
	}
    
    public int getPoint() {
		return point;
	}
    
    public void setPoint(int point) {
		this.point = point;
	}
    
    public String getAdress() {
		return adress;
	}
    
    public void setAdress(String adress) {
		this.adress = adress;
	}
    
    
    public boolean getWasPainter() {
		return this.wasPainter;
	}
    
    public void setWasPainter(boolean wasPainter) {
		this.wasPainter = wasPainter;
	}
	*/
    
    


}