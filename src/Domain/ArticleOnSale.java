package Domain;

import java.util.ArrayList;
import java.util.List;

public abstract class ArticleOnSale {
    private List<Observer> observers = new ArrayList<>();

    /**
     * registers users who are interested
     * when this article is on sale
     * @param o
     */
    public void register(Observer o){
        observers.add(o);
    }

    /**
     * unregisters users from the list of users who get
     * notified when the article is on sale
     * @param o
     */
    public void unregister(Observer o){
        observers.remove(o);
    }

    /**
     * notifies all the users who are interested
     * when this article is on sale.
     * @param a article that is on sale
     */
    public void notifyUser(Article a){
        for(Observer o : observers){
            o.notify(a);
        }
    }
}
