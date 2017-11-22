package ASL;

import Domain.Article;
import Domain.ArticleOnSale;

import java.util.List;


public interface ArticleDAO  {

    void createNewArticle(Article a);

    void deleteArticle(Article a);

    void updateArticle(Article a);

    List<Article> readAllArticles();

    Article getArticleById(int a_id);
}
