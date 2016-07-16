package PTJ4.transcode.dao;

import PTJ4.transcode.module.User;

public interface UserDao extends BaseDao {
	User getByName(String name);

    User getByEmail(String email);
}
