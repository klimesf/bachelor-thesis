package cz.filipklimes.bachelor.model;

import cz.filipklimes.bachelor.model.annotations.Entity;
import cz.filipklimes.bachelor.model.annotations.Table;
import cz.filipklimes.bachelor.model.annotations.UiOrder;
import cz.filipklimes.bachelor.model.annotations.UiPassword;
import cz.filipklimes.bachelor.model.annotations.NotNull;

/**
 * @author Filip Klimes <filip@filipklimes.cz>
 */
@Entity
@Table(name = "cc_user")
public class User
{

    @NotNull
    private String username;

    @UiOrder(33)
    private String password;

    private boolean confirmed = false;

    @UiOrder(1)
    @NotNull
    public String getUsername()
    {
        return username;
    }

    @UiOrder(2)
    @NotNull
    @UiPassword
    public String getPassword()
    {
        return password;
    }

    @NotNull
    public boolean isConfirmed()
    {
        return confirmed;
    }
}
