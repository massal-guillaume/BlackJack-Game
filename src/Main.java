import com.massal.controller.GameControlleur;
import com.massal.view.View;

public class Main {
    public static void main(String[] args) {

        View view = new View();
        GameControlleur controlleur = new GameControlleur(view);
        controlleur.run();

    }
}