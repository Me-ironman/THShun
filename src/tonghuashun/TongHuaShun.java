package tonghuashun;

import java.util.Arrays;
import java.util.Scanner;


public class TongHuaShun {

    public static void main(String[] args) {
        _main();
    }

    public static String _main(){
        int win = 0;
        System.out.print("请您输入双方手牌[示例:(Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH)]:\n");
        Scanner input = new Scanner(System.in);
        String player1 = input.next();
        String player1_card1 = input.next();
        String player1_card2 = input.next();
        String player1_card3 = input.next();
        String player1_card4 = input.next();
        String player1_card5 = input.next();

        Card[] player_no1 = new Card[]{
                new Card(player1_card1.charAt(1), player1_card1.charAt(0)),
                new Card(player1_card2.charAt(1), player1_card2.charAt(0)),
                new Card(player1_card3.charAt(1), player1_card3.charAt(0)),
                new Card(player1_card4.charAt(1), player1_card4.charAt(0)),
                new Card(player1_card5.charAt(1), player1_card5.charAt(0))
        };
        int[] player1_result = check_THS(player_no1);

        String player2 = input.next();
        String player2_card1 = input.next();
        String player2_card2 = input.next();
        String player2_card3 = input.next();
        String player2_card4 = input.next();
        String player2_card5 = input.next();

        Card[] player_no2 = new Card[]{
                new Card(player2_card1.charAt(1), player2_card1.charAt(0)),
                new Card(player2_card2.charAt(1), player2_card2.charAt(0)),
                new Card(player2_card3.charAt(1), player2_card3.charAt(0)),
                new Card(player2_card4.charAt(1), player2_card4.charAt(0)),
                new Card(player2_card5.charAt(1), player2_card5.charAt(0))
        };
        int[] player2_result = check_THS(player_no2);
        for (int k = 0; k < 6; k++) {
            if (player1_result[k] - player2_result[k] != 0) {
                win = player1_result[k] - player2_result[k];
                break;
            }
        }
        String output = win != 0 ? ((win > 0 ? player1.replace(":", " ") : player2.replace(":", " ")) + "wins") : "Tie";
        System.out.println(output);
        return output;
    }

    // 判断是否同花顺
    static int[] check_THS(Card[] cards) {
        /*
         * 判断同花顺==-1 / 同花==-2 /顺子==-3 / ...以此类推
         * */
        // 判断花色
        int[] intArray = new int[]{0, 0, 0, 0, 0, 0};
        char suit = cards[0].getSuit();
        int min = cards[0].getNum();
        intArray[1] = min;
        int max = cards[0].getNum();
        for (int i = 1; i < cards.length; i++) {
            // 有花色异常则返回false
            if ((!(suit == cards[i].getSuit())) && intArray[0] == 0)
                intArray[0] = -3;
            int num = cards[i].getNum();
            intArray[i + 1] = num;
            if (num > max)
                max = num;
            else if (num < min)
                min = num;
        }
        if (max - min == 4 && intArray[0] == 0) {
            intArray[0] = -1;
            intArray[5] = max;
        } else {
            if (intArray[0] == -3) {
                Arrays.sort(intArray);
                if (!(intArray[1] + 4 == intArray[2] + 3 && intArray[2] + 3 == intArray[3] + 2 && intArray[3] + 2 == intArray[4] + 1 && intArray[4] + 1 == intArray[5])) {
                    intArray[0] = 0;
                }
            } else {
                intArray[0] = -2;
                Arrays.sort(intArray);
            }
        }
        if (intArray[0] == 0) {
            intArray = check_DZ(intArray);
        } else {
            for (int r = 1; r < (intArray.length - 1) / 2.0; r++) {
                intArray[r] = intArray[r] + intArray[6 - r];
                intArray[6 - r] = intArray[r] - intArray[6 - r];
                intArray[r] = intArray[r] - intArray[6 - r];
            }
        }
        return intArray;
    }

    static int[] check_DZ(int[] intArray) {
        int times = 1, num = intArray[1], _i = 0;
        int[] _array = new int[]{0, 0, 0, 0, 0};
        for (int i = 2; i < 6; i++) {
            if (intArray[i] == num && times < 3) {
                times += 1;
            } else {
                if (times >= 3) {
                    _i = 3;
                    _array[0] = 100 * times + num;
                    break;
                } else {
                    if (times == 2)
                        _i += 1;
                    for (int j = 0; j < 5; j++) {
                        if (_array[j] == 0) {
                            _array[j] = 100 * times + num;
                            break;
                        }
                    }
                }
                times = 1;
                num = intArray[i];
            }
        }
        if (_i != 3) {
            if (times >= 3) {
                _array[0] = 100 * times + num;
                _i = 3;
            } else {
                if (times == 2)
                    _i += 1;
                for (int j = 0; j < 5; j++) {
                    if (_array[j] == 0) {
                        _array[j] = 100 * times + num;
                        break;
                    }
                }
            }
        }
        Arrays.sort(_array);
        if (_i == 3) {
            intArray[0] = -4;
        }
        if (_i == 2) {
            intArray[0] = -5;
        }
        if (_i == 1) {
            intArray[0] = -6;
        }
        if (_i == 0) {
            intArray[0] = -7;
        }
        for (int t = 4; t >= 0; t--) {
            intArray[5 - t] = _array[t];
        }
        return intArray;
    }

    // 扑克牌类
    static class Card {
        char suit;// 花色
        int num;// 大小

        public char getSuit() {
            return suit;
        }

        public void setSuit(char suit) {
            this.suit = suit;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Card(char suit, char num) {
            super();
            this.suit = suit;
            //1按照14理解
            if (num <= '9') {
                this.num = num - '0';
            }
            if (num == 'T')
                this.num = 10;
            if (num == 'J')
                this.num = 11;
            if (num == 'Q')
                this.num = 12;
            if (num == 'K')
                this.num = 13;
            if (num == 'A')
                this.num = 14;

        }
    }
}
