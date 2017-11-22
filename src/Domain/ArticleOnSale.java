package Domain;

import java.util.ArrayList;
import java.util.List;

public abstract class ArticleOnSale {
    private List<User> users = new ArrayList<>();

    /**
     * registers users who are interested
     * when this article is on sale
     * @param u
     */
    public void register(User u){
        users.add(u);
    }

    /**
     * unregisters users from the list of users who get
     * notified when the article is on sale
     * @param u
     */
    public void unregister(User u){
        users.remove(u);
    }

    /**
     * notifies all the users who are interested
     * when this article is on sale.
     * @param a article that is on sale
     */
    public void notifyUser(Article a){
        for(User u : users){
            u.notify(a);
        }
    }
}
