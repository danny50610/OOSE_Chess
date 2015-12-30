public class ChessBoard {
    Factory factory = new BWChessFactory();

    private Chess ChessStatus[][] = new BWChess[20][20];
    private short nowPlayer;

    // What game to play
    private Rule rule;

    // Record all chess
    private Record gameRecord;

    public ChessBoard(Rule rule) {
        initializeGame();
        // Clean Chess Board
        initializeChessBoard();
        this.rule = rule;
    }

    private void initializeGame() {
        // Black Chess First
        this.nowPlayer = Const.BLACK_CHESS;

        // Generate record
        // FIXME: getname from UI
        this.gameRecord = new Record("BLACK", "WHITE");
    }

    private void initializeChessBoard() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                this.ChessStatus[i][j] = null;
            }
        }
    }

    public short clickDot(Location loc) {
        // Make Chess
        this.ChessStatus[loc.getX()][loc.getY()] = ((BWChessFactory) factory).makeChess(this.nowPlayer, loc);

        // Generate Record
        this.gameRecord.addRecord(this.ChessStatus[loc.getX()][loc.getY()]);
        // DEBUG
        System.out.println(this.gameRecord.lastRecord());

        // Check Finish
        return this.checkFinish();
    }

    public short checkFinish() {
        return this.rule.check((BWChess[][]) ChessStatus);
    }

    public short checkToLose() {
        return this.rule.toLose(nowPlayer);
    }

    public void changePlayer() {
        if (this.nowPlayer == Const.BLACK_CHESS) {
            this.nowPlayer = Const.WHITE_CHESS;
        } else {
            this.nowPlayer = Const.BLACK_CHESS;
        }
    }

    public short getNowPlayer() {
        return nowPlayer;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}