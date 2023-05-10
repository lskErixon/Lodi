import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        boolean helper = true;
        while (helper) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the number of ships you want to shoot down");
                int inputUser = sc.nextInt();
                ShipBattleGame game = new ShipBattleGame(inputUser);
                game.play();
                helper = false;
            } catch(IOException e){
                System.out.println("textures load error");
            } catch (Exception e) {
                helper = true;
                System.out.println("Invalid number, enter again!");
            }
        }
    }
}
