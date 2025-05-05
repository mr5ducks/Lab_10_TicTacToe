import java.util.Scanner;

public class Main {
    //don't do the psvm short cut after writing everything
//don't forget to check the methods some of them aren't working with the safe input
//also
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    public static void main (String[]args){
        Scanner in = new Scanner(System.in);
        boolean again;

//a few of the do loops aren't stopping their loops mabye add break
//
        do {
            clearBoard();
            String currentPlayer = "X";
            int moveCount = 0;
            boolean gameWon = false;
//this while loop needs a go over probably needs a ture or false
            while (!gameWon && moveCount < ROWS * COLS) {
                display();
                int row, col;
                do { //the get ranged isn't working
                row = SafeInput.getRangedInt(in, currentPlayer + "'s turn - Enter row 1-3= ", 1, 3) - 1; //note to self don't forget ;
                col = SafeInput.getRangedInt(in, "Enter column 1-3= ", 1, 3) - 1;
                } while (!isValidMove(row, col));
                board[row][col] = currentPlayer;
                moveCount++;
// ITS ++ NOT +
                if (moveCount >= 5 && isWin(currentPlayer)) {
                    display();
                    System.out.println(currentPlayer + " wins");
                    gameWon = true;
                } else if (moveCount == ROWS * COLS || isTie()) {
                    display();
                    System.out.println("tie");
                    break; //keep this won't work without
                } else {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                }
            }
            again = SafeInput.getYNConfirm(in, "Play again? Y/N= ");
        } while (again);

        System.out.println("Thanks for playing!");
    }
    //old coments gone
    //go through each one some aren't working
    //also is win is acting strange
    //and vaild move isn't working
private static void clearBoard() {
    for (int r = 0; r < ROWS; r++) {
    for (int c = 0; c < COLS; c++) {
         board[r][c] = " ";
            }
        }
    }

private static void display() {
    System.out.println("Current board:");
    for (int r = 0; r < ROWS; r++) {
        System.out.print("|");
    for (int c = 0; c < COLS; c++) {
        System.out.print(board[r][c] + "|");
        }
        System.out.println();
        }
    }

private static boolean isValidMove(int row, int col) {
    return board[row][col].equals(" ");
    }

private static boolean isWin(String player) {
    return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

private static boolean isRowWin(String player) {
    for (int r = 0; r < ROWS; r++) {
    if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player))
        return true;
    }
    return false;
    }

private static boolean isColWin(String player) {
    for (int c = 0; c < COLS; c++) {
    if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player))
        return true;
    }
    return false;
    }

private static boolean isDiagnalWin(String player) {
    return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
       (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

private static boolean isTie() {
    for (int r = 0; r < ROWS; r++) {
    for (int c = 0; c < COLS; c++) {
    if (board[r][c].equals(" ")) return false;
    }
    }
    return true;
    }
}