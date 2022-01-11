package entity;


/**
 * ユーザの情報のクラス
 * 今後大改修がありそう
 * @author Kazuki0724
 *
 */
public class Player{

	//通常仕様(クライアント的に必須なもの)
    private String id;
    private int win;
    private int lose;
    private double rate;
    private int games;

    //private int playerNum;


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
    public Player(String id, int win,int games){
        this.id = id;
        this.win = win;
        this.lose = games - win;
        this.games = games;

        if(games == 0) {
        	this.rate = 0;
        }else {
        	this.rate = win / games;
        }
    }


    /*
     * ログアウトの時の初期化処理
     */
    public void init() {
    	this.id = "unknown";
    	this.win = -1;
    	this.lose = -1;
    	this.games = -1;
    	this.rate = -1;
    }


    /**
     * idのgetter
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * idのsetter
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 勝ち数のgetter
     * @return
     */
    public int getWin() {
        return win;
    }

    /**
     * 勝ち数のsetter
     * @param win
     */
    public void setWin(int win) {
        this.win = win;
    }

    /**
     * 負け数のgetter
     * @return
     */
    public int getLose() {
        return lose;
    }

    /**
     * 負け数のsetter
     * @param lose
     */
    public void setLose(int lose) {
        this.lose = lose;
    }

    /**
     * rateのgetter
     * @return
     */
    public double getRate() {
		return rate;
	}

    /**
     * rateのseter
     * @param rate
     */
    public void setRate(double rate) {
		this.rate = rate;
	}


    /**
     * 試合数のgetter
     * @return
     */
    public int getGames() {
        return games;
    }

    /**
     * 試合数のsetter
     * @param games
     */
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