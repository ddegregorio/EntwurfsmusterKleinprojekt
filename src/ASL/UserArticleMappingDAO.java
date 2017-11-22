package ASL;

import Domain.User;
import Domain.UserArticleMapping;

import java.util.List;

public interface UserArticleMappingDAO {

    void createNewUserArticleMapping(UserArticleMapping ua);

    List<UserArticleMapping> readAllUserArticleMappings();

    void deleteUserArticleMapping(int u_id, int a_id);
}
