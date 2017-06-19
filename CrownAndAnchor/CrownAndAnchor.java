public class CrownAndAnchor {
    public static void main(String[] args) {
        boolean[] board = new boolean[6];
        int dice1, dice2, dice3;
        int winner; // 0 for house, 1 for player, 2 for neither
        int playerWins = 0;
        int houseWins = 0;
        int dollarsBet;
        int totalDollarsBet = 0;
        int playerDollarsWon;
        int moneyWonByHouse = 0;
        for (int i = 0; i < 10000000; i++) {
            dollarsBet = 0;
            playerDollarsWon = 0;
            for(int j = 0; j < board.length; j++) {
                board[j] = Math.random() < 0.5;
            }
            for(int j = 0; j < board.length; j++) {
                if (board[j]) {
                    dollarsBet++;
                }
            }
            totalDollarsBet += dollarsBet;
            dice1 = (int)(Math.random() * 6);
            dice2 = (int)(Math.random() * 6);
            dice3 = (int)(Math.random() * 6);
            if (board[dice1]) {
                playerDollarsWon += 2;
            }
            if (board[dice2]) {
                if(dice1 == dice2) {
                    playerDollarsWon += 1;
                } else {
                    playerDollarsWon += 2;
                }
            }
            if (board[dice3]) {
                if(dice3 == dice2 || dice3 == dice1) {
                    playerDollarsWon += 1;
                } else {
                    playerDollarsWon += 2;
                }
            }

            if (playerDollarsWon < dollarsBet) {
                houseWins++;
                moneyWonByHouse += (dollarsBet - playerDollarsWon);
            } else if (playerDollarsWon > dollarsBet) {
                playerWins++;
                moneyWonByHouse -= (playerDollarsWon -= dollarsBet);
            }
        }

        System.out.println("Rounds played: " + 10000001);
        System.out.println("House Wins: " + houseWins);
        System.out.println("Player Wins: " + playerWins);
        System.out.println("totalDollarsBet: " + totalDollarsBet);
        System.out.println("moneyWonByHouse: " + moneyWonByHouse);
        System.out.println("Percentage of total money bet won by the house: " + ((double)moneyWonByHouse/(double)totalDollarsBet) * 100 + "%");
        // System.out.println("House wins " + ((double) houseWins/10000001) * 100 + " percent of the time");

    }
}
