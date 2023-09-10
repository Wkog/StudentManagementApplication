package wkog.model;

import wkog.entities.UserAccount;

public interface IUserAccountDAO {
    public String checkUserLogin(UserAccount userAccount);
}
