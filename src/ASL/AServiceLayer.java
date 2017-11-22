package ASL;

import Domain.Article;
import Domain.User;
import Domain.UserArticleMapping;
import Infrastructure.ArticleDAOImpl;
import Infrastructure.UserArticleMappingImpl;
import Infrastructure.UserDAOImpl;
import java.util.List;

public class AServiceLayer {
    private static ArticleDAO articleDAO;
    private static UserDAO userDAO;
    private static UserArticleMappingDAO uaDAO;

    private static User userUsingSystem;

    private AServiceLayer() {

    }

    public static AServiceLayer getASLInstance(String username, String password){
        articleDAO = new ArticleDAOImpl();
        userDAO = new UserDAOImpl();
        uaDAO = new UserArticleMappingImpl();

        if (checkIfUserIsAdmin(username, password))
        {
            User userWantingToUseSystem = new User(username, password, true);

            // Get the reference of the user who wants to use
            // the ServiceLayer and set him as the user who
            // currently uses the service layer.
            for (User u: userDAO.readAllUsers())
            {
                if (u.equals(userWantingToUseSystem)){
                    userUsingSystem = u;
                }
            }

            loadUserToArticleMappingFromDB();

            return new AServiceLayer();
        }
        else{
            return null;
        }
    }

    /**
     * Check if the user is an admin.
     * @param username provided username
     * @param password provided password
     * @return boolean true if the provided credentials are valid
     */
    private static boolean checkIfUserIsAdmin(String username, String password)
    {
        boolean userIsAdmin = false;

        for(User u : userDAO.readAllUsers())
        {
            // Find the user with the provided username password combination
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
            {
                // Check if the user is an admin
                if(u.isAdmin()){
                    userIsAdmin = true;
                }
            }
        }

        return userIsAdmin;
    }

    /**
     * Loads the UserArticleMappings from the table userarticlemappings
     * and registers the user on those articles they are interested in.
     */
    private static void loadUserToArticleMappingFromDB(){
        User u;
        Article a;

        for (UserArticleMapping ua : uaDAO.readAllUserArticleMappings())
        {
            u = userDAO.getUserByID(ua.getU_id());
            if(u == null){
                System.out.println("Could not find the corresponding user from the Mapping: u_id="+ ua.getU_id());
                continue;
            }

            a = articleDAO.getArticleById(ua.getA_id());
            if(a == null){
                System.out.println("Could not find the corresponding article from the Mapping: a_id=" + ua.getA_id() );
                continue;
            }

            a.register(u);
        }
    }
/*
    public void storeNewArticleInDB(int uid, String articlename, double price, boolean discount){
        Article a = new Article(uid);
        a.setArticleName(articlename);
        a.setPrice(price);
        a.setDiscount(discount);

        articleDAO.createNewArticle(a);
    }

    public void deleteArticleInDB(int uid, String articlename, double price, boolean discount)
    {
        Article a = new Article(uid);
        a.setArticleName(articlename);
        a.setPrice(price);
        a.setDiscount(discount);

        articleDAO.deleteArticle(a);
    }*/

    /**
     * Prints all the articles from the Database.
     */
    public void printAllArticlesFromDB()
    {
        System.out.println("Nr.\t\tDescription\t\t\tPrice\t\tDiscount");
        for(Article a : articleDAO.readAllArticles())
        {
            System.out.println(a);
        }
    }

    /*
    public void subscribeToSalesOnArticle(int uid, String articlename, double price, boolean discount)
    {
        List<Article> articles = articleDAO.readAllArticles();

        Article keyArticle = new Article(uid);
        keyArticle.setArticleName(articlename);
        keyArticle.setPrice(price);
        keyArticle.setDiscount(discount);

        for(Article a : articles)
        {
            if(a.equals(keyArticle))
            {
                a.register(userUsingSystem);
            }
        }
    }*/

    /**
     * Sets the article on discount, but only if
     * it is not already set on discount.
     *
     * @param uid the uid of the article which should be set on
     *            discount.
     */
    public void setDiscountOnArticle(int uid)
    {
        for(Article a : articleDAO.readAllArticles())
        {
            if (a.getA_id() == uid)
            {
                if(a.isDiscount() == false){
                    a.setDiscount(true);
                    articleDAO.updateArticle(a);
                    System.out.println("Article with Articlenr "+ uid + " has been set on sale.");
                }
                else{
                    System.out.println("Article is already on sale!");
                }
            }
        }
    }
}
