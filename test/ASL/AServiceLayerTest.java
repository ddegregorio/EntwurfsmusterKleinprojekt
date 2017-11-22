package ASL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AServiceLayerTest {
    AServiceLayer asl;

    @BeforeEach
    void setUp() {
        // TODO create test admin user that is allowed to use the ASL

        asl =  asl.getASLInstance("Sabine", "testitest");
    }

    @Test
    void storeNewArticleInDB() {
        if(asl != null){
            asl.storeNewArticleInDB(5000, "articleTest", 5.8, false);
        }
        else{
            assertFalse(true);
        }
    }

    @Test
    void deleteArticleInDB() {
    }

    @Test
    void printAllArticlesFromDB() {
    }

    @Test
    void setDiscountOnArticle() {

    }

}