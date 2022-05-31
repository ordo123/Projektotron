package highscore;

import java.util.Comparator;

/**
 * 
 * Klassen j�mf�r highscore och placerar det 'b�sta' h�gst upp i highscore-filen.
 *
 */
public class Comparer implements Comparator<Score> {
        public int compare(Score score1, Score score2) {

            int sc1 = score1.getScore();
            int sc2 = score2.getScore();
          
            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return 1;
            }else{
                return 0;
            }
        }
}