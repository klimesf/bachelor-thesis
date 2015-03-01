package cz.filipklimes.bachelor.inspection.model;

import cz.filipklimes.bachelor.annotation.UiOrder;
import cz.filipklimes.bachelor.annotation.UiPassword;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author klimesf
 */
@Entity
@Table(name = "cc_user")
public class User {

    @NotNull
    private String username;

    @UiOrder(33)
    private String password;

    private boolean confirmed = false;

    @UiOrder(1)
    @NotNull
    public String getUsername() {
        return username;
    }

    @UiOrder(2)
    @NotNull
    @UiPassword
    public String getPassword() {
        return password;
    }

    @NotNull
    public boolean isConfirmed() {
        return confirmed;
    }
}
